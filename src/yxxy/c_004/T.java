package src.yxxy.c_004;

public class T {
	private static int count = 10;

	public synchronized static void m() {//等同于在方法的代码执行时要synchronized(yxxy_004.T.class)
			count--;
			System.out.println(Thread.currentThread().getName() + " count =" + count);
	}
	
	public static void mm(){
		synchronized (T.class) {
			count--;
		}
	}
	
}
