package Logic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t)
    {
        Server minQueueServer = servers.get(0);
        int minQueueSize = minQueueServer.getTasks().size();
        if (minQueueServer.getCurrentTask() != null)
            minQueueSize++;
        for (Server server : servers)
        {
            int currQueueSize = server.getTasks().size();
            if (server.getCurrentTask() != null)
                currQueueSize++;
            if (currQueueSize < minQueueSize)
            {
                minQueueSize = currQueueSize;
                minQueueServer = server;
            }
        }
        minQueueServer.addTask(t);
    }
}
