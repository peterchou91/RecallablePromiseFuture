import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class RecallableFuture<V> extends FutureTask<V> {

    public  RecallableFuture<V> next = null;
    public  RecallableFuture<V> pre = null;

    private boolean setOutcome;
    private boolean setException;

    public RecallableFuture(Callable<V> callable){
        super(callable);
    }

    @Override
    protected void set(V v) {
        if(setOutcome || setException) return;
        super.set(v);
        this.setOutcome = true;
        if(next != null) next.set(v);
        if(pre != null) pre.set(v);
    }
    @Override
    protected void setException(Throwable t) {
        if(setOutcome || setException) return;
        super.setException(t);
        this.setException = true;
        if(next != null) next.setException(t);
        if(pre != null) pre.setException(t);
    }


}
