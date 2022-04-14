package team.burgerhi.kiosk.model.service;

import java.sql.Connection;
import java.util.List;

import team.burgerhi.kiosk.model.dao.ClientDAO;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

import static team.burgerhi.common.JDBCTemplate.getConnection;
import static team.burgerhi.common.JDBCTemplate.close;

public class ClientService {
	private ClientDAO clientDAO = new ClientDAO();
	public List<UserDTO> loginResult(String id, String pwd) {
		Connection con = getConnection();
		
		List<UserDTO> userList = clientDAO.loginResult(con, id, pwd);
		
		close(con);
		
		return userList;
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

}
