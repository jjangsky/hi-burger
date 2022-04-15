package team.burgerhi.kiosk.model.service;

import java.sql.Connection;
import java.util.List;

import team.burgerhi.kiosk.model.dao.ClientDAO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.OrderMenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

import static team.burgerhi.common.JDBCTemplate.getConnection;
import static team.burgerhi.common.JDBCTemplate.close;

public class ClientService {
	private ClientDAO clientDAO = new ClientDAO();
	public UserDTO loginResult(String id, String pwd) {
		Connection con = getConnection();
		
		UserDTO userDTO = clientDAO.loginResult(con, id, pwd);
		
		close(con);
		
		return userDTO;
	}
	public List<CategoryDTO> selectAllCategory() {
		Connection con = getConnection();
		
		List<CategoryDTO> categoryList = clientDAO.selectAllCategory(con);
		
		return categoryList;
	}
	public List<MenuDTO> selectMenuBy(int categoryNo) {
		Connection con = getConnection();
		
		List<MenuDTO> menuList = clientDAO.selectMenuBy(con, categoryNo);
				
		close(con);
		return menuList;
	}
	public int insertOrderMenu(int userNo, int inputMenuNo, int inputAmount) {
		Connection con = getConnection();
		
		int insertOrderMenu = clientDAO.insertOrderMenu(userNo, inputMenuNo, inputAmount);
		
		close(con);
		
		return insertOrderMenu;
	}
	public List<OrderMenuDTO> selectOrderMenu(int userNo) {
		Connection con = getConnection();
		
		List<OrderMenuDTO> orderMenuList = clientDAO.selectOrderMenu(con, userNo);
		
		close(con);
		
		return orderMenuList;
	}

}
