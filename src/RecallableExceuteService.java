import java.util.concurrent.*;

public class RecallableExceuteService extends ThreadPoolExecutor {
    public RecallableExceuteService(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected <V> RecallableFuture<V> newTaskFor(Callable<V> callable) {
        RecallableFuture<V> ret = new RecallableFuture<V>(callable);
        //if()
        return ret;
    }

    public <V> Future<V> submit(Callable<V> task) {
        if (task == null) throw new NullPointerException();
        RecallableFuture<V> ftask = newTaskFor(task);
        execute(ftask);
        return ftask;
    }
    public <V> Future<V> submit(Callable<V> task, RecallableFuture<V> future) {
        if (task == null) throw new NullPointerException();
        if (future == null) throw new NullPointerException();
        RecallableFuture<V> ftask = newTaskFor(task);
        ftask.pre = future;
        future.next = ftask;
        execute(ftask);
        return ftask;
    }

}
