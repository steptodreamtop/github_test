/**   
* @Title: T02_CopyOnWriteList.java 
* @Package yxxy.c_025 
* @Description:  
* @author 代富有   
* @date 2018年1月28日 上午9:52:36 
* @version V1.0   
*/
package src.yxxy.c_025;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName: T02_CopyOnWriteList 
 * @Description:  写时复制容器copy on write
 * 多线程环境下，写效率低，读效率高
 * 适合写少读多的环境
 *
 * 事件监听器
 * @author 代富有
 * @date 2018年1月28日 上午9:52:36 
 *  
 */
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists=
                //new ArrayList<>();//这个会出现并发问题！
        //new Vector();
        new CopyOnWriteArrayList<>();
        Random r=new Random();
        Thread[] ths=new Thread[100];
        for (int i = 0; i <ths.length ; i++) {
            Runnable task=new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <1000 ; i++) {
                        lists.add("a" + r.nextInt(10000));
                    }
                }
            };
                    ths[i]=new Thread(task);
        }
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

     static void runAndComputeTime(Thread[] ths) {
long s1=System.currentTimeMillis();
         Arrays.asList(ths).forEach(t->t.start());
         Arrays.asList(ths).forEach(t->{
             try {
                 t.join();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         });
         long s2=System.currentTimeMillis();
         System.out.println(s2-s1);
    }

}
