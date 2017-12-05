import bean.Menu;
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
        try {
            menuService.initMenus();
            menuService.printMenus();

            boolean isExit = false;
            while(!isExit){
                Scanner scanner = new Scanner(System.in);
                String input = scanner.next();
                menuService.handlerUserInput(input);

                if(input.equals("0")){
                    isExit = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
