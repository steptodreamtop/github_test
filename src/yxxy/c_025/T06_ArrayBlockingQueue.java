/**
 * @Title: T06_ArrayBlocking.java
 * @Package yxxy.c_025
 * @Description:
 * @author 代富有
 * @date 2018年1月28日 上午9:54:20
 * @version V1.0
 */
package src.yxxy.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * @ClassName: T06_ArrayBlocking
 * @Description:
 * @author 代富有
 * @date 2018年1月28日 上午9:54:20 
 *
 */
public class T06_ArrayBlockingQueue {
    static BlockingDeque<String> strs= (BlockingDeque<String>) new ArrayBlockingQueue<String>(10);
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        //strs.put("aaa");
        strs.add("aaa");
        //strs.offer("aaa");
        //strs.offer("aaa",1, TimeUnit.SECONDS);

        System.out.println(strs);

    }
}
