package com.dawnpro.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author dpzhoufeng;
 * @Date 2018/8/8,20:41;
 */

public class getJenkinsStatus {
    public static int temp() throws IOException {
        // 我们需要进行请求的地址：
        String temp = "http://10.99.6.192:8080/job/testzhouf/lastCompletedBuild/api/json";
        int returnstatus=-1;
        try {
            // 1.URL类封装了大量复杂的实现细节，这里将一个字符串构造成一个URL对象
            URL url = new URL(temp);
            // 2.获取HttpURRLConnection对象
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            // 3.调用connect方法连接远程资源
            connection.connect();
            // 4.访问资源数据，使用getInputStream方法获取一个输入流用以读取信息
            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));

            // 对数据进行访问
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // 关闭流
            bReader.close();
            // 关闭链接
            connection.disconnect();
            // 打印获取的结果
            System.out.println(stringBuilder.toString());

            // 将获得的String对象转为JSON格式
            //JSONObject jsonObject = JSONObject.fromObject(stringBuilder.toString());
            String resultstr = stringBuilder.toString();
              returnstatus = resultstr.indexOf("关于百度");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return returnstatus;
    }
}
