/**   
 * @Title: MyContainer1.java 
 * @Package yxxy.c_019 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:11:11 
 * @version V1.0   
 */
package src.yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MyContainer1
 * @Description: 曾经的面试题：淘宝 实现一个容器，提供两个方法，add，size
 *               写两个线程，线程1添加10个元素到容器中，线程2监控元素的个数，当个数到5个时，线程2给出提示并结束
 *               分析下面这个程序能完成这个功能吗？
 * @author 代富有
 * @date 2018年1月20日 下午6:11:11
 * 
 */
public class MyContainer1 {
	 List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		final MyContainer1 c = new MyContainer1();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					c.add(new Object());
					System.out.println("add" + i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (c.size() == 5) {
						break;
					}
				}
				System.out.println("t2 结束");
			}
		}, "t2").start();

	}
}
