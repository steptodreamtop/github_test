/**
 * @Title: T05_LinkedBlockingQueue.java
 * @Package yxxy.c_025
 * @Description:
 * @author 代富有
 * @date 2018年1月28日 上午9:53:59
 * @version V1.0
 */
package src.yxxy.c_025;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: T05_LinkedBlockingQueue
 * @Description:
 * @author 代富有
 * @date 2018年1月28日 上午9:53:59 
 *
 */
public class T05_LinkedBlockingQueue {
    static BlockingDeque<String> strs = new LinkedBlockingDeque<>();
    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);//如果满了，就会等待
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "take -" + strs.take());//如果空了，就会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }

    }
}
