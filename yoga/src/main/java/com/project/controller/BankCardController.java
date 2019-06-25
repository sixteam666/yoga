package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bean.BankCardBean;
import com.project.service.IBankCardService;

@Controller
@RequestMapping("card")
public class BankCardController {

	@Autowired
	private IBankCardService bandCardService;
	
	@RequestMapping("removeCard.do")
	@ResponseBody
	public void removeCard(Integer id) {
		bandCardService.removeBankCard(id);
	}
	
	@RequestMapping("addCard.do")
	@ResponseBody
	public void addCard(BankCardBean bankCard) {
		//获取到用户session
		//bankCard.setUserId(b_userId);
		bandCardService.insertBankCard(bankCard);
	}
	
}
