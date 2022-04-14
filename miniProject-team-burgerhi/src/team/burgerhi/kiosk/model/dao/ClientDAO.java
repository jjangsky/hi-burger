package team.burgerhi.kiosk.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.dto.UserDTO;

public class ClientDAO {
	Properties prop = new Properties();
	
	public ClientDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/burgerhi-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<UserDTO> loginResult(Connection con, String id, String pwd) {
		/*
		 * id와 pwd가 일치할 경우 회원의 이름과 등급번호 List에 담아 전달
		 * 일치하는 id와 pwd가 없을 경우 name = null값으로 지정하여 전달
		 */
		
		
		
		return null;
	}

	public List<CategoryDTO> selectAllCategory(Connection con) {
		/* 모든 카테고리 Select 후 반환 */
		
		
		return null;
	}

	public List<MenuDTO> selectMenuBy(Connection con, int categoryNo) {
		/* 카테고리 번호에 해당하는 메뉴만 Select 후 반환 */
		
		
		return null;
	}

}
