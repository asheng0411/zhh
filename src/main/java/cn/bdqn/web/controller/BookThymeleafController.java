package cn.bdqn.web.controller;

import cn.bdqn.entity.Books;
import cn.bdqn.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;

@Controller
@RequestMapping("Books")
public class BookThymeleafController {
    @Autowired
    private IBooksService booksService;

    @GetMapping("/selectBooks")
    public String selectBook(Integer pageNum,Model model){
        int pagenum=1;
        if(pageNum!=null && pageNum>0){
             pagenum=pageNum;
        }
        int pageSize=3;
        int num=booksService.selectCount();
        int count=num%pageSize==0?num/pageSize:num/pageSize+1;
        ArrayList<Books> books=booksService.selectAll(pagenum,pageSize);
        model.addAttribute("pageNum",pagenum);
        model.addAttribute("count",count);
        model.addAttribute("books",books);
        return "selAll";
    }

    @GetMapping("/deleteBooks")
    public String deleteBook(int bookid){
        int row=booksService.delBook(bookid);
        return "redirect:/Books/selectBooks";
    }

    @GetMapping("/upd")
    public String upd(int bookid,String bookname,int bookcounts,String detail,Model model){
        model.addAttribute("bookid",bookid);
        model.addAttribute("bookname",bookname);
        model.addAttribute("bookcounts",bookcounts);
        model.addAttribute("detail",detail);
        return "updBooks";
    }

    @GetMapping("/updateBooks")
    public String updateBook(int bookid,String bookname,int bookcounts,String detail){
        int row=booksService.updBook(bookid,bookname,bookcounts,detail);
        return "redirect:/Books/selectBooks";
    }

    @GetMapping("/add")
    public String add(){
        return "addBooks";
    }

    @GetMapping("/addBooks")
    public String addBook(String bookname,int bookcounts,String detail){
        booksService.addBook(bookname,bookcounts,detail);
        return  "redirect:/Books/selectBooks";
    }


}
