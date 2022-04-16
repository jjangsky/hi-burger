package team.burgerhi.kiosk.views;

public class OrderResultSet {
	
	public void displayDmlResult(String code) {
		switch(code) {
			case "orderMenuSuccess": System.out.println("방금 누르신 메뉴가 장바구니에 담겼습니다!"); break;
			case "orderMenuFail" : System.out.println("죄송합니다. KIOSK 오류로 인해 장바구니에 담지 못했습니다."); break;
			case "deleteOrderMenuSuccess" : System.out.println("선택하신 메뉴가 장바구니에서 삭제되었습니다!"); break;
			case "deleteOrderMenuFail" : System.out.println("죄송합니다. KIOSK 오류로 인해 메뉴가 삭제되지 않았습니다."); break;
		}
	}

	public void closeDisplayMainMenu() {
		/* 담님이 작성 한 종료 쓰레드 작성 메소드 */
		
	}
	

}
