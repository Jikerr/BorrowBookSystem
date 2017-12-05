package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zh
 * @Description : 菜单实体类
 * @Date Created in 11:26 2017/12/5
 * @Modified By :
 */
public class Menu {

    private int showNum;
    private int id;
    private int parentId;
    private String menuName;
    private String action;

    private List<Menu> UpperLevel = new ArrayList<Menu>();
    private List<Menu> lowerLevel = new ArrayList<Menu>();

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<Menu> getUpperLevel() {
        return UpperLevel;
    }

    public void setUpperLevel(List<Menu> upperLevel) {
        UpperLevel = upperLevel;
    }

    public List<Menu> getLowerLevel() {
        return lowerLevel;
    }

    public void setLowerLevel(List<Menu> lowerLevel) {
        this.lowerLevel = lowerLevel;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
