/**   
 * @Title: T.java 
 * @Package yxxy.c_013 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月17日 下午10:39:59 
 * @version V1.0   
 */
package src.yxxy.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: T
 * @Description:
 * @author 代富有
 * @date 2018年1月17日 下午10:39:59
 * 
 */
public class T {
	volatile int count = 0;

	void m() {
		for (int i = 0; i < 10000; i++)
			count++;

	}

	public static void main(String[] args) {
	final T t=new T();
	List<Thread> threads=new ArrayList<Thread>();
	
	for(int i=0;i<10;i++){
		threads.add(new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}));
	}
	
	
//	for (int i = 0; i < 10; i++) {
//		threads.add(new Thread(t::m,"thread-"+i));
//	}
	
	//threads.forEach((o)->o.start());
	
//	threads.forEach((o)->{
//		try {
//			o.join();
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	});
	
	System.out.println(t.count);
	
}
}
