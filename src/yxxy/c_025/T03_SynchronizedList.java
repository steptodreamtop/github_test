/**   
* @Title: T03_SynchronizedList.java 
* @Package yxxy.c_025 
* @Description:  
* @author 代富有   
* @date 2018年1月28日 上午9:53:02 
* @version V1.0   
*/
package src.yxxy.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: T03_SynchronizedList 
 * @Description:  
 * @author 代富有
 * @date 2018年1月28日 上午9:53:02 
 *  
 */
public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> strs=new ArrayList<>();
        List<String> strsSync= Collections.synchronizedList(strs);
    }
}
