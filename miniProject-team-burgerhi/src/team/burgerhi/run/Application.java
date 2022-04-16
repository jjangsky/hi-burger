package team.burgerhi.run;

import team.burgerhi.kiosk.views.OrderMenu;  // PUSH-TEST Ver2

public class Application {
	public static void main(String[] args) {
		
		OrderMenu orderMenu = new OrderMenu();
		orderMenu.displayMainMenu();
	}
}
