package service;

import enums.Function;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 15:56 2017/12/6
 * @Modified By :
 */
public class FunctionController {
    public void handlerAction(Integer fId){
        switch (fId){
            case 4:{
                BookService bookService = new BookService();
                bookService.showBooksList();
                System.out.print("您的选择 (输入序号): ");
            }
        }
    }
}
