/**   
 * @Title: ReentrantLock2.java 
 * @Package yxxy.c_020 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:14:15 
 * @version V1.0   
 */
package yxxy.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLock2
 * @Description:reentrantlock用于替代synchronized
 * 由于m1锁定this，只有m1执行完，m2才能执行
 * 这里是复习synchronized最原始的语义
 * 
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要手动释放锁
 * 使用synochronized锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * @author 代富有
 * @date 2018年1月20日 下午6:14:15
 * 
 */
public class ReentrantLock2 {
	Lock lock = new ReentrantLock();

	void m1() {
		try {
			lock.lock();//synchronized(this)
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	void m2() {
		lock.lock();
		System.out.println("m2...");
		lock.unlock();
	}

	public static void main(String[] args) {
		final ReentrantLock2 rl = new ReentrantLock2();
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
