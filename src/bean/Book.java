package bean;

/**
 * @Author: zh
 * @Description :
 * @Date Created in 10:36 2017/12/6
 * @Modified By :
 */
public class Book {
    private int id;
    private String bookName;
    private String author;
    private BooksType booksType;
    private int likes;
    private boolean isHot;
    private String createTime;
    private String createDate;
    private Long dateStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BooksType getBooksType() {
        return booksType;
    }

    public void setBooksType(BooksType booksType) {
        this.booksType = booksType;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
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
