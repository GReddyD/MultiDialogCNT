import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Main {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		int CNT_THREAD = 4;
		int VAL_ITERATION1 = 5;
		int VAL_ITERATION2 = 7;
		int VAL_ITERATION3 = 9;
		int VAL_ITERATION4 = 12;

		ExecutorService threadPool = Executors.newFixedThreadPool(CNT_THREAD);

		List<Callable<Integer>> tasks = new ArrayList<>();
		tasks.add(callableTask(VAL_ITERATION1));
		tasks.add(callableTask(VAL_ITERATION2));
		tasks.add(callableTask(VAL_ITERATION3));
		tasks.add(callableTask(VAL_ITERATION4));

		int taskResult = threadPool.invokeAny(tasks);

		System.out.println("\nЗавершение потоков");
		threadPool.shutdown();

		System.out.printf("\nРезультат выполнения задачи = %s", taskResult);
	}

	public static Callable<Integer> callableTask(int valueOfIteration) {
		return () -> {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SS");

			int taskCnt = 0;
			try {
				while (taskCnt < valueOfIteration) {
					Thread.sleep(1500);
					System.out.println(sdf.format(new Date()) + " : " + Thread.currentThread().getName());
					taskCnt++;
				}
			} catch (InterruptedException e) {
				System.out.printf("\n%s Завершен", Thread.currentThread().getName());
			}
			return taskCnt;
		};
	}
}
