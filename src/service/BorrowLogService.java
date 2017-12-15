package service;

import bean.Book;
import bean.BorrowLog;
import bean.Menu;
import enums.Constants;
import util.FileUtils;
import util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 10:40 2017/12/6
 * @Modified By :
 */
public class BorrowLogService {

    private String fileName = "/resource/borrowLog.txt";

    public List<BorrowLog> getLogByUserId(Integer userId) {
        List<BorrowLog> userLogList = new ArrayList<BorrowLog>();
        List<BorrowLog> allLogList = allLogs();
        //迭代全部的借书日志 , 匹配传入的userId返回用户借书记录
        for (BorrowLog log : allLogList) {
            if (log.getUserId() == userId) {
                userLogList.add(log);
            }
        }
        return userLogList;
    }

    public List<BorrowLog> allLogs() {
        List<BorrowLog> logs = new ArrayList<BorrowLog>();
        FileUtils fileUtils = new FileUtils();
        try {
            List<String> lineArray = fileUtils.readText(fileName);

            for (int i = 1; i < lineArray.size(); i++) {//从1开始循环 , 忽略列名描述
                String line = lineArray.get(i).trim();//注意除去空格
                String[] borrowLogArray = line.split("#");
                BorrowLog log = new BorrowLog();
                log.setId(Integer.valueOf(borrowLogArray[0]));
                log.setBookId(Integer.valueOf(borrowLogArray[1]));
                log.setUserId(Integer.valueOf(borrowLogArray[2]));
                log.setBorrowDate(borrowLogArray[3]);
                log.setReturnDate(borrowLogArray[4]);
                logs.add(log);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }

    //插入借书记录
    public boolean insertLog(BorrowLog log) {
        FileUtils fileUtils = new FileUtils();
        try {
            fileUtils.insertLineText(fileName, log.toString(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void printAll() {
        BookService bookService = new BookService();
        MenuService menuService = new MenuService();
        FunctionController functionController = new FunctionController();
        List<BorrowLog> logs = allLogs();
        System.out.println("======书籍名称=============借书人=============借书时间=============到期还书日======");
        for (BorrowLog log : logs) {
            Book book = bookService.getBookById(log.getBookId());
            String bookName = book.getBookName();
            String borrowUserName = "未知用户";
            System.out.println(bookName + "\t\t" + borrowUserName + "\t" + log.getBorrowDate() + "\t\t" + log.getReturnDate());
        }
        System.out.println("===================================================================================");
        System.out.println(Constants.MSG.selectCommandTip + " 【B】退回上一级\t【0】 退出系统");
        String  input = functionController.onInput();
        while (true) {
            if (StringUtils.isEmpty(input) || input.length() > 1 ||
                    (!Constants.OPERATIONCODE.back.equals(input) && !Constants.OPERATIONCODE.exit.equals(input))) {
                System.out.println(Constants.MSG.inputError);
                input = functionController.onInput();
            }else{
                break;
            }
        }
        if (Constants.OPERATIONCODE.back.equals(input)) {
            menuService.back(false);
        } else if (Constants.OPERATIONCODE.exit.equals(input)) {
            menuService.systemExit();
        }
    }
}
