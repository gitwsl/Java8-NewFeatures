package com.example.filetest.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Silen
 */
@Slf4j
public class HttpUtil {


    public static Map<String, Object> get(String url) {
        Map<String, Object> mapResult = new HashMap<>();
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
        String str = "";
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(defaultRequestConfig);
        //httpGet.addHeader("Authorization", "Basic "+authorization);
        HttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            System.out.println(response1.getStatusLine());
            str = EntityUtils.toString(response1.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapResult.put("code", response1.getStatusLine().getStatusCode());
        mapResult.put("data", str);

        return mapResult;
    }

    /**
     * get请求 - 带header
     *
     * @param url        http路径
     * @param headerList header信息
     */
    public static Map<String, Object> get(String url, List<Map<String, String>> headerList) {
        Map<String, Object> mapResult = new HashMap<>();
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
        String str = "";
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(defaultRequestConfig);
        for (Map<String, String> headerMap : headerList) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        //httpGet.addHeader("Authorization", "Basic "+authorization);
        HttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            System.out.println(response1.getStatusLine());
            str = EntityUtils.toString(response1.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapResult.put("code", response1.getStatusLine().getStatusCode());
        mapResult.put("data", str);

        return mapResult;
    }

    public static Map<String, Object> post(String url, String jsonParam) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        Map<String, Object> mapResult = new HashMap<>();
        HttpPost method = new HttpPost(url);
        String str = "";
        HttpResponse result = null;
        try {
            if (null != jsonParam) {
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json;charset=UTF-8");
                // method.addHeader("Authorization", "Basic "+authorization);
                method.setEntity(entity);
            }
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000)
                    .setConnectionRequestTimeout(10000)
                    .build();
            method.setConfig(defaultRequestConfig);
            result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            try {
                str = EntityUtils.toString(result.getEntity());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapResult.put("code", result.getStatusLine().getStatusCode());
        mapResult.put("data", str);
        return mapResult;
    }

    /**
     * 调用对方接口方法
     *
     * @param path     对方或第三方提供的路径
     * @param paramMap 向对方或第三方发送的数据
     */
    public static String doPost(String path, String paramMap) {
        String returnValueInfo = null;
        String str = "";
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            StringBuffer stringBuffer = new StringBuffer(str);
            //设置通用的请求属性
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("debug", "true");
            conn.setRequestProperty("token", "A200Token");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded charset=UTF-8");
            //设置编码 charset=UTF-8
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
//            String pollingJson = String.valueOf(JSONObject.toJSON(paramMap));
            System.out.println("util参数" + paramMap);
            out.print(paramMap.getBytes("UTF-8"));
            //缓冲数据
            out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((str = br.readLine()) != null) {
                //返回的数据
                StringBuffer append = stringBuffer.append(str);
                returnValueInfo = new String(append);
                System.out.println("str" + str);
                return str;
            }
            //关闭流
            is.close();
            //断开连接，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");
        } catch (Exception e) {
//            System.out.println("---------请求第三方接口-T------callInterfaceUtil--------异常信息" + e);
            str = "---------Exception-------callInterfaceUtil--------" + e;
        }
//        System.out.println(returnValueInfo);
        if (returnValueInfo != null) {
            return returnValueInfo;
        }
        return str;
    }

    public static CloseableHttpClient getClient() {
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(65000)
                        .setConnectTimeout(65000).setSocketTimeout(200000).build())
                .setDefaultCookieStore(cookieStore).build();
        return httpClient;
    }

    public static String get(CloseableHttpClient httpClient, String url, Map<String, String> headers) throws ClientProtocolException, IOException {
        HttpGet httpGet = new HttpGet(url);
        if (headers != null) {
            headers.entrySet().stream().forEach(
                    entry -> {
                        httpGet.setHeader(entry.getKey(), entry.getValue());
                    }
            );
        }
        HttpResponse httpResponse = httpClient.execute(httpGet);
        int code = httpResponse.getStatusLine().getStatusCode();
        if (code >= 299) {
            log.error("status code is {}", code);
        }
        return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
    }

    public static String post(CloseableHttpClient httpClient, String url, Map<String, Object> params, Map<String, String> headers)
            throws ParseException, IOException {

        HttpPost httpPost = new HttpPost(url);
//		UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(params), "UTF-8");
        String str;
        if (params == null) {
            Map<String, Object> map = new HashMap<>();
            str = JSON.toJSONString(map);
        } else {
            if (params.containsKey(" ")) {
                Object object = params.get(" ");
                str = JSON.toJSONString(object);
            } else {
                str = JSON.toJSONString(params);
            }
        }

        StringEntity postEntity = new StringEntity(str, "UTF-8");
        httpPost.setEntity(postEntity);
        if (headers != null) {
            addHeaders(httpPost, headers);
        }
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int code = httpResponse.getStatusLine().getStatusCode();
        if (code >= 299) {
            log.error("status code is {}", code);
        }
        return EntityUtils.toString(httpResponse.getEntity());
    }

    public static String postByForm(CloseableHttpClient httpClient, String url, Map<String, Object> params, Map<String, String> headers)
            throws ParseException, IOException {
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(params), "UTF-8");
        httpPost.setEntity(postEntity);
        if (headers != null) {
            addHeaders(httpPost, headers);
        }
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int code = httpResponse.getStatusLine().getStatusCode();
        if (code >= 299) {
            log.error("status code is {}", code);
        }
        return EntityUtils.toString(httpResponse.getEntity());
    }

    public static String put(CloseableHttpClient httpClient, String url, Map<String, Object> params, Map<String, String> headers)
            throws ParseException, IOException {
        HttpPut httpPut = new HttpPut(url);
        String str = JSON.toJSONString(params);
        StringEntity postEntity = new StringEntity(str, "UTF-8");
        httpPut.setEntity(postEntity);
        if (headers != null) {
            addHeaders(httpPut, headers);
        }
        HttpResponse httpResponse = httpClient.execute(httpPut);
//		int code = httpResponse.getStatusLine().getStatusCode();
        return EntityUtils.toString(httpResponse.getEntity());
    }

    private static SSLConnectionSocketFactory createSSLsocket() {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, null, null,
                    new NoopHostnameVerifier());
            return sslsf;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static CloseableHttpClient getNoCookieClient() {
        SSLConnectionSocketFactory sslsf = createSSLsocket();

//		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(10000)
//				.setConnectTimeout(10000).setSocketTimeout(10000).build())
//		.build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(30000)
                        .setConnectTimeout(30000).setSocketTimeout(30000).build())
                .build();

        return httpClient;
    }

    public static CloseableHttpClient getNoCookieClient_longTimeWait() {
        SSLConnectionSocketFactory sslsf = createSSLsocket();

//		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(10000)
//				.setConnectTimeout(10000).setSocketTimeout(10000).build())
//		.build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(30000)
                        .setConnectTimeout(30000).setSocketTimeout(900000).build())
                .build();

        return httpClient;
    }

    //	public static String getSpecifiedUrl(String uri, Map<String, String> param){
//		StringBuilder urlBuilder = new StringBuilder(UrlList.HOST);
//		urlBuilder.append(uri).append("?");
//		param.entrySet().stream().forEach(entry -> {
//			urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//		});
//		if (urlBuilder.lastIndexOf("&") == urlBuilder.length()-1) {
//			urlBuilder.delete(urlBuilder.length()-1, urlBuilder.length());
//		}
//		return urlBuilder.toString();
//	}
    public static String getSpecifiedUrl(String host, String uri, Map<String, String> param) {
        StringBuilder urlBuilder = new StringBuilder(host);
        urlBuilder.append(uri).append("?");
        param.entrySet().stream().forEach(entry -> {
            urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        });
        if (urlBuilder.lastIndexOf("&") == urlBuilder.length() - 1) {
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        return urlBuilder.toString();
    }

    public static String getSpecifiedUrl(String host, String uri) {
        StringBuilder urlBuilder = new StringBuilder(host);
        urlBuilder.append(uri);
        return urlBuilder.toString();
    }


    /**
     * 参数
     *
     * @param parameterMap
     * @return
     */
    private static List<NameValuePair> getParam(Map<String, Object> parameterMap) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        if (parameterMap != null) {
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                param.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        return param;
    }

    private static void addHeaders(HttpEntityEnclosingRequestBase httpRequest, Map<String, String> headerMap) {
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpRequest.addHeader(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     */
//    public static String sendGet(String url, String param, Map<String, String> header) throws UnsupportedEncodingException, IOException {
//        String result = "";
//        BufferedReader in = null;
//        String urlNameString = url + "?" + param;
//        URL realUrl = new URL(urlNameString);
//        // 打开和URL之间的连接
//        URLConnection connection = realUrl.openConnection();
//        //设置超时时间
//        connection.setConnectTimeout(5000);
//        connection.setReadTimeout(15000);
//        // 设置通用的请求属性
//        if (header!=null) {
//            Iterator<Map.Entry<String, String>> it =header.entrySet().iterator();
//            while(it.hasNext()){
//                Map.Entry<String, String> entry = it.next();
//                System.out.println(entry.getKey()+":"+entry.getValue());
//                connection.setRequestProperty(entry.getKey(), entry.getValue());
//            }
//        }
//
//        connection.setRequestProperty("accept", "*/*");
//        connection.setRequestProperty("connection", "Keep-Alive");
//        connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//
//        // 建立实际的连接
//        connection.connect();
//        // 获取所有响应头字段
//        Map<String, List<String>> map = connection.getHeaderFields();
//        // 遍历所有的响应头字段
//        for (String key : map.keySet()) {
//            System.out.println(key + "--->" + map.get(key));
//        }
//        // 定义 BufferedReader输入流来读取URL的响应，设置utf8防止中文乱码
//        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
//        String line;
//        while ((line = in.readLine()) != null) {
//            result += line;
//        }
//        if (in != null) {
//            in.close();
//        }
//        return result;
//    }

    /**
     * httpclient发送get请求
     */
    public static InputStream httpGetDownloadFile(String uri) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        InputStream inputContent = null;
        try {
            // 创建httpclient get请求.
            HttpGet httpget = new HttpGet(uri);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
//            try {
                return response.getEntity().getContent();
//                FileUtils.input2file(response.getEntity().getContent(), new File(CommonConsts.UPLOAD_FILE_PATH, uri.substring(uri.lastIndexOf(File.separator))));
//            } finally {
//                response.close();
//            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return null;
    }

    public static String upload(String url, File file, String paramFileName, Map<String, String> param) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();

            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(url);

            // 相当于<input type="file" name="file"/>
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(paramFileName == null ? "file" : paramFileName, new FileInputStream(file), ContentType.DEFAULT_BINARY, file.getName());
            for (Map.Entry<String, String> entry : param.entrySet()) {
                String key = entry.getKey();
                // 相当于<input type="text" name="userName" value=userName>
                StringBody value = new StringBody(entry.getValue(), ContentType.create("text/plain", Consts.UTF_8));
                builder.addPart(key, value);
            }
            HttpEntity reqEntity = builder.build();

            httpPost.setEntity(reqEntity);

            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);

            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200 && resEntity != null) {
                return EntityUtils.toString(response.getEntity());
            }
            // 销毁
            EntityUtils.consume(resEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
