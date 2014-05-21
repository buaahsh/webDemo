/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.client;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @author HuangShaohan
 *
 */
public class Md5Gen {

	
	private final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',  
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private MessageDigest messagedigest = null;  
	
	
	
	public Md5Gen() {
		super();
		 try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	public String toSortString(String[] keys){
		StringBuffer buffer=new StringBuffer();
		for(String query:keys){
			buffer.append(query);
		}
		return buffer.toString();
	}
	private  String bufferToHex(byte bytes[]) {  
		int m=0;
		int n=bytes.length;
        StringBuffer stringbuffer = new StringBuffer(2 * n);  
        int k = m + n;  
        for (int l = m; l < k; l++) {  
            appendHexPair(bytes[l], stringbuffer);  
        }  
        return stringbuffer.toString();  
    }  
	
	private void appendHexPair(byte bt, StringBuffer stringbuffer) {  
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同   
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换   
        stringbuffer.append(c0);  
        stringbuffer.append(c1);  
    }  
	
	public String getMd5Sum(String clearCode){
		try {
			messagedigest.update(clearCode.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        return bufferToHex(messagedigest.digest());  
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
