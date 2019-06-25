package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.BankCardBean;
import com.project.dao.IBankCardDao;
import com.project.service.IBankCardService;

@Service
public class BankCardServcieImpl implements IBankCardService {

	@Autowired
	private IBankCardDao bankCardDao;
	
	@Override
	public Integer updateBankCard(Integer id, double money) {
		return bankCardDao.updateBankCard(id, money);
	}

	@Override
	public Integer insertBankCard(BankCardBean bankCard) {
		return bankCardDao.insertBankCard(bankCard);
	}

	@Override
	public Integer removeBankCard(Integer id) {
		return bankCardDao.removeBankCard(id);
	}

	@Override
	public List<BankCardBean> listBankCard(String userId) {
		return bankCardDao.listBankCard(userId);
	}

}
