package team.burgerhi.kiosk.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import team.burgerhi.kiosk.model.dao.AdminDAO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;

import static team.burgerhi.common.JDBCTemplate.getConnection;
import static team.burgerhi.common.JDBCTemplate.close;

public class AdminService {
	private AdminDAO adminDAO = new AdminDAO();
	public Map<Integer, String> selectHambergerRanking() {
		Connection con = getConnection();
		
		Map<Integer, String> hamberger = adminDAO.selectHambergerRanking(con);
		
		close(con);
		return hamberger;
	}
	public Map<Integer, String> selectDrinkRanking() {
		Connection con = getConnection();
		
		Map<Integer, String> drink = adminDAO.selectDrinkRanking(con);
		
		close(con); 
		return drink;
	}
	public Map<Integer, String> selectSideRanking() {
		Connection con = getConnection();
		
		Map<Integer, String> side = adminDAO.selectSideRanking(con);
		
		close(con);
		return side;
	}
	public List<CategoryDTO> selectAllCategory() {
		Connection con = getConnection();
		
		List<CategoryDTO> categoryList = adminDAO.selectAllCategory(con);
		
		close(con);
		
		return categoryList;
	}
	public int insertCategory(String categoryName, int refCategory) {
		Connection con = getConnection();
		
		int insertResult = adminDAO.insertCategory(con, categoryName, refCategory);
		
		
		close(con);
		return insertResult;
	}
	public int updateCategory(int categoryCode, String categoryName, int refCode) {
		Connection con = getConnection();
		
		int updateResult = adminDAO.updateCategory(con, categoryCode, categoryName, refCode);
		
		close(con);
		return updateResult;
	}
	public int deleteCategory(String categoryName) {
		Connection con = getConnection();
		
		int deleteResult = adminDAO.deleteCategory(con, categoryName);
		
		close(con);
		return deleteResult;
	}
	public List<MenuDTO> selectAllMenu() {
		Connection con = getConnection();
		
		List<MenuDTO> menuList = adminDAO.selectAllMenu(con);
		
		close(con);
		return menuList;
	}
	public int insertMenu(String menuName, int menuPrice, String menuExplain, int categoryCode, String orderable) {
		Connection con = getConnection();
		
		int insertMenu = adminDAO.insertMenu(con, menuName, menuPrice, menuExplain, categoryCode, orderable);
		
		close(con);
		
		return insertMenu;
	}
	public int updateMenu(int menuNum, String menuName, int menuPrice, String menuExplain, int categoryCode, String orderable) {
		Connection con = getConnection();
		
		int updateMenu = adminDAO.updateMenu(con, menuNum, menuName, menuPrice, menuExplain, categoryCode, orderable);
		
		close(con);
		return updateMenu;
	}
	public int deleteMenu(String menuName) {
		Connection con = getConnection();
		
		int deleteMenu = adminDAO.deleteMenu(con, menuName);
		
		close(con);
		return deleteMenu;
	}
	public int selectMonthSales(int month) {
		Connection con = getConnection();
		
		int monthSales = adminDAO.selectMonthSales(con, month);
		
		close(con);
		return monthSales;
	}
	public int selectDateSales(int month, int date) {
		Connection con = getConnection();
		
		int selectSales = adminDAO.selectDateSales(con, month, date);
		
		close(con);
		return selectSales;
	}
	public int selectAllSales() {
		Connection con = getConnection();
		
		int selectSales = adminDAO.selectAllSales(con);
		
		close(con);
		return selectSales;
	}
	public Map<Integer, Integer> selectGradeSales() {
		Connection con = getConnection();
		
		Map<Integer, Integer> gradeSales = adminDAO.selectGradeSales(con);
		
		close(con);
		return gradeSales;	
	}
	public Map<String, Integer> selectMethodSales() {
		Connection con = getConnection();
		
		Map<String, Integer> methodSales = adminDAO.selectMethodSales(con);
		
		close(con);
		return methodSales;	
	}
	public List<Object> selectAllUser() {
		Connection con = getConnection();
		
		List<Object> allUser = adminDAO.selectAllUser(con);

		close(con);
		return allUser;
	}

	public int updateUserGrade(int userNo, int gradeNo) {
		Connection con = getConnection();

		int updateUserGrade = adminDAO.updateUserGrade(con, userNo, gradeNo);
		
		close(con);

		return updateUserGrade;

	}

}
