package cn.bdqn.web.controller;

import cn.bdqn.entity.Books;
import cn.bdqn.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("Book")
public class BookVueController {
    @Autowired
    private IBooksService booksService;

    @GetMapping("/selectBook")
    @ResponseBody
    public ArrayList<Books> selectBook(){
        return booksService.selectAll2();
    }

    @GetMapping("/deleteBook")
    @ResponseBody
    public int deleteBook(int bookid){
        return booksService.delBook(bookid);
    }

    @GetMapping("/updateBook")
    @ResponseBody
    public int updateBook(int bookid,String bookname,int bookcounts,String detail){
        return booksService.updBook(bookid,bookname,bookcounts,detail);
    }

    @GetMapping("/addBook")
    @ResponseBody
    public int addBook(String bookname,int bookcounts,String detail){
        return booksService.addBook(bookname,bookcounts,detail);
    }

}
