package enums;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 16:53 2017/12/5
 * @Modified By :
 */
public enum MenuAction {
    _BACK("back", "返回"),
    _FUNCTION("function", "功能");

    private String code;
    private String remark;

    MenuAction(String code, String remark) {
        this.code = code;
        this.setRemark(remark);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static String getNameByCode(String code) {
        for (MenuAction menuAction : MenuAction.values()) {
            if (menuAction.getCode().equals(code)) {
                return menuAction.getRemark();
            }
        }
        return null;
    }
}
