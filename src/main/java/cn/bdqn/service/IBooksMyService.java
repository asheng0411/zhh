package cn.bdqn.service;

import cn.bdqn.entity.Books;

import java.util.List;
import java.util.Map;

public interface IBooksMyService {
    public int selCount();

    public List<Books> selAll(Map<String,Object> map);

    public List<Books> selAlll();

    public List<Books> selBookname(String Bookname);

    public int delBook(int bookid);

    public int updBook(Books books);

    public int addBook(Books books);
}
