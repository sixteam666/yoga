package com.project.service;

import java.util.List;

import com.project.bean.BankCardBean;

public interface IBankCardService {

	Integer updateBankCard(Integer id, double money);
	
	Integer insertBankCard(BankCardBean bankCard);
	
	Integer removeBankCard(Integer id);
	
	List<String> listBankCard(String userId);
}
