package service;

import bean.Book;
import bean.BorrowLog;
import bean.UserInfo;
import enums.Constants;
import util.DateUtils;
import util.FileUtils;
import util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 10:40 2017/12/6
 * @Modified By :
 */
public class UserService {

    private String fileName = "/resource/userInfo.txt";
    private static String loginUser = null;

    public List<UserInfo> getUserById(Integer userId) {
        List<UserInfo> resultList = new ArrayList<UserInfo>();
        List<UserInfo> allUserList = allUsers();
        //迭代全部的借书日志 , 匹配传入的userId返回用户借书记录
        for (UserInfo user : allUserList) {
            if (user.getId() == userId) {
                resultList.add(user);
            }
        }
        return resultList;
    }

    public List<UserInfo> allUsers() {
        List<UserInfo> Users = new ArrayList<UserInfo>();
        FileUtils fileUtils = new FileUtils();
        try {
            List<String> lineArray = fileUtils.readText(fileName);

            for (int i = 1; i < lineArray.size(); i++) {//从1开始循环 , 忽略列名描述
                String line = lineArray.get(i).trim();//注意除去空格
                String[] userInfoArray = line.split("#");

                UserInfo user = new UserInfo();
                user.setId(Integer.valueOf(userInfoArray[0]));
                user.setUserName(userInfoArray[1]);
                user.setPassword(userInfoArray[2]);
                user.setRealName(userInfoArray[3]);
                user.setRegisterDate(userInfoArray[4]);
                user.setProhibitLogin(Boolean.valueOf(userInfoArray[5]));
                Users.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Users;
    }

    public boolean insertUser(UserInfo userInfo) {
        FileUtils fileUtils = new FileUtils();
        try {
            fileUtils.insertLineText(fileName, userInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void printAll() {
        BookService bookService = new BookService();
        MenuService menuService = new MenuService();
        FunctionController functionController = new FunctionController();
        List<UserInfo> Users = allUsers();
        System.out.println(Constants.BANNER.userListTitle);
        for (UserInfo user : Users) {
            System.out.println(user.getId() + "\t\t" + user.getUserName() + "\t" + user.getPassword() + "\t\t" + user.getRealName()+"\t\t"+user.getRegisterDate()+"\t\t"+user.isProhibitLogin());
        }
        System.out.println(Constants.BANNER.userListDownL);
        System.out.println(Constants.MSG.selectCommandTip + " 【B】退回上一级\t【0】 退出系统");
        String  input = functionController.onInput(false);
        while (true) {
            if (StringUtils.isEmpty(input) || input.length() > 1 ||
                    (!Constants.OPERATIONCODE.back.equals(input) && !Constants.OPERATIONCODE.exit.equals(input))) {
                System.out.println(Constants.MSG.inputError);
                input = functionController.onInput(false);
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

    public void register(){
        MenuService menuService = new MenuService();

        FunctionController functionController = new FunctionController();
        System.out.println(Constants.BANNER.registerTitle);
        String userName = null;
        String password = null;
        while(true){
            System.out.print(Constants.MSG.pleaseInputUserName);
            userName = functionController.onInput(true);
            if(StringUtils.isEmpty(userName)){//校验空用户名
                System.out.println("===================================================");
                System.out.println("〉" + Constants.MSG.emptyUserName + "〈");
                System.out.println("===================================================");
                continue;
            }
            if(!StringUtils.isLegalUserName(userName)){//校验用户名合法性
                System.out.println("=============================================================");
                System.out.println("〉" + Constants.MSG.unLegalUserName + "〈");
                System.out.println("=============================================================");
                continue;
            }
            UserInfo userInfo = getUserByUserName(userName);
            if(null!=userInfo){//校验用户名是否占用
                System.out.println("=====================");
                System.out.println("〉" + Constants.MSG.userExisted + "〈");
                System.out.println("=====================");
                continue;
            }
            break;
        }

        while(true){
            System.out.print(Constants.MSG.pleaseInputPassword);
            password = functionController.onInput(true);

            if(StringUtils.isEmpty(password)){//校验空密码
                System.out.println("==========================================");
                System.out.println("〉" + Constants.MSG.emptyPassword + "〈");
                System.out.println("==========================================");
                continue;
            }
            if(!StringUtils.isLegalPassword(password)){//校验密码合法性
                System.out.println("=============================================================");
                System.out.println("〉" + Constants.MSG.unLegalPassword + "〈");
                System.out.println("=============================================================");
                continue;
            }
            if(password.length()<6){//校验短密码
                System.out.println("=======================================");
                System.out.println("〉" + Constants.MSG.shortPassword + "〈");
                System.out.println("=======================================");
                continue;
            }
            break;
        }
        //注册阶段
        UserInfo registerUserInfo = new UserInfo();
        registerUserInfo.setId(Long.valueOf(new Date().getTime()).intValue());
        registerUserInfo.setUserName(userName);
        registerUserInfo.setPassword(password);
        registerUserInfo.setRegisterDate(DateUtils.now(DateUtils.yyyy年MM月dd日_HHmmss_pattern));
        registerUserInfo.setProhibitLogin(false);
        insertUser(registerUserInfo);

        System.out.println("=============================================================");
        System.out.println(Constants.MSG.registerSuccessed);//提示注册成功
        System.out.println("=============================================================");
        System.out.println(Constants.MSG.selectCommandTip + "【D】登录\t\t【B】退回上一级\t\t【0】退出系统");
        System.out.print(Constants.MSG.tipFunctionInput);
        String input = functionController.onInput(true);
        if (Constants.OPERATIONCODE.exit.equals(input)) {
            menuService.systemExit();
        } else if (Constants.OPERATIONCODE.back.equals(input)) {
            menuService.back(false);
            return;
        }else if(Constants.OPERATIONCODE.D.equals(input)){
            login();
        }
    }

    public boolean login(){
        MenuService menuService = new MenuService();
        FunctionController functionController = new FunctionController();
        System.out.println(Constants.BANNER.loginTitle);
        String userName = null;
        String password = null;
        inputUserName : while(true){
            System.out.print(Constants.MSG.pleaseInputUserName);
            userName = functionController.onInput(true);

            if(StringUtils.isEmpty(userName)){//校验空用户名 ,密码
                System.out.println("===================================================");
                System.out.println("〉" + Constants.MSG.emptyUserName + "〈");
                System.out.println("===================================================");
                continue;
            }
            break;
        }
        while(true){
            System.out.print(Constants.MSG.pleaseInputPassword);
            password = functionController.onInput(true);
            if(StringUtils.isEmpty(password)){//校验用户名合法性
                System.out.println("=============================================================");
                System.out.println("〉" + Constants.MSG.emptyPassword + "〈");
                System.out.println("=============================================================");
                continue;
            }
            break;
        }

        UserInfo userInfo = getUserByUserName(userName);
        if(null==userInfo){
            System.out.println("==================");
            System.out.println("〉" + Constants.MSG.userNotFound + "〈");
            System.out.println("==================");
            return login();//重新登录
        }

        if(userInfo.isProhibitLogin()){
            System.out.println("=============================================================");
            System.out.println("〉" + Constants.MSG.userProhibit + "〈");
            System.out.println("=============================================================");
            return login();
        }
        if(null!=userInfo){
            String userPassword = userInfo.getPassword();
            if(userName.equals(userInfo.getUserName()) && userPassword.equals(password)){
                System.out.println("====================================================");
                System.out.println(Constants.MSG.loginSuccessed);
                System.out.println("====================================================");
                menuService.back(false);
            }else{
                System.out.println("=============================================================");
                System.out.println(Constants.MSG.userNotFoundAndPasswordIsError);
                System.out.println("=============================================================");
                return login();
            }
        }
        return true;
    }


    public UserInfo getUserByUserName(String userName){
        if(StringUtils.isEmpty(userName)){
            return null;
        }
        List<UserInfo> allUsers = allUsers();
        for(UserInfo user : allUsers){
            String userNameTemp = user.getUserName();
            if(userName.equals(userNameTemp)){
                return user;
            }
        }
        return null;
    }
}
