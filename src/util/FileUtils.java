package util;

import java.io.*;
import java.net.URL;
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
        InputStream is = this.getClass().getResourceAsStream(fileName);//"/resource/menus.txt"
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String s = "";
        while ((s = br.readLine())!=null)
            if(!StringUtils.isEmpty(s)){
                lines.add(s);
            }
        return lines;
    }

    public void insertLineText(String fileName, String content) throws IOException {
        URL url = this.getClass().getResource(fileName);//"/resource/menus.txt"
        String path = url.getPath();
        File file = new File(path);
        BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
        output.write("\r\n");//写入之前先换行
        output.write(content);
        output.flush();
        output.close();
    }


    public static void main(String[] args) throws IOException {
        FileUtils fileUtils = new FileUtils();
        /*List<String> results = fileUtils.readText("/resource/menus.txt");
        for(String line : results){
            System.out.println(line);
        }*/
        fileUtils.insertLineText("/resource/borrowLog.txt", "111");
    }
}
