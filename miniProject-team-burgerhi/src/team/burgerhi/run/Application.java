package team.burgerhi.run;

<<<<<<< HEAD
import team.burgerhi.kiosk.views.OrderMenu;
=======
import team.burgerhi.kiosk.views.OrderMenu;  // PUSH-TEST Ver2
>>>>>>> 22e84e9ad14dd7133b6a1f1d38d4f6f38424ab36

public class Application {
	public static void main(String[] args) {
		
		/* View 화면이 보여지도록 인스턴스 생성 및 메소드 호출 */
		OrderMenu orderMenu = new OrderMenu();
		orderMenu.displayMainMenu();
	}
}