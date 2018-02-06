/**
 * @Title: T04_ConcurrentQueue.java
 * @Package yxxy.c_025
 * @Description:
 * @author 代富有
 * @date 2018年1月28日 上午9:53:31
 * @version V1.0
 */
package src.yxxy.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @ClassName: T04_ConcurrentQueue
 * @Description:
 * @author 代富有
 * @date 2018年1月28日 上午9:53:31 
 *
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);//add
        }
        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());
        System.out.println(strs.size());

        System.out.println(strs.peek());
        System.out.println(strs.size());

        //双端队列Deque
    }
}
