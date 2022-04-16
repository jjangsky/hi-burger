package team.burgerhi.run;

import team.burgerhi.kiosk.views.OrderMenu;
import team.burgerhi.kiosk.views.OrderMenu;  // PUSH-TEST Ver2

public class Application {
	public static void main(String[] args) {
		
		/* View 화면이 보여지도록 인스턴스 생성 및 메소드 호출 */
		OrderMenu orderMenu = new OrderMenu();
		orderMenu.displayMainMenu();
	}
}