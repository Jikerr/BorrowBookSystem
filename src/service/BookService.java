package service;

import bean.Book;
import bean.Menu;
import util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 10:40 2017/12/6
 * @Modified By :
 */
public class BookService {

    public static List<Book> booksList = null;
    //初始化书籍列表
    public void initBooks() throws IOException {
        booksList = new ArrayList<>();

        FileUtils fileUtils = new FileUtils();//初始化书籍列表
        List<String> results = fileUtils.readText("/resource/books.txt");
        for(int i = 1;i<results.size();i++){//从1开始循环 , 忽略列名描述
            String line = results.get(i).trim();//注意除去空格
            String [] bookDetails = line.split("#");
            Book book = new Book();
            book.setId(Integer.valueOf(bookDetails[0]));
            book.setBookName(bookDetails[1]);
            book.setAuthor(bookDetails[2]);
            //book.setBooksType(Integer.valueOf(bookDetails[3]));
            book.setHot(Integer.valueOf(bookDetails[4])==1?true:false);
            book.setLikes(Integer.valueOf(bookDetails[5]));
            book.setCreateTime(bookDetails[6]);
            book.setCreateDate(bookDetails[7]);
            book.setDateStamp(Long.valueOf(bookDetails[8]));
            booksList.add(book);
        }
    }

    public void showBooksList(){
        System.out.println("===书名=============作者=============点赞数===");
        for(Book book : booksList){
            System.out.println(book.getBookName()+"\t"+book.getAuthor()+"\t"+book.getLikes());
        }
        System.out.println("==============================================");
    }



}