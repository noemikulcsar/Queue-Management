package Logic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Scheduler {
    private final List<Server> servers;
    private Strategy strategy = new ConcreteStrategyTime();

    public Scheduler(int maxNoServers) {
        servers = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(maxNoServers);
        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server();
            servers.add(server);
            executorService.execute(server);
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        else if (policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();
    }

    public void dispatchTask(Task t) {
        strategy.addTask(servers, t);
    }

    public List<Server> getServers() {
        return servers;
    }
}
