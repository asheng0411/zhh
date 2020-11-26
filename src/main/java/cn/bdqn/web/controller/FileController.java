package cn.bdqn.web.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * 文件上传下载
 */
@Controller
public class FileController {

    @GetMapping("/toFileView")
    public String toFileView() {
        return "file";
    }

    /**
     * 单文件上传
     * @param file
     * @return
     */
    @PostMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(MultipartFile file) {
        //1.校验文件是否存在
        if (file == null || file.isEmpty()) {
            return "文件为空，请重新选择文件";
        } else {
            String path = "E:/file";
            //2.解决文件名重复问题
            String filename = file.getOriginalFilename();//得到文件名 111.jpg
            String suffix = filename.substring(filename.lastIndexOf(".")); //得到 .jpg 后缀
            String prefix = UUID.randomUUID().toString();//动态生成唯一的 UUID 序列号
            filename = prefix + suffix; // 111zxcsdasdasdasd.jps 序列号跟后缀拼接

            //3.文件保存
            File newFile = new File(path + "/" + filename);
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdir();//如果file目录不存在就创建
            }
            //4.文件保存
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
                return "文件上传失败，原因：" + e.getMessage();
            }
        }
        return "文件上传成功";
    }

    /**
     * 多文件上传
     * @param files
     * @return
     */
    @PostMapping("/manyFileUpload")
    @ResponseBody
    public String manyFileUpload(MultipartFile[] files) {  //这里可以用数组或者集合List来接受多个
        //1.校验文件是否存在
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                return "文件名为空，请选择文件上传";
            } else {
                String path = "E:/file";
                //2.解决文件名重复问题
                String filename = file.getOriginalFilename();//得到文件名 111.jpg

                String suffix = filename.substring(filename.lastIndexOf(".")); //得到 .jpg 后缀
                String prefix = UUID.randomUUID().toString();//动态生成唯一的 UUID 序列号
                filename = prefix + suffix; // 111zxcsdasdasdasd.jps 序列号跟后缀拼接
                //3.文件保存
                File newFile = new File(path + "/" + filename);
                if (!newFile.getParentFile().exists()) {
                    newFile.getParentFile().mkdir();//如果file目录不存在就创建
                }
                //4.文件保存
                try {
                    file.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "文件上传失败，原因：" + e.getMessage();
                }
            }
        }
        return "文件上传成功";
    }

    /**
     * 文件下载
     * @param fileName
     * @param response
     * @return
     */
    @PostMapping("/fileDownload")
    @ResponseBody
    public String fileDownload(String fileName, HttpServletResponse response){
        //检查文件名是否为空
        if(fileName ==null || fileName.equals("")){
            return "文件为空";
        }else{
            //检查文件是否存在
            File file= new File("e:/file/"+fileName);
            if(file.exists()){
                //下载.start
                // 设置浏览器强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

                //下载
                 //读取文件数据到内存（输入流）
                //缓存流(可以不用，用了可以提升读取的性能)
                byte [] bytes=new byte[2048];//字节数组，用于分批存取缓存流里面的数据

                try(FileInputStream fis=new FileInputStream(file);
                    BufferedInputStream bis=new BufferedInputStream(fis);
                    OutputStream os=response.getOutputStream();
                ) {
                    int i = bis.read(bytes);//取缓存流里面数据，如果返回-1则表示缓存流里面没有数据，否则返回读取到的字符下标
                    while(i != -1){
                        os.write(bytes,0,i);
                        i=bis.read(bytes);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return "失败";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "失败";
                }
            }else{
                return "文件不存在";
            }
        }
        return "文件下载成功";
    }
}
