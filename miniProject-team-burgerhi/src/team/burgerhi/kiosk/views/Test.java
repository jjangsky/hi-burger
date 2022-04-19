package team.burgerhi.kiosk.views;

public class Test {
	Thread th = new Thread();
	public void ani() {
	
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
		System.out.println("?번 고객님 메뉴가 준비됐어요!");
		System.out.println("▶▶▶▶▶▶▶");
		th.sleep(1000);
		th.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}  /* finally */
}
}