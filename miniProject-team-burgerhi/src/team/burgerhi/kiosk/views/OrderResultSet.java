package team.burgerhi.kiosk.views;

public class OrderResultSet {

	public void orderMenuSuccess() {
		System.out.println("방금 누르신 메뉴가 장바구니에 담겼습니다!");
	}

	public void orderMenuFail() {
		System.out.println("죄송합니다. KIOSK 오류로 인해 장바구니에 담지 못했습니다.");
	}

}
