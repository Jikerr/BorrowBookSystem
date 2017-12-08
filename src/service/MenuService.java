package service;

import bean.Menu;
import enums.MenuAction;
import util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 11:30 2017/12/5
 * @Modified By :
 */
public class MenuService {
    String menusFileName = "/resource/menus.txt";
    public static Menu menuIndex = null;
    public static List<Menu> menuList = null;

    //初始化菜单
    public void initMenus() throws IOException {

        FileUtils fileUtils = new FileUtils();//获取菜单数据
        List<String> results = fileUtils.readText("/resource/menus.txt");
        menuList = formatMenuList(results);

        Menu rootMenu = new Menu();
        rootMenu.setId(0);
        rootMenu.setParentId(0);
        rootMenu.setLowerLevel(treeMenuList(menuList, rootMenu.getParentId()));

        menuIndex = rootMenu;
        menuList.add(rootMenu);
    }

    public void printMenus() {
        for (Menu menu : this.menuIndex.getLowerLevel()) {
            System.out.println(menu.getShowNum() + "." + menu.getMenuName());
        }
    }

    public void enter(int showNum) {
        clear();
        Menu targetMenu = getMenuByShowNum(showNum);
        menuIndex = targetMenu;//重新定位当前菜单位置
        System.out.println("====================================================");
        for (Menu menu : targetMenu.getLowerLevel()) {
            System.out.println(menu.getShowNum() + "." + menu.getMenuName());
        }
        System.out.println("====================================================");
    }

    public void back() {
        clear();
        Menu menuOnce = this.menuIndex.getUpperLevel().get(0);

        Menu targetMenu = getMenuById(menuOnce.getParentId());
        menuIndex = targetMenu;
        System.out.println("====================================================");
        for (Menu menu : targetMenu.getLowerLevel()) {
            System.out.println(menu.getShowNum() + "." + menu.getMenuName());
        }
        System.out.println("====================================================");
    }

    //将菜单以树的形式递归出来
    public List<Menu> treeMenuList(List<Menu> menuList, int parentId) {
        List<Menu> childMenu = new ArrayList<>();
        for (Menu menu : menuList) {
            int menuId = menu.getId();
            int pid = menu.getParentId();

            if (parentId == pid) {
                List<Menu> c_node = treeMenuList(menuList, menuId);
                List<Menu> upperLevel = new ArrayList<>();
                for (Menu smenu : this.menuList) {
                    if (pid == smenu.getParentId()) {
                        upperLevel.add(smenu);
                    }
                }
                menu.setUpperLevel(upperLevel);
                menu.setLowerLevel(c_node);
                childMenu.add(menu);
            }
        }
        return childMenu;
    }


    //将基础数据格式化为标准菜单列表 txt--> Menu
    public List<Menu> formatMenuList(List<String> results) {
        List<Menu> menusList = new ArrayList<>();

        for(int i = 1;i<results.size();i++){
        //for (String menuCell : results) {
            String menuCell = results.get(i);
            String[] cellDetail = menuCell.trim().split("#");
            String showNumber = cellDetail[0];
            String menuName = cellDetail[1];
            String id = cellDetail[2];
            String parentId = cellDetail[3];
            String action = null;
            if (cellDetail.length == 5) {
                action = cellDetail[4];
            }

            Menu menu = new Menu();
            menu.setId(Integer.valueOf(id));
            menu.setParentId(Integer.valueOf(parentId));
            menu.setMenuName(menuName);
            menu.setShowNum(Integer.valueOf(showNumber));
            menu.setAction(action);

            menusList.add(menu);
        }
        return menusList;
    }

    public Menu getMenuById(int id) {
        Menu menu = null;
        for (Menu menuTemp : menuList) {
            if (menuTemp.getId() == id) {
                menu = menuTemp;
                break;
            }
        }
        return menu;
    }

    public Menu getMenuByShowNum(int showNum) {
        Menu targetMenu = new Menu();

        //获取当前节点的所有菜单
        for (Menu menu : this.menuIndex.getLowerLevel()) {
            if (menu.getShowNum() == showNum) {//匹配输入
                targetMenu = menu;
                break;
            }
        }
        return targetMenu;
    }

    public void handlerUserInput(String input) {
        Menu menu = getMenuByShowNum(Integer.valueOf(input));
        if (MenuAction._BACK.getCode().equals(menu.getAction())) {
            back();
        } else if (MenuAction._FUNCTION.getCode().equals(menu.getAction())) {
            clear();
            FunctionController functionController = new FunctionController();
            Menu selectFunctionMenu = getMenuByShowNum(Integer.valueOf(input));
            functionController.handlerAction(selectFunctionMenu.getId());
            //System.out.println("功能建设中(已经返回上一级)...");
            //printMenus();
        }else if (MenuAction._EXIT.getCode().equals(menu.getAction())) {
            clear();
            System.out.println("您已经成功退出系统...");
            System.exit(0);
        }else {
            enter(Integer.valueOf(input));
        }
    }

    public void clear() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException e) {
            for (int i = 0; i < 400; i++) {
                System.out.println("\n");
                if (i == 200) {
                    System.out.println("我是清屏...");
                }
            }
        }
    }
}
