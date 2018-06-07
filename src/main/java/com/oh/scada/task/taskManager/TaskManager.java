package com.oh.scada.task.taskManager;

import com.oh.scada.task.Task.Task;
import com.oh.scada.task.excutor.TaskExecutor;
import com.oh.scada.task.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author daimeng
 */
public class TaskManager{

    private ConcurrentHashMap<String, Boolean> grainIsWorkingMap;
    private ConcurrentHashMap<String, Integer> workingTasks;
    private BlockingQueue<Task> taskQueue;
    private ExecutorService allocateThread;
    private boolean isRunning;
    private ConcurrentLinkedQueue<String> messageQueue;



    @Autowired
    private TaskExecutor taskExecutor;

    public void init(){
        grainIsWorkingMap = getGrainIsWorkingMap();
        workingTasks = new ConcurrentHashMap<String, Integer>();
        taskQueue = new LinkedBlockingQueue<Task>();
        messageQueue = new ConcurrentLinkedQueue<String>();
        isRunning = true;
        allocateThread = Executors.newSingleThreadExecutor();
        allocateThread.execute(this::run);
    }

    public void addTask(Task task) {
        if (task != null) {
            taskQueue.add(task);
        }
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

    /**
     * taskManager主要的执行流程。
     */
    public void run() {
        try {
            while (isRunning) {
                //  待分配的任务的队列，检查首位是否可以执行分配。
                Task task = taskQueue.peek();
                if (task != null) {
                    //待分配的任务 需要涉及的 grains ，
                    List<String> grains = task.getOperateGrains();
                    //是否可以分配
                    boolean canExcute = true;
                    //检测是否可以分配task给executor去执行
                    //如果task里涉及grains才可能去分配执行
                    if (grains.size() != 0) {
                        for (int i = 0; i < grains.size(); i++) {
                            //检测任务管理器里是否含有涉及的grains
                            if (grainIsWorkingMap.containsKey(grains.get(i))) {
                                //如果任务管理器里 含有 涉及的grains
                                // 检查任务管理器里的这个grain 的状态是否在执行任务中
                                boolean thisGrainIsWorking = grainIsWorkingMap.get(grains.get(i));
                                if (thisGrainIsWorking) {
                                    //如果有一个grain 在执行任务中，，也就是不能分配待执行的任务，这个任务会停在queue里，线程停在这里。
                                    canExcute = false;
                                }
                            } else {  //如果待分配任务涉及到的grain不存在于taskManager里，会报错。
                                alertMessage(task.getName() + "TaskManager doesn't have all related grains!");
                            }
                        }
                    } else {
                        // 如果task里没有grains，直接删除这个任务
                        canExcute = false;
                        taskQueue.poll();
                    }
                    //task符合分配要求，可以分配执行。
                    if (canExcute) {
                        try {
                            task = taskQueue.poll();
                            for (int i = 0; i < grains.size(); i++) {
                                grainIsWorkingMap.put(grains.get(i), true);
                            }
                            //把task交给 taskExecutor去执行
                            taskExecutor.bootTask(task);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            isRunning = false;
        }
    }

    private void alertMessage(String mes) {
        messageQueue.add(mes);
    }

    public void stopRunning() {
        synchronized (this) {
            isRunning = false;
        }
    }

    public void startRunning() {
        synchronized (this) {
            if (!isRunning) {
                isRunning = true;
                allocateThread.execute(this::run);
            }
        }
    }

}
