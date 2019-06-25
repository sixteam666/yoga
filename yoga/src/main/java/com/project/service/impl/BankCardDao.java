package com.project.service.impl;

import java.util.List;

public interface BankCardDao {

	/**
	 * 教练提现更新银行卡
	 * @param cardId
	 * @param money
	 * @return
	 */
	Integer updateBankCard(Integer cardId, double money);
	
	
	Integer insertBankCard();
	
	
	Integer removeBankCard(Integer id);
	
	
	List<String> listBankCard(String userId);

}
