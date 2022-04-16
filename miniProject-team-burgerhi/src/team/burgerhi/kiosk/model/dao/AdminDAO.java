package team.burgerhi.kiosk.model.dao;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;

import static team.burgerhi.common.JDBCTemplate.close;

public class AdminDAO {
	private Properties prop = new Properties();
	
	public AdminDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/burgerhi-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, String> selectHambergerRanking(Connection con) {
		/* hashMap 형태로 순위 담아서 넘기기 / key에는 숫자 순위 담기*/
		
		return null;
	}

	public Map<Integer, String> selectDrinkRanking(Connection con) {
		/* hashMap 형태로 순위 담아서 넘기기 / key에는 숫자 순위 담기*/
		
		return null;
	}

	public Map<Integer, String> selectSideRanking(Connection con) {
		/* hashMap 형태로 순위 담아서 넘기기 / key에는 숫자 순위 담기*/
		
		return null;
	}
	
	public List<CategoryDTO> selectAllCategory(Connection con) {	// 담
		/* List에 카테고리 모두 담아서 출력하기 */
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllCategory");
		List<CategoryDTO> categoryList = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				CategoryDTO cate = new CategoryDTO();
				cate.setCode(rset.getInt("CATEGORY_CODE"));
				cate.setName(rset.getString("CATEGORY_NAME"));
				cate.setRefCode(rset.getInt("REF_CATEGORY_CODE"));
				
				categoryList.add(cate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return categoryList;
	}

	public int insertCategory(Connection con, String categoryName, int refCategory) {
		/* category테이블 insert */
		
		return 0;
	}

	public int updateCategory(Connection con, int categoryCode, String categoryName, int refCode) {
		/* category테이블 Update */
		
		return 0;
	}

	public int deleteCategory(Connection con, String categoryName) {
		/* category테이블 delete */
		
		return 0;
	}

	public List<MenuDTO> selectAllMenu(Connection con) {
		/* List에 메뉴 모두 담아서 출력하기 */
		
		return null;
	}

	public int insertMenu(Connection con, String menuName, int menuPrice, String menuExplain, int categoryCode,
			String orderable) {
		/* Menu insert */
		
		return 0;
	}

	public int updateMenu(Connection con, String menuName, int menuPrice, String menuExplain, int categoryCode,
			String orderable) {
		/* Menu update */
		
		return 0;
	}

	public int deleteMenu(Connection con, String menuName) {
		/* Menu delete */
		
		return 0;
	}

	public int selectMonthSales(Connection con, int month) {
		/* 보고싶은 월의 매출 조회 */
		
		return 0;
	}

	public int selectDateSales(Connection con, int month, int date) {
		/* 보고싶은 날짜의 매출 조회 */
		
		return 0;
	}

	public int selectAllSales(Connection con) {
		/* 조회일을 기준으로 총 누적 매출액 Select */
		return 0;
	}

	public Map<String, Integer> selectGradeSales(Connection con) {
		/* HashMap 형태로 등급별 매출액 조회 key = 등급명, value = 매출액 */
		
		return null;
	}

	public Map<String, Integer> selectMethodSales(Connection con) {
		/* HashMap 형태로 결제방법 별 매출액 조회 key = 결제방법, value = 매출액 */
		
		return null;
	}


}
