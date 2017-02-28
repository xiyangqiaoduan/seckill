package com.yangcb.seckill.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author yangcb 请求工具类
 */
public class HttpClientUtil {

	/**
	 * post请求方式
	 * @param url
	 * @param param
	 * @return
	 */
	public static String post(String url, Map<String, String> param) {

		CloseableHttpClient httpClient = null;
		UrlEncodedFormEntity urlEncodedFormEntity = null;
		CloseableHttpResponse httpResponse = null;
		try {
//			url=getHttpUrl(url, null);
//			System.out.println(url);
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			if (param != null && !param.isEmpty()) {

				for (Entry<String, String> entry : param.entrySet()) {
					formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
			httpResponse = httpClient.execute(httpPost);
			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity, "UTF-8");
				}
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (httpResponse != null) {
					httpResponse.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * GET 方式请求 传递参数信息
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String get(String url, Map<String, String> param) {
		url = getHttpUrl(url, param);
		return get(url);
	}

	/**
	 * GET方式
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {

		String result = "";
		url = getHttpUrl(url, null);
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			httpResponse = httpClient.execute(httpGet);
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					// 打印响应内容
					return EntityUtils.toString(httpEntity, "UTF-8");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return result;

		} finally {

			try {
				if (httpResponse != null) {
					httpResponse.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return result;
	}

	/**
	 * 获取链接地址 拼接地址参数
	 * 
	 * @param url
	 * @param param
	 *            key为参数名称 value为参数值
	 * @return 字符串
	 */
	private static String getHttpUrl(String url, Map<String, String> param) {

		if (StringUtils.isNotBlank(url)) {

			StringBuilder sb = new StringBuilder(256);

			if (url.indexOf("http://") < 0) {
				sb.append("http://");
			}

			sb.append(url);

			if (param != null && !param.isEmpty()) {

				if (!url.contains("?")) {
					sb.append("?");
				}
				sb.append("&");
				for (Entry<String, String> entry : param.entrySet()) {
					sb.append(entry.getKey());
					sb.append("=");
					sb.append(entry.getValue());
					sb.append("&");
				}

			}
			return sb.toString();
		}

		return url;
	}

}
