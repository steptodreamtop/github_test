/**   
 * @Title: TicketSeller4.java 
 * @Package yxxy.c_024 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:17:53 
 * @version V1.0   
 */
package src.yxxy.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @ClassName: TicketSeller4
 * @Description:
 * @author 代富有
 * @date 2018年1月20日 下午6:17:53
 * 
 */
public class TicketSeller4 {
	static Queue<String> tickets = new ConcurrentLinkedDeque<>();

	static {
		for (int i = 0; i < 10000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						String s = tickets.poll();
						if (s == null) {
							break;
						} else {
							System.out.println("销售了--" + s);
						}
					}
				}
			}).start();
		}
	}

}
