package thread;

/**
 * 测试使用ThreadLocal
 *
 * @author zengxm
 * @date 2015年4月30日
 *
 */
public class ApplicationContext {

	private static ThreadLocal<Long> currentContext = new ThreadLocal<Long>(){
		protected Long initialValue() {
			return (long) Math.floor(Math.random() * 100000);
		};
	};

	public long getTime() {
		System.out.println(Thread.currentThread().getName() + ":"
				+ currentContext.get());
		return currentContext.get();
	}

	public void setTime(long time) {
		currentContext.set(time);
	}
}
