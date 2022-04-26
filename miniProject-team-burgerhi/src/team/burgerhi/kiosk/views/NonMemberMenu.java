package team.burgerhi.kiosk.views;


import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.controller.ClientController;
import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;

public class NonMemberMenu {
	private ClientController clientController = new ClientController();
	Scanner sc = new Scanner(System.in);
	public void displayMainMenu() {
//		int menuPrice = 0;
		int totalPrice = 0;
		int lastPayment =0;
		int paymentBy = 0;
		int cardCode = 0;
		int userNo = 0;
		int gradeNo = 0;
		int refPrice = 0;
		int setAmount = 0;
		int setPrice = 0;
		int setDiscount = 0;
		int inputMenuNo = 0;
		int categoryNo = 0;
		int num = 0;
		int num2 = 0;
		int num3 = 0;
		int inputAmount = 0;
		int inputPrice = 0;
		int checkCard = 0;
		double cardDiscount = 0;
		boolean flag = true;
		boolean flag2 = true;
		List<Integer> setList = new ArrayList<Integer>();
		
		while(flag){
			boolean flag1 = true;
			System.out.println(">>>>        BurgerHI 비회원 주문하기         <<<<");
			System.out.println("=================================================");
			System.out.println();   
			List<CategoryDTO> categoryList = clientController.selectAllCategory(); // Category 출력 메소드		
			while(true) {
				try {		// 실수로 문자열을 입력했을 경우의 예외처리
					System.out.print("\n →  원하시는 카테고리의 번호를 입력해 주세요: ");
					categoryNo = sc.nextInt();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				} catch(InputMismatchException e) {
					System.out.println("\n 숫자로 입력해 주세요!");
					sc.next();
					continue;
				} break;
				}
			
			gradeNo = clientController.selectNonMemberGradeNo();
			userNo = clientController.insertNonMemberUser(gradeNo);
//			System.out.println(gradeNo);						// 오류 구문 확인
//			System.out.println("NullPointException Test1");		// 오류 구문 확인
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			if(categoryNo == 0) {
				flag2 = false;	// 메뉴 주문 while문 탈출
				break;			// 메인메뉴로 돌아가도록 설정
			} else if (categoryNo == 1){

				while(true) {
					try {		// 실수로 문자열을 입력했을 경우의 예외처리
						System.out.print("세트메뉴로 주문 하시겠습니까?(1.예 / 2. 아니오): ");
						 num2 = sc.nextInt();
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					} catch(InputMismatchException e) {
						System.out.println("\n 숫자로 입력해 주세요!");
						sc.next();
						continue;
					} break;
					}
				
				if(num2 == 1) {
					// 세트메뉴 선택 가능한 메소드
					setList = clientController.ShowSetMenu(userNo);
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					if(setList.size() > 0) {
						System.out.println("방금 누르신 메뉴가 장바구니에 담겼습니다!");
					}
				} else {
					
					while(flag1) {
						// 기존 메뉴선택 메소드
						List<MenuDTO> menuList = clientController.ShowOrderMenu(categoryNo);
						
						/* 원하는 Menu 선택하도록 하여 장바구니에 Insert */
						while(true) {
							try {		// 실수로 문자열을 입력했을 경우의 예외처리
								System.out.print("\n → 원하시는 메뉴의 번호를 입력해 주세요: ");
								inputMenuNo = sc.nextInt();
							} catch(InputMismatchException e) {
								System.out.println("\n 숫자로 입력해 주세요!");
								sc.next();
								continue;
							} break;
						}
						
						for(MenuDTO menu : menuList) {
							if(inputMenuNo == menu.getMenuCode() || inputMenuNo == 0) {
								flag1 = false;
								break;
							}
						}
						if(flag1 == true) {
							System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						}
					}
					
					if(inputMenuNo == 0) {
						flag1 = false;
						flag = false;
						flag2  = false;
						break;
					}
					
					while(true) {
						try {		// 실수로 문자열을 입력했을 경우의 예외처리
							System.out.print("\n → 선택한 메뉴의 수량을 입력해 주세요: ");
							inputAmount = sc.nextInt();
							System.out.println("\n\n\n\n\n");
						} catch(InputMismatchException e) {
							System.out.println("\n 숫자로 입력해 주세요!");
							sc.next();
							continue;
						} break;
					}
					/* 장바구니에 Insert */
					clientController.insertOrderMenu(userNo, inputMenuNo, inputAmount);
					
					/* 사용자가 선택한 모든 메뉴의 총 금액을 totalPrice변수에 누적시켜 결제시 활용 */
//					menuPrice = clientController.selectOrderMenuPrice(inputMenuNo);
//					totalPrice += (inputAmount * menuPrice);
					
					/* 추천카테고리의 메뉴 랜덤 추천 */
					refPrice = clientController.selectRefMenu(categoryNo, userNo);
				} 	
			}else if(categoryNo > categoryList.size()) {
				System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				continue;
			}else {
				while(flag1) {
					// 기존 메뉴선택 메소드
					List<MenuDTO> menuList = clientController.ShowOrderMenu(categoryNo);
					
					/* 원하는 Menu 선택하도록 하여 장바구니에 Insert */				
					while(true) {
						try {		// 실수로 문자열을 입력했을 경우의 예외처리
							System.out.print("\n → 원하시는 메뉴의 번호를 입력해 주세요: ");
							inputMenuNo = sc.nextInt();
						} catch(InputMismatchException e) {
							System.out.println("\n 숫자로 입력해 주세요!");
							sc.next();
							continue;
						} break;
						}
					
					for(MenuDTO menu : menuList) {
						if(inputMenuNo == menu.getMenuCode() || inputMenuNo == 0) {
							flag1 = false;
							break;
						}
					}
					if(flag1 == true) {
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					}
				}
				
				if(inputMenuNo == 0) {
					flag1 = false;
					flag = false;
					flag2  = false;
					break;
				}
				
				while(true) {
					try {		// 실수로 문자열을 입력했을 경우의 예외처리
						System.out.print("\n → 선택한 메뉴의 수량을 입력해 주세요: ");
						inputAmount = sc.nextInt();
						System.out.println("\n\n\n\n\n");
					} catch(InputMismatchException e) {
						System.out.println("\n 숫자로 입력해 주세요!");
						sc.next();
						continue;
					} break;
				}
				/* 장바구니에 Insert */
				clientController.insertOrderMenu(userNo, inputMenuNo, inputAmount);
				
				/* 사용자가 선택한 모든 메뉴의 총 금액을 totalPrice변수에 누적시켜 결제시 활용 */
//				menuPrice = clientController.selectOrderMenuPrice(inputMenuNo);
//				totalPrice += (inputAmount * menuPrice);
				
				/* 추천카테고리의 메뉴 랜덤 추천 */
				refPrice = clientController.selectRefMenu(categoryNo, userNo);
			}
			
			/* 추천 메뉴 금액과 세트메뉴 금액을 합산 할 총 금액 변수 */
//			totalPrice += refPrice;
			
			flag1 = true;
			while(flag1) {
			/* 추가 주문 여부 확인 및 장바구니 확인 선택 출력 */
			System.out.println(">>>>           BurgerHI 메뉴 선택            <<<<");
			System.out.println("=================================================");
			System.out.println("                       |                       ");
			System.out.println("           1           |           2           ");
			System.out.println("      추가 주문하기    |     장바구니 보기     ");
			System.out.println("                       |                       ");
			System.out.println("=================================================");
			System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");	
			while(true) {
				try {		// 실수로 문자열을 입력했을 경우의 예외처리
					System.out.print("\n → 번호를 선택해 주세요: ");
					 num = sc.nextInt();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				} catch(InputMismatchException e) {
					System.out.println("\n 숫자로 입력해 주세요!");
					sc.next();
					continue;
				} break;
				}
			
			
			/* 추가 주문하기 */
			if(num == 0) {
				clientController.deleteAllOrderMenu();
				flag2 = false;
				flag = false;
				flag1 = false;
				break;
			} else if(num == 1) {
				flag1 = false;
				break; // while문의 처음으로 돌아가도록 설정
			  /* 장바구니 확인하기 */
			} else if (num == 2) {
				totalPrice = clientController.selectOrderMenu(setList); // OrderMenu(장바구니) 모두 출력되도록 하는 메소드
				
			/* 장바구니 while문 */
				while(true) { // 번호를 잘못 입력할 경우 계속 하단 화면이 보이도록 while문 추가
					System.out.println(">>>>           BurgerHI 메뉴 선택            <<<<");
					System.out.println("=================================================");
					System.out.println("               |               |               ");
					System.out.println("      1 　   　|      2　　    |       3       ");
					System.out.println(" 추가 주문하기 | 장바구니 수정 |   결제 하기   ");
					System.out.println("               |               |               ");
					System.out.println("=================================================z");
					System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");		
				while(true) {
					try {		// 실수로 문자열을 입력했을 경우의 예외처리
						System.out.print("\n → 번호를 선택해 주세요: ");
						 num3 = sc.nextInt();
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					} catch(InputMismatchException e) {
						System.out.println("\n 숫자로 입력해 주세요!");
						sc.next();
						continue;
					} break;
					}

				if(num3 == 0) {
					clientController.deleteAllOrderMenu();
					flag = false;
					flag2 = false;
					flag1 = false;
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					break;
				} else if(num3 == 1) { // 추가 주문하기
					flag2 =false;
					flag1 = false;
					break;// 장바구니 while문 빠져나가서 메뉴주문 while문 처음으로 돌아감
				} else if(num3 == 2) { // 장바구니에 있는 메뉴 수정하기
					clientController.deleteOrderMenu(setList);
					totalPrice = clientController.selectOrderMenu(setList);
				} else if(num3 == 3) {
					for(int i = 0; i < setList.size(); i+= 5) {
						int berger = setList.get(i);
						int drink = setList.get(i+1);
						int side = setList.get(i+2);
						int amount = setList.get(i+4);
						clientController.insertOrderSetMenu(userNo, berger, amount);
						clientController.insertOrderSetMenu(userNo, drink, amount);
						clientController.insertOrderSetMenu(userNo, side, amount);
					}
					flag = false;
					flag2 = true;
					flag1 = false;
					break;
				} else {
					System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				}
			} // 장바구니 while문 종료

		}else {
			System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
	}
	} // 메뉴주문 while문 종료

		/* 결제 진행 */
		while (flag2) {
			System.out.println(">>>>         BurgerHI 장바구니 결제          <<<<");
			System.out.println("=================================================");
			System.out.println("               |               |               ");
			System.out.println("       1 　  　|        2　　  |       3      ");
			System.out.println("     카 드     |      현 금    |    기프티콘  ");
			System.out.println("               |               |              ");
			System.out.println("=================================================z");
			System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");			
				while(true) {
					try {		// 실수로 문자열을 입력했을 경우의 예외처리
						System.out.print("\n → 결제하실 수단을 선택해 주세요: ");
						paymentBy = sc.nextInt();
					} catch(InputMismatchException e) {
						System.out.println("\n 숫자로 입력해 주세요!");
						sc.next();
						continue;
					} break;
					}

				if(paymentBy == 0) {
					flag2 = false;
					break;
				} else if(paymentBy == 1) { // 카드 결제
					System.out.println("\n\n\n\n\n");
					System.out.println("★★★★    제휴카드 중복 할인 Event!    ★★★★");
					System.out.println("=================================================");

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
					for(int i = 0; i < cardList.size(); i++) {
						CardDTO card = cardList.get(i);
						if (card.getBank().equals(paymentCard) && card.getCardable() == "Y") {  // cardable 여부로 할인 적용 판정
							cardDiscount = 0.1;
							checkCard = 1;
							break;
						}else if (card.getBank().substring(0, 2).equals(paymentCard) && card.getCardable() == "Y") {
							cardDiscount = 0.1;
							checkCard = 1;
							break;
						}else if (card.getBank().equals(paymentCard) || card.getBank().substring(0, 2).equals(paymentCard)) {
							checkCard = 1;
							break;
						}else {
							continue;
						}
						
					}							
					
						if(checkCard == 0) {
							System.out.println("\n 사용할 수 없는 카드입니다.");
							System.out.println("다시 결제를 시도해 주세요.");
						continue;
						
					}

					cardCode = clientController.selectCardBy(paymentCard);

					cardDiscount = totalPrice * cardDiscount; // 카드 할인: 10%이기 때문에 총 금액에서 10%가 얼마인지 계산 후 변수에 담기

					/* 할인 내역 및 결제 금액 모두 출력 */
					System.out.println("▶ 장바구니 총 금액: " + totalPrice + "원");
					System.out.println("▶ 카드사 할인 금액: " + (int)cardDiscount + "원");
					System.out.println();
					for(int i = 0; i < setList.size(); i += 5) {
						setAmount += setList.get(i+4);
					}
					setPrice = setAmount * 1000;
					lastPayment = (int) (totalPrice - cardDiscount - setPrice);
					System.out.println("▶ 세트 할인 금액: " + setPrice + "원");
					System.out.println("▶ 총 결제 금액은 " + lastPayment + "원 입니다.");
					System.out.println();
					System.out.println("고객님의 " + paymentCard + "로 총" + lastPayment + "원이 결제 되었습니다!");
					System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");

				} else if (paymentBy == 2) { // 현금 결제
				
					while(true) {
						try {		// 실수로 문자열을 입력했을 경우의 예외처리
							System.out.println("▶ 장바구니 총 금액: " + totalPrice + "원");
							System.out.println();
							for(int i = 0; i < setList.size(); i += 5) {
								setAmount += setList.get(i+4);
							}
							setPrice = setAmount * 1000;
							System.out.println("▶ 세트 할인 금액: " + setPrice + "원");
							System.out.println("▶ 총 결제 금액은 " + (totalPrice - setPrice) + "원 입니다.");
							System.out.print("\n → 결제하실 금액을 입력해 주세요: ");
							inputPrice = sc.nextInt();
						} catch(InputMismatchException e) {
							System.out.println("\n 숫자로 입력해 주세요!");
							sc.next();
							continue;
						} break;
						}
					
					if (inputPrice == totalPrice) {
						System.out.println("결제가 완료 되었습니다! 주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
					} else if (inputPrice > totalPrice) {

						System.out.println("거스름돈은 " + (inputPrice - totalPrice) + "원 입니다!");
						System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
					} else {
						System.out.println("결제 금액이 " + (totalPrice - inputPrice) + "원 부족합니다!");
						System.out.println("\n 추가 금액 " + (totalPrice - inputPrice) + "원을 결제해 주세요!\n\n\n");
						System.out.println(">>>>         BurgerHI 장바구니 결제          <<<<");
						System.out.println("=================================================");
						System.out.println("                                                 ");
						System.out.println("                       1                         ");
						System.out.println("                    카  드                       ");
						System.out.println("                                                 ");
						System.out.println("=================================================");
						System.out.print("\n → 결제하실 수단을 선택해 주세요: ");
						paymentBy = sc.nextInt();
						if(paymentBy == 1) {
							List<CardDTO> cardList = clientController.selectCard();
							/* 사용자가 결제 할 카드 입력받기 */
							System.out.print("\n →결제하실 카드명을 입력해 주세요: ");
							sc.nextLine();
							String paymentCard = sc.nextLine();
							if(paymentCard.equals("0")) {
								flag2 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
							}
							for(int i = 0; i < cardList.size(); i++) {
								CardDTO card = cardList.get(i);
								if (card.getBank().equals(paymentCard) && card.getCardable() == "Y") {  // cardable 여부로 할인 적용 판정
									cardDiscount = 0.1;
									checkCard = 1;
									break;
								}else if (card.getBank().substring(0, 2).equals(paymentCard) && card.getCardable() == "Y") {
									cardDiscount = 0.1;
									checkCard = 1;
									break;
								}else if (card.getBank().equals(paymentCard) || card.getBank().substring(0, 2).equals(paymentCard)) {
									checkCard = 1;
									break;
								}else {
									continue;
								}
								
							}							
							
								if(checkCard == 0) {
									System.out.println("\n 사용할 수 없는 카드입니다.");
									System.out.println("다시 결제를 시도해 주세요.");
								continue;
								
							}
							System.out.println(
									"고객님의 " + paymentCard + "로 총" + (totalPrice - inputPrice) + "원이 결제 되었습니다!");
							System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
							System.out.println("\n\n\n\n\n\n\n\n\n\n");
						}
					}
				} else if (paymentBy == 3) { // 기프티콘 결제
					for(int i = 0; i < setList.size(); i += 5) {
						setAmount += setList.get(i+4);
					}
					setPrice = setAmount * 1000;
					System.out.println("▶ 세트 할인 금액: " + setPrice + "원");
					System.out.println("\n → 총 결제 금액은 " + (totalPrice - setPrice) + "원 입니다.");
					System.out.println();
					System.out.print("\n → 사용하실 기프티콘 번호를 입력해 주세요: ");
					sc.nextLine();
					String inputGiftNo = sc.nextLine();
					int gifticonPrice = clientController.selectGifticonBy(inputGiftNo);
					if(gifticonPrice == 0) {
						System.out.println("기프티콘 번호를 잘못 입력하셨습니다. 다시 입력해 주세요!");
						System.out.println("\n\n\n\n\n\n\n\n\n\n");
						continue;
					}
					if (gifticonPrice >= totalPrice) {
						gifticonPrice = gifticonPrice - totalPrice;
						System.out.println("결제가 완료 되었습니다! 기프티콘 잔액은 " + gifticonPrice + "원 입니다!");
						System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");

						/* 사용한 기프티콘 잔액 수정 */
						clientController.updateGifticonPrice(inputGiftNo, gifticonPrice);
					} else {
						System.out.println("기프티콘 사용이 완료 되었습니다!");
						System.out.println("추가 금액 " + (totalPrice - gifticonPrice) + "원을 결제해 주세요! \n\n\n\n");
						System.out.println(">>>>         BurgerHI 장바구니 결제          <<<<");
						System.out.println("=================================================");
						System.out.println("                       |                        ");
						System.out.println("           1           |            2           ");
						System.out.println("        카  드      　 |         현  금         ");
						System.out.println("                       |                        ");
						System.out.print("\n → 결제하실 수단을 선택해 주세요: ");
						paymentBy = sc.nextInt();
						if(paymentBy == 1) {
							List<CardDTO> cardList = clientController.selectCard();
							/* 사용자가 결제 할 카드 입력받기 */
							System.out.print("\n →결제하실 카드명을 입력해 주세요: ");
							sc.nextLine();
							String paymentCard = sc.nextLine();
							if(paymentCard.equals("0")) {
								flag2 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
							}
							for(int i = 0; i < cardList.size(); i++) {
								CardDTO card = cardList.get(i);
								if (card.getBank().equals(paymentCard) && card.getCardable() == "Y") {  // cardable 여부로 할인 적용 판정
									cardDiscount = 0.1;
									checkCard = 1;
									break;
								}else if (card.getBank().substring(0, 2).equals(paymentCard) && card.getCardable() == "Y") {
									cardDiscount = 0.1;
									checkCard = 1;
									break;
								}else if (card.getBank().equals(paymentCard) || card.getBank().substring(0, 2).equals(paymentCard)) {
									checkCard = 1;
									break;
								}else {
									continue;
								}
								
							}							
							
								if(checkCard == 0) {
									System.out.println("\n 사용할 수 없는 카드입니다.");
									System.out.println("다시 결제를 시도해 주세요.");
								continue;
								
							}
							System.out.println(
									"고객님의 " + paymentCard + "로 총" + (totalPrice - gifticonPrice) + "원이 결제 되었습니다!");
							System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
						}else if (paymentBy == 2) {					
							while(true) {
								try {		// 실수로 문자열을 입력했을 경우의 예외처리
									System.out.print("\n → 결제하실 금액을 입력해 주세요: ");
									inputPrice = sc.nextInt();
									inputPrice = sc.nextInt();
								} catch(InputMismatchException e) {
									System.out.println("\n 숫자로 입력해 주세요!");
									sc.next();
									continue;
								} break;
								}
							
							
							if (inputPrice == (totalPrice - gifticonPrice)) {
								System.out.println("결제가 완료 되었습니다! 주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
							}else if (inputPrice > (totalPrice - gifticonPrice)) {

								System.out.println(
										"거스름돈은 " + (inputPrice - (totalPrice - gifticonPrice)) + "원 입니다!");
								System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
							}
						}
						gifticonPrice = 0;
						/* 사용한 기프티콘 잔액 수정 */
						clientController.updateGifticonPrice(inputGiftNo, gifticonPrice);
					}
				} else {
					System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					continue;
				}

				/* 최종 모두 확정된 정보를 테이블에 Insert */
				clientController.insertOrder(totalPrice);
				int orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, totalPrice, paymentBy);
				clientController.insertSalesAmount(orderCode);
				clientController.gifticonEvent(orderCode);
				

				/* 모든 주문이 종료되면 주문번호를 호출하는 메소드 */
//				orderResultSet.closeDisplayMainMenu();
				System.out.println("\n\n\n\n");
				break;
		}
	}
	
	
	public void createUserInfo(){
		
		/* 비회원 회원가입 절차 */
		System.out.println(">>>>         BurgerHI 회원가입 안내         <<<<");
		System.out.println("=================================================");
		System.out.print("\n →  본인의 성함을 입력해 주세요: ");
//		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("\n →  사용하실 아이디를 입력해 주세요: ");
		String userId = sc.nextLine();
		System.out.print("\n →  사용하실 비밀번호를 입력해 주세요: ");
		String userPwd = sc.nextLine();
		System.out.print("\n →  사용하시는 휴대폰 번호를 입력해 주세요: ");
		String userPhone = sc.nextLine();
		String formatUserPhone = clientController.phoneFormat(userPhone);		// 휴대폰 번호의 경우 일정한 format으로 가공하여 DB Insert
//		System.out.println(formatUserPhone);									// 제대로 적용 되는지 확인 차 출력
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
		int result = clientController.createUserInfo(name, userId, userPwd, formatUserPhone);
		
		if(result > 0) {
			System.out.println(" 회원가입이 정상적으로 처리되었습니다.");
		}else {
			System.out.println(" 회원가입에 실패하셨습니다.");
		}
	}
}
