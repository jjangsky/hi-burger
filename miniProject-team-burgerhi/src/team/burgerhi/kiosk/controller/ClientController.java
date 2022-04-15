package team.burgerhi.kiosk.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.OrderDTO;
import team.burgerhi.kiosk.model.dto.OrderMenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;
import team.burgerhi.kiosk.model.service.ClientService;
import team.burgerhi.kiosk.views.OrderResultSet;

public class ClientController {
	private ClientService clientService = new ClientService();
	private OrderResultSet orderResultSet = new OrderResultSet();
	String code = "";
	
	public UserDTO loginResult(String id, String pwd) {
		
		UserDTO userDTO = clientService.loginResult(id, pwd);
		int gradeNo = userDTO.getGradeNo();
		String userName = userDTO.getName();
		
		if(userName != null) {
			System.out.println(">>>> " + userName + "님 환영합니다!");
			System.out.println();
		} else {
			System.out.println("회원정보가 일치하지 않습니다. 다시 입력해 주세요!");
			System.out.println("\n\n\n");
		}
		return userDTO;
	}

	public List<CategoryDTO> selectAllCategory() {
		
		List<CategoryDTO> categoryList = clientService.selectAllCategory();
		
		return categoryList;
	}

	public List<MenuDTO> selectMenuBy(int categoryNo) {
		
		List<MenuDTO> menuList = clientService.selectMenuBy(categoryNo);
				
		return menuList;
	}

	public void insertOrderMenu(int userNo, int inputMenuNo, int inputAmount) {
		
		int insertOrderMenu = clientService.insertOrderMenu(userNo, inputMenuNo, inputAmount);
		if(insertOrderMenu > 0) {
			code = "orderMenuSuccess";
		} else {
			code = "orderMenuFail";
		}
		orderResultSet.displayDmlResult(code);
		
	}

	public List<OrderMenuDTO> selectOrderMenu(int userNo) {
		
		List<OrderMenuDTO> orderMenuList = clientService.selectOrderMenu(userNo);
		
		return orderMenuList;
	}

	public void deleteOrderMenu(int deleteMenuCode) {
		
		int deleteResult = clientService.deleteOrderMenu(deleteMenuCode);
		
		if(deleteResult > 0) {
			code = "deleteOrderMenuSuccess";
		} else {
			code = "deleteOrderMenuFail";
		}
		
		orderResultSet.displayDmlResult(code);
	}

	public int selectGrade(int gradeNo) {
		/* 여기서 String -> int로 가공 처리 */
		int gradeDiscount = 0;
		String gradeResult = clientService.selectGrade(gradeNo);
		
		return gradeDiscount;
	}

	public List<CardDTO> selectCard() {
		List<CardDTO> cardList = clientService.selectCard();
		return cardList;
	}

	public void insertOrder(double lastPayment) {
		Date now = new Date();
		SimpleDateFormat smdf = new SimpleDateFormat("yyyy/mm/dd");
		String date = smdf.format(now);
		
		int insertResult = clientService.insertOrder(date, lastPayment);
		
		if(insertResult > 0) {
			code = "insertOrderSuccess";
		} else {
			code = "insertOrderFail";
		}
		
		orderResultSet.displayDmlResult(code);
		
	}

	public void insertPayment(int userNo, int totalPrice, double gradeDiscount, double cardDiscount, double lastPayment,
			int paymentBy) {
		String payment = "";
		switch(paymentBy) {
		case 1: payment = "카드";
		case 2: payment = "현금";
		case 3: payment = "기프티콘";
		}
		
		int insertResult = clientService.insertPayment(userNo, totalPrice, gradeDiscount, cardDiscount, lastPayment, payment);
		
	}

}
