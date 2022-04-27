package team.burgerhi.kiosk.views;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import team.burgerhi.kiosk.controller.AdminController;
import team.burgerhi.kiosk.model.dto.CategoryDTO;
import team.burgerhi.kiosk.model.dto.MenuDTO;

public class AdminMenu {
   private AdminController adminController = new AdminController();

   public int displayMainMenu() {
      Scanner sc = new Scanner(System.in);
      boolean flag = true;
      int num = 0;
      int rankNum = 0;
      int menuAdmin = 0;
      int categoryNum = 0;
      int menuNum = 0;
      int salesNum = 0;
      int exit = 1;
      while (flag) {
         System.out.println(">>>>       BurgerHI 관리자 페이지       <<<<");
         System.out.println("=================================================");
         System.out.println("               |                |               ");
         System.out.println("      1      　|        2       |       3       ");
         System.out.println("판매 순위 확인 |    메뉴 관리   |   매출 확인   ");
         System.out.println("               |                |               ");
         System.out.println("------------------------------------------------");
         System.out.println("                       |                       ");
       System.out.println("           4           |           5           ");
       System.out.println("     회원 정보 확인    |     메뉴 주문 하기    ");
       System.out.println("                       |                       ");
         System.out.println("=================================================");
         System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
         while (true) {
         try {
            System.out.print("\n  → 번호를 선택해 주세요: ");
            
            num = sc.nextInt();
            sc.nextLine();
         } catch(InputMismatchException e) {
            System.out.println("\n 숫자로 입력해 주세요!");
            sc.next();
            continue;
            
         } break;
         }
            
         System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
         
         /* 판매 순위 확인 */
         if (num == 0) {
            exit = 0;
            break;
         }else if(num == 1) {
            System.out.println(">>>>       BurgerHI 카테고리 랭킹       <<<<");
            System.out.println("=================================================");
            System.out.println("               |                |               ");
            System.out.println("      1      　|        2       |       3      ");
            System.out.println("  햄버거 랭킹  |   음료수 랭킹  |  사이드 랭킹  ");
            System.out.println("               |                |             ");
            System.out.println("=================================================");
            System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
            
            while (true) {
            try {
               System.out.print("\n  → 번호를 선택해 주세요: ");
               rankNum = sc.nextInt();
               sc.nextLine();
            } catch(InputMismatchException e) {
               System.out.println("\n 숫자로 입력해 주세요!");
               sc.next();
               continue;
               
            } break;
            }
               
            
            
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            switch (rankNum) {
            case 0: flag = false; break;
            case 1: adminController.selectHambergerRanking(); break;      // 햄버거 랭킹을 보여 줄 메소드
            case 2: adminController.selectDrinkRanking(); break;         // 음료수 랭킹을 보여 줄 메소드
            case 3: adminController.selectSideRanking(); break;            // 사이드 랭킹을 보여 줄 메소드
            default: System.out.println("번호를 잘못 입력하셨습니다!"); continue;   // 번호 잘못 입력할 경우 다시 처음으로 돌아가게 설정
            }

           /* 메뉴 관리 확인 */
         } else if (num == 2) {
            System.out.println(">>>>        BurgerHI 메뉴 관리 시스템        <<<<");
            System.out.println("=================================================");
            System.out.println("                       |                       ");
            System.out.println("           1           |           2           ");
            System.out.println("     카테고리 관리     |        메뉴 관리      ");
            System.out.println("                       |                       ");
            System.out.println("=================================================");
            System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
            while (true) {
               try {
                  System.out.print("\n  → 번호를 선택해 주세요: ");
                  menuAdmin = sc.nextInt();
                  sc.nextLine();
               } catch(InputMismatchException e) {
                  System.out.println("\n 숫자로 입력해 주세요!");
                  sc.next();
                  continue;
                  
               } break;
               }
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            
            /* 카테고리 추가/수정/삭제 */
            if(menuAdmin == 0) {
               break;
            } else if(menuAdmin == 1) {
               System.out.println(">>>>      BurgerHI 카테고리 관리 시스템      <<<<");
               System.out.println("=================================================");
               System.out.println("               |                |               ");
               System.out.println("      1      　|       2        |       3      ");
               System.out.println(" 카테고리 추가 | 카테고리 수정  | 카테고리 삭제 ");
               System.out.println("               |                |               ");
               System.out.println("=================================================");
               System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
               /* 담 */
               List<CategoryDTO> categoryList = adminController.selectAllCategory();
               for(CategoryDTO cate : categoryList) {
                  System.out.println("▶ " + cate.getCode() + ". " + cate.getName() + "(추천 카테고리: " + cate.getRefName() + ")");
               }
               while (true) {
                  try {
                     System.out.print("\n  → 번호를 선택해 주세요: ");
                     categoryNum = sc.nextInt();
                     sc.nextLine();
                  } catch(InputMismatchException e) {
                     System.out.println("\n 숫자로 입력해 주세요!");
                     sc.next();
                     continue;
                     
                  } break;
                  }
               System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
               
               switch(categoryNum) {
                  case 0: flag = false; break;
                  case 1: adminController.insertCategory(); break;            // 카테고리 추가하는 메소드
                  case 2: adminController.updateCategory(); break;            // 카테고리 수정하는 메소드
                  case 3: adminController.deleteCategory(); break;            // 카테고리 삭제하는 메소드
                  default : System.out.println("번호를 잘못 입력하셨습니다!"); continue;   // 번호 잘못 입력할 경우 다시 처음으로 돌아가게 설정
               }
               
              /* 메뉴 추가/수정/삭제 */
            } else if(menuAdmin == 2) {
               System.out.println(">>>>        BurgerHI 메뉴 관리 시스템        <<<<");
               System.out.println("=================================================");
               System.out.println("               |                |               ");
               System.out.println("      1      　|       2        |       3       ");
               System.out.println("　 메뉴 추가 　|   메뉴 수정    |　 메뉴 삭제 　");
               System.out.println("               |                |               ");
               System.out.println("=================================================");
               System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
               System.out.println();
               List<MenuDTO> menuList = adminController.selectAllMenu();
               for(MenuDTO menu : menuList) {
                  System.out.println("▶ " + menu.getMenuCode() + ". " + menu.getName() + "$" + menu.getPrice());
                  System.out.println("　 카테고리 번호: " + menu.getCategoryCode() + " 주문 가능 여부: " + menu.getOrderable());
                  System.out.println("　  \"" + menu.getExplain() + "\"");
               }
               while (true) {
                  try {
                     System.out.print("\n  → 번호를 선택해 주세요: ");
                     menuNum = sc.nextInt();
                     sc.nextLine();
                  } catch(InputMismatchException e) {
                     System.out.println("\n 숫자로 입력해 주세요!");
                     sc.next();
                     continue;
                     
                  } break;
                  }
               System.out.println("\n\n\n\n\n");
               
               switch(menuNum) {
                  case 0: flag = false; break;
                  case 1: adminController.insertMenu(); break;               // 메뉴 추가하는 메소드
                  case 2: adminController.updateMenu(); break;               // 메뉴 수정하는 메소드
                  case 3: adminController.deleteMenu(); break;               // 메뉴 삭제하는 메소드
                  default : System.out.println("번호를 잘못 입력하셨습니다!"); continue;   // 번호 잘못 입력할 경우 다시 처음으로 돌아가게 설정
               }
               
            } else {
               System.out.println("번호를 잘못 입력하셨습니다!");
               continue;
            }
            
           /* 종류별 매출 확인 */
         } else if (num == 3) {
            System.out.println(">>>>        BurgerHI 매출 관리 시스템        <<<<");
            System.out.println("=================================================");
            System.out.println("                       |                       ");
            System.out.println("           1           |           2           ");
            System.out.println("      날짜별 매출      |     카테고리별 매출   ");
            System.out.println("                       |                       ");
            System.out.println("=================================================");
            System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
            while (true) {
               try {
                  System.out.print("\n  → 번호를 선택해 주세요: ");
                  salesNum = sc.nextInt();
                  sc.nextLine();
               } catch(InputMismatchException e) {
                  System.out.println("\n 숫자로 입력해 주세요!");
                  sc.next();
                  continue;
                  
               } break;
               }
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            
            switch(salesNum) {
               case 0: flag = false; break;
               case 1: adminController.SalesBydate(); break;      // 날짜별 매출 확인하는 메소드
               case 2: adminController.SalesCategory(); break;      // 카테고리별 매출 확인하는 메소드
            }
            /* 회원 정보 확인 */
         } else if(num == 4) {
            System.out.println(">>>>        BurgerHI 회원 정보 시스템        <<<<");
             System.out.println("=================================================");
             System.out.println("                       |                       ");
             System.out.println("           1           |           2           ");
             System.out.println("       회원 조회       |     회원 등급 수정    ");
             System.out.println("                       |                       ");
             System.out.println("=================================================");
             System.out.println(" * 프 로 그 램 종 료 는 0 번 을 눌 러 주 세 요. ");
          int selectUser = 0;   
             while (true) {
               try {
                  System.out.print("\n  → 번호를 선택해 주세요: ");
                  selectUser = sc.nextInt();
                  sc.nextLine();
               } catch (InputMismatchException e) {
                  System.out.println("\n 숫자로 입력해 주세요!");
                  sc.next();
                  continue;
               }
               break;
            }
             if(selectUser == 0) {
                exit = 0;
                break;
             } else if(selectUser == 1) {
                adminController.selectUserAll();
             } else if(selectUser == 2) {
                adminController.updateUserGrade();
             }
             
            
            /* 메뉴 주문 하기 */
         } else if(num == 5) {
            break;
         }
      } // while문 종료
      return exit;
   }
}