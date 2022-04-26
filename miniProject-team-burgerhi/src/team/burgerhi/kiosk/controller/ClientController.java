package team.burgerhi.kiosk.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
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
	DecimalFormat format = new DecimalFormat("###,###");
	List<Integer> list = new ArrayList<>();
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
		int firstInput  = 0;
		int inputdelete = 0;
		int num = 0;
		
		
		while(true) {
			System.out.println(">>>>           BurgerHI 메뉴 선택           <<<<");
			System.out.println("=================================================");
			System.out.println("                       |                       ");
			System.out.println("          1            |           2           ");
			System.out.println("     메뉴 주문하기     |     회원 정보 확인    ");
			System.out.println("                       |                       ");
			System.out.println("=================================================");
			System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
			while(true) {
				try {	// 문자열 예외처리
					System.out.print("\n → 번호를 선택해 주세요: ");
					firstInput = sc.nextInt();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				} catch(InputMismatchException e) {
					System.out.println("\n 숫자로 입력해 주세요!");
					sc.next();
					continue;
				} break;
				}
			
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
				System.out.println("▶ 보유포인트: " + format.format(user.get(5)));
				System.out.println("▶ 전화번호: " + user.get(6));
				System.out.println();
				
				while(true) {
					try {	// 문자열 예외처리
						System.out.println(" → 회원 정보 수정은 1번을 회원 탈퇴는 2번을 눌러주세요.");
						System.out.println(" → 이전 화면으로 돌아가시려면 3번을 눌러주세요.");
						num = sc.nextInt();
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					} catch(InputMismatchException e) {
						System.out.println("\n 숫자로 입력해 주세요!");
						sc.next();
						continue;
					} break;
					}
				
				
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
					
					while(true) {
						try {	// 문자열 예외처리
							System.out.print("\n → 번호를 선택해 주세요: ");
							inputdelete = sc.nextInt();
							sc.nextLine();
						} catch(InputMismatchException e) {
							System.out.println("\n 숫자로 입력해 주세요!");
							sc.next();
							continue;
						} break;
						}
					
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
	public List<CategoryDTO> selectAllCategory() {
		/* Sevice -> DAO -> DB를 통해 List 형태로 전달 받은 카테고리 */
		List<CategoryDTO> categoryList = clientService.selectAllCategory();
		
		/* for each문 통해서 카테고리 번호 + 카테고리 이름 출력 */
		for(CategoryDTO cate : categoryList) {
			System.out.println("▶ " + cate.getCode() + ". " + cate.getName());
		}
		System.out.println();
		
		return categoryList;
	}

	/* 사용자가 선택한 Category의 전체 Menu를 출력하기 위한 메소드 */
	public List<MenuDTO> selectMenuBy(int categoryNo) {
		List<MenuDTO> menuList = clientService.selectMenuBy(categoryNo);
		
		return menuList;
	}
	
	/* 회원 메뉴 보여지고 선택받기 */
	public List<MenuDTO> ShowOrderMenu(int categoryNo) {
		/* 사용자가 선택한 Category의 전체 Menu 출력 */
		System.out.println(">>>>           BurgerHI 메뉴 선택            <<<<");
		System.out.println("=================================================");
		System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
		System.out.println();
		List<MenuDTO> menuList = selectMenuBy(categoryNo); // Menu 출력 메소드
		for (MenuDTO menu : menuList) {
			System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "  "
					+ format.format(menu.getPrice()) + "원\n     " + menu.getExplain());
		}		
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
	public int selectOrderMenu(List<Integer> setList) {
		String setMenu = null;
		int totalPrice = 0;
		int setAmount = 0;
		/* 장바구니에 Insert했던 내용 출력(회원번호를 조건으로 가져오기) */
		List<String> orderMenuList = clientService.selectOrderMenu();
		
		if(orderMenuList.size() > 0 || setList.size() > 0) {
			System.out.println(">>>>         BurgerHI 장바구니 확인          <<<<");
			System.out.println("=================================================");
			System.out.println();
			
			/* for문으로 사용자에게 보여줄 내용 출력 */
			for(int i = 0; i < orderMenuList.size(); i += 5) {
				int amount = Integer.valueOf(orderMenuList.get(i + 3));
				int price = Integer.valueOf(orderMenuList.get(i + 4)); 
//			System.out.println(i + "번째" + orderMenuList.get(i));		// 값이 제대로 담겨 출력 되는지 확인
//			System.out.println("▶ 주문번호: " + orderMenuList.get(i));
				System.out.println("▶ 메뉴번호: " + orderMenuList.get(i + 1));			
				System.out.println("▶ 메뉴명  : " + orderMenuList.get(i + 2));
				System.out.println("▶ 주문수량: " + orderMenuList.get(i + 3));
				System.out.println("▶ 금액    : " + format.format(price) + " * " + amount + " = " + format.format((price *  amount)));
				System.out.println();
				totalPrice += (price *  amount);
			}
			
			for(int i = 0; i < setList.size(); i += 5) {
				System.out.println("▶ 메뉴번호  : " + setList.get(i));
				List<MenuDTO> menuList = clientService.selectMenuBy(1);
				for(int j = 0; j < menuList.size(); j++) {
					if(setList.get(i) == menuList.get(j).getMenuCode()) {
						setMenu = menuList.get(j).getName();
					}
				}
				System.out.println("▶ 메뉴명    : " + setMenu + " 세트");
				List<MenuDTO> drinkList = clientService.selectMenuBy(2);
				for(int j = 0; j < drinkList.size(); j++) {
					if(setList.get(i+1) == drinkList.get(j).getMenuCode()) {
						setMenu = drinkList.get(j).getName();
					}
				}
				System.out.println("▶ 세트음료  : " + setMenu);
				List<MenuDTO> sideList = clientService.selectMenuBy(3);
				for(int j = 0; j < sideList.size(); j++) {
					if(setList.get(i+2) == sideList.get(j).getMenuCode()) {
						setMenu = sideList.get(j).getName();
					}
				}
				setAmount += setList.get(i+4);
				System.out.println("▶ 세트사이드: " + setMenu);
				System.out.println("▶ 주문수량  : " + setList.get(i+4));
				System.out.println("▶ 금액      : " + format.format(setList.get(i+3)) + " * " + setAmount + " = " + format.format((setList.get(i+3) * setAmount)));
				totalPrice += setList.get(i+3) * setAmount;
			}
			
			
//		System.out.println("set값 제대로 들어갔는지? " + set);
			if(setList.size() > 0) {
				int setSalePrice = setAmount * 1000;
				System.out.println("\n\n▶ 세트 할인 금액: " + format.format(setSalePrice));
				System.out.println("▶ 총 금액: " + format.format(totalPrice) + " - " + format.format(setSalePrice) + " = "  +format.format((totalPrice - setSalePrice)));
				System.out.println("\n\n\n\n\n\n\n\n\n");
			} else {
				System.out.println("\n\n▶ 총 금액: " + format.format(totalPrice));
				System.out.println("\n\n\n\n\n\n\n\n\n");
			}
		} else {
			System.out.println(">>>>         BurgerHI 장바구니 확인          <<<<");
			System.out.println("=================================================");
			System.out.println("\n                  🍔 텅 🍔                     ");
			System.out.println("\n            장바구니가 텅비었어요.             \n");
			System.out.println("=================================================");
			System.out.println("\n → 주문하러 가볼까요?");
			totalPrice = 0;
		}
		return totalPrice;
	}

	/* 장바구니 내용 수정 | OrderMenu 테이블에서 원하지 않는 메뉴 삭제 후 최종 결제할 메뉴만 남기도록 설정 */
	public int deleteOrderMenu(List<Integer> setList) {
		int deleteMenuCode = 0;
		int deleteResult = 0;
		
		System.out.println(">>>>         BurgerHI 장바구니 수정          <<<<");
		System.out.println("=================================================");
		System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
		while(true) {
			try {	// 문자열 예외처리
				System.out.print("\n → 삭제하실 메뉴 번호를 입력해 주세요: ");
				
				deleteMenuCode = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("\n 숫자로 입력해 주세요!");
				sc.next();
				continue;
			} break;
		}
		
		for(int i = 0; i < setList.size(); i += 5) {
			if(deleteMenuCode == setList.get(i)) {
				System.out.print("\n\n → 세트 메뉴를 삭제하시겠습니까?(1.예 / 2. 아니오):");
				int num = sc.nextInt();
				if(num == 1) {
//					deleteResult = clientService.deleteOrderMenuAmount(setList.get(i));
//					deleteResult = clientService.deleteOrderMenuAmount(setList.get(i+1));
//					deleteResult = clientService.deleteOrderMenuAmount(setList.get(i+2));
					setList.remove(i);setList.remove(i);setList.remove(i);setList.remove(i);setList.remove(i);
					deleteResult = 1;
					break;
				} 
			}
		}
		
		if(deleteMenuCode != 0 && deleteResult == 0) {
			/* MenuCode를 조건으로 걸어 OrderMenu테이블의 목록을 삭제하는 메소드 */
			deleteResult = clientService.deleteOrderMenu(deleteMenuCode);
		}
		
		if(deleteResult > 0) {
			code = "deleteOrderMenuSuccess";
		} else {
			code = "deleteOrderMenuFail";
		}
		orderResultSet.displayDmlResult(code);
		
		return deleteMenuCode;
	}

	/* 등급에 따른 할인율 확인 */
	public int selectGrade(int gradeNo){
		
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
		
		clientService.insertPayment(orderCode, userNo, totalPrice, gradeNo, cardCode, lastPayment, payment);
		
		
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
		clientService.updateGifticonPrice(inputGiftNo, gifticonPrice);
//		System.out.println(result);
//		System.out.println(gifticonPrice);
	}

	/* 장바구니에서 결제한 메뉴의 경우 전체 Delete */
	public void deleteAllOrderMenu() {
		
		clientService.deleteAllOrderMenu();
//		System.out.println("장바구니 삭제 완료"); 	// 장바구니 삭제 확인
	}

	/* 사용자가 선택한 메뉴의 단가 Select */
//	public int selectOrderMenuPrice(int inputMenuNo) {
//		
//		int menuPrice = clientService.selectOrderMenuPrice(inputMenuNo);
//		
//		return menuPrice;
//	}

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
		
		clientService.insertNonMemberUser(gradeNo);
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
	         clientService.insertSalesAmount(orderCode, menuCode, amount, totalPrice);
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

		/* clientService의 Insert 메소드 사용 */
		clientService.insertGifticonEvent();

		/* List<GifticonDTO>로 Select 메소드 사용 */
		List<GifticonDTO> gifticonList = clientService.selectlastgifticon();

		/* if문 사용(orderCode == 10) */
		for (int i = 0; i < orderCode; i += 10) {
			if (orderCode == i) {
				System.out.println("★★★★    BergerHI의 특별한 Event!    ★★★★");
				System.out.println("=================================================");
				System.out.println("\n      BergerHI에서 준비한 특별한 Event!!!! ");
				System.out.println("\n    " + i + "번째 고객님께 드리는 선물🎁");
				System.out.println("\n → 기프티콘 번호:" + gifticonList.get(1).getNo());
				System.out.println("\n → 기프티콘 금액:" + format.format(gifticonList.get(1).getPrice()));
				System.out.println("\n 다음 주문부터 사용이 가능하며, 현금으로 교환은 어렵습니다.");
				System.out.println("  기프티콘 금액은 분할로 사용이 가능하며, 유효기간은 1년 입니다.");
				System.out.println("\n\n BergerHI를 사랑해 주셔서 감사합니다. \n 좋은 하루 보내세요♥ ");
			}
		}
	}
	/* 추천카테고리의 메뉴 랜덤 추천 */
	public int selectRefMenu(int categoryNo, int userNo) {
		int category = clientService.selectCategoryBy(categoryNo);
		int refPrice = 0;
		int refNum = 0;
		
		List<MenuDTO> randomMenu = clientService.selectMenuBy(category);
		int i = (int)(Math.random() * randomMenu.size());
//		System.out.println(randomMenu.size());			// 랜덤기능 확인 출력문
//		System.out.println(i);
		System.out.println("\n\n\n ******** BergerHI가 추천하는 함께하면 좋을 메뉴 ********");
		System.out.println("\n▶ " + randomMenu.get(i).getMenuCode() + ". " + randomMenu.get(i).getName() + "  "
				+ format.format(randomMenu.get(i).getPrice()) + "원\n     " + randomMenu.get(i).getExplain());
		
		while(true) {
			try {		// 실수로 문자열을 입력했을 경우의 예외처리
				System.out.print("\n → 장바구니에 함께 담아드릴까요? (1.예 / 2.아니오): ");
				refNum = sc.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("\n 숫자로 입력해 주세요!");
				sc.next();
				continue;
			} break;
			}
		
		
		int refAmount = 0;
		if(refNum == 1) {
			System.out.print("\n\n 탁월한 선택이세요! 수량은 몇 개 담아드릴까요? ");
			refAmount = sc.nextInt();
			insertOrderMenu(userNo, randomMenu.get(i).getMenuCode(), refAmount);
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		refPrice = (refAmount * randomMenu.get(i).getPrice());
		sc.nextLine();
		return refPrice;
	}
	
/* 멤버쉽 포인트 변경 */
	public int updateMemberPoint(int userNo, int selectPoint) {
		
		int memberPoint = clientService.updateMemberPoint(userNo, selectPoint);
		
		return memberPoint;
	}
/* 멤버쉽 포인트 조회 */
	public int selectMemberPoint(int userNo) {
		
		int selectPoint = clientService.selectMemberPoint(userNo);
		
		return selectPoint;
	}
	/* 골드 등급으로 변경 */
	public void updateGoldGrade(int userNo) {
		clientService.updateGoldGrade(userNo);
	}
	/* 실버 등급으로 변경 */
	public void updateSilverGrade(int userNo) {
		clientService.updateSilverGrade(userNo);
	}
	/* 패밀리 등급으로 변경 */
	public void updateFamilyGrade(int userNo) {
		clientService.updateFamilyGrade(userNo);
		
	}

	/* 세트메뉴 선택 가능한 메소드 */
	public List<Integer> ShowSetMenu(int userNo) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(">>>>           BurgerHI 세트 선택            <<<<");
		System.out.println("=================================================");
		System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
		System.out.println();
		List<MenuDTO> burgerList = clientService.selectAllBurger();
		List<MenuDTO> drinkList = clientService.selectAllDrink();
		List<MenuDTO> sideList = clientService.selectAllSide();
		int selectBurgerPrice = 0;
		int selectDrinkPrice = 0;
		int selectSidePrice = 0;
		int selectBurgerCode = 0;
		int selectDrinkCode = 0;
		int selectSideCode = 0;
		int setPrice = 0;
		for (MenuDTO menu : burgerList) {
			System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "세트  "
					+ (menu.getPrice()+3000)+ "원  " + " (세트할인 1000)\n" +  " 세트구성은 " + menu.getName() + ", 코카콜라, 감자튀김입니다.");
		}
		
		System.out.print("\n → 원하시는 세트메뉴의 번호를 입력해 주세요: ");
		int inputSetNo = sc.nextInt();
		System.out.print("\n → 선택한 세트메뉴의 수량을 입력해 주세요: ");
		int inputAmount = sc.nextInt();
		System.out.print("\n → 현재 구성으로 주문하시겠습니까?(1.예 / 2.아니오): ");
		int inputSetOrder = sc.nextInt();
		if(inputSetOrder == 1) {
			
			/* 장바구니에 insert */
//			clientService.insertOrderMenu(userNo, inputSetNo, inputAmount);	// 버거 insert
//			clientService.insertDrinkMenu(userNo, inputAmount);				// 음료 insert
//			clientService.insertSetMenu(userNo, inputAmount);				// 사이드 insert
			
			for(int i = 0; i < burgerList.size(); i++) {
				if(inputSetNo == burgerList.get(i).getMenuCode()) {
					selectBurgerPrice = burgerList.get(i).getPrice();
				}
			}
			selectDrinkPrice = drinkList.get(0).getPrice();
			selectSidePrice = sideList.get(0).getPrice();
			int a = selectBurgerPrice;
			int b = selectDrinkPrice;
			int c = selectSidePrice;
			setPrice = a+b+c;
			
			list.add(inputSetNo);
			list.add(4);
			list.add(6);
			list.add(setPrice);
			list.add(inputAmount);
		} else if(inputSetOrder == 2){
			/* 구성을 변경하는 메소드 */
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println(">>>>           BurgerHI 음료 변경            <<<<");
			System.out.println("=================================================");
			for (MenuDTO menu : drinkList) {
				System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "  "
						+ menu.getPrice() + "원\n     " + menu.getExplain());
			}
			System.out.print("\n → 변경 할 음료를 골라주세요: ");
			int inputDrinkNo = sc.nextInt();
			
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println(">>>>           BurgerHI 사이드 변경            <<<<");
			System.out.println("=================================================");
			for (MenuDTO menu : sideList) {
				System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "  "
						+ menu.getPrice() + "원\n     " + menu.getExplain());
			}
			System.out.print("\n → 변경 할 사이드를 골라주세요: ");
			int inputsideNo = sc.nextInt();
			/* 장바구니에 insert */
//			clientService.insertOrderMenu(userNo, inputSetNo, inputAmount);			// 버거 insert
//			clientService.insertOrderMenu(userNo, inputDrinkNo, inputAmount);		// 음료 insert
//			clientService.insertOrderMenu(userNo, inputsideNo, inputAmount);		// 사이드 insert
			
			for(int i = 0; i < burgerList.size(); i++) {
				if(inputSetNo == burgerList.get(i).getMenuCode()) {
					selectBurgerPrice = burgerList.get(i).getPrice();
					selectBurgerCode = burgerList.get(i).getMenuCode();
				}
			}
			for(int i = 0; i < drinkList.size(); i++) {
				if(inputDrinkNo == drinkList.get(i).getMenuCode()) {
					selectDrinkPrice = drinkList.get(i).getPrice();
					selectDrinkCode = drinkList.get(i).getMenuCode();
				}
			}
			for(int i = 0; i < sideList.size(); i++) {
				if(inputsideNo == sideList.get(i).getMenuCode()) {
					selectSidePrice = sideList.get(i).getPrice();
					selectSideCode = sideList.get(i).getMenuCode();
				}
			}

			int a = selectBurgerPrice;
			int b = selectDrinkPrice;
			int c = selectSidePrice;
			setPrice = a+b+c;
			
			list.add(selectBurgerCode);
			list.add(selectDrinkCode);
			list.add(selectSideCode);
			list.add(setPrice);
			list.add(inputAmount);
		}
		return list;
	}

	public int insertOrderSetMenu(int userNo, int menuNo, int amount) {
		int insertOrderMenu = clientService.insertOrderMenu(userNo, menuNo, amount);
		
		return insertOrderMenu;
		
	}

}
