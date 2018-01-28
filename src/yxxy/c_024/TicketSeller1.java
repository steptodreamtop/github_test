/**   
 * @Title: TicketSeller1.java 
 * @Package yxxy.c_024 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:16:58 
 * @version V1.0   
 */
package yxxy.c_024;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TicketSeller1
 * @Description: 有N张火车票，每张票都有一个编号 同时有10个窗口对外售票 请写一个模拟程序
 * 
 *               分析下面的程序可能会产生哪些问题？ 重复销售？超量销售？
 * @author 代富有
 * @date 2018年1月20日 下午6:16:58
 * 
 */
public class TicketSeller1 {
	static  List<String> tickets = new ArrayList<>();
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
					while (tickets.size() > 0) {
						System.out.println("销售了--" + tickets.remove(0));
					}
				}
			}).start();
		}
	}

}
