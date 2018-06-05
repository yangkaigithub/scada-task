package com.oh.scada.task.taskManager;

import com.oh.scada.task.Task.Task;
import com.oh.scada.task.excutor.TaskExecutor;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TaskManager implements Runnable{

    private ConcurrentHashMap<String ,Boolean> grainIsWorkingMap;
    private ConcurrentHashMap<String,Integer> workingTasks;
    private BlockingQueue<Task> taskQueue;
    private Thread allocateThread;
    private boolean isRunning;
    private ConcurrentLinkedQueue<String> messageQueue;

    private TaskExecutor taskExecutor;

    TaskManager(){
        grainIsWorkingMap = getGrainIsWorkingMap();
        workingTasks = new ConcurrentHashMap<String,Integer> ();
        taskQueue = new LinkedBlockingQueue<Task>();
        messageQueue = new ConcurrentLinkedQueue<String>();

        isRunning = true;
        allocateThread = new Thread(this);
        allocateThread.run();
    }

    public void addTask(Task task){
        if (task!=null) taskQueue.add(task);
    }

    private ConcurrentHashMap<String, Boolean> getGrainIsWorkingMap() {
        return null;
    }


    public void setWorkingTasks(ConcurrentHashMap<String, Integer> workingTasks) {
        this.workingTasks = workingTasks;
    }

    public ConcurrentHashMap<String, Integer> getWorkingTasks() {
        return workingTasks;
    }

    // taskManager主要的执行流程。
    public void run() {
        try {
            while (isRunning) {
                Task task = taskQueue.peek(); //  待分配的任务的队列，检查首位是否可以执行分配。
                if (task != null) {
                    ArrayList<String> grains = task.getOperateGrains();    //待分配的任务 需要涉及的 grains ，
                    boolean canExcute = true; //是否可以分配
                    //检测是否可以分配task给executor去执行
                    if (grains.size()!=0){ //如果task里涉及grains才可能去分配执行
                        for (int i = 0; i < grains.size(); i++) {
                            //检测任务管理器里是否含有涉及的grains
                            if (grainIsWorkingMap.containsKey(grains.get(i))){ //如果任务管理器里 含有 涉及的grains
                                boolean thisGrainIsWorking = grainIsWorkingMap.get(grains.get(i)); // 检查任务管理器里的这个grain 的状态是否在执行任务中
                                if (thisGrainIsWorking){
                                    canExcute =false; //如果有一个grain 在执行任务中，，也就是不能分配待执行的任务，这个任务会停在queue里，线程停在这里。
                                }
                            }else {  //如果待分配任务涉及到的grain不存在于taskManager里，会报错。
                                alertMessage(task.getName()+"TaskManager doesn't have all related grains!");
                            }
                        }
                    }else {
                        // 如果task里没有grains，直接删除这个任务
                        canExcute =false;
                        taskQueue.poll();
                    }
                    //task符合分配要求，可以分配执行。
                    if(canExcute){
                        try {
                            task = taskQueue.poll();
                            for (int i = 0; i < grains.size(); i++) {
                                grainIsWorkingMap.put(grains.get(i),true);
                            }
                            taskExecutor.execute(task); //把task交给 taskExecutor去执行
                        }catch (Exception e){
                            System.out.println(e.toString());
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            isRunning=false;
        }
    }

    private void alertMessage(String mes){
        messageQueue.add(mes);
    }

    public void stopRunning(){
        synchronized (this) {
            isRunning = false;
        }
    }

    public void startRunning(){
        synchronized (this){
            if (!isRunning){
                isRunning=true;
                allocateThread.run();
            }
        }
    }


    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
