package cn.bdqn.web.controller;

import cn.bdqn.entity.Books;
import cn.bdqn.entity.Emp;
import cn.bdqn.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Books books;

    @Autowired
    private Emp emp;

    @GetMapping("test1")
    public String test1(){
        return "index";
    }

    @GetMapping("test2")
    @ResponseBody
    public String test2(){
        return books.toString();
    }

    @GetMapping("test3")
    @ResponseBody
    public Emp test3(){
        return emp;
    }

    @GetMapping("test4")
    public String test4(Model model){
        model.addAttribute("mes","!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "login";
    }

    @RequestMapping("/testException")
    public String testException(){
        int i = 10 / 0;
        return "index";
    }

    @RequestMapping("/testException2")
    public String testException2(){
        String str = null;
        System.out.println(str.length());
        return "index";
    }

    @RequestMapping("/testException3")
    public String testException3(String username) {
        if(username == null){
            throw new BizException("-1","用户姓名不能为空！");
        }
        return "index";
    }

    @RequestMapping("/testDruid")
    @ResponseBody
    public String testDruid() {
        String sql = "SELECT * from books";
        String str = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = dataSource.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                str = rs.getString(2);
                System.out.println(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }
}
