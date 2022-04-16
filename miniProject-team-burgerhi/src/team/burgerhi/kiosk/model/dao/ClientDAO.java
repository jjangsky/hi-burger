package team.burgerhi.kiosk.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.OrderMenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

public class ClientDAO {
	Properties prop = new Properties();		// xml 파일로 저장되어 있는 쿼리를 불러오기 위한 인스턴스 생성
	
	public ClientDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/burgerhi-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Login 진행 메소드 */
	public List<UserDTO>  loginResult(Connection con, String id, String pwd) {
		/*
		 * id와 pwd가 일치할 경우 회원의 이름과 등급번호 List에 담아 전달
		 * 일치하는 id와 pwd가 없을 경우 name = null값으로 지정하여 전달
		 */
		
		
		
		return null;
	}

	/* 전체 Category를 출력하는 메소드 */
	public List<CategoryDTO> selectAllCategory(Connection con) {
		/* 모든 카테고리 Select 후 반환 */
		
		
		return null;
	}

	/* 사용자가 선택한 Category의 전체 Menu를 출력하기 위한 메소드 */
	public List<MenuDTO> selectMenuBy(Connection con, int categoryNo) {
		/* 카테고리 번호에 해당하는 메뉴만 Select 후 반환 */
		
		
		return null;
	}

	/* 사용자가 선택한 Menu를 장바구니에 Insert 하는 메소드 */
	public int insertOrderMenu(int userNo, int inputMenuNo, int inputAmount) {
		/* OrderMenu 테이블에 지금까지 입력한 내용을 모두 insert */
		
		return 0;
	}

	/* OrderMenu(장바구니) 테이블의 Insert 되어 있는 내용 모두 출력하는 메소드 */
	public List<OrderMenuDTO> selectOrderMenu(Connection con, int userNo) {
		/* where = userNo  */
		
		
		return null;
	}

	/* 장바구니 내용 수정 | OrderMenu 테이블에서 원하지 않는 메뉴 삭제 후 최종 결제할 메뉴만 남기도록 설정 */
	public int deleteOrderMenu(int deleteMenuCode) {
		/* where = deleteMenuCode */
		
		return 0;
	}

	/* 등급에 따른 할인율 확인 */
	public String selectGrade(Connection con, int gradeNo) {
		/* where = gradNo */
		
		return null;
	}

	/* 카드 할인 가능한 전체 제휴 카드 리스트 출력 */
	public List<CardDTO> selectCard() {
		/* card 테이블 where = cardAble - Y */
		
		return null;
	}

	/* 결제까지 완료 된 확정 정보를 Order 테이블에 Insert */
	public int insertOrder(Connection con, String date, double lastPayment) {
		/* order테이블 insert */
		
		return 0;
	}

	/* Payment 테이블에 필요한 OrderCode select */
	public int selectLastOrderCode() {
		/* order테이블 orderCode 마지막 번호 반환 */
		
		return 0;
	}

	/* 결제까지 완료 된 확정 정보를 Payment 테이블에 Insert */
	public int insertPayment(int orderCode, int userNo, int totalPrice, double gradeDiscount, double cardDiscount,
			double lastPayment, String payment) {
		/* payment 테이블 insert */
		
		
		return 0;
	}
}
