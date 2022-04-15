package team.burgerhi.kiosk.views;

import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.controller.ClientController;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;

public class OrderMenu {
	private ClientController clientController = new ClientController();
	private AdminMenu admin = new AdminMenu();

	public void displayMainMenu() {
		Scanner sc = new Scanner(System.in);
		List<E>
		boolean flag = true;
		// 김유찬 Test
		do {
			
			System.out.println(">>>> 어서오세요 BurgerHI 입니다. <<<<");
			System.out.println("================================");
			System.out.println();
			System.out.println("     1       |      2        ");
			System.out.println("  회원 주문하기  |  비회원 주문하기 ");
			System.out.println();
			System.out.print(">>>> 번호를 선택해 주세요: ");
			int num = sc.nextInt();
			System.out.println("\n\n\n\n\n");
			
			if(num == 1) {
				System.out.println(">>>>   BurgerHI 회원 주문하기   <<<<");
				System.out.println("================================");
				System.out.println();
				System.out.print(">>>> ID를 입력해 주세요: ");
				sc.nextLine();
				String id = sc.nextLine();
				System.out.print(">>>> PassWord를 입력해 주세요: ");
				String pwd = sc.nextLine();
				System.out.println("\n\n\n");
				
				int gradeNo = clientController.loginResult(id, pwd);
				if(gradeNo == 4) {
					admin.displayMainMenu();
				}
				
				System.out.println("\n\n\n\n\n");
				System.out.println(">>>>   BurgerHI 카테고리 선택   <<<<");
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
				System.out.println();
				System.out.print(">>>> 원하시는 메뉴의 번호를 입력해 주세요: ");
				int menuNo = sc.nextInt();
				System.out.print(">>>> 선택한 메뉴의 수량을 입력해 주세요: ");
				int amount = sc.nextInt();
				System.out.println("\\n\\n\\n\\n\\n");
				
				
				
				
				
			} else if(num == 2) {
				
			}else {
				System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요!");
			}
			
			
			
			
			
			
			
			
			
		} while(false);
	}
	

}
