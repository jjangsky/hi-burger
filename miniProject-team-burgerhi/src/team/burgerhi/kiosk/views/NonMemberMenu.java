package team.burgerhi.kiosk.views;

import java.util.List;
import java.util.Scanner;

import oracle.sql.ConcreteProxyUtil;
import team.burgerhi.kiosk.controller.ClientController;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;

public class NonMemberMenu {
	private ClientController clientController = new ClientController();
	public void displayMainMenu() {
		
		Scanner sc = new Scanner(System.in);
		  
		do {
			
			System.out.println(">>>>   BurgerHI 비회원 주문하기   <<<<");
			System.out.println("================================");
			System.out.println();   

			System.out.println();
			System.out.print(">>>> 원하시는 카테고리의 번호를 입력해 주세요: ");
			int categoryNo = sc.nextInt();
			System.out.println("\\n\\n\\n\\n\\n");
			
			System.out.println(">>>>    BurgerHI 메뉴 선택    <<<<");
			System.out.println("================================");
			System.out.println();
			List<MenuDTO> menuList = clientController.selectMenuBy(categoryNo);
			  
			for(MenuDTO menu : menuList) {
				System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "  " + menu.getPrice() + "원/n     " + menu.getExplain());
			}
			
			System.out.println();
			System.out.print(">>>> 원하시는 메뉴의 번호를 입력해 주세요: ");
			int menuNo = sc.nextInt();
			System.out.print(">>>> 선택한 메뉴의 수량을 입력해 주세요: ");
			int amount = sc.nextInt();          
			System.out.println("\\n\\n\\n\\n\\n");
			
			
			
			
			
		} while(true);
		
	}
	public void createUserInfo(){
		
		Scanner sc = new Scanner(System.in);
		/* 비회원 회원가입 절차 */
			
			System.out.println(">>>>   BurgerHI 회원가입 안내  <<<<");
			System.out.println("================================");
			System.out.println();  
			System.out.print(">>>> 본인의 성함을 입력해 주세요. ");
			String name = sc.nextLine();
			System.out.print(">>>> 사용하실 아이디를 입력해 주세요. ");
			String userId = sc.nextLine();
			System.out.print(">>>> 사용하실 비밀번호를 입력해 주세요. ");
			String userPwd = sc.nextLine();
			System.out.print(">>>> 사용하시는 휴대폰 번호를 입력해 주세요.('-' 포함) ");
			String userPhone = sc.nextLine();
		
		int result = clientController.createUserInfo(name, userId, userPwd, userPhone);
		
		if(result > 0) {
			System.out.println(" 회원가입이 정상적으로 처리되었습니다.");
		}else {
			System.out.println(" 회원가입에 실패하셨습니다.");
		}
		
	
	}

}
