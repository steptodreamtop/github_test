/**   
* @Title: T.java 
* @Package yxxy.c_018 
* @Description:  
* @author 代富有   
* @date 2018年1月19日 下午9:14:18 
* @version V1.0   
*/
package src.yxxy.c_018;

/** 
 * @ClassName: T 
 * @Description:  不要以字符串常量为锁定对象
 * 在下面的例子中，m1和m2其实锁定的是同一个对象
 * 这种情况还会发生比较诡异的现象，比如你用到了一个类库，在该类库中代码锁定了字符串"hello"，
 * 但是你读不到源码，所以在你的代码中也锁定了"hello"，这时候就有可能发生死锁阻塞，
 * 因为你的程序和你用到的类库不经意间使用了同一把锁
 * @author 代富有
 * @date 2018年1月19日 下午9:14:18 
 *  
 */
public class T {
String s1="hello";
String s2="hello";
void m1(){
	synchronized (s1) {
		
	}
}
void m2(){
	synchronized (s2) {
		
	}
}
}
