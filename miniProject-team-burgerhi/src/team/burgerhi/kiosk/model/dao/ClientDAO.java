package team.burgerhi.kiosk.model.dao;

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
import team.burgerhi.kiosk.model.dto.UserDTO;
import static team.burgerhi.common.JDBCTemplate.close;

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
//	            System.out.println(userList);		// 메소드 실행 확인
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	      }
	      return userList;
	}
	
	/* 로그인 한 회원의 정보 조회 */
	public List<Object> selectUserBy(Connection con, int userNo, String gradeName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Object> userList = new ArrayList<Object>();
		String query = prop.getProperty("selectUserBy");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				userList.add(rset.getInt("USER_NO"));
				userList.add(rset.getString("USER_NAME"));
				userList.add(rset.getString("USER_ID"));
				userList.add(rset.getString(4));
				userList.add(rset.getString("GRADE_NAME"));
				userList.add(rset.getInt("USER_POINT"));
				userList.add(rset.getString("PHONE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	/* 로그인 한 회원의 정보 수정(pwd, phone) */
	public int UpdateUserInfo(Connection con, int userNo, String pwd, String phone) {
		/* where = userNo | set = pwd, phone */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateUserInfo");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, phone);
			pstmt.setInt(3, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/* 로그인 한 회원의 정보 삭제('Y' → 'N') */
	public int deleteUserBy(Connection con, int userNo) {
		/* USER_YN 컬럼의 데이터만 Y → N (즉, Update문 사용) */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteUserBy");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/* 전체 Category를 출력하는 메소드 */
	public List<CategoryDTO> selectAllCategory(Connection con) {
		/* 모든 카테고리 Select 후 반환 */
		Statement stmt = null;
		ResultSet rset = null;
		
		List<CategoryDTO> categoryList = new ArrayList<>();
		String query = prop.getProperty("selectAllCategory");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				CategoryDTO category = new CategoryDTO();
				category.setCode(rset.getInt("CATEGORY_CODE"));
				category.setName(rset.getString("CATEGORY_NAME"));
				category.setRefCode(rset.getInt("REF_CATEGORY_CODE"));
				
				categoryList.add(category);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return categoryList;
	}

	/* 사용자가 선택한 Category의 전체 Menu를 출력하기 위한 메소드 */
	public List<MenuDTO> selectMenuBy(Connection con, int categoryNo) {
		/* 카테고리 번호에 해당하는 메뉴만 Select 후 반환 */
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<MenuDTO> menuList = new ArrayList<>();
		String query = prop.getProperty("selectMenuBy");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MenuDTO menu = new MenuDTO();
				menu.setMenuCode(rset.getInt("MENU_CODE"));
				menu.setName(rset.getString("MENU_NAME"));
				menu.setPrice(rset.getInt("PRICE"));
				menu.setExplain(rset.getString("MENU_EXPLAIN"));
				menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				menu.setOrderable(rset.getString("ORDERABLE"));
				
				menuList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return menuList;
	}

	/* 사용자가 선택한 Menu를 장바구니에 Insert 하는 메소드 */
	public int insertOrderMenu(Connection con, int userNo, int inputMenuNo, int inputAmount) {
		/* OrderMenu 테이블에 지금까지 입력한 내용을 모두 insert */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertOrderMenu");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, inputMenuNo);
			pstmt.setInt(3, inputAmount);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/* 최종 금액을 구하기 위한 하나의 메뉴 금액 select 하는 메소드 */
	public int selectOrderMenuPrice(Connection con, int inputMenuNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int menuPrice = 0;
		String query = prop.getProperty("selectOrderMenuPrice");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, inputMenuNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				menuPrice = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt); 
		}
		return menuPrice;
	}

	/* OrderMenu(장바구니) 테이블의 Insert 되어 있는 내용 모두 출력하는 메소드 */
	public List<String> selectOrderMenu(Connection con) {
		/* 전체select  */
		Statement stmt = null;
		ResultSet rset = null;
		
		List<String> orderMenuList = new ArrayList<>();
		String query = prop.getProperty("selectOrderMenu");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				orderMenuList.add("" + rset.getInt(1));
				orderMenuList.add("" + rset.getInt(2));
				orderMenuList.add(rset.getString(3));
				orderMenuList.add("" + rset.getInt(4));
				orderMenuList.add("" + rset.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return orderMenuList;
	}

	/* 장바구니 내용 수정 | OrderMenu 테이블에서 원하지 않는 메뉴 삭제 후 최종 결제할 메뉴만 남기도록 설정 */
	public int deleteOrderMenu(Connection con, int deleteMenuCode) {
		/* where = deleteMenuCode */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteOrderMenu");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, deleteMenuCode);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/* 등급에 따른 할인율 확인 */
	public int selectGrade(Connection con, int gradeNo) {
		/* where = gradNo */
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int gradediscount = 0;
		String query = prop.getProperty("selectGrade");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, gradeNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				gradediscount = rset.getInt("DISCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return gradediscount;
	}

	/* 카드 할인 가능한 전체 제휴 카드 리스트 출력 */
	public List<CardDTO> selectCard(Connection con) {
		/* card 테이블 where = cardAble - Y */
		Statement stmt = null;
		ResultSet rset = null;
		List<CardDTO> cardList = new ArrayList<CardDTO>();
		String query = prop.getProperty("selectCard");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				CardDTO card = new CardDTO();
				card.setCode(rset.getInt("CARD_CODE"));
				card.setBank(rset.getString("CARD_BANK"));
				card.setDiscount(rset.getNString("CARD_DISCOUNT"));
				card.setCardable(rset.getString("CARDABLE"));
				
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
	public int insertOrder(Connection con, String date, int lastPayment) {
		/* order테이블 insert */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertOrder");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, date);
			pstmt.setInt(2, lastPayment);
			
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
				lastCode = rset.getInt(1);
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

	/* 사용자가 입력 한 기프티콘 번호를 입력받아 기프티콘 테이블에서 Select */
	public int selectGifticonBy(Connection con, String inputGiftNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int gifticonPrice = 0;
		String query = prop.getProperty("selectGifticonBy");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, inputGiftNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				gifticonPrice = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return gifticonPrice;
	}

	/* 사용한 기프티콘의 경우 사용 후 금액 테이블에 Update */
	public int updateGifticonPrice(Connection con, String inputGiftNo, int gifticonPrice) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateGifticonPrice");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, gifticonPrice);
			pstmt.setString(2, inputGiftNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/* 결제 한 장바구니속 메뉴는 모두 삭제 */
	public int deleteAllOrderMenu(Connection con) {
		Statement stmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteAllOrderMenu");
		
		try {
			stmt = con.createStatement();
			result = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}

	/* 비회원 회원가입 절차 */
	public int creatUserInfo(Connection con, UserDTO userDTO) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String qeury = prop.getProperty("insertUser");
		try {
			pstmt = con.prepareStatement(qeury);
			pstmt.setString(1, userDTO.getName());
			pstmt.setString(2, userDTO.getId());
			pstmt.setString(3, userDTO.getPwd());
			pstmt.setString(4, userDTO.getPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 등급 변동사항이 있을 수도 있기 때문에 혹시 몰라서 할인율이 0인 비회원 등급번호 조회 */
	public int selectNonMemberGradeNo(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int gradeNo = 0;
		String query = prop.getProperty("selectNonMemberGradeNo");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				gradeNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gradeNo;
	}

	/* 비회원과 회원 sequence 공유로 중복 값 없도록 insert */
	public int insertNonMemberUser(Connection con, int gradeNo) {
		/* 비회원 user_table insert */
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertNonMemberUser");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, gradeNo);
			pstmt.setString(2, "0");
			pstmt.setString(3, "N");
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/* orderMenu(장바구니)에 필요한 비회원 userNo 조회 */
	public int selectNonMemberUserNo(Connection con) {
		/* orderMenu(장바구니)에 필요한 비회원 userNo 조회 */
		Statement stmt = null;
		ResultSet rset = null;
		int userNo = 0;
		String query = prop.getProperty("selectNonMemberUserNo");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				userNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return userNo;
	}
	

	/* AdminMenu의 매출 조회를 위한 SalesAmount테이블 Insert */
	public int insertSalesAmount(Connection con, int orderCode, int menuCode, int amount,
			int totalPrice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertSalesAmount");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, orderCode);
			pstmt.setInt(2, menuCode);
			pstmt.setInt(3, amount);
			pstmt.setInt(4, totalPrice);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteSalesAmount(Connection con, int deleteMenuCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteSalesAmount");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, deleteMenuCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
}
