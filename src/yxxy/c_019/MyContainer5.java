/**   
 * @Title: Mycontainer5.java 
 * @Package yxxy.c_019 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:13:36 
 * @version V1.0   
 */
package yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.sun.jndi.url.dns.dnsURLContext;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @ClassName: Mycontainer5
 * @Description: 曾经的面试题：淘宝 实现一个容器，提供两个方法，add，size
 *               写两个线程，线程2添加10个元素到容器中，线程2监控元素的个数，当个数达到5个时，线程2给出提示并结束
 *               给list添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做？
 * 
 *               这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 *               需要注意的是，运用这种方法，必须保证t2先执行，也就是首先让t2监听才可以
 * 
 *               阅读下面的程序，并分析输出结果 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 *               想想这是为什么？
 * 
 *               notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行 整个通信过程比较繁琐
 * 
 *               使用Latch(门闩)替代wait notify来进行通知 好处是通信方式简单，同时也可以指定等待时间
 *               使用await和countdown方法替代wait和notify
 *               CountDownLatch不涉及锁定，当count的值为零时当前线程继续运行
 *               当不涉及同步，只是涉及线程通信的时候，用synchronized+wait/notify就显得太重了
 *               这时应该考虑countdownlatch/cylicbarrier/semaphore
 * 
 * 
 * @author 代富有
 * @date 2018年1月20日 下午6:13:36
 * 
 */
public class MyContainer5 {
	// 添加volatile，使t2能够得到通知
	volatile List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		final MyContainer5 c = new MyContainer5();
		final CountDownLatch latch = new CountDownLatch(1);
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t2 启动");
				if (c.size() != 5) {
					try {
						latch.await();
						// 也可以指定等待时间
						// latch.await(50000,TimeUnit.MICROSECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2 结束");
			}
		}, "t2").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t1 启动");
				for (int i = 0; i < 10; i++) {
					c.add(new Object());
					System.out.println("add " + i);
					if (c.size() == 5) {
						// 打开门闩，让t2可以执行
						latch.countDown();
					}
				}
			}
		}, "t1").start();

	}
}
