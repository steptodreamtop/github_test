/**   
* @Title: Mycontainer4.java 
* @Package yxxy.c_019 
* @Description:  
* @author 代富有   
* @date 2018年1月20日 下午6:13:24 
* @version V1.0   
*/
package src.yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** 
 * @ClassName: Mycontainer4 
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
 *               notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 *               整个通信过程比较繁琐
 * @author 代富有
 * @date 2018年1月20日 下午6:13:24 
 *  
 */
public class MyContainer4 {
	// 添加volatile，使t2能够得到通知
	volatile List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		final MyContainer4 c = new MyContainer4();
		final Object lock = new Object();
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lock) {
					System.out.println("t2 启动");
					if (c.size() != 5) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					System.out.println("t2 结束");
					//通知t1继续执行
					lock.notify();
				}
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
				synchronized (lock) {
					for (int i = 0; i < 10; i++) {
						c.add(new Object());
						System.out.println("add " + i);
						if (c.size() == 5) {
							lock.notify();
							try {
								//释放锁，让t2得以执行
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "t1").start();

	}
}
