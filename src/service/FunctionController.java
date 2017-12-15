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

    public void handlerAction(Integer fId){

        switch (fId){
            case Constants.FUNCTION.SHOW_ALLBOOKLIST:{
                BookService bookService = new BookService();
                bookService.showBooksList();
                bookService.booksListWorrowbook();
                /*String operationInputString = getOperationInput();*/

                /*offInput();*/
            }
        }
    }
    /*public void offInput(){
        waitingOperation = false;
    }*/

    public String onInput(){
        /*waitingOperation = true;
        while(waitingOperation){*/
            System.out.print("您的选择 (输入序号) : ");//注意此处不换行
            Scanner scanner = new Scanner(System.in);
            return scanner.next();
            /*operationInput = input;*/
        /*}*/
    }
}
