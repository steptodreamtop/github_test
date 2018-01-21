/**   
* @Title: Mycontainer3.java 
* @Package yxxy.c_019 
* @Description:  
* @author 代富有   
* @date 2018年1月20日 下午6:13:04 
* @version V1.0   
*/
package yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** 
 * @ClassName: Mycontainer3 
 * @Description:  
 * @author 代富有
 * @date 2018年1月20日 下午6:13:04 
 *  
 */
public class MyContainer3 {
	//添加volatile，使t2能够得到通知
	volatile List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		final MyContainer3 c = new MyContainer3();

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
