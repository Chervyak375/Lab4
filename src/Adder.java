import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

public class Adder {
    protected List<Double> l1;
    protected List<Double> l2;

    public Adder(List<Double> l1, List<Double> l2)
    {
        this.l1=l1;
        this.l2=l2;
    }

    public List<Double> sum() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(l1.size());
        Vector<Double> result=new Vector<Double>();

        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            final int j = i;
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> l1.get(j) + l2.get(j),
                            threadPool
                    ));
        }

        for (Future<Double> future : futures) {
             result.add(future.get());
        }

        return result;
    }
}


