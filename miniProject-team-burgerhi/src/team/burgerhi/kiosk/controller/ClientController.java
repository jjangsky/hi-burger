package team.burgerhi.kiosk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.OrderMenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;
import team.burgerhi.kiosk.model.service.ClientService;
import team.burgerhi.kiosk.views.OrderResultSet;

public class ClientController {
	private ClientService clientService = new ClientService();
	private OrderResultSet orderResultSet = new OrderResultSet();
	
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
			orderResultSet.orderMenuSuccess();
		} else {
			orderResultSet.orderMenuFail();
		}
		
	}

	public List<OrderMenuDTO> selectOrderMenu(int userNo) {
		
		List<OrderMenuDTO> orderMenuList = clientService.selectOrderMenu(userNo);
		
		return orderMenuList;
	}


}
