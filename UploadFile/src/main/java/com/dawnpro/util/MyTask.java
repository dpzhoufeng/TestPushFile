package com.dawnpro.util;

import java.io.IOException;
import java.util.TimerTask;

/**
 * @Author dpzhoufeng;
 * @Date 2018/8/9,19:42;
 */

public class MyTask extends TimerTask {
    private int status;
    @Override
    public void run() {
        try {
            status   = getJenkinsStatus.temp();
            System.out.println("进程在执行");
            if(status!=0){}
            else{

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
