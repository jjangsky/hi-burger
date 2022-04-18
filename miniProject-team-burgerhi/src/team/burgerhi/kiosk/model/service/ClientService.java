package team.burgerhi.kiosk.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import team.burgerhi.kiosk.model.dao.ClientDAO;
import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.OrderMenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

import static team.burgerhi.common.JDBCTemplate.getConnection;
import static team.burgerhi.common.JDBCTemplate.close;

public class ClientService {
	private ClientDAO clientDAO = new ClientDAO();
	
	/* Login 진행 메소드 */
	public List<UserDTO> loginResult(String id, String pwd) {
		Connection con = getConnection();
		
		List<UserDTO> userList = clientDAO.loginResult(con, id, pwd);
		
		close(con);
		
		return userList;
	}
	
	/* 로그인 한 회원의 정보 조회 */
	public List<Object> selectUserBy(int userNo, String gradeName) {
		Connection con = getConnection();
		
		List<Object> user = clientDAO.selectUserBy(con, userNo, gradeName);
		
		close(con);
		return user;
	}
	
	/* 로그인 한 회원의 정보 수정 */
	public int UpdateUserInfo(int userNo, String pwd, int phone) {
		Connection con = getConnection();
		
		int result = clientDAO.UpdateUserInfo(con, userNo, pwd, phone);
		
		close(con);
		return result;
		
	}
	
	/* 로그인 한 회원의 정보 삭제 */
	public int deleteUserBy(int userNo) {
		Connection con = getConnection();
		
		int result = clientDAO.deleteUserBy(con, userNo);
		
		close(con);
		return result;
	}
	
	/* 전체 Category를 출력하는 메소드 */
	public List<CategoryDTO> selectAllCategory() {
		Connection con = getConnection();
		
		List<CategoryDTO> categoryList = clientDAO.selectAllCategory(con);
		
		return categoryList;
	}
	
	/* 사용자가 선택한 Category의 전체 Menu를 출력하기 위한 메소드 */
	public List<MenuDTO> selectMenuBy(int categoryNo) {
		Connection con = getConnection();
		
		List<MenuDTO> menuList = clientDAO.selectMenuBy(con, categoryNo);
				
		close(con);
		return menuList;
	}
	
	/* 사용자가 선택한 Menu를 장바구니에 Insert 하는 메소드 */
	public int insertOrderMenu(int userNo, int inputMenuNo, int inputAmount) {
		Connection con = getConnection();
		
		int insertOrderMenu = clientDAO.insertOrderMenu(con, userNo, inputMenuNo, inputAmount);
		
		close(con);
		
		return insertOrderMenu;
	}
	
	/* 최종 금액을 구하기 위한 하나의 메뉴 금액 select 하는 메소드 */
	public int selectOrderMenuPrice(int inputMenuNo) {
		Connection con = getConnection();
		
		int menuPrice = clientDAO.selectOrderMenuPrice(con, inputMenuNo);
		
		close(con);
		return menuPrice;
	}
	
	/* OrderMenu(장바구니) 테이블의 Insert 되어 있는 내용 모두 출력하는 메소드 */
	public List<String> selectOrderMenu() {
		Connection con = getConnection();
		
		List<String> orderMenuList = clientDAO.selectOrderMenu(con);
		
		close(con);
		
		return orderMenuList;
	}
	
	/* 장바구니 내용 수정 | OrderMenu 테이블에서 원하지 않는 메뉴 삭제 후 최종 결제할 메뉴만 남기도록 설정 */
	public int deleteOrderMenu(int deleteMenuCode) {
		Connection con = getConnection();
		
		int deleteResulte = clientDAO.deleteOrderMenu(con, deleteMenuCode);
		
		close(con);
		
		return deleteResulte;
	}
	
	/* 등급에 따른 할인율 확인 */
	public int selectGrade(int gradeNo) {
		Connection con = getConnection();
		
		int gradediscount = clientDAO.selectGrade(con, gradeNo);
		
		close(con);
		return gradediscount;
	}
	
	/* 카드 할인 가능한 전체 제휴 카드 리스트 출력 */
	public List<CardDTO> selectCard() {
		Connection con = getConnection();
		
		List<CardDTO> cardList = clientDAO.selectCard(con);
		
		close(con);
		return cardList;
	}
	
	/* Payment 테이블에 필요한 카드 번호 Select */
	public List<CardDTO> selectAllCard() {
		Connection con = getConnection();
		
		List<CardDTO> cardList = clientDAO.selectAllCard(con);
		
		close(con);
		return cardList;
	}
	
	/* 결제까지 완료 된 확정 정보를 Order 테이블에 Insert */
	public int insertOrder(String date, double lastPayment) {
		Connection con = getConnection();
		
		int insertResult = clientDAO.insertOrder(con, date, lastPayment);
		
		close(con);
		
		return insertResult;
	}
	
	/* 결제까지 완료 된 확정 정보를 Payment 테이블에 Insert */
	public int insertPayment(int userNo, int totalPrice, int gradeNo, int cardCode, double lastPayment,
			String payment) {
		
		Connection con = getConnection();
		
		int orderCode = clientDAO.selectLastOrderCode(con);
		
		int inserResult = clientDAO.insertPayment(con, orderCode, userNo, totalPrice, gradeNo, cardCode, lastPayment, payment);
		
		close(con);
		
		return inserResult;
	}

	public int selectGifticonBy(String inputGiftNo) {
		Connection con = getConnection();
		
		int gifticonPrice = clientDAO.selectGifticonBy(con, inputGiftNo);
		
		close(con);
		return gifticonPrice;
	}

	public int updateGifticonPrice(String inputGiftNo, int gifticonPrice) {
		Connection con = getConnection();
		
		int result = clientDAO.updateGifticonPrice(con, inputGiftNo, gifticonPrice);
		
		return result;
	}

	public int deleteAllOrderMenu() {
		Connection con = getConnection();
		
		int result  = clientDAO.deleteAllOrderMenu(con);
		
		close(con);
		return result;
	}

	public int creatUserInfo(UserDTO userDTO) {
		/* 비회원 회원가입 절차 */
		Connection con = getConnection();
		
		int result = clientDAO.creatUserInfo(con, userDTO);
		
		close(con);
		
		return result;
	}



	

	

}
