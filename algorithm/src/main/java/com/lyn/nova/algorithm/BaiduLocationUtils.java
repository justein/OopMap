package com.lyn.nova.algorithm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.CharSetUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/***
 * @ClassName: BaiduLocationUtils
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/9/3 下午10:03
 * @version : V1.0
 */
public class BaiduLocationUtils {

    public static Map<String, BigDecimal> getLatAndLngByAddress(String addr) throws UnsupportedEncodingException {
        String address = "";
        String city = "济南市";
        String lat = "";
        String lng = "";
        String myAK = "8jHGeq0L404R9Ig7wLpKuzDqCvrbZj5V";
        String mySK = "c9HdFCg2sTlSe91W1SY5RzNT9DxlFs8t";
        try {
            address = java.net.URLEncoder.encode(addr,"UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        URL myURL = null;
        URLConnection httpsConn = null;

        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("address", addr);
        paramsMap.put("output", "json");
        paramsMap.put("ak", myAK);

        Map paramsMapWithCity = new LinkedHashMap<String, String>();
        paramsMapWithCity.put("address", addr);
        paramsMapWithCity.put("output", "json");
        paramsMapWithCity.put("city",city);
        paramsMapWithCity.put("ak", myAK);

        // 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
        String paramsStr = toQueryString(paramsMapWithCity);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
        String wholeStr = new String("/geocoding/v3/?" + paramsStr + mySK);

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

        // 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
        System.out.println("sn is : "+MD5(tempStr));
        //进行转码
        String url = String.format("http://api.map.baidu.com/geocoding/v3/?address=%s"
                +"&output=json&ak="+myAK+"&sn="+MD5(tempStr),address);

        String urlWithCity = String.format("http://api.map.baidu.com/geocoding/v3/?address=%s"
                +"&output=json&city=%s"+"&ak="+myAK+"&sn="+MD5(tempStr),address,java.net.URLEncoder.encode(city,"UTF-8"));
        try {
            myURL = new URL(urlWithCity);
        } catch (MalformedURLException e) {

        }
        try {
            httpsConn = myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String json ="";
                String data = null;
//                if ((data = br.readLine()) != null) {
//                    lat = data.substring(data.indexOf("\"lat\":")
//                            + ("\"lat\":").length(), data.indexOf("},\"precise\""));
//                    lng = data.substring(data.indexOf("\"lng\":")
//                            + ("\"lng\":").length(), data.indexOf(",\"lat\""));
//                }
                while ((data=br.readLine())!=null) {
                    json = json + data +"\n";
                }
                System.out.println(json);
                JSONObject obj=JSON.parseObject(json);//将json字符串转换为json对象
                String resultInner   = obj.getString("result");
                JSONObject resultObj = JSON.parseObject(resultInner);
                JSONObject locationObj = JSON.parseObject(resultObj.getString("location"));
                System.out.println("location : "+addr+" , 经度："+locationObj.getString("lng")+" , 纬度："+locationObj.getString("lat"));


                insr.close();
            }
        } catch (IOException e) {

        }
        Map<String, BigDecimal> map = new HashMap<>();
//        map.put("lat", new BigDecimal(lat));
//        map.put("lng", new BigDecimal(lng));
//        System.out.println("位置："+addr+", 经度："+new BigDecimal(lng)+ "纬度："+new BigDecimal(lat));
        return map;
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    public static String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        getLatAndLngByAddress("北胡小区");
    }
}
