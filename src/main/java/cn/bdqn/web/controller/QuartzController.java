package cn.bdqn.web.controller;

import cn.bdqn.job.MyJob;
import cn.bdqn.web.util.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/job")
public class QuartzController {
    @Autowired
    private QuartzManager quartzManager;

    @GetMapping(value = "/addJob")
    public @ResponseBody
    Object addJob(
            HttpServletResponse response){
        try {
//            public void addJob(String jobName, String jobGroupName,
//                    String triggerName, String triggerGroupName, Class<? extends Job > jobClass,
//                    String time) {
            quartzManager.addJob("333", "333", "qqqq","wwww", MyJob.class,"0/5 * * * * ?");
            return ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
