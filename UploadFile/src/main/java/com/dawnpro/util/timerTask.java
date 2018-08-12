package com.dawnpro.util;


import java.util.Timer;


/**
 * @Author dpzhoufeng;
 * @Date 2018/8/8,21:43;
 */

public class timerTask {
   public static int getStatus(){
       Timer timer = new Timer();
       int resultstatus = -1;
       MyTask myTask = new MyTask();
       timer.schedule( myTask,0,2000);
        return resultstatus;
   }

}
