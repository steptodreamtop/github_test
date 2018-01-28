/**   
 * @Title: ThreadLocal1.java 
 * @Package yxxy.c_022 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:15:56 
 * @version V1.0   
 */
package yxxy.c_022;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadLocal1
 * @Description: ThreadLocal线程局部变量
 * @author 代富有
 * @date 2018年1月20日 下午6:15:56
 * 
 */
public class ThreadLocal1 {
	volatile static Person p = new Person();

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(p.name);
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
				p.name = "lisi";
			}
		}).start();

	}
}

class Person {
	String name = "zhangsan";
}
