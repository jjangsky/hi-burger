package team.burgerhi.kiosk.views;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.controller.ClientController;
import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

public class OrderMenu {
	private ClientController clientController = new ClientController();
	private NonMemberMenu nonMemberMenu = new NonMemberMenu();
	private AdminMenu admin = new AdminMenu();
	private OrderResultSet orderResultSet = new OrderResultSet();

	public void displayMainMenu() {
		Scanner sc = new Scanner(System.in);


		while(true) {
			/* do~while문 밖에서도 사용해야 할 변수 */
			int num = 0;
			int userNo = 0;
			int gradeNo = 0;
			int menuPrice = 0;
			int totalPrice = 0;
			int paymentBy = 0;
			int cardCode = 0;
			int lastPayment = 0;
			double gradeDiscount = 0;
			double cardDiscount = 0;
			boolean flag = true;
			boolean flag1 = true;
			boolean flag2 = true;
			
			do {
				clientController.deleteAllOrderMenu();
				/* BurgerHI 메인 주문 화면(첫 화면) */
				System.out.println();
				System.out.println(">>>>       어서오세요 BurgerHI 입니다.       <<<<");
				System.out.println("=================================================");
				System.out.println("               |                  |             ");
				System.out.println("      1        |        2         |      3     ");
				System.out.println(" 회원 주문하기 |  비회원 주문하기 |   회원가입 ");
				System.out.println("               |                  |             ");
				System.out.println("=================================================");
				System.out.print("\n  → 번호를 선택해 주세요: ");
//				try {
					num = sc.nextInt();
//				} catch(InputMismatchException e){
					System.out.println("번호로 입력해 주세요!");
//					continue;
//				}
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				

				if(num == 1) { // 회원 주문하기
					/* loginResult 메소드에서 로그인 화면 출력 및 가공처리 */
					UserDTO userDTO = clientController.loginResult();

					/* login이 정상적으로 처리되지 않았을 경우 continue로 while문 처음으로 돌아가도록 설정 */
					if(userDTO.getUserNo() == 0) {
						continue;
					}

					/* 다른 메소드에서 필요한 user 정보는 전역변수에 미리 넣어놓고 사용 */
					userNo = userDTO.getUserNo();
					gradeNo = userDTO.getGradeNo();

					/* 회원 등급이 4일 경우 관리자로 분류되어 관리자 페이지로 이동 */
					if(gradeNo == 4) {
						admin.displayMainMenu();
						continue;
					}

					/* 메뉴 선택 or 회원 정보 조회, 수정, 탈퇴 메소드 */
					int userInfo = clientController.userInfoSelect(userNo);
					if(userInfo == 0){
						continue;
					} else if(userInfo == 1) {
						
					} 

					/* 메뉴주문 while문 */
					while(flag1) {

						/* 전체 Category 출력 */
						System.out.println(">>>>         BurgerHI 카테고리 선택          <<<<");
						System.out.println("=================================================");
						System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
						System.out.println();
						clientController.selectAllCategory(); // Category 출력 메소드

						/* 메뉴 출력을 위해 필요한 category 번호 받기 */
						System.out.print("\n → 원하시는 카테고리의 번호를 입력해 주세요: ");
						int categoryNo = sc.nextInt();
						if(categoryNo == 0) {
							flag1 = false;		// 메뉴 주문 while문 탈출
							continue;			// 메인메뉴로 돌아가도록 설정
						}
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

						/* 사용자가 선택한 Category의 전체 Menu 출력 */
						System.out.println(">>>>           BurgerHI 메뉴 선택            <<<<");
						System.out.println("=================================================");
						System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
						System.out.println();
						List<MenuDTO> menuList = clientController.selectMenuBy(categoryNo); // Menu 출력 메소드
						for (MenuDTO menu : menuList) {
							System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "  "
									+ menu.getPrice() + "원\n     " + menu.getExplain());
						}

						/* 원하는 Menu 선택하도록 하여 장바구니에 Insert */
						System.out.print("\n → 원하시는 메뉴의 번호를 입력해 주세요: ");
						int inputMenuNo = sc.nextInt();
						if(inputMenuNo == 0) {
							flag1 = false;		// 메뉴 주문 while문 탈출
							System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							continue;			// 메인메뉴로 돌아가도록 설정
						}
						System.out.print("\n → 선택한 메뉴의 수량을 입력해 주세요: ");
						int inputAmount = sc.nextInt();
						if(inputAmount == 0) {
							flag1 = false;		// 메뉴 주문 while문 탈출
							continue;			// 메인메뉴로 돌아가도록 설정
						}
						
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						
						/* OrderMenu(장바구니) Insert 메소드 */
						clientController.insertOrderMenu(userNo, inputMenuNo, inputAmount);
																							

						/* 사용자가 선택한 모든 메뉴의 총 금액을 totalPrice변수에 누적시켜 결제시 활용 */
						menuPrice = clientController.selectOrderMenuPrice(inputMenuNo);
						
						totalPrice += (inputAmount * menuPrice);

						/* 추가 주문 여부 확인 및 장바구니 확인 선택 출력 */
						System.out.println(">>>>           BurgerHI 메뉴 선택            <<<<");
						System.out.println("=================================================");
						System.out.println("                       |                       ");
						System.out.println("           1           |           2           ");
						System.out.println("      추가 주문하기    |     장바구니 보기     ");
						System.out.println("                       |                       ");
						System.out.println("=================================================");
						System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
						System.out.print("\n → 번호를 선택해 주세요: ");
						num = sc.nextInt();
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

						if(num == 0) { 	//프로그램 종료
									// 프로그램이 종료 되면서 장바구니 내역 삭제
							flag1 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
							continue;									// 맨 처음 메인메뉴로 돌아가도록 설정
						}else if(num == 1) { // 추가 주문하기
							continue; // while문의 처음으로 돌아가도록 설정
						} else if (num == 2) { // 장바구니 확인하기
							clientController.selectOrderMenu(totalPrice); // OrderMenu(장바구니) 모두 출력되도록 하는 메소드

							/* 장바구니 while문 */
							while(true) { // 번호를 잘못 입력할 경우 계속 하단 화면이 보이도록 while문 추가

								System.out.println(">>>>           BurgerHI 메뉴 선택            <<<<");
								System.out.println("=================================================");
								System.out.println("               |               |               ");
								System.out.println("      1 　   　|       2　　   |       3       ");
								System.out.println(" 추가 주문하기 | 장바구니 수정 |   결제 하기   ");
								System.out.println("               |               |               ");
								System.out.println("=================================================");
								System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
								System.out.print("\n → 번호를 선택해 주세요: ");
								num = sc.nextInt();
								System.out.println("\n\n\n\n\n");

								if(num == 0) {
									flag2 = false;								// 결제 while문 미실행
									flag1 = false;								// 메뉴 주문 while문 탈출
									break;										// 장바구니 while문 탈출
								} else if(num == 1) { // 추가 주문하기
									break; // 장바구니 while문 빠져나가서 메뉴주문 while문 처음으로 돌아감
								} else if(num == 2) { // 장바구니에 있는 메뉴 수정하기
									int close = clientController.deleteOrderMenu();
									if(close == 0) {
										clientController.deleteAllOrderMenu();		// 프로그램이 종료 되면서 장바구니 내역 삭제
										break; 										// 장바구니 while문 빠져나가서 메뉴주문 while문 처음으로 돌아감
									} else {
										totalPrice = clientController.selectOrderMenu(totalPrice);
									}
								} else if(num == 3) {
									flag = false; // 모든 while문을 빠져나가 최종 결제 화면이 뜨도록 함
									flag1 = false;
									break;
								} else {
									System.out.println("번호를 잘못 입력하셨습니다! ");
									System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
								}
							} // 장바구니 while문 종료
						}
					} // 메뉴주문 while문 종료

				} else if(num == 2) { // 비회원 주문하기
					nonMemberMenu.displayMainMenu();
				} else if(num == 3) { // 회원가입 넘어가기
					nonMemberMenu.createUserInfo();
				} else {
					System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요!");
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				}
			} while(flag);

			/* 결제 진행 */
			
			while (flag2) {
				System.out.println(">>>>         BurgerHI 장바구니 결제          <<<<");
				System.out.println("=================================================");
				System.out.println("               |               |               ");
				System.out.println("       1 　  　|        2　　  |       3      ");
				System.out.println("     카 드     |      현 금    |    기프티콘  ");
				System.out.println("               |               |              ");
				System.out.println("=================================================");
				System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
				System.out.print("\n → 결제하실 수단을 선택해 주세요: ");
				paymentBy = sc.nextInt();
				System.out.println("\n\n\n\n\n\n\n\n\n\n");

					if (paymentBy == 0) {
						flag2 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
					} else if (paymentBy == 1) { // 카드 결제
						System.out.println("★★★★    제휴카드 중복 할인 Event!    ★★★★");
						System.out.println("=================================================");
						System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");

						/* 할인 가능한 전체 제휴카드 리스트 출력 */
						List<CardDTO> cardList = clientController.selectCard();
						for (CardDTO card : cardList) {
							System.out.println("▶ " + card.getBank() + "의 할인율 :" + card.getDiscount() + "%");
						}

						/* 사용자가 결제 할 카드 입력받기 */
						System.out.print("\n →결제하실 카드명을 입력해 주세요: ");
						sc.nextLine();
						String paymentCard = sc.nextLine();
						if(paymentCard.equals("0")) {
							flag2 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
						}
						for (CardDTO card : cardList) {
							if (card.getBank().equals(paymentCard)) {
								cardDiscount = 0.1;
								break;
							}
						}

						cardCode = clientController.selectCardBy(paymentCard);

						cardDiscount = totalPrice * cardDiscount; // 카드 할인: 10%이기 때문에 총 금액에서 10%가 얼마인지 계산 후 변수에 담기

						/* 할인 내역 및 결제 금액 모두 출력 */
						System.out.println("▶ 장바구니 총 금액: " + totalPrice + "원");
						int grade = clientController.selectGrade(gradeNo);
						gradeDiscount = totalPrice * (grade * 0.01);
						System.out.println("▶ 등급 할인 금액: " + gradeDiscount + "원");
						System.out.println("▶ 카드사 할인 금액: " + cardDiscount + "원");
						lastPayment = (int) (totalPrice - gradeDiscount - cardDiscount);
						System.out.println();
						System.out.println("▶ 총 결제 금액은 " + lastPayment + "원 입니다.");
						System.out.println();
						System.out.println("고객님의 " + paymentCard + "로 총" + lastPayment + "원이 결제 되었습니다!");
						System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
					} else if (paymentBy == 2) { // 현금 결제
						System.out.println("▶ 장바구니 총 금액: " + totalPrice + "원");
						int grade = clientController.selectGrade(gradeNo);
						gradeDiscount = totalPrice * (grade * 0.01);
						lastPayment = (int) (totalPrice - gradeDiscount);
						System.out.println("▶ 등급 할인 금액: " + (int)gradeDiscount + "원");
						System.out.println("▶ 총 결제 금액은 " + lastPayment + "원 입니다.");
						System.out.println();
						System.out.print("\n → 결제하실 금액을 입력해 주세요: ");
						int inputPrice = sc.nextInt();
						if (inputPrice == lastPayment) {
							System.out.println("결제가 완료 되었습니다! 주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
						} else if (inputPrice > lastPayment) {

							System.out.println("거스름돈은 " + (inputPrice - lastPayment) + "원 입니다!");
							System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
						} else {
							System.out.println("결제 금액이 " + (lastPayment - inputPrice) + "원 부족합니다!");
							System.out.println("최종 결제 금액은 " + lastPayment + "원 입니다.");
							System.out.println("다시 결제를 시도해 주세요 :)");
						}

					} else if (paymentBy == 3) { // 기프티콘 결제
						System.out.println("▶ 장바구니 총 금액: " + totalPrice + "원");
						int grade = clientController.selectGrade(gradeNo);
						gradeDiscount = totalPrice * (grade * 0.01);
						lastPayment = (int)(totalPrice - gradeDiscount);
						System.out.println("▶ 등급 할인 금액: " + (int)gradeDiscount + "원");
						System.out.println("▶ 총 결제 금액은 " + lastPayment + "원 입니다.");
						System.out.println();
						System.out.print("\n → 사용하실 기프티콘 번호를 입력해 주세요: ");
						sc.nextLine();
						String inputGiftNo = sc.nextLine();
//						System.out.println(inputGiftNo);
						int gifticonPrice = clientController.selectGifticonBy(inputGiftNo);
//						System.out.println(lastPayment);
//						System.out.println(gifticonPrice);
						
						if (gifticonPrice >= lastPayment) {
							gifticonPrice = gifticonPrice - lastPayment;
							System.out.println("\n 결제가 완료 되었습니다! 기프티콘 잔액은 " + gifticonPrice + "원 입니다!");
							System.out.println("\n 주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
							
							/* 사용한 기프티콘 잔액 수정 */
							clientController.updateGifticonPrice(inputGiftNo, gifticonPrice);
//							System.out.println(inputGiftNo);
//							System.out.println(gifticonPrice);
							System.out.println("\n\n\n\n\n\n\n\n\n\n");
							
						} else if(gifticonPrice < lastPayment){
							System.out.println("\n 기프티콘 사용이 완료 되었습니다!");
							System.out.println("\n 추가 금액 " + (lastPayment - gifticonPrice) + "원을 결제해 주세요!\n\n\n");
							System.out.println(">>>>         BurgerHI 장바구니 결제          <<<<");
							System.out.println("=================================================");
							System.out.println("                       |                        ");
							System.out.println("           1           |            2           ");
							System.out.println("        카  드      　 |         현  금         ");
							System.out.println("                       |                        ");
							System.out.print("\n → 결제하실 수단을 선택해 주세요: ");
							paymentBy = sc.nextInt();
							if(paymentBy == 1) {
								System.out.print("\n →  결제하실 카드명을 입력해 주세요: ");
								sc.nextLine();
								String paymentCard = sc.nextLine();
								System.out.println(
										"고객님의 " + paymentCard + "로 총" + (lastPayment - gifticonPrice) + "원이 결제 되었습니다!");
								System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
								System.out.println("\n\n\n\n\n\n\n\n\n\n");
							}else if (paymentBy == 2) {
								System.out.print("\n → 결제하실 금액을 입력해 주세요: ");
								int inputPrice = sc.nextInt();
								if (inputPrice == (lastPayment - gifticonPrice)) {
									System.out.println("결제가 완료 되었습니다! 주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
									System.out.println("\n\n\n\n\n\n\n\n\n\n");
								}else if (inputPrice > (lastPayment - gifticonPrice)) {

									System.out.println(
											"거스름돈은 " + (inputPrice - (lastPayment - gifticonPrice)) + "원 입니다!");
									System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
									System.out.println("\n\n\n\n\n\n\n\n\n\n");
								}
								
							}
							gifticonPrice = 0;
							/* 사용한 기프티콘 잔액 수정 */
							clientController.updateGifticonPrice(inputGiftNo, gifticonPrice);
						}
					}

					/* 최종 모두 확정된 정보를 테이블에 Insert */
					clientController.insertOrder(lastPayment);
					int orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, lastPayment, paymentBy);
					
					/* 장바구니 delete */
					clientController.insertSalesAmount(orderCode);

					/* 모든 주문이 종료되면 주문번호를 호출하는 메소드 */
//					orderResultSet.closeDisplayMainMenu();
					flag2 = false;
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			} 
		} 
	}
}
