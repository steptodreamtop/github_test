/**   
 * @Title: T.java 
 * @Package yxxy.c_016 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月19日 下午6:15:48 
 * @version V1.0   
 */
package src.yxxy.c_016;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: T
 * @Description:
 * @author 代富有
 * @date 2018年1月19日 下午6:15:48
 * 
 */
public class T {
	int count = 0;

	synchronized void m1() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count++;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) {
			count++;
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
