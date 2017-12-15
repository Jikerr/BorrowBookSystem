package bean;

public class BorrowLog {

    private Integer id;
    private Integer bookId;
    private Integer userId;
    private String borrowDate;
    private String returnDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String toString(boolean spit) {
        if(!spit){
            return id+"\t"+bookId+"\t"+userId+"\t"+borrowDate+"\t"+returnDate;
        }
        return id+"#"+bookId+"#"+userId+"#"+borrowDate+"#"+returnDate;
    }
}
