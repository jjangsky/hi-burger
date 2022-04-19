package team.burgerhi.kiosk.views;

public class OrderResultSet {
	
	public void displayDmlResult(String code) {
		switch(code) {
			case "updateUserInfoSuccess" : System.out.println("입력하신 회원 정보 수정이 모두 완료 되었습니다!"
											+ "\n\n\n\n\n\n\n\n\n\n\n\n"); break;
			case "updateUserInfoFail" : System.out.println("죄송합니다. KIOSK 오류로 인해 회원 정보 수정이 완료되지 않았습니다."
											+ "\n\n\n\n\n\n\n\n\n\n\n\n"); break;
			case "deleteUserInfoSuccess" : System.out.println("고객님의 회원 탈퇴가 정상적으로 진행 되었습니다. 이용해 주셔서 감사합니다."
											+ "\n\n\n\n\n\n\n\n\n\n\n\n"); break;
			case "deleteUserInfoFail" : System.out.println("죄송합니다. KIOSK 오류로 인해 회원 탈퇴가 완료되지 않았습니다."
											+ "\n\n\n\n\n\n\n\n\n\n\n\n"); break;
			case "orderMenuSuccess": System.out.println("방금 누르신 메뉴가 장바구니에 담겼습니다!"
										+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); break;
			case "orderMenuFail" : System.out.println("죄송합니다. KIOSK 오류로 인해 장바구니에 담지 못했습니다."	
									+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); break;
			case "deleteOrderMenuSuccess" : System.out.println("선택하신 메뉴가 장바구니에서 삭제되었습니다!"
											+ "\n\n\n\n\n\n\n\n\n\n\n\n"); break;
			case "deleteOrderMenuFail" : System.out.println("죄송합니다. KIOSK 오류로 인해 메뉴가 삭제되지 않았습니다."
										+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); break;
		}
	}

	public void closeDisplayMainMenu() {
		/* 담님이 작성 한 종료 쓰레드 작성 메소드 */
		Thread th = new Thread();
		try {
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("주문접수 중");
			System.out.println("▷▷▷▷▷▷▷");
			th.sleep(1500);
			for (int k = 0; k < 30; k++) {
				System.out.println();
			}
			System.out.println("주문접수 중.");
			System.out.println("▷▷▷▷▷▷▷");
			th.sleep(1500);
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
			System.out.println("🍔 메뉴가 준비됐어요! 🍔");
			System.out.println("▶▶▶▶▶▶▶");
			th.sleep(1000);
			th.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  /* finally */
	}
	

}
