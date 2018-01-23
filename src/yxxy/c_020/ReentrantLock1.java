/**   
 * @Title: ReentrantLock1.java 
 * @Package yxxy.c_020 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:14:01 
 * @version V1.0   
 */
package yxxy.c_020;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ReentrantLock1
 * @Description:reentrantlock用于替代synchronized
 * 由于m1锁定this，只有m1执行完，m2才能执行
 * 这里是复习synchronized最原始的语义
 * @author 代富有
 * @date 2018年1月20日 下午6:14:01
 * 
 */
public class ReentrantLock1 {
	synchronized static void m1() {
		for (int i = 0; i < 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}

	synchronized static void m2() {
		System.out.println("m2 ...");
	}

	public static void main(String[] args) {
		final ReentrantLock1 rl = new ReentrantLock1();
		new Thread(new Runnable() {

			@Override
			public void run() {
				rl.m1();
			}
		}).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				rl.m2();
			}
		}).start();

	}

}
