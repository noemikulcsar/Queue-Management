package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private final BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int time;
    private Task currentTask;

    public Server() {
        tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
        time = 0;
    }
    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
        time+=newTask.getServiceTime();
    }
    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    @Override
    public void run() {
        while (true) {
            currentTask = null;
            try {
                currentTask = tasks.take();
                //waitingPeriod.addAndGet(-currentTask.getServiceTime());
                Thread.sleep(currentTask.getServiceTime() * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    @Override
    public String toString() {
        return tasks.toString();
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public int getTotalTasks() {
        return tasks.size();
    }
}