package team.burgerhi.kiosk.model.dao;

import static team.burgerhi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public List<UserDTO> loginResult(Connection con, String id, String pwd) {
		/*
		 * id와 pwd가 일치할 경우 회원의 이름과 등급번호 List에 담아 전달 일치하는 id와 pwd가 없을 경우 name = null값으로
		 * 지정하여 전달
		 */
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("loginResult");
		List<UserDTO> userList = new ArrayList<>();
		try {
			UserDTO userDTO = new UserDTO();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				userDTO.setUserNo(rset.getInt("USER_NO"));
				userDTO.setName(rset.getString("USER_NAME"));
				userDTO.setId(rset.getString("USER_ID"));
				userDTO.setPwd(rset.getString("USER_PWD"));
				userDTO.setGradeNo(rset.getInt("GRADE_NO"));
				userDTO.setUserPoint(rset.getInt("USER_POINT"));
				userDTO.setPhone(rset.getString("PHONE"));
				userList.add(userDTO);
				System.out.println(userList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
		}
		return userList;
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
	public int insertOrderMenu(Connection con, int userNo, int inputMenuNo, int inputAmount) {
		/* OrderMenu 테이블에 지금까지 입력한 내용을 모두 insert */
		
		return 0;
	}

	/* OrderMenu(장바구니) 테이블의 Insert 되어 있는 내용 모두 출력하는 메소드 */
	public List<OrderMenuDTO> selectOrderMenu(Connection con) {
		/* 전체select  */
		
		
		return null;
	}

	/* 장바구니 내용 수정 | OrderMenu 테이블에서 원하지 않는 메뉴 삭제 후 최종 결제할 메뉴만 남기도록 설정 */
	public int deleteOrderMenu(Connection con, int deleteMenuCode) {
		/* where = deleteMenuCode */
		
		return 0;
	}

	/* 등급에 따른 할인율 확인 */
	public String selectGrade(Connection con, int gradeNo) {
		/* where = gradNo */
		
		return null;
	}

	/* 카드 할인 가능한 전체 제휴 카드 리스트 출력 */
	public List<CardDTO> selectCard(Connection con) {
		/* card 테이블 where = cardAble - Y */
		
		return null;
	}

	/* Payment 테이블에 필요한 카드 번호 Select */
	public List<CardDTO> selectAllCard(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		List<CardDTO> cardList = new ArrayList<CardDTO>();
		String query = prop.getProperty("selectAllCard");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				CardDTO card = new CardDTO();
				card.setCode(rset.getInt(1));
				card.setBank(rset.getString(2));
				card.setDiscount(rset.getString(3));
				card.setCardable(rset.getString(4));
				
				cardList.add(card);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return cardList;
	}
	
	/* 결제까지 완료 된 확정 정보를 Order 테이블에 Insert */
	public int insertOrder(Connection con, String date, double lastPayment) {
		/* order테이블 insert */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertOrder");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, date);
			pstmt.setInt(2, (int)lastPayment);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/* Payment 테이블에 필요한 OrderCode select */
	public int selectLastOrderCode(Connection con) {
		/* order테이블 orderCode 마지막 번호 반환 */
		Statement stmt = null;
		ResultSet rset = null;
		int lastCode = 0;
		
		String query = prop.getProperty("selectLastOrderCode");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				lastCode = rset.getInt("CURRVAL");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return lastCode;
	}

	/* 결제까지 완료 된 확정 정보를 Payment 테이블에 Insert */
	public int insertPayment(Connection con, int orderCode, int userNo, int totalPrice, int gradeNo, int cardCode,
			double lastPayment, String payment) {
		/* payment 테이블 insert */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertPayment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, orderCode);
			pstmt.setInt(2, userNo);
			pstmt.setInt(3, totalPrice);
			pstmt.setInt(4, gradeNo);
			pstmt.setInt(5, cardCode);
			pstmt.setInt(6, (int)lastPayment);
			pstmt.setString(7, payment);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
