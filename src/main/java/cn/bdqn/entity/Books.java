package cn.bdqn.entity;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="books")
public class Books {
    private Integer bookid;
    private String bookname;
    private Integer bookcounts;
    private String detail;
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getBookcounts() {
        return bookcounts;
    }

    public void setBookcounts(Integer bookcounts) {
        this.bookcounts = bookcounts;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Books(Integer bookid, String bookname, Integer bookcounts, String detail) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookcounts = bookcounts;
        this.detail = detail;
    }

    public Books() {
    }

    public Books(String bookname, Integer bookcounts, String detail) {
        this.bookname = bookname;
        this.bookcounts = bookcounts;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", bookcounts=" + bookcounts +
                ", detail='" + detail + '\'' +
                '}';
    }
}
