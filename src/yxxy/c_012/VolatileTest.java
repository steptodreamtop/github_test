/**   
 * @Title: VolatileTest.java 
 * @Package yxxy.c_012 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月17日 下午9:50:29 
 * @version V1.0   
 */
package yxxy.c_012;

/**
 * @ClassName: VolatileTest
 * @Description: volatile变量自增运算测试
 * @author 代富有
 * @date 2018年1月17日 下午9:50:29
 * 
 */
public class VolatileTest {
	public static volatile int race = 0;

	public static void increase() {
		race++;
	}

	private static final int THREADS_COUNT = 20;

	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 1000; i++) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		// 等待所有累加线程都结束
		while (Thread.activeCount() > 1)
			Thread.yield();

		System.out.println(race);

	}
}
