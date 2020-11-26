package cn.bdqn.service;


import cn.bdqn.entity.Books;

import java.util.ArrayList;

public interface IBooksService {
    public int selectCount();

    public ArrayList<Books> selectAll(Integer pageNum, Integer pageSize);

    public ArrayList<Books> selectAll2();

    public int addBook(String bookname,int bookcounts,String detail);

    public int delBook(int bookid);

    public int updBook(int bookid,String bookname,int bookcounts,String detail);
}
