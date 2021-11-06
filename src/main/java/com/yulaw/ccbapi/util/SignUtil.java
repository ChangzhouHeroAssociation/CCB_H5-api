package com.yulaw.ccbapi.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class SignUtil {

    private static RedisTemplate redisTemplate ;

    public static String APP_ID="wxe514b4e491dedf9d";//在controller中初始化
    public static String APP_SECRET="1dcf0e77b3be82205614fff3f8554224";


    @Resource(name="redisTemplate")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        SignUtil.redisTemplate = redisTemplate;
    }

    public static Map<String, String> getResult(String url){

        Map<String, String> ret = sign(getTicket(), url);
        ret.put("appId", getAppId());
        return ret;
    }

    private static String getAppId(){
        return APP_ID;
    }

    public static String getToken(){
        String accessToken = "";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+getAppId()+"&secret="+APP_SECRET;
        try {
            JsonNode resultNode =HttpUtil.get(url);
            if (null == resultNode.get("errcode")) {
                accessToken = resultNode.get("access_token").asText();
                //System.out.println(accessToken);
            }else{
                //System.out.println("返回错误，请检查请求报文或者请求地址是否正确");
                //System.out.println(resultNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(accessToken)){
            BoundHashOperations<String,String,String> hashKey = redisTemplate.boundHashOps("access_token");
            hashKey.put("token",accessToken);
            redisTemplate.expire("access_token",7200,TimeUnit.SECONDS);
        }
        return accessToken;
    }

    public static String getTicket(){

        String ticket = "";
        String url;
        if (redisTemplate.hasKey("access_token")){
            BoundHashOperations<String,String,String> hashKey = redisTemplate.boundHashOps("access_token");
            url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+hashKey.get("token")+"&type=jsapi";
        }else{
            url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+getToken()+"&type=jsapi";
        }
        try {
            JsonNode resultNode =HttpUtil.get(url);
            if (null != resultNode.get("ticket")) {
                ticket = resultNode.get("ticket").asText();
            }else{
                //System.out.println("返回值为空，请检查请求报文或者请求地址是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        //System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString().replace("-","");
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
