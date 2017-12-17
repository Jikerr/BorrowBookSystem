package enums;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 10:52 2017/12/7
 * @Modified By :
 */
public class Constants {

    public static class FUNCTION {
        public static final int LOGIN_CUSTOMER = 2;//用户端登录
        public static final int REGISTER_CUSTOMER = 3;//用户端注册
        public static final int SHOW_ALLBOOKLIST = 4;//查看全部书籍列表
        public static final int SHOW_BORROWLOGS = 9;//查看全部借书记录
        public static final int MANAGE_USER = 12;//用户管理
    }

    public static class MSG {
        public static final String emptyUnit = "该功能程序员大叔正在努力制作 ~";//
        public static final String exitSystemSuccessed = "您已经成功退出系统~.";
        public static final String emptyMenu = "菜单不存在呦 ~";//
        public static final String unKnownMenuNumType = "请输入合法的菜单编号 , 他是可爱的数字~";//
        public static final String inputError = "输入有误 ， 请重新输入 ！";//
        public static final String tipMenuInput = "您的选择(输入序号) : ";
        public static final String tipFunctionInput = "选择命令 : ";
        public static final String selectCommandTip = "输入命令 : ";
        public static final String unFoundBook = "呜~ 这本书不存在呢 ! 可能管理员已经删除 , 请重新选择 !";
        public static final String borrowBookSuccess = "借书成功啦 ! 按照提示继续操作吧 !";
        public static final String pleaseInputUserName = "请输入用户名 : ";
        public static final String pleaseInputPassword = "请输入密码 : ";
        public static final String emptyUserName = "用户名不可为空 ! ";
        public static final String emptyPassword = "密码不可为空 ! ";
        public static final String userNotFoundAndPasswordIsError = "用户名或密码错误 !";
        public static final String userNotFound = "该用户不存在 !";
        public static final String unLegalUserName = "用户名不合法 , 请输入字母开头加数字且长度小于16的用户名 !";
        public static final String unLegalPassword = "密码不合法 , 请输入字母开头加数字且长度小于16的密码 !";
        public static final String shortPassword = "密码强度过低 , 请尝试增加密码长度 !";
        public static final String registerSuccessed = "注册成功 , 请牢记您的用户名并注意密码安全 !";
        public static final String loginSuccessed = "登录成功 , 即将返回 !";
        public static final String userProhibit = "该账号已被管理员封停!";
        public static final String userExisted = "该用户名已被占用!";

    }

    public static class BANNER {
        public static final String clearBanner = "我是清屏...";//
        public static final String worrowLogList = "======书籍名称=============借书人=============借书时间=============到期还书日======";//
        public static final String userListTitle = "======识别ID=============用户名=============密码=============真实姓名=============注册时间=============是否封停======";//
        public static final String userListDownL = "=====================================================================================================================";
        public static final String registerTitle = "========================注册========================";//
        public static final String loginTitle = "========================登录========================";//
    }


    public static class OPERATIONCODE {
        public static final String J = "J";//借书
        public static final String Z = "Z";//赞这本书
        public static final String exit = "0";//退出
        public static final String back = "B";//返回上一级
        public static final String D = "D";//登录
    }
}

