package cn.bdqn.dao;

import cn.bdqn.entity.Books;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BooksDao extends BaseDao {

    /**
     * 查询数据总条数
     */
    public int selectCount(){
        int row=0;
        String sql="SELECT COUNT(*) FROM books";
        ResultSet rs=executeQuery(sql,null);
        try{
            if(rs.next()){
                row=rs.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 分页查询所有信息
     */
    public ArrayList<Books> selectAll(Integer pageNum, Integer pageSize) {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "select * from books limit ?,?";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add((pageNum - 1) * pageSize);
        params.add(pageSize);
        ResultSet rs = executeQuery(sql, params);
        try{
            while (rs.next()) {

                Books book = new Books(rs.getInt("bookid"), rs.getString("bookname"),
                        rs.getInt("bookcounts"), rs.getString("detail"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 查询所有信息
     */
    public ArrayList<Books> selectAll2() {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "select * from books";
        ResultSet rs = executeQuery(sql, null);
        try {
            while (rs.next()) {
                Books book = new Books(rs.getInt("bookid"), rs.getString("bookname"),
                        rs.getInt("bookcounts"), rs.getString("detail"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 新增
     */
    public int addBook(String bookname, int bookcounts, String detail) {
        int row = 0;
        String sql = "insert into books values(null,?,?,?)";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(bookname);
        params.add(bookcounts);
        params.add(detail);
        row = executeNoQuery(sql, params);
        return row;
    }

    /**
     * 删除
     */
    public int delBook(int bookid) {
        int row = 0;
        String sql = "delete from books where bookid=?";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(bookid);
        row = executeNoQuery(sql, params);
        return row;
    }

    /**
     * 修改
     */
    public int updBook(int bookid, String bookname, int bookcounts, String detail) {
        int row = 0;
        String sql = "update books SET bookname=?,bookcounts=?,detail=? WHERE bookid=?";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(bookname);
        params.add(bookcounts);
        params.add(detail);
        params.add(bookid);
        row = executeNoQuery(sql, params);
        return row;
    }
}
