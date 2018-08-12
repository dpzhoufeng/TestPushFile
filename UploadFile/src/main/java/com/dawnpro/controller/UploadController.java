package com.dawnpro.controller;

import com.dawnpro.util.BiMsgPropertyUtil;

import com.dawnpro.util.getJenkinsStatus;
import com.dawnpro.util.timerTask;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Controller
public class UploadController {


	@Autowired
	BiMsgPropertyUtil biMsgPropertyUtil;

    @GetMapping("/")
    public String index(@RequestParam("type" )int type,
                        ModelMap map) {
    	map.addAttribute("type",type);
    	if(type > biMsgPropertyUtil.getCallurl().size() - 1){
		    map.addAttribute("message", "调用类型异常，请检查类型！");
		    return "uploadStatus";
	    }
    	return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("type")int  type,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择上传的文件！");
            return "redirect:uploadStatus";
        }
	    String savefilepth = biMsgPropertyUtil.getSavepath().get(type);
	    String callurl = biMsgPropertyUtil.getCallurl().get(type);

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get( savefilepth+ file.getOriginalFilename());
            Files.write(path, bytes);

	        HttpClient httpclient = new DefaultHttpClient();

	        HttpPost httppost = new HttpPost(callurl);

	        HttpResponse response = httpclient.execute(httppost);

	        int statusCode = response.getStatusLine().getStatusCode();

			try {
				int result1=-1;
				do{
					TimeUnit.MILLISECONDS.sleep(2000);
					result1 = getJenkinsStatus.temp();
					System.out.println("查看休眠是否成功");}
				while(result1==-1);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        if(statusCode == HttpStatus.SC_CREATED){
		        redirectAttributes.addFlashAttribute("message",
				        "已经成功上传文件 '" + file.getOriginalFilename() + "'");
	        }else{
		        redirectAttributes.addFlashAttribute("message",
				        "文件上传异常请稍后重试。");
	        }



        } catch (Exception e) {
            e.printStackTrace();
	        redirectAttributes.addFlashAttribute("message",
			        "文件上传异常请稍后重试。");
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}