package src.yxxy.c_007;

//分析程序的输出（线程重入问题）
public class T {

	public synchronized void m1() {
		System.out.println(Thread.currentThread().getName() + "m1 start...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "m1 end...");
	}

	public void m2() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "m2");
	}

	public static void main(String[] args) {
		final T t = new T();
		new Thread(new Runnable() {

			@Override
			public void run() {
				t.m1();
			}
		}).start();;

		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		}).start();;
	}

}
