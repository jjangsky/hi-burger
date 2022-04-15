package team.burgerhi.kiosk.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.controller.ClientController;
import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.OrderMenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

public class OrderMenu {
	private ClientController clientController = new ClientController();
	private NonMemberMenu nonMemberMenu = new NonMemberMenu();
	private AdminMenu admin = new AdminMenu();

	public void displayMainMenu() {
		Scanner sc = new Scanner(System.in);
		int userNo = 0;
		int gradeNo = 0;
		int menuPrice = 0;
		int totalPrice = 0;
		int paymentBy = 0;
		double gradeDiscount = 0;
		double cardDiscount = 0;
		double lastPayment = 0;
		boolean flag = true;
		
		do {
			
			System.out.println(">>>> 어서오세요 BurgerHI 입니다. <<<<");
			System.out.println("================================");
			System.out.println();
			System.out.println("     1       |      2       ");
			System.out.println("  회원 주문하기  |  비회원 주문하기  ");
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
				
				UserDTO userDTO = clientController.loginResult(id, pwd);
				userNo = userDTO.getUserNo();
				gradeNo = userDTO.getGradeNo();
				
				if(gradeNo == 4) {
					admin.displayMainMenu();							// 관리자 view 클래스로 연동
					break;
				}
				
				while(true) {
					System.out.println("\n\n\n\n\n");
					System.out.println(">>>>    BurgerHI 카테고리 선택    <<<<");
					System.out.println("===================================");
					System.out.println();
					List<CategoryDTO> categoryList = clientController.selectAllCategory();
					
					for(CategoryDTO cate : categoryList) {
						System.out.println("▶ " + cate.getCode() + ". " + cate.getName());
					}
					System.out.println();
					System.out.print(">>>> 원하시는 카테고리의 번호를 입력해 주세요: ");
					int categoryNo = sc.nextInt();
					System.out.println("\n\n\n\n\n");
					
					System.out.println(">>>>     BurgerHI 메뉴 선택     <<<<");
					System.out.println("===================================");
					System.out.println();
					List<MenuDTO> menuList = clientController.selectMenuBy(categoryNo);
					for(MenuDTO menu : menuList) {
						System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "  " + menu.getPrice() + "원/n     " + menu.getExplain());
						menuPrice = menu.getPrice();
					}
					
					System.out.println();
					System.out.print(">>>> 원하시는 메뉴의 번호를 입력해 주세요: ");
					int inputMenuNo = sc.nextInt();
					System.out.print(">>>> 선택한 메뉴의 수량을 입력해 주세요: ");
					int inputAmount = sc.nextInt();
					System.out.println("\n\n\n\n\n");
					clientController.insertOrderMenu(userNo, inputMenuNo, inputAmount);
					totalPrice += inputAmount * menuPrice;
					
					System.out.println("\n\n\n");
					System.out.println("     1       |      2     ");
					System.out.println("  다시 주문하기  |  장바구니 보기 ");
					System.out.println();
					System.out.print(">>>> 번호를 선택해 주세요: ");
					num = sc.nextInt();
					if(num == 1) {
						continue;
					} else if(num == 2) {
						List<OrderMenuDTO> orderMenuList = clientController.selectOrderMenu(userNo);
						while(true) {
							System.out.println("\n\n\n\n\n");
							System.out.println(">>>>    BurgerHI 장바구니 확인    <<<<");
							System.out.println("===================================");
							System.out.println();
							
							for(OrderMenuDTO orderMenu : orderMenuList) {
								MenuDTO menu = new MenuDTO();
								if(menu.getMenuCode() == orderMenu.getMenuCode()) {
									System.out.println("▶ 메뉴번호: " + orderMenu.getMenuCode());
									System.out.println("▶ 메뉴명 : " + menu.getName());
									System.out.println("▶ 주문수량: " + orderMenu.getOrderAmount());
									System.out.println("▶ 금액: " + menu.getPrice() + " * " + orderMenu.getOrderAmount() + " = " + menu.getPrice() * orderMenu.getOrderAmount());
								}
							} System.out.println("▶ 총 금액: " + totalPrice);
							
							
							System.out.println("\n\n\n");
							System.out.println("     1       |     2      |     3   ");
							System.out.println("  추가 주문하기  |  메뉴 삭제하기 |   결제하기 ");
							System.out.println();
							System.out.print(">>>> 번호를 선택해 주세요: ");
							num = sc.nextInt();
							if(num == 1) {
								break;
							} else if(num ==2) {
								System.out.println("\n\n\n\n\n");
								System.out.println(">>>>    BurgerHI 장바구니 수정    <<<<");
								System.out.println("===================================");
								System.out.println();
								System.out.print(">>>> 삭제하실 메뉴 번호를 입력해 주세요: ");
								int deleteMenuCode = sc.nextInt();
								clientController.deleteOrderMenu(deleteMenuCode);
								
							} else if(num == 3) {
								flag = false;
							} else {
								System.out.println("번호를 잘못 입력하셨습니다! ");
							}
							
						}// 장바구니 while문 종료
					}
				}//메뉴주문 while문 종료
			} else if(num == 2) {
				nonMemberMenu.displayMainMenu();
			}else {
				System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요!");
			}
		} while(flag);
		
		
		/* 결제 진행 */
		while(true) {
			System.out.println(">>>>    BurgerHI 장바구니 결제    <<<<");
			System.out.println("===================================");
			System.out.println();
			System.out.println("    1 　　|     2　　 |     3   ");
			System.out.println("   카 드　 |    현 금   |   기프티콘 ");
			System.out.println();
			System.out.print(">>>> 결제하실 수단을 선택해 주세요: ");
			paymentBy = sc.nextInt();
			if(paymentBy == 1) {
				List<CardDTO> cardList = clientController.selectCard();
				System.out.println("\n\n\n\n\n");
				System.out.println("★★★★  제휴카드 중복 할인 Event!  ★★★★");
				System.out.println("===================================");
				for(CardDTO card : cardList) {
					System.out.println("▶ " +card.getBank() + "의 할인율 :" + card.getDiscount() + "%");
				}
				System.out.print(">>>> 결제하실 카드명을 입력해 주세요: ");
				sc.nextLine();
				String paymentCard = sc.nextLine();
				
				
				for(CardDTO card : cardList) {
					if(card.getBank().equals(paymentCard)) {
						cardDiscount = 0.01;
						break;
					}
				}
				cardDiscount = totalPrice * cardDiscount;
				System.out.println("▶ 장바구니 총 금액: " + totalPrice + "원");
				int grade = clientController.selectGrade(gradeNo);
				gradeDiscount = totalPrice * (grade * 0.01);
				System.out.println("▶ 등급 할인 금액: " + gradeDiscount + "원");
				System.out.println("▶ 카드사 할인 금액: " + cardDiscount + "원");
				lastPayment = totalPrice - gradeDiscount - cardDiscount;
				System.out.println();
				System.out.println("▶ 총 결제 금액은 " + lastPayment + "원 입니다.");
				
			} else {
				System.out.println("▶ 장바구니 총 금액: " + totalPrice + "원");
				int grade = clientController.selectGrade(gradeNo);
				gradeDiscount = totalPrice * (grade * 0.01);
				lastPayment = totalPrice - gradeDiscount;
				System.out.println("▶ 등급 할인 금액: " + gradeDiscount + "원");
				System.out.println("▶ 총 결제 금액은 " + lastPayment + "원 입니다.");
			}
			
			clientController.insertOrder(lastPayment);
			clientController.insertPayment(userNo, totalPrice, gradeDiscount, cardDiscount, lastPayment, paymentBy);

			
		}

	}
}