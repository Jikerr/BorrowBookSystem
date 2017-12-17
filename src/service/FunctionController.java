package service;


import com.sun.deploy.util.StringUtils;
import enums.Constants;

import java.util.Scanner;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 15:56 2017/12/6
 * @Modified By :
 */
public class FunctionController {

    /*private boolean waitingOperation = false;*/
    /*public static String operationInput;*/

   /* public String getOperationInput(){
        if(waitingOperation){
            return this.operationInput;
        }
        return null;
    }*/

    public void handlerAction(Integer fId) {
        MenuService menuService = new MenuService();

        switch (fId) {
            case Constants.FUNCTION.SHOW_ALLBOOKLIST: {
                BookService bookService = new BookService();
                bookService.showBooksList();
                bookService.booksListWorrowbook(true);
                break;
            }
            case Constants.FUNCTION.REGISTER_CUSTOMER: {
                UserService userService = new UserService();
                userService.register();
                break;
            }

            case Constants.FUNCTION.LOGIN_CUSTOMER: {
                UserService userService = new UserService();
                userService.login();
                break;
            }

            case Constants.FUNCTION.SHOW_BORROWLOGS: {
                BorrowLogService borrowLogService = new BorrowLogService();
                borrowLogService.printAll();
                break;
            }
            case Constants.FUNCTION.MANAGE_USER: {
                UserService userService = new UserService();
                userService.printAll();
                break;
            }
            default: {
                System.out.println("==================================");
                System.out.println("〉" + Constants.MSG.emptyUnit + "〈");
                System.out.println("==================================");
                menuService.loopBack();
                //menuService.handlerUserMenuInput();
                break;
            }
        }
    }
    /*public void offInput(){
        waitingOperation = false;
    }*/

    public String onInput(boolean noPrintTip) {
        /*waitingOperation = true;
        while(waitingOperation){*/
        if(!noPrintTip){
            System.out.print(Constants.MSG.tipFunctionInput);//注意此处不换行
        }
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
            /*operationInput = input;*/
        /*}*/
    }
}
