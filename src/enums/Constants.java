package enums;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 10:52 2017/12/7
 * @Modified By :
 */
public class Constants {

    public static class FUNCTION {
        public static final int SHOW_ALLBOOKLIST = 4;//查看全部书籍列表
        public static final int SHOW_BORROWLOGS = 9;//查看全部借书记录
    }

    public static class MSG {
        public static final String emptyUnit = "该功能程序员大叔正在努力制作 ~";//
        public static final String emptyMenu = "菜单不存在呦 ~";//
        public static final String unKnownMenuNumType = "请输入合法的菜单编号 , 他是可爱的数字~";//
        public static final String inputError = "输入有误 ， 请重新输入 ！";//
        public static final String tipMenuInput = "您的选择(输入序号) : ";
        public static final String tipFunctionInput = "选择命令 : ";
        public static final String selectCommandTip = "输入命令 : ";
        public static final String unFoundBook = "呜~ 这本书不存在呢 ! 可能管理员已经删除 , 请重新选择 !";
        public static final String borrowBookSuccess = "借书成功啦 ! 按照提示继续操作吧 !";
    }

    public static class OPERATIONCODE {
        public static final String J = "J";//借书
        public static final String Z = "Z";//赞这本书
        public static final String exit = "0";//退出
        public static final String back = "B";//返回上一级
    }


}
