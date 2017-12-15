package service;

import bean.Book;
import bean.BorrowLog;
import bean.Menu;
import com.sun.org.apache.xpath.internal.operations.Bool;
import enums.Constants;
import util.DateUtils;
import util.FileUtils;
import util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 10:40 2017/12/6
 * @Modified By :
 */
public class BookService {

    public static List<Book> booksList = null;
    private String fileName = "/resource/books.txt";
    //初始化书籍列表
    public void initBooks() throws IOException {
        booksList = new ArrayList<>();

        FileUtils fileUtils = new FileUtils();//初始化书籍列表
        List<String> results = fileUtils.readText(fileName);
        for (int i = 1; i < results.size(); i++) {//从1开始循环 , 忽略列名描述
            String line = results.get(i).trim();//注意除去空格
            String[] bookDetails = line.split("#");
            Book book = new Book();
            book.setId(Integer.valueOf(bookDetails[0]));
            book.setBookName(bookDetails[1]);
            book.setAuthor(bookDetails[2]);
            //book.setBooksType(Integer.valueOf(bookDetails[3]));
            book.setHot(Integer.valueOf(bookDetails[4]) == 1 ? true : false);
            book.setLikes(Integer.valueOf(bookDetails[5]));
            book.setCreateTime(bookDetails[6]);
            book.setCreateDate(bookDetails[7]);
            book.setDateStamp(Long.valueOf(bookDetails[8]));
            booksList.add(book);
        }
    }

    public Book getBookById(Integer id) {
        for (Book book : booksList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }


    public void showBooksList() {
        System.out.println("===编号=============书名=============作者=============点赞数====");
        for (Book book : booksList) {
            System.out.println(book.getId() + "\t" + book.getBookName() + "\t" + book.getAuthor() + "\t" + book.getLikes());
        }
        System.out.println("================================================================");
    }

    //书籍列表借书功能
    public void booksListWorrowbook(boolean promptCommand) {
        MenuService menuService = new MenuService();//菜单服务类
        BorrowLogService borrowLogService = new BorrowLogService();//借书记录服务类
        FunctionController controller = new FunctionController();//功能控制器
        if(promptCommand){//只有传入提示命令才打印这句话
            System.out.println(Constants.MSG.selectCommandTip + "  [J编号]借书\t[Z编号]赞一下这本书\t【B】退回上一级\t【0】 退出系统");
        }
        String input = controller.onInput();
        if (StringUtils.isEmpty(input)) {
            System.out.println(Constants.MSG.inputError);
            booksListWorrowbook(true);//重新调用借书方法
            return;
        }
       /* String str = FunctionController.operationInput;*/
        String inputOperationCode = input.substring(0, 1);
       /*System.out.println("inputOperationCode : "+inputOperationCode);*/
        if (OperationCode.matchCode(inputOperationCode)) {//匹配到了对应命令
            if (Constants.OPERATIONCODE.J.equals(inputOperationCode)) {//借书
                if (input.length() < 2) {//判断是否输入了后面的编号
                    System.out.println(Constants.MSG.inputError);
                    booksListWorrowbook(true);
                    return;
                }
                String inputNumString = input.substring(1, input.length());
                Integer inputNum = Integer.valueOf(inputNumString);
                Book selectedbook = getBookById(inputNum);
                if (null == selectedbook) {
                    System.out.println(Constants.MSG.unFoundBook);//提示找不到这个书了
                    booksListWorrowbook(true);
                    return;
                } else {
                    BorrowLog log = new BorrowLog();
                    log.setId(Long.valueOf(new Date().getTime()).intValue());//暂时使用时间戳
                    log.setUserId(1);
                    log.setBookId(selectedbook.getId());
                    log.setBorrowDate(DateUtils.now(DateUtils.yyyy年MM月dd日_HHmmss_pattern));
                    log.setReturnDate(DateUtils.now(DateUtils.yyyy年MM月dd日_HHmmss_pattern));//@TODO 暂时未做
                    borrowLogService.insertLog(log);
                    System.out.println(Constants.MSG.borrowBookSuccess);//提示借书成功
                    booksListWorrowbook(false);//继续做后续操作
                    return;
                }
            }

        } else if (Constants.OPERATIONCODE.exit.equals(inputOperationCode)) {
            menuService.systemExit();
        } else if (Constants.OPERATIONCODE.back.equals(inputOperationCode)) {
            menuService.back(false);
            return;
        } else {
            System.out.println(Constants.MSG.inputError);
            booksListWorrowbook(true);//重新调用借书方法
            return;
        }
    }
}

enum OperationCode {
    _J("J", "借书"),
    _Z("Z", "赞书");

    private String code;
    private String remark;

    OperationCode(String code, String remark) {
        this.code = code;
        this.setRemark(remark);
    }

    //匹配操作code
    public static boolean matchCode(String code) {
        for (OperationCode operationCode : OperationCode.values()) {
            if (operationCode.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static String getNameByCode(String code) {
        for (OperationCode operationCode : OperationCode.values()) {
            if (operationCode.getCode().equals(code)) {
                return operationCode.getRemark();
            }
        }
        return null;
    }
}
