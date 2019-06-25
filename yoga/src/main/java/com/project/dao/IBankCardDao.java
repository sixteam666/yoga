package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.BankCardBean;

public interface IBankCardDao {

	/**
	 * 教练提现更新银行卡
	 * @param cardId
	 * @param money
	 * @return
	 */
	@Update("update t_bankcard set b_balance = b_balance + money where id = #{id}")
	Integer updateBankCard(@Param("id") Integer id, @Param("money") double money);
	
	
	/**
	 * 添加银行卡
	 * @param bankCard
	 * @return
	 */
	@Insert("insert into t_bankcard(b_carid,b_bank,b_userId) "
			+ "values(#{b_carid},#{b_bank},#{b_userId})")
	Integer insertBankCard(BankCardBean bankCard);
	
	
	/**
	 * 删除银行卡
	 * @param id
	 * @return
	 */
	@Delete("delete from t_bankcard where id = #{id}")
	Integer removeBankCard(Integer id);
	
	/**
	 * 查询当前用户所有的银行卡
	 * @param userId
	 * @return
	 */
	@Select("select * from t_bankcard wehre b_userId = #{userId}")
	List<String> listBankCard(String userId);

}
