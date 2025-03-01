package Logic;

import GUI.QueueFrame;
import Model.Server;
import Model.Task;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationManager implements Runnable {
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int numberOfClients;
    private final Scheduler scheduler;
    private List<Task> generatedTasks;
    private final ExecutorService executorService;

    @Override
    public void run() {
        try {
            QueueFrame queueFrame = new QueueFrame();
            simulate(queueFrame);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public SimulationManager(int numberOfServers) {
        executorService = Executors.newFixedThreadPool(numberOfServers);
        scheduler = new Scheduler(numberOfServers);
    }
    private void generateNRandomTasks() {
        generatedTasks = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            int arrivalTime = minArrivalTime + (int) (Math.random() * (maxArrivalTime - minArrivalTime + 1));
            int processingTime = minProcessingTime + (int) (Math.random() * (maxProcessingTime - minProcessingTime + 1));
            Task task = new Task(i, arrivalTime, processingTime);
            generatedTasks.add(task);
        }
        generatedTasks.sort(Comparator.comparingInt(Task::getArrivalTime));
    }
    private void simulate(QueueFrame queueFrame) throws IOException, InterruptedException {
        try(FileWriter fileWriter = new FileWriter("file.txt"))
        {
            generateNRandomTasks();
            int currentTime = 0;
            while (currentTime <= timeLimit) {
                Iterator<Task> iterator = generatedTasks.iterator();
                while (iterator.hasNext()) {
                    Task task = iterator.next();
                    if (task.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(task);
                        iterator.remove();
                    }
                }
                fileWriter.write("Time " + currentTime + "\n");
                System.out.println("Time " + currentTime);
                queueFrame.updateFrame(currentTime, generatedTasks, scheduler);
                printQueues(fileWriter);
                for (Server server : scheduler.getServers()) {
                    Iterator<Task> taskIterator = server.getTasks().iterator();
                    if (taskIterator.hasNext()) {
                        Task task = taskIterator.next();
                        if(server.getCurrentTask()==null)
                        {
                            task.setServiceTime(task.getServiceTime() - 1);
                            //server.setTime(server.getTime()-1);
                            server.getWaitingPeriod().decrementAndGet();
                            if (task.getServiceTime() == 0) {
                                taskIterator.remove();
                            }
                        }
                    }
                    if(server.getCurrentTask()!=null)
                    {
                        server.getCurrentTask().setServiceTime(server.getCurrentTask().getServiceTime()-1);
                        //server.setTime(server.getTime()-1);
                        server.getWaitingPeriod().decrementAndGet();
                        if(server.getCurrentTask().getServiceTime()==0)
                        {
                            server.setCurrentTask(null);
                        }
                    }
                }
                currentTime++;
                Thread.sleep(1000L);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally {
            executorService.shutdown();
        }
    }

    public void printQueues(FileWriter fileWriter) throws IOException {
        fileWriter.write("Waiting clients: " + generatedTasks + "\n");
        fileWriter.write("Servers:\n");
        //System.out.println("Waiting clients: " + generatedTasks);
        //System.out.println("Servers:");
        for (int i = 0; i < scheduler.getServers().size(); i++) {
            Server server = scheduler.getServers().get(i);
            List<Task> serverTasks = new ArrayList<>(server.getTasks());
            int queueNumber = i + 1;
            fileWriter.write("Queue " + queueNumber + ":");
            //System.out.print("Queue " + queueNumber + ":");
            if(server.getCurrentTask()!=null)
            {
                fileWriter.write(server.getCurrentTask()+ " ");
                //System.out.print(server.getCurrentTask()+ " ");
            }
            for (Task task : serverTasks) {
                fileWriter.write(task + " ");
                //System.out.print(task + " ");
            }
            fileWriter.write("\n");
            //System.out.println();
        }
    }
    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setArrivalTimeBounds(int lowerBound, int upperBound) {
        this.minArrivalTime = lowerBound;
        this.maxArrivalTime = upperBound;
    }

    public void setServiceTimeBounds(int lowerBound, int upperBound) {
        this.minProcessingTime = lowerBound;
        this.maxProcessingTime = upperBound;
    }

    public void setSelectionPolicy(String selectionPolicy) {
        SelectionPolicy selectionPolicy1;
        if (selectionPolicy.equals("Shortest Queue")) {
            selectionPolicy1 = SelectionPolicy.SHORTEST_QUEUE;
        } else if (selectionPolicy.equals("Shortest Time")) {
            selectionPolicy1 = SelectionPolicy.SHORTEST_TIME;
        }
    }
}