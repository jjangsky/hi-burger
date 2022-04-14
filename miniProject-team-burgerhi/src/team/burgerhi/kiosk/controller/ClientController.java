package team.burgerhi.kiosk.controller;

import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;
import team.burgerhi.kiosk.model.service.ClientService;

public class ClientController {
	private ClientService clientService = new ClientService();
	
	public int loginResult(String id, String pwd) {
		
		List<UserDTO> userList = clientService.loginResult(id, pwd);
		String userName = "";
		int gradeNo = 0;
		for(UserDTO user : userList) {
			userName = user.getName();
			gradeNo = user.getGradeNo();
		}
		
		Scanner sc = new Scanner(System.in);
		if(userName != null) {
			System.out.println(">>>> " + userName + "님 환영합니다!");
			System.out.println();
		} else {
			System.out.println("회원정보가 일치하지 않습니다. 다시 입력해 주세요!");
			System.out.println("\n\n\n");
		}
		return gradeNo;
	}

	public List<CategoryDTO> selectAllCategory() {
		
		List<CategoryDTO> categoryList = clientService.selectAllCategory();
		
		return categoryList;
	}

	public List<MenuDTO> selectMenuBy(int categoryNo) {
		
		List<MenuDTO> menuList = clientService.selectMenuBy(categoryNo);
				
		return menuList;
	}

}
