import bean.Menu;
import service.BookService;
import service.MenuService;
import service.SceneService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //打印欢迎页
        SceneService sceneService = new SceneService();
        sceneService.printWelcomePage();
        //初始化主菜单
        MenuService menuService = new MenuService();
        BookService bookService = new BookService();

        try {
            menuService.initMenus();//初始化菜单数据
            bookService.initBooks();//初始化书籍数据
            menuService.printMenus();//打印主菜单
            menuService.handlerUserMenuInput();//接收用户在菜单上的输入

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
