package cn.utopay.gblwsdk.httpserver;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Map;

import cn.utopay.gblwsdk.utils.MyHashMap;


/**
 * 作者: peijiangping<BR>
 * 2013-9-13下午2:02:52<BR>
 * V3BaseThread.java<BR>
 * 功能:所有的请求线程父类
 */
public class BaseHttpThread implements Runnable {
    protected Map<String, String> maps;
    protected Handler handler;
    public static final int PostSuccess = 1;
    public static final int PostFail = -1;
    public static final int ParseFail = -2;
    protected Message msg = new Message();
    protected String value = null;
    protected String url;

    public BaseHttpThread(Handler handler, Map<String, String> maps) {
        this.handler = handler;
        if (maps == null) {
            maps = new MyHashMap<String, String>();
        }
        this.maps = maps;
    }

    public BaseHttpThread() {

    }

    public BaseHttpThread(Context c, Handler handler, Map<String, String> maps) {
        this.handler = handler;
        if (maps == null) {
            maps = new MyHashMap<String, String>();
        }
        this.maps = maps;
    }

    public BaseHttpThread(String url, Handler handler, Map<String, String> maps) {
        this.url = url;
        this.handler = handler;
        if (maps == null) {
            maps = new MyHashMap<String, String>();
        }
        this.maps = maps;
    }

    public BaseHttpThread(Handler handler, Map<String, String> maps, Object obj) {
        this.handler = handler;
        if (maps == null) {
            maps = new MyHashMap<String, String>();
        }
        this.maps = maps;
    }

    @Override
    public void run() {

    }


    /**
     * 作者:peijiangping<BR>
     * 时间:2013-9-13下午2:01:10<BR>
     * 功能:获取完整的请求链接<BR>
     * 返回值:String<BR>
     */
    public static String getFullUrl(String url, Map<String, String> params) {
        if (!url.endsWith("?")) {
            url = url + "?";
        }
        StringBuilder sb = new StringBuilder(url);
        String urlStr = null;
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value == null) {
                    // LogUtil.v("参数" + key + "为空");
                    value = "";
                }
                sb.append(key + "=" + value + "&");
            }
        }
        urlStr = sb.toString();
        return urlStr;
    }

    public static ArrayList<NameValuePair> getPostParams(Map<String, String> params) {
        ArrayList<NameValuePair> newparams = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value == null) {
                    // LogUtil.v("参数" + key + "为空");
                    value = "";
                }
                newparams.add(new BasicNameValuePair(key, value));
            }
        }
        return newparams;
    }
}
