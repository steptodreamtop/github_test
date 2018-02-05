/**   
 * @Title: Mycontainer1.java 
 * @Package yxxy.c_021 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:15:26 
 * @version V1.0   
 */
package src.yxxy.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Mycontainer1
 * @Description: 面试题：写一个固定容器同步容器，拥有put和get方法，以及getCount方法，
 *               能够支持两个2个生产者线程和10个消费者线程的阻塞调用 使用wait和notify/notifyAll来实现
 * @author 代富有
 * @date 2018年1月20日 下午6:15:26
 * 
 */
public class MyContainer1<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10;// 最多10个元素
	private int count = 0;

	public synchronized void put(T t) {
		while (lists.size() == MAX) {//想想为什么用while而不是用if
			try {
				this.wait();//effective java
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		lists.add(t);
		++count;
		this.notifyAll();// 通知消费者线程进行消费

	}

	public synchronized T get() {
		T t = null;
		while (lists.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		count--;
		this.notifyAll();// 通知生产者进行生产
		return t;
	}

	public static void main(String[] args) {
		final MyContainer1<String> c = new MyContainer1<>();
		// 启动消费者线程
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
						System.out.println(c.get());
					}
				}
			}, "c" + i).start();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 启动生产者线程
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 25; j++) {
						c.put(Thread.currentThread().getName() + "" + j);
					}
				}
			}, "p" + i).start();
		}

	}

}
