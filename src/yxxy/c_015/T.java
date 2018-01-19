/**   
 * @Title: T.java 
 * @Package yxxy.c_015 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月17日 下午11:09:38 
 * @version V1.0   
 */
package yxxy.c_015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @ClassName: T
 * @Description:
 * @author 代富有
 * @date 2018年1月17日 下午11:09:38
 * 
 */
public class T {
	/* volatile */// int count = 0;
	AtomicInteger count = new AtomicInteger(0);

	/* synchronized */void m() {
		for (int i = 0; i < 10000; i++)
			//if count.get()<1000;
			count.incrementAndGet();

	}

	public static void main(String[] args) {
T t=new T();

List<Thread> threads=new ArrayList<Thread>();
//for (int i = 0; i < 10; i++) {
//	threads.add(new Thread(new Runnable() {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			m();
//		}
//	}));
//}

//for (int i = 0; i < 10; i++) {
//	threads.add(new Thread(t::m,"thread-"+i));
//}
//
//threads.forEach((o)->o.start());
//
//threads.forEach((o)->{
//	try {
//		o.join();
//	} catch (InterruptedException e) {
//		e.printStackTrace();
//	}
//});

System.out.println(t.count);

}
}
