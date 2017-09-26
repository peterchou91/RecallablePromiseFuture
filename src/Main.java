import java.util.concurrent.*;

public class Main {
    public static class MyCall<V> implements Callable<V>{
        private int cnt = 1;
        public MyCall(int i){
            cnt = i;
        }

        @Override
        public V call() throws Exception {
            System.out.println("task " + cnt + " is running");
            Thread.sleep(cnt * 10);
            System.out.println("task " + cnt + " end");
            return (V)("" + cnt);
        }
    }
    public static void main(String[] args){
        ThreadPoolExecutor pool = new RecallableExceuteService(10,10,100, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));
/*
        Callable<String> call1 = new MyCall<String>(1);
        RecallableFuture<String> f = (RecallableFuture<String>)pool.submit(call1);
        try {
            String ret = f.get(1, TimeUnit.MILLISECONDS);
            System.out.println("f returned:" + ret);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("f timed out");
        }
        Callable<String> call2 = new MyCall<String>(10);
        RecallableFuture<String> f2 = (RecallableFuture<String>)pool.submit(call2, f);
        try {
            String ret = f2.get();
            System.out.println("f2 returned:" + ret);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("f2 timed out");
        }
        System.out.println("end");
        System.out.println(pool.getCompletedTaskCount());
        System.out.println(pool.getActiveCount());
*/
        Callable<String> call1 = new MyCall<String>(1);
        Future<String> f = (Future<String>)pool.submit(call1);
        try {
            String ret = f.get(1, TimeUnit.MILLISECONDS);
            System.out.println("f returned:" + ret);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("f timed out");
        }
    }
}
