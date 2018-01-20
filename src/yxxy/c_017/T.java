/**   
 * @Title: T.java 
 * @Package yxxy.c_017 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月19日 下午7:52:10 
 * @version V1.0   
 */
package yxxy.c_017;

import java.util.concurrent.TimeUnit;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @ClassName: T
 * @Description:
 * @author 代富有
 * @date 2018年1月19日 下午7:52:10
 * 
 */
public class T {
	Object o = new Object();

	void m() {
		synchronized (o) {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}

	public static void main(String[] args) {
		final T t = new T();
		// 启动第一个线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				t.m();
			}
		},"t1").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 启动第一个线程
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				t.m();
			}
		},"t2");
		t.o = new Object();// 锁对象发生改变，所以t2线程得以执行，如果注释掉这句话，线程2将永远得不到执行机会
		t2.start();

	}

}
