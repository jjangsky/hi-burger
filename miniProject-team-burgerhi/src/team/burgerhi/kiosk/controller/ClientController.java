package team.burgerhi.kiosk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.GifticonDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;
import team.burgerhi.kiosk.model.service.ClientService;
import team.burgerhi.kiosk.views.OrderResultSet;

public class ClientController {
	private ClientService clientService = new ClientService();
	private OrderResultSet orderResultSet = new OrderResultSet();
	Scanner sc = new Scanner(System.in);
	String code = "";
	
	/* Login 진행 메소드 */
	public UserDTO loginResult() {
		/* View에 DTO 형태로 넘겨야 하기 때문에 인스턴스 생성 */
		UserDTO userDTO = new UserDTO();
		/* 로그인 화면 출력 및 id와 pwd 입력하도록 유도 */ 
		System.out.println(">>>>            BurgerHI 회원 주문           <<<<");
		System.out.println("=================================================");
		
		System.out.print("\n  →  ID를 입력해 주세요: ");
		String id = sc.nextLine();
		System.out.print("\n  →  PassWord를 입력해 주세요: ");
		String pwd = sc.nextLine();
		System.out.println();
		/* 회원 정보 dto로 담아서 return */
		List<UserDTO> userList = clientService.loginResult(id, pwd);
		for(UserDTO user : userList) {
			if(user.getId().equals(id) && user.getPwd().equals(pwd)) {
				userDTO.setUserNo(user.getUserNo());
				userDTO.setName(user.getName());
				userDTO.setId(user.getId());
				userDTO.setPwd(user.getPwd());
				userDTO.setGradeNo(user.getGradeNo());
				userDTO.setUserPoint(user.getUserPoint());
				userDTO.setPhone(user.getPhone());
				break;
			}
//			}
		}

		/* name에 들어있는 값이 있을 경우 로그인 성공 | 없을 경우(null) 로그인 실패로 간주 */
		if(userDTO.getName() != null) {
			System.out.println(" → " + userDTO.getName() + "님 환영합니다!");
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		} else {
			System.out.println("회원정보가 일치하지 않습니다. 다시 입력해 주세요!");
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
//		userDTO.getName();
//		System.out.println(userDTO.getName());
		/* 화면단에서 필요한 user 정보들을 보내기 위해 List 형태로 전달 */
		return userDTO;
	}
	
	/* 본인 정보 확인 및 수정 탈퇴 메소드 */
	public int userInfoSelect(int userNo) {
		String gradeName = null;
		int MenuPageNum = 0;
		
		while(true) {
			System.out.println(">>>>           BurgerHI 메뉴 선택           <<<<");
			System.out.println("=================================================");
			System.out.println("                       |                       ");
			System.out.println("          1            |           2           ");
			System.out.println("     메뉴 주문하기     |     회원 정보 확인    ");
			System.out.println("                       |                       ");
			System.out.println("=================================================");
			System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
			System.out.print("\n → 번호를 선택해 주세요: ");
			int firstInput = sc.nextInt();
			sc.nextLine();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			if(firstInput == 0) {
				break;			// 프로그램 종료로 메인매뉴로 돌아가도록 설정
			} else if(firstInput == 1) {
				MenuPageNum = 1;
				break;
			} else if(firstInput ==2) {
				System.out.println(">>>>         BurgerHI 회원 정보 조회         <<<<");
				System.out.println("=================================================");
				System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
				System.out.println();
				List<Object> user = clientService.selectUserBy(userNo, gradeName);
				
				System.out.println("▶ 회원번호: " + user.get(0));
				System.out.println("▶ 회원이름: " + user.get(1));
				System.out.println("▶ 회원ID: " + user.get(2));
				System.out.println("▶ 회원PWD: " + user.get(3));
				System.out.println("▶ 등급: " + user.get(4));
				System.out.println("▶ 보유포인트: " + user.get(5));
				System.out.println("▶ 전화번호: " + user.get(6));
				System.out.println();
				System.out.println(" → 회원 정보 수정은 1번을 회원 탈퇴는 2번을 눌러주세요.");
				System.out.println(" → 이전 화면으로 돌아가시려면 3번을 눌러주세요.");
				int num = sc.nextInt();
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				if(num == 0) {
					break;
				} else if(num == 1) {
					System.out.println(">>>>         BurgerHI 회원 정보 수정         <<<<");
					System.out.println("=================================================");
					System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
					System.out.print("\n → 수정하실 회원 pwd를 입력해 주세요: ");
					sc.nextLine();
					String pwd = sc.nextLine();
					if(pwd.equals("0")) {
						break;
					}
					System.out.print(" → 수정하실 전화번호를 입력해 주세요: ");
					String phone = sc.nextLine();
					if(phone.equals("0")) {
						break;
					}
					String formatUserPhone = phoneFormat(phone);
//					System.out.println(formatUserPhone);		// 제대로 적용 되는지 확인 차 출력

					int result = clientService.UpdateUserInfo(userNo, pwd, formatUserPhone);
					
					if(result > 0) {
						code = "updateUserInfoSuccess";
					} else {
						code = "updateUserInfoFail";
					}
					orderResultSet.displayDmlResult(code);
					
				} else if(num == 2) {
					System.out.println(">>>>           BurgerHI 회원 탈퇴            <<<<");
					System.out.println("=================================================");
					System.out.println();
					System.out.println("★ 고객님의 모든 정보가 사라집니다. 모든 정보는 복구되지 않습니다. ★ \n 그래도 진행 하시겠습니까?");
					System.out.println("                       |                       ");
					System.out.println("          1            |           2           ");
					System.out.println("      회원 탈퇴        |         취소          ");
					System.out.println("                       |                       \n");
					System.out.print("\n → 번호를 선택해 주세요: ");
					int inputdelete = sc.nextInt();
					sc.nextLine();
					if(inputdelete == 1) {
						int result = clientService.deleteUserBy(userNo);
						if(result > 0) {
							code = "deleteUserInfoSuccess";
							System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						} else {
							code = "deleteUserInfoFail";
							System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						}
						break;
					}
				}
			}
			
		}
		
		return MenuPageNum;
	}
	
	
	/* 전체 Category를 출력하는 메소드 */
	public void selectAllCategory() {
		
		/* Sevice -> DAO -> DB를 통해 List 형태로 전달 받은 카테고리 */
		List<CategoryDTO> categoryList = clientService.selectAllCategory();
		
		/* for each문 통해서 카테고리 번호 + 카테고리 이름 출력 */
		for(CategoryDTO cate : categoryList) {
			System.out.println("▶ " + cate.getCode() + ". " + cate.getName());
		}
		System.out.println();
	}

	/* 사용자가 선택한 Category의 전체 Menu를 출력하기 위한 메소드 */
	public List<MenuDTO> selectMenuBy(int categoryNo) {
		
		List<MenuDTO> menuList = clientService.selectMenuBy(categoryNo);
				
		return menuList;
	}

	/* 사용자가 선택한 Menu를 장바구니에 Insert 하는 메소드 */
	public void insertOrderMenu(int userNo, int inputMenuNo, int inputAmount) {
		
		int insertOrderMenu = clientService.insertOrderMenu(userNo, inputMenuNo, inputAmount);
		if(insertOrderMenu > 0) {
			code = "orderMenuSuccess";
		} else {
			code = "orderMenuFail";
		}
		orderResultSet.displayDmlResult(code);
		
	}

	/* OrderMenu(장바구니) 테이블의 Insert 되어 있는 내용 모두 출력하는 메소드 */
	public int selectOrderMenu(int totalPrice) {
		
		/* 장바구니에 Insert했던 내용 출력(회원번호를 조건으로 가져오기) */
		List<String> orderMenuList = clientService.selectOrderMenu();
		totalPrice = 0;
		System.out.println(">>>>         BurgerHI 장바구니 확인          <<<<");
		System.out.println("=================================================");
		System.out.println();
		
		/* for문으로 사용자에게 보여줄 내용 출력 */
		for(int i = 0; (i/5) < (orderMenuList.size() / 5); i++) {
			int price = Integer.valueOf(orderMenuList.get(i + 4)); 
			int amount = Integer.valueOf(orderMenuList.get(i + 3));
//			System.out.println(i + "번째" + orderMenuList.get(i));		// 값이 제대로 담겨 출력 되는지 확인
			System.out.println("▶ 주문번호: " + orderMenuList.get(i));
			System.out.println("▶ 메뉴번호: " + orderMenuList.get(i + 1));			
			System.out.println("▶ 메뉴명 : " + orderMenuList.get(i + 2));
			System.out.println("▶ 주문수량: " + orderMenuList.get(i + 3));
			System.out.println("▶ 금액: " + price + " * " + amount + " = " + (price *  amount));
			System.out.println();
			i += 4;
			totalPrice += (price *  amount);
		}
		System.out.println("▶ 총 금액: " + totalPrice);	
		System.out.println("\n\n\n\n");

		return totalPrice;
	}

	/* 장바구니 내용 수정 | OrderMenu 테이블에서 원하지 않는 메뉴 삭제 후 최종 결제할 메뉴만 남기도록 설정 */
	public int deleteOrderMenu() {
		
		System.out.println(">>>>         BurgerHI 장바구니 수정          <<<<");
		System.out.println("=================================================");
		System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
		System.out.print("\n → 삭제하실 메뉴 번호를 입력해 주세요: ");
		int deleteMenuCode = sc.nextInt();
		sc.nextLine();
		
		if(deleteMenuCode != 0) {
			/* MenuCode를 조건으로 걸어 OrderMenu테이블의 목록을 삭제하는 메소드 */
			int deleteResult = clientService.deleteOrderMenu(deleteMenuCode);
			
			if(deleteResult > 0) {
				code = "deleteOrderMenuSuccess";
			} else {
				code = "deleteOrderMenuFail";
			}
			
			orderResultSet.displayDmlResult(code);
		}
		return deleteMenuCode;
		
	}

	/* 등급에 따른 할인율 확인 */
	public int selectGrade(int gradeNo) {
		
		int gradediscount = clientService.selectGrade(gradeNo);
		
		return gradediscount;
	}

	/* 카드 할인 가능한 전체 제휴 카드 리스트 출력 */
	public List<CardDTO> selectCard() {
		
		List<CardDTO> cardList = clientService.selectCard();
		
		return cardList;
	}

	/* Payment 테이블에 필요한 카드 번호 Select */
	public int selectCardBy(String paymentCard) {
		
		List<CardDTO> cardList = clientService.selectAllCard();
		int cardCode = 0;
		for(CardDTO card : cardList) {
			if(card.getBank().equals(paymentCard)) {
				cardCode = card.getCode();
				break;
			}
		}
		
		return cardCode;
	}
	
	/* 결제까지 완료 된 확정 정보를 Order 테이블에 Insert */
	public void insertOrder(int lastPayment) {
		Date now = new Date();
		SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd");
		String date = smdf.format(now);
		
		int insertResult = clientService.insertOrder(date, lastPayment);
		
		if(insertResult > 0) {
			code = "insertOrderSuccess";
		} else {
			code = "insertOrderFail";
		}
		
		orderResultSet.displayDmlResult(code);
		
	}
	
	/* 결제까지 완료 된 확정 정보를 Payment 테이블에 Insert */
	public int insertPayment(int userNo, int totalPrice, int gradeNo, int cardCode, double lastPayment,
			int paymentBy) {
		String payment = "";
		switch(paymentBy) {
		case 1: payment = "카드"; break;
		case 2: payment = "현금"; break;
		case 3: payment = "기프티콘"; break;
		}
		
		int orderCode = clientService.selectLastOrderCode();
		
		int insertResult = clientService.insertPayment(orderCode, userNo, totalPrice, gradeNo, cardCode, lastPayment, payment);
		
		
		return orderCode;
	}

	/* 사용자가 입력 한 기프티콘 번호를 입력받아 기프티콘 테이블에서 Select */
	public int selectGifticonBy(String inputGiftNo) {
		int gifticonPrice = 0;
		
		if(inputGiftNo != null) {
			inputGiftNo = inputGiftNo.replaceAll("[^0-9]", "");
			inputGiftNo = inputGiftNo.substring(0, 6) + "-" + inputGiftNo.substring(6, 10) + "-" + inputGiftNo.substring(10);
		}
		List<GifticonDTO> gifticonList = clientService.selectGifticonBy(inputGiftNo);
//		System.out.println(inputGiftNo);		// 기프티콘 번호 정상적으로 입력되는지 확인 출력문
		for(GifticonDTO gif: gifticonList) {
			if(gif.getNo().equals(inputGiftNo)) {
				gifticonPrice = gif.getPrice();
			} else {
				
			}
		}
		return gifticonPrice;
	}

	/* 사용한 기프티콘의 경우 사용 후 금액 테이블에 Update */
	public void updateGifticonPrice(String inputGiftNo, int gifticonPrice) {
		
		if(inputGiftNo != null) {
			inputGiftNo = inputGiftNo.replaceAll("[^0-9]", "");
			inputGiftNo = inputGiftNo.substring(0, 6) + "-" + inputGiftNo.substring(6, 10) + "-" + inputGiftNo.substring(10);
		}
		int result = clientService.updateGifticonPrice(inputGiftNo, gifticonPrice);
//		System.out.println(result);
//		System.out.println(gifticonPrice);
	}

	/* 장바구니에서 결제한 메뉴의 경우 전체 Delete */
	public void deleteAllOrderMenu() {
		
		int result = clientService.deleteAllOrderMenu();
//		System.out.println("장바구니 삭제 완료"); 	// 장바구니 삭제 확인
	}

	/* 사용자가 선택한 메뉴의 단가 Select */
	public int selectOrderMenuPrice(int inputMenuNo) {
		
		int menuPrice = clientService.selectOrderMenuPrice(inputMenuNo);
		
		return menuPrice;
	}

	/* 비회원 회원가입 절차, View 에서 받은 Scanner 값을 DTO에 담아서 Service 계층으로 전송 */
	public int createUserInfo(String name, String userId, String userPwd, String userPhone) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(name);
		userDTO.setId(userId);
		userDTO.setPwd(userPwd);
		userDTO.setPhone(userPhone);
		
		int result = clientService.creatUserInfo(userDTO);
		
		return result;
	}

	/* 비회원의 경우 회원번호와 중복되지 않도록 시퀀스 공유하여 Insert + 비회원 번호 Select */
	public int insertNonMemberUser(int gradeNo) {
		
		int result = clientService.insertNonMemberUser(gradeNo);
//		System.out.println("NullPointException Test2");		// 오류 여부 확인 구문
		
		int userNo = clientService.selectNonMemberUserNo();
//		System.out.println("NullPointException Test3");		// Service getConnection 오류 찾음
		
		return userNo;
	}

	/* 비회원의 등급(nonMember|할인율 0) 번호 Select */
	public int selectNonMemberGradeNo() {
		
		int gradeNo = clientService.selectNonMemberGradeNo();
		
		return gradeNo;
	}

	public void insertSalesAmount(int orderCode) {
	      List<String> orderMenuList = clientService.selectOrderMenu();
	      
	      int menuCode = 0;
	      int amount = 0;
	      int totalPrice = 0;
	      for(int i = 0; i < orderMenuList.size(); i+=5 ) {
	         int price = Integer.valueOf(orderMenuList.get(i + 4)); 
	         amount = Integer.valueOf(orderMenuList.get(i + 3));
	         menuCode = Integer.valueOf(orderMenuList.get(i + 1));
	         totalPrice = price * amount;
	         int result = clientService.insertSalesAmount(orderCode, menuCode, amount, totalPrice);
	      }
	      
	   }

	/* 전화번호가 일정한 format으로 들어갈 수 있도록 하는 메소드 */
	public String phoneFormat(String userPhone) {
		if(userPhone != null) {
			userPhone = userPhone.replaceAll("[^0-9]", "");
			if(userPhone.length() == 11) {
				userPhone = userPhone.substring(0, 3) + "-" + userPhone.substring(3, 7) + "-" + userPhone.substring(7);
			} else if(userPhone.length() == 8){
				userPhone = userPhone.substring(0, 4) + "-" + userPhone.substring(4);
			} else {
				if(userPhone.startsWith("02")) {
					userPhone = userPhone.substring(0, 2) + "-" + userPhone.substring(2, 5) + "-" + userPhone.substring(5);
				} else {
					userPhone = userPhone.substring(0, 3) + "-" + userPhone.substring(3, 6) + "-" + userPhone.substring(7);
				}
			}
		}
		return userPhone;
	}

	/* 주문번호가 10번인 고객님의 경우 기프티콘 증정하는 이벤트 */
	public void gifticonEvent(int orderCode) {
		
		/* if문 사용(orderCode == 10) */
		
		
		/* clientService의 Insert 메소드 사용 */
		
		
		/* List<GifticonDTO>로 Select 메소드 사용 */
		
		
	}

}
