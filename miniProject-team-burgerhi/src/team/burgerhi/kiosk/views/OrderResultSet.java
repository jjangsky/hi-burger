package team.burgerhi.kiosk.views;

import java.text.DecimalFormat;
import java.util.Scanner;

import team.burgerhi.kiosk.controller.ClientController;

public class OrderResultSet {
	Scanner sc = new Scanner(System.in);
	DecimalFormat format = new DecimalFormat("###,###");
	public void displayDmlResult(String code) {
		switch(code) {
			case "updateUserInfoSuccess" : System.out.println("\n\n입력하신 회원 정보 수정이 모두 완료 되었습니다!"
											+ "\n\n\n\n\n\n\n"); break;
			case "updateUserInfoFail" : System.out.println("\n\n죄송합니다. KIOSK 오류로 인해 회원 정보 수정이 완료되지 않았습니다."
											+ "\n\n\n\n\n\n\n"); break;
			case "deleteUserInfoSuccess" : System.out.println("\n\n고객님의 회원 탈퇴가 정상적으로 진행 되었습니다. 이용해 주셔서 감사합니다."
											+ "\n\n\n\n\n\n\n"); break;
			case "deleteUserInfoFail" : System.out.println("\n\n죄송합니다. KIOSK 오류로 인해 회원 탈퇴가 완료되지 않았습니다."
											+ "\n\n\n\n\n\n\n"); break;
			case "orderMenuSuccess": System.out.println("\n\n방금 누르신 메뉴가 장바구니에 담겼습니다!"
										+ "\n\n\n\n\n\n\n\n\n"); break;
			case "orderMenuFail" : System.out.println("\n\n죄송합니다. KIOSK 오류로 인해 장바구니에 담지 못했습니다."	
									+ "\n\n\n\n\n\n\n\n\n\n\n"); break;
			case "deleteOrderMenuSuccess" : System.out.println("\n\n선택하신 메뉴가 장바구니에서 삭제되었습니다!"
											+ "\n\n\n\n\n\n\n"); break;
			case "deleteOrderMenuFail" : System.out.println("\n\n죄송합니다. KIOSK 오류로 인해 메뉴가 삭제되지 않았습니다."
										+ "\n\n\n\n\n\n\n\n\n"); break;
		}
	}

	public void closeDisplayMainMenu() {
		/* 담님이 작성 한 종료 쓰레드 작성 메소드 */
		Thread th = new Thread();
		try {
			th.sleep(2500);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("주문접수 중");
			System.out.println("▷▷▷▷▷▷▷");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("주문접수 중.");
			System.out.println("▷▷▷▷▷▷▷");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("주문접수 중..");
			System.out.println("▶▷▷▷▷▷▷");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("메뉴가 준비중이에요!");
			System.out.println("▶▶▷▷▷▷▷");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("메뉴가 준비중이에요!");
			System.out.println("▶▶▶▷▷▷▷");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("메뉴가 준비중이에요!");
			System.out.println("▶▶▶▶▷▷▷");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("메뉴 포장중.");
			System.out.println("▶▶▶▶▶▷▷");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("메뉴 포장중..");
			System.out.println("▶▶▶▶▶▶▷");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("메뉴 포장중...");
			System.out.println("▶▶▶▶▶▶▶");
			th.sleep(1000);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("메뉴가 준비됐어요! 🍔");
			System.out.println("▶▶▶▶▶▶▶");
			th.sleep(1000);
			th.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  /* finally */
	}
	
	/* 멤버쉽 누적 및 등급 조절 메소드 */
	public void memberGradePoint(int userNo, int lastPayment) {
		ClientController clientController = new ClientController();
		
		int selectPoint = clientController.selectMemberPoint(userNo);
		selectPoint = selectPoint + lastPayment;
		System.out.println("\n\n현재 " + format.format(lastPayment) + "Point 적립되셨습니다." );
		System.out.println("고객님의 현재 누적된 멤버쉽은 " + format.format(selectPoint) + "Point 입니다.");
		/* 멤버쉽 포인트 변경 */
	 int memberPoint = clientController.updateMemberPoint(userNo, selectPoint);
		/* 멤버쉽 등급 변경 */
		if(selectPoint >= 300000) { /* 골드 등급으로 변경 */
			clientController.updateGoldGrade(userNo);
			System.out.println("\n\n회원님의 현재 등급은 Gold 입니다.");
		} else if(selectPoint >= 100000) { /* 실버 등급으로 변경 */
			clientController.updateSilverGrade(userNo);
			System.out.println("\n\n회원님의 현재 등급은 Silver 입니다.");
			System.out.println("다음 등급까지" + format.format((300000 - selectPoint)) + "Point 남았습니다. ^_^");
		}else {
			clientController.updateFamilyGrade(userNo); /* 패밀리 등급으로 변경 */
			System.out.println("\n\n회원님의 현재 등급은 Family 입니다.");
			System.out.println("다음 등급까지 " + format.format((100000 - selectPoint)) + "Point 남았습니다. ^_^");
		}
		
	}
	
	/* 기프티콘 결제 후 금액 멤버쉽 누적 및 등급 조절 메소드 */
	public void giftMemberPoint(int userNo, int lastPayment, int gifticonPrice) {
		ClientController clientController = new ClientController();
		
		int afterPayment = lastPayment - gifticonPrice;
		int selectPoint = clientController.selectMemberPoint(userNo);
		selectPoint = selectPoint + afterPayment;
		System.out.println("\n\n현재 " + format.format(afterPayment) + "Point 적립되셨습니다." );
		System.out.println("고객님의 현재 누적된 멤버쉽은 " + format.format(selectPoint) + "Point 입니다.");
		/* 멤버쉽 포인트 변경 */
		int memberPoint = clientController.updateMemberPoint(userNo, selectPoint);
		/* 멤버쉽 등급 변경 */
		if(selectPoint >= 300000) { /* 골드 등급으로 변경 */
			clientController.updateGoldGrade(userNo);
			System.out.println("\n\n회원님의 현재 등급은 Gold 입니다.");
		} else if(selectPoint >= 100000) { /* 실버 등급으로 변경 */
			clientController.updateSilverGrade(userNo);
			System.out.println("\n\n회원님의 현재 등급은 Silver 입니다.");
			System.out.println("다음 등급까지" + format.format((300000 - selectPoint)) + "Point 남았습니다. ^_^");
		}else {
			clientController.updateFamilyGrade(userNo); /* 패밀리 등급으로 변경 */
			System.out.println("\n\n회원님의 현재 등급은 Family 입니다.");
			System.out.println("다음 등급까지 " + format.format((100000 - selectPoint)) + "Point 남았습니다. ^_^");
		}
		
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
