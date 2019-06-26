package com.project.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;
import com.project.service.ICoachService;
import com.project.service.IGymService;
import com.project.service.IStudentService;

/**
 * 安全数据源
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	private IStudentService studentService;
	@Autowired
	private ICoachService coachService;
	@Autowired
	private IGymService gymService;
	
	/**
	 * 学员权限设置
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}

	/**
	 * 学员身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取拼接后的身份字符串
		String tempPrincipal = (String)token.getPrincipal();
		//截取前缀
		String prefix = tempPrincipal.substring(0,1);
		//截取实际身份字符串
		String principal = tempPrincipal.substring(1);
		//学员身份验证
		if("s".equals(prefix)) {
			StudentBean student = studentService.login(principal);
			//未查找到学员，则不再进行验证，返回验证失败
			if(student == null) {
				return null;
			}
			//获取到学员的身份信息，若手机号不为空，则使用手机号进行验证，否则使用用户名进行验证，用户名也为空，则返回验证失败
			String username = student.getS_phone() != null ? student.getS_phone() 
					: (student.getS_name() != null ? student.getS_name() : null) ;
			//手机号用户名均为空，返回验证失败
			if(username == null) {
				return null;
			}
			return new SimpleAuthenticationInfo(student, student.getS_password(),this.getName());
		}
		
		//教练身份验证
		if("c".equals(prefix)) {
			CoachBean coach = coachService.login(principal);
			//未查找到教练，则不再进行验证，返回验证失败
			if(coach == null) {
				return null;
			}
			//获取到教练的身份信息，若手机号不为空，则使用手机号进行验证，否则使用用户名进行验证，用户名也为空，则返回验证失败
			String username = coach.getC_phone() != null ? coach.getC_phone() 
					: (coach.getC_name() != null ? coach.getC_name() : null) ;
			//手机用户名均为空，返回验证失败
			if(username == null) {
				return null;
			}
			ByteSource by = ByteSource.Util.bytes(coach.getC_id());
			return new SimpleAuthenticationInfo(coach, coach.getC_password(),by,this.getName());
		}
		
		//场馆身份验证
		if("g".equals(prefix)) {
			GymBean gym = gymService.login(principal);
			//未查找到场馆，则不再进行验证，返回验证失败
			if(gym == null) {
				return null;
			}
			//获取到场馆的身份信息，若手机号不为空，则使用手机号进行验证，
			//否则使用邮箱验证，邮箱为空使用用户名进行验证，用户名也为空，则返回验证失败
			String username = gym.getG_phone() != null ? gym.getG_phone() 
					: (gym.getG_email() != null ? gym.getG_email() 
					: (gym.getG_name() != null ? gym.getG_name() : null));
			//手机用户名均为空，返回验证失败
			if(username == null) {
				return null;
			}
			ByteSource by = ByteSource.Util.bytes(gym.getG_id());
			return new SimpleAuthenticationInfo(gym, gym.getG_password(),by,this.getName());
		}
		return null;
	}

}
