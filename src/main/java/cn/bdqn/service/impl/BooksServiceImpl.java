package cn.bdqn.service.impl;

import cn.bdqn.dao.BooksDao;
import cn.bdqn.entity.Books;
import cn.bdqn.service.IBooksService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BooksServiceImpl implements IBooksService {
    BooksDao dao=new BooksDao();

    public int selectCount(){
        return dao.selectCount();
    }

    public ArrayList<Books> selectAll(Integer pageNum, Integer pageSize) {
        return dao.selectAll(pageNum,pageSize);
    }

    public ArrayList<Books> selectAll2(){
        return dao.selectAll2();
    }

    public int addBook(String bookname, int bookcounts, String detail) {
        return dao.addBook(bookname, bookcounts, detail);
    }

    public int delBook(int bookid) {
        return dao.delBook(bookid);
    }

    public int updBook(int bookid, String bookname, int bookcounts, String detail) {
        return dao.updBook(bookid, bookname, bookcounts, detail);
    }
}
