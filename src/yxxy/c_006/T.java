package yxxy.c_006;
//分析程序的输出（线程重入问题）
public class T implements Runnable{
	private static int count = 10;

	public synchronized void run() {
			count--;
			System.out.println(Thread.currentThread().getName() + " count =" + count);
	}
	
	public static void main(String[] args) {
		T t=new T();
		for (int i = 0; i < 5; i++) {
			new Thread(t,"THREAD"+i).start();
		}
	}
	
}
