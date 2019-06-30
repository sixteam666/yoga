package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.bean.BankCardBean;
import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictureBean;
import com.project.service.IBankCardService;
import com.project.service.ICoachService;
import com.project.service.IGymService;
import com.project.util.FileUtil;
import com.project.util.UploadPathConstant;

/**
 * 会馆控制层类
 * 
 * @author YuChen
 *
 */
@Controller
@RequestMapping("/gym")
public class GymController {
	@Autowired
	@Qualifier("gymService")
	private IGymService gymService;
	@Autowired
	private ICoachService coachService;
	@Autowired
	private IBankCardService bankCardService;
	private FileUtil picUtil;
	
	/**
	 * 获取Session中的数据
	 * 
	 * @return
	 */
	@RequestMapping("/getGymToSession.do")
	@ResponseBody
	public GymBean getGymToSession() {
		// System.out.println("正在获取Session");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession(false);
		if (session == null) {
			System.out.println("Session:null");
			return null;
		}
		Object object = session.getAttribute("gym");
		if (object == null) {
			System.out.println("gym:null");
			return null;
		}
		GymBean gymBean = (GymBean) object;
		// System.out.println(gymBean);
		return gymBean;
	}
	
	/**
	 * 登录
	 * 
	 * @param arg 邮箱或电话号
	 * @param pwd 登录密码
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public int login(String arg, String g_password) {
		System.out.println("name" + arg +"=="+ g_password);
		// Object obj = new SimpleHash("MD5", g_password,"42067398-8e42-4de4-a25d-14645a65b338",1024);
		// 产生一个用户（门面对象）
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("g" + arg, g_password);
			try {
				currentUser.login(token); // 调用login进行认证
				Session session = currentUser.getSession(true);
				GymBean gym = gymService.login(arg);
				session.setAttribute("gym", gym); // 将gym用户放在Session中
				session.setAttribute("id", gym.getG_id()); // 将gym用户id放在Session中
				System.out.println("认证成功");
				return 1; // 登录成功
			}
			// 父异常。认证失败异常
			catch (AuthenticationException ae) {
				// unexpected condition? error?
				System.out.println("用户名或密码错误");
				return 0; // 用户名或密码错误
			}
		}
		return 1; // 如果已经登录过
	}
	
	/**
	 * 登录或注册前验证用户是否存在
	 * @param arg 邮箱或电话号
	 * @return 用户存在返回true，用户不存在返回false
	 */
	@RequestMapping("/findGym.do")
	@ResponseBody
	public Boolean loginTest(String arg) {
		if(gymService.login(arg) == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 注册
	 * 
	 * @param regName 注册的登录名（邮箱或电话号）
	 * @param g_password 注册的密码
	 * @return 注册状态 1：注册成功，0：注册失败 -1：登录名格式错误 -2:用户名已存在
	 */
	@RequestMapping("/reg.do")
	@ResponseBody
	public int register(String regName, String g_password) {
		String emailTest = "^[0-9a-z]+\\w*@([0-9a-z]+\\.)+[0-9a-z]+$"; // 邮箱正则表达式
		String phoneTest = "^1[3|4|5|7|8][0-9]\\\\d{4,8}$"; // 电话正则表达式
		if(gymService.login(regName) != null) {
			return -2; // 邮箱或电话已注册
		}
		String gymUUID = UUID.randomUUID().toString();
		GymBean gym = new GymBean();
		gym.setG_id(gymUUID);
		gym.setG_password(g_password);
		
		if(regName.contains("@")) {
			gym.setG_email(regName);
		}else if(regName.length() == 11) {
			gym.setG_phone(regName);
		}else {
			return -1; // 格式不符合要求
		}
		Object obj = new SimpleHash("MD5", gym.getG_password(),gymUUID,1024);
		gym.setG_password(obj.toString());
		
		int result = gymService.register(gym);
		if (result!=0) {
			int number = this.addPictures(gymUUID);
			System.out.println(number+"picture");
		}
		return result;
	}
	
	/**
	 * 退出登录（注销）
	 */
	@RequestMapping("/logout.do")
	public String logout() {
		System.out.println("正在注销");
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:/html/gym/gymLogin.html";
	}
	
	/**
	 * 更新(修改)密码
	 * 
	 * @param g_id        场馆id
	 * @param newPassword 新密码
	 * @return 影响行数
	 */
	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public int updatePassword(String g_id, String newPassword) {
		int num = gymService.updatePassword(g_id, newPassword);
		return num;
	}
	

	/**
	 * 查找所有场馆
	 * 
	 * @return
	 */
	@RequestMapping("/findAllGym.do")
	@ResponseBody
	public List<GymBean> findAllGym() {
		List<GymBean> list = gymService.findAllGym();
		return list;
	}

	/**
	 * 信息完善
	 * 
	 * @param gymBean
	 */
	@RequestMapping(value="/updateMsg.do",method = RequestMethod.POST)
	//@ResponseBody
	public String updateMessage(Model model,ModelMap map,@Validated GymBean gymBean,
			MultipartFile file,HttpServletRequest req,BindingResult result) {
		model.addAttribute("gymBean", gymBean);
		System.out.println(file);
		if (result.hasErrors()) {
			System.out.println("有错！！！");
			//获取到所有的校验错误信息
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError fieldError : errorList) {
				map.put("error_"+fieldError.getField(), fieldError.getDefaultMessage());
			}
			return "forward:/html/gym/msgModify.html";
		}
		
		String gymId = this.getGymToSession().getG_id();
		gymBean.setG_id(gymId);
		//gymBean.setG_id("1");
		GymBean gymBean2 = gymService.findGymById(gymId);
		//String imgName = this.getGymToSession().getG_headimg();
		String imgName = gymBean2.getG_headimg();
		System.out.println(imgName);
		if (file.getOriginalFilename()!=null && file.getOriginalFilename()!="") {
			
			imgName = FileUtil.getFileName(file, req, UploadPathConstant.HEADIMG);
		}
		gymBean.setG_headimg(imgName);
		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession(false);
		session.setAttribute("gym", gymBean);
		System.out.println(gymBean);
		int number = gymService.updateMessage(gymBean);
		System.out.println(number);
		return "redirect:/gym/showMessage.do";
	}
	
	/**
	 * 跳转到修改信息页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/showOldMsg.do")
	public String showOldMsg(ModelMap map){
		String gymId = this.getGymToSession().getG_id();
		//String gymId = "1";
		GymBean oldMsg =  gymService.findGymById(gymId);
		System.out.println(oldMsg);
		map.put("oldMsg", oldMsg);
		return "/html/gym/msgModify.html";
	}
	
	/**
	 * 跳转到展示信息页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/showMessage.do")
	public String showMessage(ModelMap map){
		String gymId = this.getGymToSession().getG_id();
		//String gymId = "1";
		GymBean gymBean =  gymService.findGymById(gymId);
		System.out.println(gymBean);
		map.put("gymBean", gymBean);
		return "/html/gym/msgShow.html";
	}

	
	/**
	 * 一次添加多张图片
	 * 
	 * @param lists
	 */
	@RequestMapping("/addPictures.do")
	@ResponseBody
	public Integer addPictures(String gymId) {
		
		List<PictureBean> list = new ArrayList<PictureBean>();
		for(int i = 1;i<=12;i++){  
			PictureBean picBean = new PictureBean();
			picBean.setP_g_id(gymId);
			picBean.setP_type(1);
			String imgName = i+".jpg";
			picBean.setP_imgname(imgName);
			list.add(picBean);
	    }  
		int number = gymService.addPictrue(list);
		return number;
	}
	
	/**
	 * 显示图片
	 * 
	 * @param lists
	 */
	@RequestMapping("/showPictures.do")
	@ResponseBody
	public List<PictureBean> showPictures(int p_type) {
		
		String gymId = this.getGymToSession().getG_id();
		System.out.println(gymId);
		//String gymId = "1";
		List<PictureBean> list = gymService.findAllPic(gymId, p_type);
		for (PictureBean pictureBean : list) {
			System.out.println(pictureBean);
		}
		return list;
	}
	
	/**
	 * 修改图片
	 * 
	 * @param lists
	 */
	@RequestMapping(value="/updatePictures.do",method = RequestMethod.POST)
	//@ResponseBody
	public String updatePictures(Integer[] p_id,MultipartFile[] file,HttpServletRequest req) {
		PictureBean pictureBean = new PictureBean();
		if(file!=null && file.length>0){  
			//循环获取file数组中得文件  
			for(int i = 0;i<file.length;i++){
				if (file[i].getOriginalFilename()!=null && file[i].getOriginalFilename()!="") {
					//MultipartFile file1 = file[i];  
					//文件名
					String imgName = picUtil.getFileName(file[i], req, UploadPathConstant.GYMIMG);
					//将图片名字保存数据库
					pictureBean.setP_id(p_id[i]);
					pictureBean.setP_imgname(imgName);
					int number = gymService.updatePicture(pictureBean);
				}
			   
		    }  
		}
		return "/html/gym/index.html";
	}
	
	/**
	 * 展示课程
	 */
	@RequestMapping("/showLesson.do")
	//@ResponseBody
	public String showLesson(LessonBean lessonBean,ModelMap map){
		System.out.println(lessonBean);
		String gymId = this.getGymToSession().getG_id();
		lessonBean.setL_g_id(gymId); 
		System.out.println(lessonBean);
		List<LessonBean> list = gymService.findLesson(lessonBean);
		if (list.isEmpty()) {
			list = null;
			map.put("lessonBean", lessonBean);
		}
		map.put("list", list);
		
		return "/html/gym/lessonModify.html";
	}
	

	/**
	 * 教练课程安排,添加课程
	 */
	@RequestMapping("/addLesson.do")
	//@ResponseBody
	public String addLesson(Model model,ModelMap map,@Validated LessonBean lessonBean,
			BindingResult result) {
		
		model.addAttribute("lessonBean", lessonBean);
		if (result.hasErrors()) {
			System.out.println("有错！！！");
			//获取到所有的校验错误信息
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError fieldError : errorList) {
				map.put("error_"+fieldError.getField(), fieldError.getDefaultMessage());
			}
			return "forward:/html/gym/lessonModify.html";
		}
		int number = gymService.addLesson(lessonBean);
		//return "forward:/html/gym/lessonModify.html";
		return "redirect:/gym/showLesson.do?l_weekday="+lessonBean.getL_weekday()+"&l_datetime="+lessonBean.getL_datetime();
	}
	
	/**
	 * 删除课程
	 */
	@RequestMapping("/deleteLesson.do")
	@ResponseBody
	public int deleteLesson(int l_id){
		int number = gymService.deleteLesson(l_id);
		return number;
	}
	
	/**
	 * 查看我的签约教练
	 * 
	 * @param g_id
	 * @return
	 */
	@RequestMapping("/findMyCoach.do")
	@ResponseBody
	public List<CoachBean> findMyCoach() {
		String gymId = this.getGymToSession().getG_id();
		List<CoachBean> list = gymService.findMyCoach(gymId);
		return list;
	}
	
	/**
	 * 通过电话号码或者昵称查找我的签约教练
	 * 
	 * @param g_id
	 * @param map
	 */
	@RequestMapping("/findCoaByNameOrPho.do")
	@ResponseBody
	public List<CoachBean> findCoaByNameOrPho(String g_id,String nameOrPho) {
		
		List<CoachBean> list = gymService.findCoaByNameOrPho(g_id, nameOrPho);
		return list;
	}

	/**
	 * 签约或解约教练
	 * 
	 * @param g_id
	 * @param c_id
	 * @return
	 */
	@RequestMapping("/updateCoach.do")
	@ResponseBody
	public int updateCoach(String g_id, String c_id) {
		if(!"0".equals(g_id)) {
			g_id = this.getGymToSession().getG_id();
		}
		int number = gymService.updateCoachBean(g_id, c_id);
		return number;
	}
	
	/**
	 * 提交签约教练的申请
	 * 
	 * @param g_id 提交申请的场馆id
	 * @param c_id 被申请的教练id
	 * @return 返回值   0:请求失败 1:请求成功 2：重复请求 3：教练已向你发送请求;
	 */
	@RequestMapping("/submitSigingApplication.do")
	@ResponseBody
	public int submitSigingApplication(String g_id, String c_id) {
		g_id = this.getGymToSession().getG_id();
		return gymService.submitSigingApplication(g_id, c_id);
	}
	

	/**
	 * 同意或拒绝教练的签约申请
	 * 
	 * @param g_id 被申请的场馆id
	 * @param c_id 提交申请的教练id
	 * @param state 1:同意，2：拒绝
	 * @return 影响行数
	 */
	@RequestMapping("/agreeSigingApplication.do")
	@ResponseBody
	public int agreeSigingApplication(String g_id, String c_id,Integer state) {
		g_id = this.getGymToSession().getG_id();
		System.out.println("测试：" + g_id);
		return gymService.agreeSigingApplication(g_id, c_id, state);
	}
	
	/**
	 * 通过场馆id查询响应签约的教练（教练向场馆发请求）
	 * 
	 * @param g_id
	 * @return 教练的对象集合 c_g_id为签约状态
	 */
	@RequestMapping("/findCoachByMyResponse.do")
	@ResponseBody
	public List<CoachBean> findCoachByMyResponse(String g_id) {
		g_id = this.getGymToSession().getG_id();
		List<CoachBean> coachList = gymService.findCoachByMyResponse(g_id);
		System.out.println("测试：" + coachList);
		return coachList;
	}
	
	/**
	 * 通过场馆id查询请求签约的教练（场馆向教练发请求）
	 * 
	 * @param g_id
	 * @return
	 */
	@RequestMapping("/findCoachByMyRequest.do")
	@ResponseBody
	public List<CoachBean> findCoachByMyRequest(String g_id) {
		g_id = this.getGymToSession().getG_id();
		return gymService.findCoachByMyRequest(g_id);
	}
	
	/**
	 * 通过教练id查找教练
	 * 
	 * @param c_id
	 * @param type 0：显示未签约教练， 1：显示已签约教练 2.处理签约通知
	 * @param map
	 * @return 教练个人详情页
	 */
	@RequestMapping("/findCoachById.do")
	public String findCoachById(String c_id,Integer type, ModelMap map) {
		System.out.println("测试：" + type + "，" + c_id);
		CoachBean coach = coachService.getCoachById(c_id);
		map.put("coach", coach);
		map.put("type", type);
		return "/html/gym/CoachMessage.html";
	}
	
	/**
	 * 查找所有教练
	 * 
	 * @return 教练对象集合
	 */
	@RequestMapping("/findAllCoach.do")
	@ResponseBody
	public List<CoachBean> findAllCoach(){
		List<CoachBean> coachList = gymService.findAllCoach();
		return coachList;
	}
	
	/**
	 * 查找所有教练
	 * 
	 * @return 教练对象集合
	 */
	@RequestMapping("/findGymById.do")
	@ResponseBody
	public GymBean findGymById(){
		String gymId = this.getGymToSession().getG_id();
		GymBean gymBean = gymService.findGymById(gymId);
		return gymBean;
	}
	
	/**
	 * 查看钱包余额
	 * @author pan
	 * @param coach 要更新的数据
	 * @return 返回个人信息显示页面
	 */
	@RequestMapping("showMoney.do")
	public String updateLessonInfo(ModelMap map) {
		String gymId = this.getGymToSession().getG_id();
		GymBean gymBean = gymService.findGymById(gymId);
		Double money = gymBean.getG_money();
		List<BankCardBean> cardList = bankCardService.listBankCard(gymId);
		map.put("cardList", cardList);
		map.put("money", money);
		return "/html/gym/money.html";
	}
}
