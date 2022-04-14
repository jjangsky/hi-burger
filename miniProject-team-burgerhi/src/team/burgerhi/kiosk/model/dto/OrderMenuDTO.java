package team.burgerhi.kiosk.model.dto;

import java.io.Serializable;

public class OrderMenuDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3963982027066769899L;

	private int userNo;			// 회원번호
	private int menuCode;		// 메뉴번호
	private int orderAmount;	// 주문수량
	
	public OrderMenuDTO() {
	}
	
	public OrderMenuDTO(int userNo, int menuCode, int orderAmount) {
		this.userNo = userNo;
		this.menuCode = menuCode;
		this.orderAmount = orderAmount;
	}
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "OrderMenuDTO [userNo=" + userNo + ", menuCode=" + menuCode + ", orderAmount=" + orderAmount + "]";
	}
}
