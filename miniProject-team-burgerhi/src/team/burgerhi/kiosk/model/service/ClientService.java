package team.burgerhi.kiosk.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import team.burgerhi.kiosk.model.dao.ClientDAO;
import team.burgerhi.kiosk.model.dto.CardDTO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.GifticonDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.OrderDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

import static team.burgerhi.common.JDBCTemplate.getConnection;
import static team.burgerhi.common.JDBCTemplate.close;
import static team.burgerhi.common.JDBCTemplate.commit;
import static team.burgerhi.common.JDBCTemplate.rollback;


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
	public int UpdateUserInfo(int userNo, String pwd, String phone) {
		Connection con = getConnection();
		int result = clientDAO.UpdateUserInfo(con, userNo, pwd, phone);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
		
	}
	
	/* 로그인 한 회원의 정보 삭제 */
	public int deleteUserBy(int userNo) {
		Connection con = getConnection();
		int result = clientDAO.deleteUserBy(con, userNo);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
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
		if(insertOrderMenu > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return insertOrderMenu;
	}
	
	/* 최종 금액을 구하기 위한 하나의 메뉴 금액 select 하는 메소드 */
//	public int selectOrderMenuPrice(int inputMenuNo) {
//		Connection con = getConnection();
//		int menuPrice = clientDAO.selectOrderMenuPrice(con, inputMenuNo);
//		close(con);
//		return menuPrice;
//	}
	
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
		if(deleteResulte > 0) {
			commit(con);
		} else {
			rollback(con);
		}
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
	public int insertOrder(String date, int lastPayment) {
		Connection con = getConnection();
		int insertResult = clientDAO.insertOrder(con, date, lastPayment);
		if(insertResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return insertResult;
	}
	
	/* 주문번호의 마지막 시퀀스 번호 확인Select */
	public int selectLastOrderCode() {
		Connection con = getConnection();
		int orderCode = clientDAO.selectLastOrderCode(con);
		close(con);
		return orderCode;
	}
	
	/* 결제까지 완료 된 확정 정보를 Payment 테이블에 Insert */
	public void insertPayment(int orderCode, int userNo, int totalPrice, int gradeNo, int cardCode, double lastPayment,
			String payment) {
		Connection con = getConnection();

		int inserResult = clientDAO.insertPayment(con, orderCode, userNo, totalPrice, gradeNo, cardCode, lastPayment, payment);
		if(inserResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

	}

	/* 사용자가 입력 한 기프티콘 번호를 입력받아 기프티콘 테이블에서 Select */
	public List<GifticonDTO> selectGifticonBy(String inputGiftNo) {
		Connection con = getConnection();
		List<GifticonDTO> gifticonList = clientDAO.selectGifticonBy(con, inputGiftNo);
		close(con);
		return gifticonList;
	}

	/* 사용한 기프티콘의 경우 사용 후 금액 테이블에 Update */
	public int updateGifticonPrice(String inputGiftNo, int gifticonPrice) {
		Connection con = getConnection();
		int result = clientDAO.updateGifticonPrice(con, inputGiftNo, gifticonPrice);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
//		System.out.println(result);
		return result;
	}

	/* 결제까지 완료 한 장바구니 메뉴는 모두 Delete */
	public int deleteAllOrderMenu() {
		Connection con = getConnection();
		int result  = clientDAO.deleteAllOrderMenu(con);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	/* 비회원 회원가입 절차 */
	public int creatUserInfo(UserDTO userDTO) {
		Connection con = getConnection();
		int result = clientDAO.creatUserInfo(con, userDTO);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	/* 비회원과 회원 sequence 공유로 중복 값 없도록 insert */
	public int insertNonMemberUser(int gradeNo) {
		Connection con = getConnection();
		int result = clientDAO.insertNonMemberUser(con, gradeNo);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}
	
	/* 등급 변동사항이 있을 수도 있기 때문에 혹시 몰라서 할인율이 0인 비회원 등급번호 조회 */
	public int selectNonMemberGradeNo() {
		Connection con = getConnection();
		int gradeNo = clientDAO.selectNonMemberGradeNo(con);
		close(con);
		return gradeNo;
	}

	/* orderMenu(장바구니)에 필요한 비회원 userNo 조회 */
	public int selectNonMemberUserNo() {
		Connection con = getConnection();
		int userNo = clientDAO.selectNonMemberUserNo(con);
		close(con);
		return userNo;
	}


	public int insertSalesAmount(int orderCode, int menuCode, int amount, int totalPrice) {
		Connection con = getConnection();
		int result = clientDAO.insertSalesAmount(con, orderCode, menuCode, amount, totalPrice);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int deleteSalesAmount(int deleteMenuCode) {
		Connection con = getConnection();
		int result = clientDAO.deleteSalesAmount(con, deleteMenuCode);
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int selectCategoryBy(int categoryNo) {
		Connection con = getConnection();
		int category = clientDAO.selectCategoryBy(con, categoryNo);
		close(con);
		return category;
	}

	/* 기프티콘 이벤트에 당첨된 기프티콘 생성 */
	public int insertGifticonEvent(int eventPrice) {
		Connection con = getConnection();
		int result = clientDAO.insertGifticonEvent(con, eventPrice);
		close(con);
		return result;
	}

	public List<GifticonDTO> selectlastgifticon() {
		Connection con = getConnection();
		List<GifticonDTO> gifticonList = clientDAO.selectlastgifticon(con);
		close(con);
		return gifticonList;
	}
/* 멤버쉽 포인트 변경 */
	public int updateMemberPoint(int userNo, int selectPoint) {
		Connection con = getConnection();
		int memberPoint = clientDAO.updateMemberPoint(con, userNo, selectPoint);
		close(con);
		
		return memberPoint;
	}
/* 멤버쉽 포인트 조회 */
	public int selectMemberPoint(int userNo) {
		Connection con = getConnection();
		int selectPoint = clientDAO.selectMemberPoint(con, userNo);
		close(con);
		return selectPoint;
	}
	/* 골드 등급으로 변경 */
	public void updateGoldGrade(int userNo) {
		Connection con = getConnection();
		clientDAO.updateGoldGrade(con, userNo);
		close(con);
		
	}

	public void updateSilverGrade(int userNo) {
		Connection con = getConnection();
		clientDAO.updateSilverGrade(con, userNo);
		close(con);		
	}

	public void updateFamilyGrade(int userNo) {
		Connection con = getConnection();
		clientDAO.updateFamilyGrade(con, userNo);
		close(con);		
	}

	public List<MenuDTO> selectAllBurger() {
		Connection con = getConnection();
		List<MenuDTO> burgerList = clientDAO.selectAllBurger(con);
		close(con);
		return burgerList;
	}

	public List<MenuDTO> selectAllDrink() {
		Connection con = getConnection();
		List<MenuDTO> drinkList = clientDAO.selectAllDrink(con);
		close(con);
		return drinkList;
	}

	public List<MenuDTO> selectAllSide() {
		Connection con = getConnection();
		List<MenuDTO> sideList = clientDAO.selectAllSide(con);
		close(con);
		return sideList;
	}

	public int insertDrinkMenu(int userNo, int inputAmount) {
		Connection con = getConnection();
		int insertDrinkMenu = clientDAO.insertDrinkMenu(con, userNo, inputAmount);
		if(insertDrinkMenu > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return insertDrinkMenu;
		
	}

	public int insertSetMenu(int userNo, int inputAmount) {
		Connection con = getConnection();
		int insertSetMenu = clientDAO.insertSetMenu(con, userNo, inputAmount);
		if(insertSetMenu > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return insertSetMenu;
		
	}

	public int deleteOrderMenuAmount(int menuNo) {
		Connection con = getConnection();
		int result = clientDAO.deleteOrderMenuAmount(con, menuNo);
		close(con);
		return result;
	}

	public int selectUserUpgrade(int userNo) {
		Connection con = getConnection();
		int updateGradeNo = clientDAO.selectUserUpgrade(con, userNo);
		close(con);
		return updateGradeNo;
	}


}
