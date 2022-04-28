package team.burgerhi.kiosk.views;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.controller.ClientController;
import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

public class OrderMenu {
	private ClientController clientController = new ClientController();
	private NonMemberMenu nonMemberMenu = new NonMemberMenu();
	private AdminMenu admin = new AdminMenu();
	private OrderResultSet orderResultSet = new OrderResultSet();
	List<Integer> setList = new ArrayList<Integer>();
	
	public void displayMainMenu() {
		Scanner sc = new Scanner(System.in);
		DecimalFormat format = new DecimalFormat("###,###");
		Thread th = new Thread();
//		List<Integer> setList = new ArrayList<Integer>();
		
		while(true) {
			/* do~while문 밖에서도 사용해야 할 변수 */
			int num = 0;
			int num2 = 0;
			int categoryNo = 0;
			int userNo = 0;
			int gradeNo = 0;
			int totalPrice = 0;
			int paymentBy = 0;
			int cardCode = 0;
			int paymentPrice = 0;
			int setDiscount = 0;
			int setAmount = 0;
			int inputPrice = 0;
			int checkCard = 0;
			int orderCode = 0;
			double gradeDiscount = 0;
			double cardDiscount = 0;
			boolean flag = true;
			boolean flag1 = true;
			boolean flag2 = true;
			boolean flag3 = true;
			
			
			
			do {
				
				flag2 = true;
				clientController.deleteAllOrderMenu();
				setList.clear();
				/* BurgerHI 메인 주문 화면(첫 화면) */
				System.out.println();
				System.out.println(">>>>       어서오세요 BurgerHI 입니다.       <<<<");
				System.out.println("=================================================");
				System.out.println("               |                  |             ");
				System.out.println("      1        |        2         |      3     ");
				System.out.println(" 회원 주문하기 |  비회원 주문하기 |   회원가입 ");
				System.out.println("               |                  |             ");
				System.out.println("=================================================");
				while(true) {
				try {		// 실수로 문자열을 입력했을 경우의 예외처리
					System.out.print("\n  → 번호를 선택해 주세요: ");
					num = sc.nextInt();
				} catch(InputMismatchException e) {
					System.out.println("\n 숫자로 입력해 주세요!");
					sc.next();
					continue;
				} break;
				}
				
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
						int exit = admin.displayMainMenu();
						if(exit == 0) {
							continue;
						}
					}

					/* 메뉴 선택 or 회원 정보 조회, 수정, 탈퇴 메소드 */
					int userInfo = clientController.userInfoSelect(userNo);
					if(userInfo == 0){
						continue;
					} else if(userInfo == 1) {
						
					} 

					/* 메뉴주문 while문 */
					flag1 = true;
					while(flag1) {
						int inputMenuNo = 0;
						int inputAmount = 0;
						
						/* 전체 Category 출력 */
						System.out.println(">>>>         BurgerHI 카테고리 선택          <<<<");
						System.out.println("=================================================");
						System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
						System.out.println();
						List<CategoryDTO> categoryList = clientController.selectAllCategory(); // Category 출력 메소드
						flag3 = true;

						/* 메뉴 출력을 위해 필요한 category 번호 받기 */
						
						while(true) {
							try {	// 문자열 예외처리
								System.out.print("\n → 원하시는 카테고리의 번호를 입력해 주세요: ");
								categoryNo = sc.nextInt();
							} catch(InputMismatchException e) {
								System.out.println("\n 숫자로 입력해 주세요!");
								sc.next();
								continue;
							} break;
						}
						
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						if(categoryNo == 0) {
							flag1 = false;		// 메뉴 주문 while문 탈출
							continue;			// 메인메뉴로 돌아가도록 설정
						} else if(categoryNo == 1) {							
							while(true) {
								try {	// 문자열 예외처리
									System.out.print("\n → 세트메뉴로 주문 하시겠습니까?(1.예 / 2. 아니오): ");
									num2 = sc.nextInt();
									System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
									System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
								}
//								System.out.println(setList);
							
							} else {
								while(flag3) {
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
											flag3 = false;
											break;
										}
									}
									if(flag3 == true) {
										System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
										System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
									}
								}
								
								if(inputMenuNo == 0) {
									flag1 = false;		// 메뉴 주문 while문 탈출
									System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
									continue;			// 메인메뉴로 돌아가도록 설정
								}
								while(true) {
									try {		// 실수로 문자열을 입력했을 경우의 예외처리
										System.out.print("\n → 선택한 메뉴의 수량을 입력해 주세요: ");
										inputAmount = sc.nextInt();
									} catch(InputMismatchException e) {
										System.out.println("\n 숫자로 입력해 주세요!");
										sc.next();
										continue;
									} break;
									}
								
								if(inputAmount == 0) {
									flag1 = false;		// 메뉴 주문 while문 탈출
									System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
									continue;			// 메인메뉴로 돌아가도록 설정
								}
								/* OrderMenu(장바구니) Insert 메소드 */
								clientController.insertOrderMenu(userNo, inputMenuNo, inputAmount);
								
								/* 사용자가 선택한 모든 메뉴의 총 금액을 totalPrice변수에 누적시켜 결제시 활용 */
//								menuPrice = clientController.selectOrderMenuPrice(inputMenuNo);
//								totalPrice += (inputAmount * menuPrice);
								
								/* 추천카테고리의 메뉴 랜덤 추천 */
								clientController.selectRefMenu(categoryNo, userNo);
							}
						} else if(categoryNo > categoryList.get(categoryList.size()-1).getCode()) {
							System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							continue;
						}else {
							while(flag3) {
								// 기존 메뉴선택 메소드
								List<MenuDTO> menuList = clientController.ShowOrderMenu(categoryNo);
								
								/* 원하는 Menu 선택하도록 하여 장바구니에 Insert */
								while(true) {
									try {	// 문자열 예외처리
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
										flag3 = false;
										break;
									}
								}
								if(flag3 == true) {
									System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
									System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
								}
							}
							
							if(inputMenuNo == 0) {
								flag1 = false;		// 메뉴 주문 while문 탈출
								System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
								continue;			// 메인메뉴로 돌아가도록 설정
							}
							System.out.print("\n → 선택한 메뉴의 수량을 입력해 주세요: ");
							while(true) {
								try {	// 문자열 예외처리
									inputAmount = sc.nextInt();
								} catch(InputMismatchException e) {
									System.out.println("\n 숫자로 입력해 주세요!");
									sc.next();
									continue;
								} break;
								}
							if(inputAmount == 0) {
								flag1 = false;		// 메뉴 주문 while문 탈출
								System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
								continue;			// 메인메뉴로 돌아가도록 설정
							}
							/* OrderMenu(장바구니) Insert 메소드 */
							clientController.insertOrderMenu(userNo, inputMenuNo, inputAmount);
							
//							menuPrice = clientController.selectOrderMenuPrice(inputMenuNo);
							
							/* 추천카테고리의 메뉴 랜덤 추천 */
							clientController.selectRefMenu(categoryNo, userNo);
						}
						
						flag3 = true;
						while(flag3) {
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
							try {	// 문자열 예외처리
								System.out.print("\n → 번호를 선택해 주세요: ");
								num = sc.nextInt();
								System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							} catch(InputMismatchException e) {
								System.out.println("\n 숫자로 입력해 주세요!");
								sc.next();
								continue;
							} break;
							}
						

						if(num == 0) { 	//프로그램 종료
							flag1 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
							flag3 = false;
							continue;									// 맨 처음 메인메뉴로 돌아가도록 설정
						}else if(num == 1) { // 추가 주문하기
							flag3 = false;
							break; // while문의 처음으로 돌아가도록 설정
						} else if (num == 2) { // 장바구니 확인하기
							totalPrice = clientController.selectOrderMenu(setList); // OrderMenu(장바구니) 모두 출력되도록 하는 메소드
							if(totalPrice == 0) {
								continue;
							}

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
								while(true) {
									try {	// 문자열 예외처리
										System.out.print("\n → 번호를 선택해 주세요: ");
										num = sc.nextInt();
										System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
									} catch(InputMismatchException e) {
										System.out.println("\n 숫자로 입력해 주세요!");
										sc.next();
										continue;
									} break;
									}

								if(num == 0) {
									flag2 = false;								// 결제 while문 미실행
									flag1 = false;								// 메뉴 주문 while문 탈출
									flag3 = false;
									break;										// 장바구니 while문 탈출
								} else if(num == 1) { // 추가 주문하기
									flag3 = false;
									break; // 장바구니 while문 빠져나가서 메뉴주문 while문 처음으로 돌아감
								} else if(num == 2) { // 장바구니에 있는 메뉴 수정하기
									clientController.selectOrderMenu(setList);
									int close = clientController.deleteOrderMenu(setList);
									System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
									if(close == 0) {
										flag3 = false;
										break; 										// 장바구니 while문 빠져나가서 메뉴주문 while문 처음으로 돌아감
									} else {
										totalPrice = clientController.selectOrderMenu(setList);
										if(totalPrice == 0) {
											break;
										}
									}
								} else if(num == 3) {
									for(int i = 0; i < setList.size(); i+= 6) {
										int berger = setList.get(i+1);
										int drink = setList.get(i+2);
										int side = setList.get(i+3);
										int amount = setList.get(i+5);
										clientController.insertOrderSetMenu(userNo, berger, amount);
										clientController.insertOrderSetMenu(userNo, drink, amount);
										clientController.insertOrderSetMenu(userNo, side, amount);
									}
									flag = false; // 모든 while문을 빠져나가 최종 결제 화면이 뜨도록 함
									flag1 = false;
									flag3 = false;
									break;
								} else {
									System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");							
								}
							} // 장바구니 while문 종료
							if(totalPrice == 0) {
								continue;
							}
						} else {
							System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");							
						}
					}
				} // 메뉴주문 while문 종료

				} else if(num == 2) { // 비회원 주문하기
					nonMemberMenu.displayMainMenu();
				} else if(num == 3) { // 회원가입 넘어가기
					nonMemberMenu.createUserInfo();
				} else {
					System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");	
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
				while(true) {
					try {	// 문자열 예외처리
						System.out.print("\n → 결제하실 수단을 선택해 주세요: ");
						paymentBy = sc.nextInt();
					} catch(InputMismatchException e) {
						System.out.println("\n 숫자로 입력해 주세요!");
						sc.next();
						continue;
					} break;
				}

					if (paymentBy == 0) {
						System.out.println("\n\n\n\n\n\n\n\n\n\n");

						flag2 = false;
						break; 										// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
					} else if (paymentBy == 1) { // 카드 결제
						System.out.println("\n\n\n\n\n\n\n\n\n\n");
						System.out.println("★★★★      카드 중복 할인 Event!      ★★★★");
						System.out.println("=================================================");
						System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");

						/* 할인 가능한 전체 제휴카드 리스트 출력 */
						List<CardDTO> cardList = clientController.selectCard();
						for(int i = 0; i < 4; i++) {
							CardDTO card = cardList.get(i);
							System.out.println("▶ " + card.getBank() + "의 할인율 :" + card.getDiscount() + "%");
						}
						System.out.println("-------------------------------------------------");
						for(int i = 4; i < 7; i++) {
							CardDTO card = cardList.get(i);
							System.out.println("▶ " + card.getBank() + " : 할인 혜택 적용 불가능");
						}
						System.out.println("=================================================");
						/* 사용자가 결제 할 카드 입력받기 */
						System.out.print("\n →결제하실 카드명을 입력해 주세요: ");
						sc.nextLine();
						String paymentCard = sc.nextLine();
						if(paymentCard.equals("0")) {
							flag2 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
						}
						
						if(paymentCard.length() == 2) {
							paymentCard = paymentCard + "카드";
						} else if(!paymentCard.endsWith("카드")) {
							paymentCard = paymentCard.substring(0, 2) + "카드";
						}
						
						
						for(int i = 0; i < cardList.size(); i++) {
							CardDTO card = cardList.get(i);
							if(card.getBank().equals(paymentCard) && card.getCardable().equals("Y")) {	// cardable 여부로 할인 적용 판정
								cardDiscount = 0.1;
								checkCard = 1;
								break;
							} else if(card.getBank().equals(paymentCard) && card.getCardable().equals("N")) {
								checkCard = 1;
								break;
							}
						}
						
						if(checkCard == 0) {
							System.out.println("\n 저희 매장과 제휴되어 있지 않은 카드 입니다.");
							System.out.println("다시 결제를 시도해 주세요!");
							continue;
						}
						
						/* 세트 메뉴 있을 경우 세트 금액 할인 변수 적용 */
						for(int i = 0; i < setList.size(); i += 6) {
							setAmount += setList.get(i+5);
						}
						/* 할인 금액 모두 변수로 담아서 최종 결제 금액 환산 */
						setDiscount = setAmount * 1000;
						cardCode = clientController.selectCardBy(paymentCard);
						cardDiscount = (totalPrice - setDiscount) * cardDiscount;
						int grade = clientController.selectGrade(gradeNo);
						gradeDiscount = (totalPrice - setDiscount) * (grade * 0.01);
						paymentPrice = (int) (totalPrice - gradeDiscount - cardDiscount - setDiscount);
						
						/* 할인 내역 및 결제 금액 모두 출력 */
						System.out.println("\n\n▶ 장바구니 총 금액: " + format.format(totalPrice) + "원");
						System.out.println("▶ 등급 할인 금액: " + format.format((int)gradeDiscount) + "원");
						System.out.println("▶ 카드사 할인 금액: " + format.format((int)cardDiscount) + "원");
						System.out.println("▶ 세트 할인 금액: " + format.format((int)setDiscount) + "원");
						System.out.println("\n▶ 총 결제 금액은 " + format.format(paymentPrice) + "원 입니다.\n");
						System.out.println("고객님의 " + paymentCard + "로 총" + format.format(paymentPrice) + "원이 결제 되었습니다!");
						System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)\n\n");
						if(gradeNo != 4) {
							orderResultSet.memberGradePoint(userNo, paymentPrice, gradeNo); // 멤버쉽 관련 메소드(누적 포인트 및 등급)
						}
						try {
							th.sleep(1800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("\n\n\n\n\n\n\n\n\n\n");
						clientController.insertOrder(paymentPrice);
						orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, paymentPrice, paymentBy);
						
						
						/* 현금 결제 */
					} else if (paymentBy == 2) {
						for(int i = 0; i < setList.size(); i += 6) {
							setAmount += setList.get(i+5);
						}
						setDiscount = setAmount * 1000;
						int grade = clientController.selectGrade(gradeNo);
						gradeDiscount = (totalPrice - setDiscount) * (grade * 0.01);
						System.out.println("\n\n▶ 장바구니 총 금액: " + format.format(totalPrice) + "원");
						System.out.println("▶ 등급 할인 금액: " + format.format((int)gradeDiscount) + "원");
						System.out.println("▶ 세트 할인 금액: " + format.format((int)setDiscount) + "원");
						paymentPrice = (int) (totalPrice - gradeDiscount - setDiscount);
						System.out.println("\n▶ 총 결제 금액은 " + format.format(paymentPrice) + "원 입니다.");
						while(true) {
							try {	// 문자열 예외처리
								System.out.print("\n\n → 결제하실 금액을 입력해 주세요: ");
								inputPrice = sc.nextInt();
							} catch(InputMismatchException e) {
								System.out.println("\n 숫자로 입력해 주세요!");
								sc.next();
								continue;
							} break;
							}
						clientController.insertOrder(paymentPrice);
						if (inputPrice == paymentPrice) {
							System.out.println("\n결제가 완료 되었습니다!");
							System.out.println(" 주문이 진행되고 있으니 잠시만 기다려 주세요 :)\n\n");
							if(gradeNo != 4) {
								orderResultSet.memberGradePoint(userNo, paymentPrice, gradeNo); // 멤버쉽 관련 메소드(누적 포인트 및 등급)
							}
							try {
								th.sleep(1800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("\n\n\n\n\n\n\n\n\n\n");
							orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, paymentPrice, paymentBy);
						} else if (inputPrice > paymentPrice) {

							System.out.println("\n 거스름돈은 " + format.format((inputPrice - paymentPrice)) + "원 입니다!");
							System.out.println(" 주문이 진행되고 있으니 잠시만 기다려 주세요 :)\n\n");
							if(gradeNo != 4) {
								orderResultSet.memberGradePoint(userNo, paymentPrice, gradeNo); // 멤버쉽 관련 메소드(누적 포인트 및 등급)
							}
							try {
								th.sleep(1800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("\n\n\n\n\n\n\n\n\n\n");
							
							orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, paymentPrice, paymentBy);
							break;
						} else if(inputPrice < paymentPrice) {
							System.out.println("\n\n※ 결제 금액이 " + format.format((paymentPrice - inputPrice)) + "원 부족합니다!");
							System.out.println("   추가 금액 " + format.format((paymentPrice - inputPrice)) + "원을 결제해 주세요!\n\n\n");
							orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, inputPrice, paymentBy);
							System.out.println(">>>>         BurgerHI 장바구니 결제          <<<<");
							System.out.println("=================================================");
							System.out.println("                                                 ");
							System.out.println("                        1                        ");
							System.out.println("                     카  드      　              ");
							System.out.println("                                                 ");
							System.out.println("=================================================");		
							List<CardDTO> cardList = clientController.selectCard();
							/* 사용자가 결제 할 카드 입력받기 */
							System.out.print("\n →결제하실 카드명을 입력해 주세요: ");
							sc.nextLine();
							String paymentCard = sc.nextLine();
							if(paymentCard.equals("0")) {
								flag2 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
							}
							
							if(paymentCard.length() == 2) {
								paymentCard = paymentCard + "카드";
							} else if(!paymentCard.endsWith("카드")) {
								paymentCard = paymentCard.substring(0, 2) + "카드";
							}
							
							
							for(int i = 0; i < cardList.size(); i++) {
								CardDTO card = cardList.get(i);
								if(card.getBank().equals(paymentCard) && card.getCardable().equals("Y")) {	// cardable 여부로 할인 적용 판정
									cardDiscount = 0.1;
									checkCard = 1;
									break;
								} else if(card.getBank().equals(paymentCard) && card.getCardable().equals("N")) {
									checkCard = 1;
									break;
								}
							}
							
							if(checkCard == 0) {
								System.out.println("\n 저희 매장과 제휴되어 있지 않은 카드 입니다.");
								System.out.println("다시 결제를 시도해 주세요!");
								continue;
							}
							System.out.println("\n고객님의 " + paymentCard + "로 총" + format.format((paymentPrice - inputPrice)) + "원이 결제 되었습니다!");
							System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
							try {
								th.sleep(1800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("\n\n\n\n\n\n\n\n\n\n");
							cardCode = clientController.selectCardBy(paymentCard);
							if(gradeNo != 4) {
								orderResultSet.memberGradePoint(userNo, paymentPrice, gradeNo); // 멤버쉽 관련 메소드(누적 포인트 및 등급)
							}
						}
						paymentBy = 1;
						orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, (paymentPrice - inputPrice), paymentBy);
						
					} else if (paymentBy == 3) { // 기프티콘 결제
						int grade = clientController.selectGrade(gradeNo);
						for(int i = 0; i < setList.size(); i += 6) {
						setAmount += setList.get(i+5);
						}
						setDiscount = setAmount * 1000;
						gradeDiscount = (totalPrice - setDiscount) * (grade * 0.01);
						System.out.println("▶ 장바구니 총 금액: " + format.format(totalPrice) + "원");
						System.out.println("▶ 등급 할인 금액: " + format.format((int)gradeDiscount) + "원");
						System.out.println("▶ 세트 할인 금액: " + format.format((int)setDiscount) + "원");
						paymentPrice = (int) (totalPrice - gradeDiscount - setDiscount);
						System.out.println();
						System.out.println("▶ 총 결제 금액은 " + format.format(paymentPrice) + "원 입니다.");
						System.out.println();
						System.out.print("\n → 사용하실 기프티콘 번호를 입력해 주세요: ");
						sc.nextLine();
						String inputGiftNo = sc.nextLine();
//						System.out.println(inputGiftNo);
						int gifticonPrice = clientController.selectGifticonBy(inputGiftNo);
						if(gifticonPrice == 0) {
							System.out.println("기프티콘 번호를 잘못 입력하셨습니다. 다시 입력해 주세요!");
							System.out.println("\n\n\n\n\n\n\n\n\n\n");
							continue;
						}
						clientController.insertOrder(paymentPrice);
//						System.out.println(lastPayment);
//						System.out.println(gifticonPrice);
						
						if (gifticonPrice >= paymentPrice) {
							gifticonPrice = gifticonPrice - paymentPrice;
							System.out.println("\n 결제가 완료 되었습니다! 기프티콘 잔액은 " + format.format(gifticonPrice) + "원 입니다!");
							System.out.println("\n 주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
							try {
								th.sleep(1800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							/* 사용한 기프티콘 잔액 수정 */
							clientController.updateGifticonPrice(inputGiftNo, gifticonPrice);
//							System.out.println(inputGiftNo);
//							System.out.println(gifticonPrice);
							System.out.println("\n\n\n\n\n\n\n\n\n\n");
							orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, paymentPrice, paymentBy);
						} else if(gifticonPrice < paymentPrice){
							System.out.println("\n 기프티콘 사용이 완료 되었습니다!");
							System.out.println("\n 추가 금액 " + format.format((paymentPrice - gifticonPrice)) + "원을 결제해 주세요!\n\n\n");
							orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, gifticonPrice, paymentBy);
							System.out.println(">>>>         BurgerHI 장바구니 결제          <<<<");
							System.out.println("=================================================");
							System.out.println("                       |                        ");
							System.out.println("           1           |            2           ");
							System.out.println("        카  드      　 |         현  금         ");
							System.out.println("                       |                        ");
							System.out.println("=================================================");
							System.out.print("\n → 결제하실 수단을 선택해 주세요: ");
							paymentBy = sc.nextInt();
							
							if(paymentBy == 1) {
								List<CardDTO> cardList = clientController.selectCard();
								/* 사용자가 결제 할 카드 입력받기 */
								System.out.print("\n → 결제하실 카드명을 입력해 주세요: ");
								sc.nextLine();
								String paymentCard = sc.nextLine();
								if(paymentCard.equals("0")) {
									flag2 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
								}
								
								if(paymentCard.length() == 2) {
									paymentCard = paymentCard + "카드";
								} else if(!paymentCard.endsWith("카드")) {
									paymentCard = paymentCard.substring(0, 2) + "카드";
								}
								
								
								for(int i = 0; i < cardList.size(); i++) {
									CardDTO card = cardList.get(i);
									if(card.getBank().equals(paymentCard) && card.getCardable().equals("Y")) {	// cardable 여부로 할인 적용 판정
										cardDiscount = 0.1;
										checkCard = 1;
										break;
									} else if(card.getBank().equals(paymentCard) && card.getCardable().equals("N")) {
										checkCard = 1;
										break;
									}
								}
								
								if(checkCard == 0) {
									System.out.println("\n 저희 매장과 제휴되어 있지 않은 카드 입니다.");
									System.out.println("다시 결제를 시도해 주세요!");
									continue;
								}
								
								System.out.println("고객님의 " + paymentCard + "로 총" + format.format((paymentPrice - gifticonPrice)) + "원이 결제 되었습니다!");
								System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
								try {
									th.sleep(1800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								System.out.println("\n\n\n\n\n\n\n\n\n\n");
								orderResultSet.giftMemberPoint(userNo, paymentPrice, gifticonPrice, gradeNo); // 기프티콘 사용 후 결제 금액 누적
								cardCode = clientController.selectCardBy(paymentCard);
								paymentBy = 1;
								orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, (paymentPrice - gifticonPrice), paymentBy);
								
							}else if (paymentBy == 2) {								 
								 while(true) {
										try {	// 문자열 예외처리
											System.out.print("\n → 결제하실 금액을 입력해 주세요: ");
											inputPrice = sc.nextInt();
										} catch(InputMismatchException e) {
											System.out.println("\n 숫자로 입력해 주세요!");
											sc.next();
											continue;
										} break;
										}
								 
								if (inputPrice == (paymentPrice - gifticonPrice)) {
									System.out.println("결제가 완료 되었습니다! 주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
									try {
										th.sleep(1800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									System.out.println("\n\n\n\n\n\n\n\n\n\n");
									orderResultSet.giftMemberPoint(userNo, paymentPrice, gifticonPrice, gradeNo);// 기프티콘 사용 후 결제 금액 누적
									orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, (paymentPrice - gifticonPrice), paymentBy);
									
								}else if (inputPrice > (paymentPrice - gifticonPrice)) {

									System.out.println(
											"거스름돈은 " + format.format((inputPrice - (paymentPrice - gifticonPrice))) + "원 입니다!");
									System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)");
									try {
										th.sleep(1800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									System.out.println("\n\n\n\n\n\n\n\n\n\n");
									orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, (paymentPrice - gifticonPrice), paymentBy);
									orderResultSet.giftMemberPoint(userNo, paymentPrice, gifticonPrice, gradeNo);// 기프티콘 사용 후 결제 금액 누적
								} else if(inputPrice < (paymentPrice - gifticonPrice)) {
									System.out.println(" 결제 금액이 " + format.format((paymentPrice - inputPrice - gifticonPrice)) + "원 부족합니다!");
									System.out.println("\n 추가 금액 " + format.format((paymentPrice - inputPrice - gifticonPrice)) + "원을 결제해 주세요!\n\n\n");
									orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, inputPrice, paymentBy);
									System.out.println(">>>>         BurgerHI 장바구니 결제          <<<<");
									System.out.println("=================================================");
									System.out.println("                                                 ");
									System.out.println("                        1                        ");
									System.out.println("                     카  드      　              ");
									System.out.println("                                                 ");
									System.out.println("=================================================");		
									List<CardDTO> cardList = clientController.selectCard();
									
									/* 사용자가 결제 할 카드 입력받기 */
									System.out.print("\n → 결제하실 카드명을 입력해 주세요: ");
									sc.nextLine();
									String paymentCard = sc.nextLine();
									if(paymentCard.equals("0")) {
										flag2 = false;								// 프로그램 종료를 누를 경우 메뉴 주문 while문 탈출
									}
									
									if(paymentCard.length() == 2) {
										paymentCard = paymentCard + "카드";
									} else if(!paymentCard.endsWith("카드")) {
										paymentCard = paymentCard.substring(0, 2) + "카드";
									}
									
									
									for(int i = 0; i < cardList.size(); i++) {
										CardDTO card = cardList.get(i);
										if(card.getBank().equals(paymentCard) && card.getCardable().equals("Y")) {	// cardable 여부로 할인 적용 판정
											cardDiscount = 0.1;
											checkCard = 1;
											break;
										} else if(card.getBank().equals(paymentCard) && card.getCardable().equals("N")) {
											checkCard = 1;
											break;
										}
									}
									
									if(checkCard == 0) {
										System.out.println("\n 저희 매장과 제휴되어 있지 않은 카드 입니다.");
										System.out.println("다시 결제를 시도해 주세요!");
										continue;
									}
									
									System.out.println("고객님의 " + paymentCard + "로 총" + format.format((paymentPrice - inputPrice - gifticonPrice)) + "원이 결제 되었습니다!");
									System.out.println("주문이 진행되고 있으니 잠시만 기다려 주세요 :)\n\n");
									if(gradeNo != 4) {
										orderResultSet.memberGradePoint(userNo, paymentPrice, gradeNo); // 멤버쉽 관련 메소드(누적 포인트 및 등급)
									}
									try {
										th.sleep(1800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									System.out.println("\n\n\n\n\n\n\n\n\n\n");
									
									paymentBy = 1;
									cardCode = clientController.selectCardBy(paymentCard);
									orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, (paymentPrice - inputPrice - gifticonPrice), paymentBy);
								}
								
							}
							/* 사용한 기프티콘 잔액 수정 */
							gifticonPrice = 0;
							clientController.updateGifticonPrice(inputGiftNo, gifticonPrice);
						}
					} else {
						System.out.print("\n ※ 번호를 잘못 입력하셨습니다. 다시 입력해 주세요.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");	
						continue;
					}

					/* 최종 모두 확정된 정보를 테이블에 Insert */
					
//					int orderCode = clientController.insertPayment(userNo, totalPrice, gradeNo, cardCode, lastPayment, paymentBy);
//					clientController.gifticonEvent(orderCode);
					clientController.insertSalesAmount(orderCode);
					

					/* 모든 주문이 종료되면 주문번호를 호출하는 메소드 */
					orderResultSet.closeDisplayMainMenu();
					flag2 = false;
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			} 
		} 
	}
}
