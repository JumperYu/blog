package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 测试ApplicationContext的使用
 *
 * @author zengxm
 * @date 2015年4月30日
 *
 */
public class TestThreadLocal extends Thread {

	private ApplicationContext applicationContext = new ApplicationContext();;

	public TestThreadLocal() {
	}

	public TestThreadLocal(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void run() {
		try {
			applicationContext.getTime();
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int poolSize = 10;
		ExecutorService service = Executors.newFixedThreadPool(poolSize);
		for (int i = 0; i < poolSize; i++) {
			service.execute(new TestThreadLocal());
		}
		service.shutdown();
	}

}
