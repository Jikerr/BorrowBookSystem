package bean;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 10:34 2017/12/6
 * @Modified By :
 */
public class BooksType {
    private int id;
    private String typeName;
    private String createTime;
    private String createDate;
    private Long dateStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(Long dateStamp) {
        this.dateStamp = dateStamp;
    }
}
