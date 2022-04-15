package team.burgerhi.kiosk.views;

import java.util.List;
import java.util.Scanner;

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

			List<CategoryDTO> categoryList = clientController.selectAllCategory();
			
			for(CategoryDTO cate : categoryList) {
				System.out.println("▶ " + cate.getCode() + ". " + cate.getName());
			}
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

}
