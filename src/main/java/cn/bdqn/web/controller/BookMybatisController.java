package cn.bdqn.web.controller;

import cn.bdqn.entity.Books;

import cn.bdqn.mapper.BooksMapper;
import cn.bdqn.service.IBooksMyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("My")
public class BookMybatisController {

    @Autowired
    private IBooksMyService booksMyService;

    /**
     * 分页查询所有书籍
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/selAll")
    public String selAll(Integer pageNum, Model model){
        Map map=new HashMap();
        int pagenum=1;
        int pageSize=3;
        if(pageNum!=null && pageNum>0){
            pagenum=pageNum;
        }
        int num=booksMyService.selCount();
        int count=num%pageSize==0?num/pageSize:num/pageSize+1;
        map.put("pageNum",(pagenum-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Books> books=booksMyService.selAll(map);
        model.addAttribute("pageNum",pagenum);
        model.addAttribute("count",count);
        model.addAttribute("books",books);
        return "selAllMy";
    }

    @GetMapping("/selAlll")
    public String selalll(Integer pageNum, Model model){
        int pagenum=1;
        int pageSize=3;
        if(pageNum!=null && pageNum>0){
            pagenum=pageNum;
        }
        int num=booksMyService.selCount();
        int count=num%pageSize==0?num/pageSize:num/pageSize+1;
        PageHelper.startPage(pagenum,pageSize);
        List<Books> books=booksMyService.selAlll();
        PageInfo<Books> book=new PageInfo<>(books);
        model.addAttribute("pageNum",pagenum);
        model.addAttribute("count",count);
        model.addAttribute("books",book.getList());
        return "selAllMy";
    }

    @GetMapping("/selBookname")
    public String selBookname(Integer pageNum,String Bookname,Model model){
        int pagenum=1;
        int pageSize=3;
        if(pageNum!=null && pageNum>0){
            pagenum=pageNum;
        }
        int num=booksMyService.selCount();
        int count=num%pageSize==0?num/pageSize:num/pageSize+1;
        PageHelper.startPage((pagenum-1)*pageSize,pageSize);
        List<Books> books=booksMyService.selBookname(Bookname);
        PageInfo<Books> book=new PageInfo<>(books);
        model.addAttribute("books",book.getList());
        model.addAttribute("pageNum",pagenum);
        model.addAttribute("count",count);
        return "selAllMy";
    }

    /**
     * 下架书籍
     * @param bookid
     * @param model
     * @return
     */
    @GetMapping("/delBook")
    public String delBook(int bookid,Model model){
        booksMyService.delBook(bookid);
        return "redirect:/My/selAll";
    }

    @GetMapping("/upd")
    public String upd(int bookid,String bookname,int bookcounts,String detail,Model model){
        model.addAttribute("bookid",bookid);
        model.addAttribute("bookname",bookname);
        model.addAttribute("bookcounts",bookcounts);
        model.addAttribute("detail",detail);
        return "updBookMy";
    }

    @GetMapping("/updBook")
    public String updbook(int bookid,String bookname,int bookcounts,String detail){
        Books books=new Books(bookid,bookname,bookcounts,detail);
        booksMyService.updBook(books);
        return "redirect:/My/selAll";
    }

    @GetMapping("/add")
    public String add(){
        return "addBookMy";
    }

    @GetMapping("/addBook")
    public String addBook(String bookname,int bookcounts,String detail){
        Books books=new Books(bookname,bookcounts,detail);
        booksMyService.addBook(books);
        return "redirect:/My/selAll";
    }
}
