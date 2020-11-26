package cn.bdqn.service.impl;

import cn.bdqn.entity.Books;
import cn.bdqn.mapper.BooksMapper;
import cn.bdqn.service.IBooksMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BooksMyserviceImpl implements IBooksMyService {

    @Autowired
    private BooksMapper booksMapper;

    @Override
    public int selCount() {
        return booksMapper.selCount();
    }

    @Override
    public List<Books> selAll(Map<String, Object> map) {
        return booksMapper.selAll(map);
    }

    @Override
    public List<Books> selAlll() {
        return booksMapper.selAlll();
    }

    @Override
    public List<Books> selBookname(String Bookname) {
        return booksMapper.selBookname(Bookname);
    }

    @Override
    public int delBook(int bookid) {
        return booksMapper.delBook(bookid);
    }

    @Override
    public int updBook(Books books) {
        return booksMapper.updBook(books);
    }

    @Override
    public int addBook(Books books) {
        return booksMapper.addBook(books);
    }
}
