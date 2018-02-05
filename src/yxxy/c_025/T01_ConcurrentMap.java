/**
 * @Title: T01_ConcurrentMap.java
 * @Package yxxy.c_025
 * @Description:
 * @author 代富有
 * @date 2018年1月20日 下午6:18:47
 * @version V1.0
 */
package src.yxxy.c_025;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author 代富有
 * @ClassName: T01_ConcurrentMap
 * @Description:
 * @date 2018年1月20日 下午6:18:47
 */
public class T01_ConcurrentMap {
    public static void main(String[] args) {
	Map<String, String> map=new ConcurrentHashMap<>();
//	Map<String, String> map=new ConcurrentSkipListMap<>();
//        final Map<String, String> map = new Hashtable<>();
//	Map<String, String> map=new HashMap<>();
        final Random r = new Random();
        Thread[] ths = new Thread[100];
        final CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {

            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) map.put("a" + r.nextInt(10000), "a" + r.nextInt(10000));
                latch.countDown();
            });
        }
        Arrays.asList(ths).forEach(t -> t.start());
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}

