package com.oh.scada.task.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author tpusers
 * @date 2018/6/3
 */
public class TaskUtil {
    public static void testUtil(){
        System.out.println("111");
    }


    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("111",1000);
        map.put("112",1000000);
        map.put("113",2000000000);

        System.out.println(map.get("111111")==null);
        new TaskUtil().fun();
    }

    public void fun(){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(this::fun2);
    }

    private void fun2() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }
    }
}
