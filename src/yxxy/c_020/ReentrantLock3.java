/**   
* @Title: ReentrantLock3.java 
* @Package yxxy.c_020 
* @Description:  
* @author 代富有   
* @date 2018年1月20日 下午6:14:27 
* @version V1.0   
*/
package yxxy.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.bcel.internal.generic.LLOAD;

/** 
 * @ClassName: ReentrantLock3 
 * @Description: reentrantlock用于替代synchronized
 * 由于m1锁定this，只有m1执行完，m2才能执行
 * 这里是复习synchronized最原始的语义
 * 
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要手动释放锁
 * 使用synochronized锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 *
 * 使用reentrantlock可以进行“尝试锁定”trylock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 * @author 代富有
 * @date 2018年1月20日 下午6:14:27 
 *  
 */
public class ReentrantLock3 {
Lock lock=new ReentrantLock();
void m1(){
	try {
		lock.lock();
		for (int i = 0; i < 10; i++) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(i);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
	}finally{
		lock.unlock();
	}
}

void m2(){
	
	/*boolean locked=lock.tryLock();
	System.out.println("m2..."+locked);
	if (locked) lock.unlock();*/
	
	boolean locked=false;
	try {
		locked =lock.tryLock(5,TimeUnit.SECONDS);
		System.out.println("m2..."+locked);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally{
		if (locked) {
			lock.unlock();
		}
	}
}

public static void main(String[] args) {
	final ReentrantLock3 rl=new ReentrantLock3();
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			rl.m1();
		}
	}).start();
	try {
		TimeUnit.SECONDS.sleep(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			rl.m2();
		}
	}).start();
}


}






















