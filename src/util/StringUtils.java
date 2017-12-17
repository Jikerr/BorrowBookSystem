package util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(String string) {
        if (null == string || "".equals(string)) {
            return true;
        }
        return false;
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    //英文开头的 , 数字加字幕组成,最大长度16
    public static boolean isLegalUserName(String userName){
        Pattern p = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}");
        Matcher m = p.matcher(userName);
        boolean flg = m.matches();
        return flg;
    }
    //数字加字母组成 , 最大长度16
    public static boolean isLegalPassword(String password){
        Pattern p = Pattern.compile("[a-zA-Z0-9]{1,16}");
        Matcher m = p.matcher(password);
        boolean flg = m.matches();
        return flg;
    }

    public static void main(String[] args) {
        String str = "hhhh13212313213";
        System.out.println(isLegalUserName(str));
    }
}
