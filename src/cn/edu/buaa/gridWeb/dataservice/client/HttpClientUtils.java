package cn.edu.buaa.gridWeb.dataservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;


public class HttpClientUtils extends HttpClient{
	
	public static void  main(String arg[]) {   
		String url =  "http://www.baidu.com/" ;
		url =  "https://www.99bill.com/webapp/receiveDrawbackAction.do";
         //getDoGetURL2(url,"utf-8");//测试ok   
        //getDoGetURL(url,"utf-8");//测试ok  
		//getDoPostResponseDataByURL(url,  null ,  "utf-8" ,  true );  //测试ok   
    }   
	
	/**  
     * <p>httpClient的get请求方式</p>  
     * @param url = "http://www.baidu.com/";  
     * @param charset = ="utf-8";  
     * @return  
     * @throws Exception  
     */   
     public String getDoGetURL(String url, String charset){   
    	 
        GetMethod method = new GetMethod(url);   
 		
        // 设置请求的编码方式   
         if  ( charset  != null) {   
            method.addRequestHeader( "Content-Type" ,   
                     "application/x-www-form-urlencoded; charset="  + charset);   
        }  else  {   
            method.addRequestHeader( "Content-Type" ,   
                     "application/x-www-form-urlencoded; charset="  +  "utf-8" );   
        }  
        try{
	         int  statusCode = this.executeMethod(method);   
	  
	         if  (statusCode != HttpStatus.SC_OK) { // 打印服务器返回的状态   
				System.out.println( "Method failed: "  + method.getStatusLine());  
				return null;
	         }
	         // 返回响应消息   
	         byte [] responseBody = method.getResponseBodyAsString().getBytes(method.getResponseCharSet());   
	         // 在返回响应消息使用编码(utf-8或gb2312)   
	        String response =  new  String(responseBody,  "utf-8" );      
	        return  response;  
	        
        }  catch  (IOException e) {   
            System.out.println( "执行HTTP Post请求"  + url +  "时，发生异常！" );   
            e.printStackTrace();  
            return null;
        }  finally  {   
            method.releaseConnection();   
        }   
          
    }   
    
     /**   
      * <p>执行一个HTTP Put请求，返回请求响应的HTML</p>   
      *   
      * @param url       请求的URL地址   
      * @param params    请求的查询参数,Json String
      * @return          返回请求响应的HTML   
      */   
     public String getDoPutResponseDataByURL(String url, String params) {
    	 StringBuffer response =  new  StringBuffer(); 
    	 PutMethod putMethod = new PutMethod(url);
    	 try {
			StringRequestEntity requestEntity = new StringRequestEntity(
					 	params,
					    "application/json",
					    "UTF-8");
			putMethod.setRequestEntity(requestEntity);
			this.executeMethod(putMethod);
			 if  (putMethod.getStatusCode() == HttpStatus.SC_OK) {   
                 //读取为 InputStream，在网页内容数据量大时候推荐使用   
            	 
                BufferedReader reader =  new  BufferedReader(   
                         new  InputStreamReader(putMethod.getResponseBodyAsStream(),   
                                "utf-8"));   
                String line;   
                 while  ((line = reader.readLine()) !=  null ) {   
                        response.append(line);   
                }
                reader.close();   
            }   
        }  catch  (IOException e) {   
            System.out.println( "执行HTTP Post请求"  + url +  "时，发生异常！" );   
            e.printStackTrace();  
            return null;
        }  finally  {
        	putMethod.releaseConnection();   
        }   
        //System.out.println( "--------------------" +response.toString());   
        return  response.toString();   
    } 
     /**   
      * <p>执行一个HTTP Delete请求，返回请求响应的HTML</p>   
      *   
      * @param url       请求的URL地址   
      * @param params    请求的查询参数,Json String
      * @return          返回请求响应的HTML   
      */   
     public String getDoDeleteResponseDataByURL(String url) {
    	 DeleteMethod method = new DeleteMethod(url);
    	 method.addRequestHeader( "Content-Type" ,   
        		"application/x-www-form-urlencoded; charset="  +  "utf-8" );   
         try{
 	         int  statusCode = this.executeMethod(method);   
 	  
 	         if  (statusCode != HttpStatus.SC_OK) { // 打印服务器返回的状态   
 				System.out.println( "Method failed: "  + method.getStatusLine());  
 				return null;
 	         }
 	         // 返回响应消息   
 	         byte [] responseBody = method.getResponseBodyAsString().getBytes(method.getResponseCharSet());   
 	         // 在返回响应消息使用编码(utf-8或gb2312)   
 	        String response =  new  String(responseBody,  "utf-8" );      
 	        return  response;  
 	        
         }  catch  (IOException e) {   
             System.out.println( "执行HTTP Post请求"  + url +  "时，发生异常！" );   
             e.printStackTrace();  
             return null;
         }  finally  {   
             method.releaseConnection();   
         }  
    } 
     
     /**   
      * <p>执行一个HTTP POST请求，返回请求响应的HTML</p>   
      *   
      * @param url       请求的URL地址   
      * @param params    请求的查询参数,json String
      * @return          返回请求响应的HTML   
      */   
     public String getDoPostResponseDataByURL(String url, String params) {
    	 StringBuffer response =  new  StringBuffer(); 
    	 PostMethod postMethod = new PostMethod(url);;
    	 try {
			StringRequestEntity requestEntity = new StringRequestEntity(
					 	params,
					    "application/json",
					    "UTF-8");
			postMethod.setRequestEntity(requestEntity);
			this.executeMethod(postMethod);
			 if  (postMethod.getStatusCode() == HttpStatus.SC_OK) {   
                 //读取为 InputStream，在网页内容数据量大时候推荐使用   
            	 
                BufferedReader reader =  new  BufferedReader(   
                         new  InputStreamReader(postMethod.getResponseBodyAsStream(),   
                                "utf-8"));   
                String line;   
                 while  ((line = reader.readLine()) !=  null ) {   
                        response.append(line);   
                }
                reader.close();   
            }   
        }  catch  (IOException e) {   
            System.out.println( "执行HTTP Post请求"  + url +  "时，发生异常！" );   
            e.printStackTrace();  
            return null;
        }  finally  {
        	postMethod.releaseConnection();   
        }   
        //System.out.println( "--------------------" +response.toString());   
        return  response.toString();   
    } 
       
     /**   
     * <p>执行一个HTTP POST请求，返回请求响应的HTML</p>   
     *   
     * @param url       请求的URL地址   
     * @param params    请求的查询参数,可以为null   
     * @param charset   字符集   
     * @param pretty    是否美化   
     * @return          返回请求响应的HTML   
     */     
     public String getDoPostResponseDataByURL(String url,   
            Map<String, String> params, String charset,  boolean  pretty) {   
           
        StringBuffer response =  new  StringBuffer();   
        PostMethod method =  new  PostMethod(url);   
         if (params != null){
        	 for  (Map.Entry<String, String> entry : params.entrySet()) {   
        		 method.addParameter(entry.getKey(), entry.getValue());
        	 }  
         }

         try  {
            this.executeMethod(method);   
             if  (method.getStatusCode() == HttpStatus.SC_OK) {   
                 //读取为 InputStream，在网页内容数据量大时候推荐使用   
            	 
                BufferedReader reader =  new  BufferedReader(   
                         new  InputStreamReader(method.getResponseBodyAsStream(),   
                                charset));   
                String line;   
                 while  ((line = reader.readLine()) !=  null ) {   
                     if  (pretty)   
                        response.append(line).append(System.getProperty( "line.separator" ));   
                     else   
                        response.append(line);   
                }
                reader.close();   
            }   
        }  catch  (IOException e) {   
            System.out.println( "执行HTTP Post请求"  + url +  "时，发生异常！" );   
            e.printStackTrace();  
            return null;
        }  finally  {   
            method.releaseConnection();   
        }   
        //System.out.println( "--------------------" +response.toString());   
        return  response.toString();   
    } 
    
    
}
