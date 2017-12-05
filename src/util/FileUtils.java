package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zh
 * @Description : 文件工具
 * @Date Created in 12:35 2017/12/5
 * @Modified By :
 */
public class FileUtils {

    public List<String> readText(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();
        InputStream is=this.getClass().getResourceAsStream(fileName);//"/resource/menus.txt"
        BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
        String s="";
        while((s=br.readLine())!=null)
            lines.add(s);

        return lines;
    }

   /* public static void main(String[] args) throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> results = fileUtils.readText("/resource/menus.txt");
        for(String line : results){
            System.out.println(line);
        }
    }
*/
}
