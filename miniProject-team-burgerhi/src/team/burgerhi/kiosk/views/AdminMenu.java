package team.burgerhi.kiosk.views;

import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.controller.AdminController;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;

public class AdminMenu {
	private AdminController adminController = new AdminController();

	public void displayMainMenu() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println(">>>>   BurgerHI 관리자 페이지   <<<<");
			System.out.println("================================");
			System.out.println();
			System.out.println("     1      　|      2      |      3   ");
			System.out.println(" 판매 순위 확인   | 　 메뉴 관리 　|   매출 확인 ");
			System.out.println();
			System.out.print(">>>> 번호를 선택해 주세요: ");
			int num = sc.nextInt();
			System.out.println("\n\n\n\n\n");

			/* 판매 순위 확인 */
			if (num == 1) {
				System.out.println(">>>>   BurgerHI 카테고리 랭킹   <<<<");
				System.out.println("================================");
				System.out.println();
				System.out.println("     1      |     2      |     3      ");
				System.out.println("　햄버거 랭킹 　|  음료수 랭킹  |  사이드 랭킹  ");
				System.out.println();
				System.out.print(">>>> 번호를 선택해 주세요: ");
				int rankNum = sc.nextInt();
				System.out.println("\n\n\n\n\n");

				switch (rankNum) {
				case 1: adminController.selectHambergerRanking(); break;		// 햄버거 랭킹을 보여 줄 메소드
				case 2: adminController.selectDrinkRanking(); break;			// 음료수 랭킹을 보여 줄 메소드
				case 3: adminController.selectSideRanking(); break;				// 사이드 랭킹을 보여 줄 메소드
				default: System.out.println("번호를 잘못 입력하셨습니다!"); continue;	// 번호 잘못 입력할 경우 다시 처음으로 돌아가게 설정
				}

			  /* 메뉴 관리 확인 */
			} else if (num == 2) {
				System.out.println(">>>>  BurgerHI 메뉴 관리 시스템  <<<<");
				System.out.println("================================");
				System.out.println();
				System.out.println("      1      |     2     ");
				System.out.println("  카테고리 관리  |   메뉴 관리  ");
				System.out.println();
				System.out.print(">>>> 번호를 선택해 주세요: ");
				int menuAdmin = sc.nextInt();
				System.out.println("\n\n\n\n\n");
				
				/* 카테고리 추가/수정/삭제 */
				if(menuAdmin == 1) {
					System.out.println(">>>>  BurgerHI 카테고리 관리 시스템  <<<<");
					System.out.println("================================");
					System.out.println();
					System.out.println("　　 1 　　 |　　 2　　  |　　 3　　 ");
					System.out.println(" 카테고리 추가 | 카테고리 수정 | 카테고리 삭제 ");
					System.out.println();
					List<CategoryDTO> categoryList = adminController.selectAllCategory();
					for(CategoryDTO cate : categoryList) {
						System.out.println("▶ " + cate.getCode() + ". " + cate.getName() + "(추천 카테고리: " + cate.getRefName() + ")");
					}
					System.out.println("\n\n");
					System.out.print(">>>> 번호를 선택해 주세요: ");
					int categoryNum = sc.nextInt();
					System.out.println("\n\n\n\n\n");
					
					switch(categoryNum) {
						case 1: adminController.insertCategory(); break;				// 카테고리 추가하는 메소드
						case 2: adminController.updateCategory(); break;				// 카테고리 수정하는 메소드
						case 3: adminController.deleteCategory(); break;				// 카테고리 삭제하는 메소드
						default : System.out.println("번호를 잘못 입력하셨습니다!"); continue;	// 번호 잘못 입력할 경우 다시 처음으로 돌아가게 설정
					}
					
				  /* 메뉴 추가/수정/삭제 */
				} else if(menuAdmin == 2) {
					System.out.println("　　 1 　　|　　 2　　 |　　 3　　 ");
					System.out.println("　메뉴 추가　|  메뉴 수정  |　메뉴 삭제　");
					System.out.println();
					List<MenuDTO> menuList = adminController.selectAllMenu();
					for(MenuDTO menu : menuList) {
						System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "$" + menu.getPrice());
						System.out.println("　 카테고리 번호: " + menu.getCategoryCode() + " 주문 가능 여부: " + menu.getOrderable());
						System.out.println("　  \"" + menu.getExplain() + "\"");
					}
					System.out.print(">>>> 번호를 선택해 주세요: ");
					int menuNum = sc.nextInt();
					System.out.println("\n\n\n\n\n");
					
					switch(menuNum) {
						case 1: adminController.insertMenu(); break;					// 메뉴 추가하는 메소드
						case 2: adminController.updateMenu(); break;					// 메뉴 수정하는 메소드
						case 3: adminController.deleteMenu(); break;					// 메뉴 삭제하는 메소드
						default : System.out.println("번호를 잘못 입력하셨습니다!"); continue;	// 번호 잘못 입력할 경우 다시 처음으로 돌아가게 설정
					}
					
				} else {
					System.out.println("번호를 잘못 입력하셨습니다!");
					continue;
				}
				
			  /* 종류별 매출 확인 */
			} else if (num == 3) {
				System.out.println(">>>>  BurgerHI 매출 관리 시스템  <<<<");
				System.out.println("================================");
				System.out.println();
				System.out.println("　   1      |       2     ");
				System.out.println("  날짜별 매출  |   카테고리별 매출 ");
				System.out.println();
				System.out.print(">>>> 번호를 선택해 주세요: ");
				int salesNum = sc.nextInt();
				System.out.println("\n\n\n\n\n");
				
				switch(salesNum) {
					case 1: adminController.SalesBydate(); break;		// 날짜별 매출 확인하는 메소드
					case 2: adminController.SalesCategory(); break;		// 카테고리별 매출 확인하는 메소드
				}
			}
		}
	}
}
