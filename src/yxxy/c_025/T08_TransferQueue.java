/**
 * @Title: T08_TransferQueue.java
 * @Package yxxy.c_025
 * @Description:
 * @author 代富有
 * @date 2018年1月28日 上午9:55:25
 * @version V1.0
 */
package src.yxxy.c_025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @ClassName: T08_TransferQueue
 * @Description:
 * @author 代富有
 * @date 2018年1月28日 上午9:55:25 
 *
 */
public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        //new Thread(()-> {
        //    try {
        //        System.out.println(strs.take());
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}).start();

        //strs.transfer("aaa");
        strs.put("aaa");

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
