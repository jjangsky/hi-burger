package team.burgerhi.kiosk.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;
import team.burgerhi.kiosk.model.service.AdminService;

public class AdminController {	
	private AdminService adminService = new AdminService();
	Scanner sc = new Scanner(System.in);
	DecimalFormat format = new DecimalFormat("###,###");
	List<CategoryDTO> categoryList = new ArrayList<>();
	
	public void selectHambergerRanking() {
		
		Map<Integer, String> hamberger = adminService.selectHambergerRanking();
		
		System.out.println(">>>>          BurgerHI 햄버거 랭킹           <<<<");
		System.out.println("=================================================");
		System.out.println("\n▶ 판매량 1위 " + (hamberger.get(1) != null ? hamberger.get(1) : "판매 내역 없음"));
		System.out.println("▶ 판매량 2위 " + (hamberger.get(2) != null ? hamberger.get(2) : "판매 내역 없음"));
		System.out.println("▶ 판매량 3위 " + (hamberger.get(3) != null ? hamberger.get(3) : "판매 내역 없음"));
		System.out.println("\n\n\n\n\n");
		
	}

	public void selectDrinkRanking() {
		
		Map<Integer, String> Drink = adminService.selectDrinkRanking();
		
		System.out.println(">>>>          BurgerHI 음료수 랭킹           <<<<");
		System.out.println("=================================================");
		System.out.println("\n▶ 판매량 1위 " + (Drink.get(1) != null ? Drink.get(1) : "판매 내역 없음"));
		System.out.println("▶ 판매량 2위 " + (Drink.get(2) != null ? Drink.get(2) : "판매 내역 없음"));
		System.out.println("▶ 판매량 3위 " + (Drink.get(3) != null ? Drink.get(3) : "판매 내역 없음"));
		System.out.println("\n\n\n\n\n");
	}

	public void selectSideRanking() {
		
		Map<Integer, String> Side = adminService.selectSideRanking();
		
		System.out.println(">>>>          BurgerHI 사이드 랭킹           <<<<");
		System.out.println("=================================================");
		System.out.println("\n▶ 판매량 1위 " + (Side.get(1) != null ? Side.get(1) : "판매 내역 없음"));
		System.out.println("▶ 판매량 2위 " + (Side.get(2) != null ? Side.get(2) : "판매 내역 없음"));
		System.out.println("▶ 판매량 3위 " + (Side.get(3) != null ? Side.get(3) : "판매 내역 없음"));
		System.out.println("\n\n\n\n\n");
		
	}
	
	public List<CategoryDTO> selectAllCategory() {
		
		categoryList = adminService.selectAllCategory();
				
		return categoryList;
	}

	public void insertCategory() {
		System.out.println(">>>>      BurgerHI 카테고리 추가 시스템      <<<<");
		System.out.println("=================================================");
		for(CategoryDTO cate : categoryList) {
            System.out.println("▶ " + cate.getCode() + ". " + cate.getName() + "(추천 카테고리: " + cate.getRefName() + ")");
         }
		System.out.print("\n  → 추가할 카테고리명을 입력해 주세요: ");
//		sc.nextLine();
		String categoryName = sc.nextLine();
		System.out.print("\n  → 추천 카테고리 번호를 입력해 주세요: ");
		int refCategory = sc.nextInt();
		System.out.println("\n\n\n\n\n");
		
		int insertResult = adminService.insertCategory(categoryName, refCategory);
		
		if(insertResult == 0) {
			System.out.println("INSERT 실패");
		} else {
			System.out.println("INSERT 성공");
		}
	}

	public void updateCategory() {
		System.out.println(">>>>      BurgerHI 카테고리 수정 시스템      <<<<");
		System.out.println("=================================================");
		for(CategoryDTO cate : categoryList) {
            System.out.println("▶ " + cate.getCode() + ". " + cate.getName() + "(추천 카테고리: " + cate.getRefName() + ")");
         }
		System.out.print("\n  → 변경할 카테고리 번호를 입력해 주세요: ");
		int categoryCode = sc.nextInt();
		System.out.print("\n  → 변경할 카테고리 이름을 입력해 주세요: ");
		sc.nextLine();
		String categoryName = sc.nextLine();
		System.out.print("\n  → 변경할 추천 카테고리 번호를 입력해 주세요: ");
		int refCode = sc.nextInt();
		System.out.println("\n\n\n\n\n");
		
		int updateResult = adminService.updateCategory(categoryCode, categoryName, refCode);
		
		if(updateResult == 0) {
			System.out.println("UPDATE 실패");
		} else {
			System.out.println("UPDATE 성공");
		}
	}

	public void deleteCategory() {
		System.out.println(">>>>      BurgerHI 카테고리 삭제 시스템      <<<<");
		System.out.println("=================================================");
		for(CategoryDTO cate : categoryList) {
            System.out.println("▶ " + cate.getCode() + ". " + cate.getName() + "(추천 카테고리: " + cate.getRefName() + ")");
         }
		System.out.print("\n → 삭제할 카테고리 이름을 입력해 주세요: ");
		String categoryName = sc.nextLine();
		System.out.println("\n\n\n\n\n");
		
		int deleteResult = adminService.deleteCategory(categoryName);
		
		if(deleteResult == 0) {
			System.out.println("DELETE 실패");
		} else {
			System.out.println("DELETE 성공");
		}
	}

	public List<MenuDTO> selectAllMenu() {
		
		List<MenuDTO> menuList = adminService.selectAllMenu();
		
		return menuList;
	}
	
	public void insertMenu() {
		System.out.println(">>>>        BurgerHI 메뉴 추가 시스템        <<<<");
		System.out.println("=================================================");
		System.out.print("\n → 추가할 메뉴명을 입력해 주세요: ");
//		sc.nextLine();
		String menuName = sc.nextLine();
		System.out.print("\n → 추가할 메뉴의 가격을 입력해 주세요: ");
		int menuPrice = sc.nextInt();
		System.out.print("\n → 추가할 메뉴의 설명을 입력해 주세요: ");
		sc.nextLine();
		String menuExplain = sc.nextLine();
		System.out.print("\n → 추가할 메뉴의 카테고리를 입력해 주세요: ");
		int categoryCode = sc.nextInt();
		System.out.print("\n → 추가할 메뉴의 판매여부를 입력해 주세요: ");
		sc.nextLine();
		String orderable = sc.nextLine().toUpperCase();
		System.out.println("\n\n\n\n\n");
		
		int insertResult = adminService.insertMenu(menuName, menuPrice, menuExplain, categoryCode, orderable);
		if(insertResult > 0) {
			System.out.println("메뉴 추가 완료!");
		}else {
			System.out.println("메뉴 추가 실패...");
		}
	}

	public void updateMenu() {
		System.out.println(">>>>        BurgerHI 메뉴 수정 시스템        <<<<");
		System.out.println("=================================================");
		System.out.print("\n → 수정할 메뉴의 번호를 입력해 주세요: ");
		int menuNum = sc.nextInt();
		System.out.print("\n → 수정할 메뉴명을 입력해 주세요: ");
		sc.nextLine();
		String menuName = sc.nextLine();
		System.out.print("\n → 수정할 메뉴의 가격을 입력해 주세요: ");
		int menuPrice = sc.nextInt();
		System.out.print("\n → 수정할 메뉴의 설명을 입력해 주세요: ");
		sc.nextLine();
		String menuExplain = sc.nextLine();
		System.out.print("\n → 수정할 메뉴의 카테고리를 입력해 주세요: ");
		int categoryCode = sc.nextInt();
		System.out.print("\n → 수정할 메뉴의 판매여부를 입력해 주세요: ");
		sc.nextLine();
		String orderable = sc.nextLine().toUpperCase();
		System.out.println("\n\n\n\n\n");
		
		int updateResult = adminService.updateMenu(menuNum, menuName, menuPrice, menuExplain, categoryCode, orderable);
		if(updateResult > 0) {
			System.out.println("메뉴 수정 완료!");
		}else {
			System.out.println("메뉴 수정 실패...");
		}
	}

	public void deleteMenu() {
		System.out.println(">>>>        BurgerHI 메뉴 삭제 시스템        <<<<");
		System.out.println("=================================================");
		System.out.print("\n → 삭제할 메뉴명을 입력해 주세요: ");
//		sc.nextLine();
		String menuName = sc.nextLine();
		System.out.println("\n\n\n\n\n");
		
		int deleteResult = adminService.deleteMenu(menuName);
		if(deleteResult > 0) {
			System.out.println("메뉴 삭제 완료!");
		}else {
			System.out.println("메뉴 삭제 실패...");
		}
	}

	public void SalesBydate() {

		System.out.println(">>>>        BurgerHI 날짜별 매출 확인        <<<<");
		System.out.println("=================================================");
	      System.out.print("\n → 매출을 조회할 '월'을 입력하세요: ");
	      int month = sc.nextInt();
	      System.out.print("※ 월 단위 매출 조회 희망 시 '일'을 0으로 입력 하세요.");
	      System.out.print("\n → 매출을 조회할 '일'을 입력하세요: ");
	      int date = sc.nextInt();
	      
	      if(date == 0) {
	         int monthSales = adminService.selectMonthSales(month);
	         System.out.println("\n ▶ " + month + "월의 매출액은 총 " + format.format(monthSales) + "원 입니다.");
	         System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	      } else {
	         int monthSales = adminService.selectDateSales(month, date);
	         System.out.println("\n ▶ " + month + "월" + date + "일의 매출액은 총 " + format.format(monthSales) + "원 입니다. ");
	         System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	      }
	      
	      
	   }

	public void SalesCategory() {
		
		System.out.println(">>>>      BurgerHI 카테고리별 매출 확인      <<<<");
		System.out.println("=================================================");
		System.out.println("              |                |               ");
		System.out.println("       1 　   |        2　　   |       3       ");
		System.out.println(" 총 누적 매출 |회원 등급별 매출|결제 종류별 매출");
		System.out.println("              |                |               ");
		System.out.println("=================================================");
		System.out.print("\n  → 번호를 선택해 주세요: ");
		int salesNum = sc.nextInt();
		
		Date now = new Date();
		SimpleDateFormat sdfm = new SimpleDateFormat("yy년 MM월 dd일");
		
		if(salesNum == 1) {
			int totalSales = adminService.selectAllSales();
			System.out.println("\n\n ▶ " + sdfm.format(now) + " 기준 총 매출액은 " + format.format(totalSales) + "원 입니다.");
		} else if(salesNum == 2) {
			Map<Integer, Integer> gadeSales = adminService.selectGradeSales();
			System.out.println("\n\n ▶ Family등급 매출액: " + format.format(gadeSales.get(0)) + "원");
			System.out.println("\n ▶ Silver등급 매출액: " + format.format(gadeSales.get(1)) + "원");
			System.out.println("\n ▶ Gold등급 매출액: " + format.format(gadeSales.get(2)) + "원");
			System.out.println("\n ▶ 비회원등급 매출액: " + format.format(gadeSales.get(4)) + "원");
			
		} else if(salesNum == 3) {
			Map<String, Integer> methodSales = adminService.selectMethodSales();
			
			System.out.println("\n\n ▶ 기프티콘 결제의 총 매출: " + (methodSales.get("기프티콘") != null ? format.format(methodSales.get("기프티콘")) : 0) + "원" + "\n");
			System.out.println(" ▶ 카드 결제의 총 매출: " + (methodSales.get("카드") != null ? format.format(methodSales.get("카드")) : 0) + "원" + "\n");
			System.out.println(" ▶ 현금 결제의 총 매출: " + (methodSales.get("현금") != null ? format.format(methodSales.get("현금")) : 0) + "원" + "\n");
		}
		 System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		
		
	}

	/* 회원의 모든 정보를 볼 수 있는 메소드(혜영) */
	public void selectUserAll() {
		
		
		
		
	}

	/* 원하는 회원의 등급을 update 할 수 있는 메소드(재성) */
	public void updateUserGrade() {
		
		
		
		
	}



}
