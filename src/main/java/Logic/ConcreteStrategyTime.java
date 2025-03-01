package Logic;

import Model.Server;
import Model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t)
    {
        Server minTimeServer = servers.get(0);
        //int mini = minTimeServer.getTime();
        int mini = minTimeServer.getWaitingPeriod().intValue();
        for (Server server : servers)
        {
            int curr = server.getWaitingPeriod().intValue();
            if (curr < mini)
            {
                minTimeServer = server;
                mini = curr;
            }
        }
        minTimeServer.addTask(t);
    }
}
