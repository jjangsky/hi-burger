package team.burgerhi.kiosk.model.dao;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
		Statement stmt = null;
		ResultSet rset = null;
		Map<Integer, String> hamberger = new HashMap<Integer, String>();
		String query = prop.getProperty("selectHambergerRanking");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			for(int i = 1; rset.next(); i++) {
				hamberger.put(i, rset.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return hamberger;
	}

	public Map<Integer, String> selectDrinkRanking(Connection con) {
		/* hashMap 형태로 순위 담아서 넘기기 / key에는 숫자 순위 담기*/
		Statement stmt = null;
		ResultSet rset = null;
		Map<Integer, String> drink = new HashMap<Integer, String>();
		String query = prop.getProperty("selectDrinkRanking");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			for(int i = 1; rset.next(); i++) {
				drink.put(i, rset.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return drink;
	}

	public Map<Integer, String> selectSideRanking(Connection con) {
		/* hashMap 형태로 순위 담아서 넘기기 / key에는 숫자 순위 담기*/
		Statement stmt = null;
		ResultSet rset = null;
		Map<Integer, String> side = new HashMap<Integer, String>();
		String query = prop.getProperty("selectSideRanking");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			for(int i = 1; rset.next(); i++) {
				side.put(i, rset.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return side;
	}
	
	public List<CategoryDTO> selectAllCategory(Connection con) {
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
				cate.setRefName(rset.getString(4));
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
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertCategory");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, categoryName);
			pstmt.setInt(2, refCategory);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateCategory(Connection con, int categoryCode, String categoryName, int refCode) {
		/* category테이블 Update */
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateCategory");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, categoryName);
			pstmt.setInt(2, refCode);
			pstmt.setInt(3, categoryCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteCategory(Connection con, String categoryName) {
		/* category테이블 delete */
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteCategory");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, categoryName);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<MenuDTO> selectAllMenu(Connection con) {
		/* List에 메뉴 모두 담아서 출력하기 */
		Statement stmt = null;
		ResultSet rset = null;
		
		List<MenuDTO> menuList = new ArrayList<>();
		
		String Query = prop.getProperty("selectAllMenu");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(Query);
			
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
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return menuList;
	}

	public int insertMenu(Connection con, String menuName, int menuPrice, String menuExplain, int categoryCode,
			String orderable) {
		/* Menu insert */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMenu");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, menuPrice);
			pstmt.setString(3, menuExplain);
			pstmt.setInt(4, categoryCode);
			pstmt.setString(5, orderable);	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMenu(Connection con, int menuNum, String menuName, int menuPrice, String menuExplain, int categoryCode,
			String orderable) {
		/* Menu update */
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateMenu");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, menuPrice);
			pstmt.setString(3, menuExplain);
			pstmt.setInt(4, categoryCode);
			pstmt.setString(5, orderable);
			pstmt.setInt(6, menuNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMenu(Connection con, String menuName) {
		/* Menu delete */
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteMenu");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menuName);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectMonthSales(Connection con, int month) {
	      /* 보고싶은 월의 매출 조회 */
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      int monthSales = 0;
	      String query = prop.getProperty("selectMonthSales");
	      
	      try {
	         pstmt = con.prepareStatement(query);
	         if(month <10) {
	            pstmt.setString(1, "0" + month);
	         } else {
	            pstmt.setString(1, "" + month);
	         }
	         
	         rset = pstmt.executeQuery();
	         
	         if(rset.next()) {
	            monthSales = rset.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      return monthSales;
	   }

	public int selectDateSales(Connection con, int month, int date) {
        /* 보고싶은 날짜의 매출 조회 */
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int dateSales = 0;
        String query = prop.getProperty("selectDateSales");
        
        try {
           pstmt = con.prepareStatement(query);  
           if(month < 10) {
              pstmt.setString(1, "0" + month);              
           }else {
              pstmt.setString(1, "" + month);
           }
           if(date < 10) {
              pstmt.setString(2, "0" + date);          
           }else {
              pstmt.setString(2, "" + date);
           }
          
           rset = pstmt.executeQuery();
           
           while(rset.next()) {
              dateSales = rset.getInt(2);
           }
           
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close(rset);
           close(pstmt);
        }
        
        return dateSales;
  }

	public int selectAllSales(Connection con) {
	      /* 조회일을 기준으로 총 누적 매출액 Select */
	      Statement stmt = null;
	      ResultSet rset = null;
	      
	      int allSales = 0;
	      
	      String query = prop.getProperty("selectAllSales");
	      try {
	         stmt = con.createStatement();
	         rset = stmt.executeQuery(query);
	         
	      
	         
	         while(rset.next()) {
	            allSales = rset.getInt(1);
	         
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(stmt);
	      }
	      
	      return allSales;
	   }

	public Map<Integer, Integer> selectGradeSales(Connection con) {
		/* HashMap 형태로 등급별 매출액 조회 key = 등급명, value = 매출액 */
		Statement stmt = null;
		ResultSet rset = null;
		Map<Integer, Integer> gradeSales = new HashMap<Integer, Integer>();
		String query = prop.getProperty("selectGradeSales");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			for(int i = 0; rset.next(); i++) {
				gradeSales.put(i, rset.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return gradeSales;
	}

	public Map<String, Integer> selectMethodSales(Connection con) {
		/* 
		 * HashMap 형태로 결제방법 별 매출액 조회 key = 결제방법, value = 매출액
		 * payment 테이블로 select 필요
		 */
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMethodSales");
		
		Map<String, Integer> map = new HashMap<>();
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			List list = new ArrayList();
			while(rset.next()) {
				list.add(rset.getInt(1));
			}
			map.put("기프티콘", (Integer)list.get(0));
			map.put("카드", (Integer)list.get(1));
			map.put("현금", (Integer)list.get(2));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return map;
	}


}
