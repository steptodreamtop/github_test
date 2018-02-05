/**   
 * @Title: Singleton.java 
 * @Package yxxy.c_023 
 * @Description:  
 * @author 代富有   
 * @date 2018年1月20日 下午6:16:29 
 * @version V1.0   
 */
package src.yxxy.c_023;

/**
 * @ClassName: Singleton
 * @Description: 线程安全的单例模式 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 * @author 代富有
 * @date 2018年1月20日 下午6:16:29
 * 
 */
public class Singleton {
	private Singleton() {
		System.out.println("single");
	}

	private static class Inner {
		private static Singleton s = new Singleton();
	}

	private static Singleton getSingle() {
		return Inner.s;
	}

	public static void main(String[] args) {
		Thread[] ths = new Thread[200];
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Singleton.getSingle();
				}
			});
		}

		// Arrays.asList(ths).forEach(o->o.start());

	}

}
