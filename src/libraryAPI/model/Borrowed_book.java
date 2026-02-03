package libraryAPI.model;

import java.sql.Date;

public class Borrowed_book {
    private Integer id;
    private Integer book_id;
    private Integer reader_id;
    private Date borrow_date;
    private Date return_date;
    private Enum<Status> status;

    public Borrowed_book(Integer id, Integer book_id, Integer reader_id, Date borrow_date, Date return_date, Enum<Status> status) {
        this.id = id;
        this.book_id = book_id;
        this.reader_id = reader_id;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.status = status;
    }

    public Borrowed_book() {

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

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }
}
