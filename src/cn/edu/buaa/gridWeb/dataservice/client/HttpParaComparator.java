/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.client;

import java.util.Comparator;

/**
 * @author caorongqiang
 *
 */
public class HttpParaComparator implements Comparator<String> {

	@Override
	public int compare(String str1, String str2) {
		int index1=str1.indexOf('=');
		if(index1<=0){
			index1=str1.length();
		}
		int index2=str2.indexOf('=');
		if(index2<=0){
			index2=str2.length();
		}
		String key1=str1.substring(0,index1);
		String key2=str2.substring(0,index2);
		return key1.compareTo(key2);
	}

}
