package com.project.controller;

import org.apache.shiro.SecurityUtils;
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
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		bankCard.setUserId(id);
		bandCardService.insertBankCard(bankCard);
	}
	
}
