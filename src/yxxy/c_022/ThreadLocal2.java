/**   
 * @Title: ThreadLocal2.java 
 * @Package yxxy.c_022 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:16:09 
 * @version V1.0   
 */
package yxxy.c_022;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadLocal2
 * @Description: ThreadLocal线程局部变量
 * 
 *               ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 *               比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 *               运行下面的程序，理解ThreadLocal
 * @author 代富有
 * @date 2018年1月20日 下午6:16:09
 * 
 */
public class ThreadLocal2 {
	// volatile static Person p=new Person();
	static ThreadLocal<Person> tl = new ThreadLocal<>();

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(tl.get());
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tl.set(new Person());
			}
		}).start();

	}

	static class Person {
		String name = "zhangsan";
	}
}
