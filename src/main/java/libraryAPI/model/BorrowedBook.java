package libraryAPI.model;

import java.time.LocalDate;
import java.util.Date;

public class BorrowedBook{
    private Integer id;
    private Integer book_id;
    private Integer reader_id;
    private Date borrow_date;
    private Date return_date;
    private Status status;

    public BorrowedBook(Integer id, Integer book_id, Integer reader_id, Date borrow_date, Date return_date, Status status) {
        this.id = id;
        this.book_id = book_id;
        this.reader_id = reader_id;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.status = status;
    }

    public BorrowedBook() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getReader_id() {
        return reader_id;
    }

    public void setReader_id(Integer reader_id) {
        this.reader_id = reader_id;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
