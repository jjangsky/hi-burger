package team.burgerhi.kiosk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.OrderMenuDTO;
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
		System.out.println(">>>>   BurgerHI 회원 주문하기   <<<<");
		System.out.println("================================");
		System.out.println();
		System.out.print(">>>> ID를 입력해 주세요: ");
<<<<<<< HEAD
//		sc.nextLine();
=======
>>>>>>> c841090d9ce374e0807cd2d55bf7d313ce756827
		String id = sc.nextLine();
		System.out.print(">>>> PassWord를 입력해 주세요: ");
		String pwd = sc.nextLine();
		System.out.println("\n\n\n");

		/* 회원 정보 dto로 담아서 return */
		List<UserDTO> userList = clientService.loginResult(id, pwd);
<<<<<<< HEAD
		for(UserDTO user : userList) {
			if(user.getId().equals(id) && user.getPwd().equals(pwd)) {
=======
		for (UserDTO user : userList) {
//			if(user.getId().equals(id) && user.getPwd().equals(pwd)) {
>>>>>>> c841090d9ce374e0807cd2d55bf7d313ce756827
				userDTO.setUserNo(user.getUserNo());
				userDTO.setName(user.getName());
				userDTO.setId(user.getId());
				userDTO.setPwd(user.getPwd());
				userDTO.setGradeNo(user.getGradeNo());
				userDTO.setUserPoint(user.getUserPoint());
				userDTO.setPhone(user.getPhone());
<<<<<<< HEAD
				break;
			}
=======
//			}
>>>>>>> c841090d9ce374e0807cd2d55bf7d313ce756827
		}

		/* name에 들어있는 값이 있을 경우 로그인 성공 | 없을 경우(null) 로그인 실패로 간주 */
		if(userDTO.getName() != null) {
			System.out.println(">>>> " + userDTO.getName() + "님 환영합니다!");
			System.out.println("\n\n\n\n\n");
		} else {
			System.out.println("회원정보가 일치하지 않습니다. 다시 입력해 주세요!");
			System.out.println("\n\n\n\n\n");
		}
//		userDTO.getName();
//		System.out.println(userDTO.getName());
		/* 화면단에서 필요한 user 정보들을 보내기 위해 List 형태로 전달 */
		return userDTO;
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
	public List<OrderMenuDTO> selectOrderMenu(int totalPrice) {
		
		/* 장바구니에 Insert했던 내용 출력(회원번호를 조건으로 가져오기) */
		List<OrderMenuDTO> orderMenuList = clientService.selectOrderMenu();
		
		System.out.println(">>>>    BurgerHI 장바구니 확인    <<<<");
		System.out.println("===================================");
		System.out.println();
		
		/* for each문으로 사용자에게 보여줄 내용 출력 */
		for(OrderMenuDTO orderMenu : orderMenuList) {
			MenuDTO menu = new MenuDTO();
				System.out.println("▶ 메뉴번호: " + orderMenu.getMenuCode());
				System.out.println("▶ 메뉴명 : " + menu.getName());
				System.out.println("▶ 주문수량: " + orderMenu.getOrderAmount());
				System.out.println("▶ 금액: " + menu.getPrice() + " * " + orderMenu.getOrderAmount() + " = " + menu.getPrice() * orderMenu.getOrderAmount());
		} System.out.println("▶ 총 금액: " + totalPrice);		// 메뉴가 여러 개 일수도 있어서 총 금액은 반복문이 종료된 후 출력되도록 설정
		
		return orderMenuList;
	}

	/* 장바구니 내용 수정 | OrderMenu 테이블에서 원하지 않는 메뉴 삭제 후 최종 결제할 메뉴만 남기도록 설정 */
	public void deleteOrderMenu() {
		
		System.out.println(">>>>    BurgerHI 장바구니 수정    <<<<");
		System.out.println("===================================");
		System.out.println();
		System.out.print(">>>> 삭제하실 메뉴 번호를 입력해 주세요: ");
		int deleteMenuCode = sc.nextInt();
		
		/* MenuCode를 조건으로 걸어 OrderMenu테이블의 목록을 삭제하는 메소드 */
		int deleteResult = clientService.deleteOrderMenu(deleteMenuCode);
		
		if(deleteResult > 0) {
			code = "deleteOrderMenuSuccess";
		} else {
			code = "deleteOrderMenuFail";
		}
		
		orderResultSet.displayDmlResult(code);
	}

	/* 등급에 따른 할인율 확인 */
	public int selectGrade(int gradeNo) {
		/* 여기서 String -> int로 가공 처리 */
		int gradeDiscount = 0;
		String gradeResult = clientService.selectGrade(gradeNo);
		
		return gradeDiscount;
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
	public void insertOrder(double lastPayment) {
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
	public void insertPayment(int userNo, int totalPrice, int gradeNo, int cardCode, double lastPayment,
			int paymentBy) {
		String payment = "";
		switch(paymentBy) {
		case 1: payment = "카드";
		case 2: payment = "현금";
		case 3: payment = "기프티콘";
		}
		int insertResult = clientService.insertPayment(userNo, totalPrice, gradeNo, cardCode, lastPayment, payment);
	}

}
