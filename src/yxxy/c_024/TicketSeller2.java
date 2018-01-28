/**   
* @Title: TicketSeller2.java 
* @Package yxxy.c_024 
* @Description:  
* @author 代富有   
* @date 2018年1月20日 下午6:17:27 
* @version V1.0   
*/
package yxxy.c_024;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/** 
 * @ClassName: TicketSeller2 
 * @Description:  
 * @author 代富有
 * @date 2018年1月20日 下午6:17:27 
 *  
 */
public class TicketSeller2 {
static Vector<String> tickets=new Vector<>();
static{
	for (int i = 0; i < 10000; i++) {
		tickets.add("票编号："+i);
	}
}

public static void main(String[] args) {
	for (int i = 0; i < 10; i++) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(tickets.size()>0){
//					try {
//						TimeUnit.MICROSECONDS.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					System.out.println("销售了--"+tickets.remove(0));
				}
			}
		}).start();
	}
}


}
