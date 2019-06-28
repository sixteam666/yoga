package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092900622191";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCy6VYqNsqoPsBX53aLn11S5LcM6G7uqrODRmdJAinyz5Xta3V9fMcou6PJqtsqvvJOaT1ytxV6K9yvfRRtqXcAEGsr3Qi1DMF3tGFgmRe03yV38pG+vhcBVlr8DbIcg/BB2ZITDt2uGWU3Y5TNqNNfAJexR2Sg9Em/4f9s1RAQAWgpzZT9g+ARC7sQsw2TVXzfYRjHekxEUy8fp7kdM8TLCN80mlxBisgOqkwV3kxibUvPK6GtitnUn+QmonxahwV7z8NAneb7Z+Tstltj0+33mhxEcD7amTfGToi2gTBL59QdLTzatWqWjeaa6Uf3Wsad21YqraRBFMP4Clhfpy9pAgMBAAECggEAPT5Gspii8sjYSr7/A3iAF7oXNyHxp11jqdJV1NYayfVpWyZnx5cJDdZ9kLEd6RoIE/NOh5BIl9Of7aqhbZCPHcSBCzjUnDVLnNBwbQK67L1eNBsi7OphMaH/7zw49i3r9n6l1ERIeipTTYyXCXd7M5AeXR8VQUueW0Lfjr+4ieF9QAYv80op5p5b0z/8S5lXWpm2RYy86hc7ecPdy6Ai5gJXcPIq30/U7PoS6Pn9CVZOyQoXfFaAmzGwWouCxxejI5R/ejD48uOe+anQqnO16Qv05CAX2sE8LmA/sMqxMLVMolbj162rjIUn/5CTWKOI0bGw9YmNM+vF4WRl5e0KoQKBgQD4gaO6EJlYNnZVlM8MO3cZsuZTWmoVkhaz8teHByOF7G06rctaGYLW5TFa82OHmOvemmtaWrkA3anr9SaAeWt9UP7S+SWWTU/20VFA4CMxWChj4bvQxTPZljurglR62Qmcjza2vDbobMw2weS//aq8LkCefxaw2qxGCA1JSU72hQKBgQC4TnRkzVnvwXnSX+O8j5r7TyhZZIn9PhdDOcixKuyLkwdNfV1U5NVjMmJ2iwTpxGxziQwiddReuxgSTLC2LCqANgyJ9UIZRMLZcCA4tJ4WMUgR5Py7X6u4ETZ3Z1DEvyKfoRf6yACg23HNXy9EbgD/lMyZmVlAzBI/e1brUhsklQKBgHU0v8H8HjcKyNkobFb6nPvT8uoRVMODuY151clrxoddsU7htW1zZtBwHM0myiSkLWs6LbPZ4jPUWFZfs58YpWffkbBzrhSrtjFVHBubGz4ktDgXuXuH5yKSxuXnfGzMfMoIiee58bPBzLduz7L1s1c6AWQ/0LXtDR+ioJGWL9X5AoGBAJlAMyw2TFg9ymwRa644wqfA1E4weUTnwjQV8jjO9k3AfcwDN/4FISIQnSrwwa8Xsw4GoN5ul54FeCiPOEjiRJzDVH3KP6H/OP3QbBp6pRDS/ZhTjYrILGMrfNygPdq7C7EsJGJUt6EJl4EraoQ96/qY4d++USa+CVZb2pRle0hZAoGAOLu7RzFNUlljfmX3jHYjcyjjQCNG3xUBoBtZbYq9lCH25QhBGHjTK2xExYjC2rt2f2GByjrubQUawipWQHH8sq+5rZ55L3p/xM3KbNOHLlxGmynvDCj9gvqV3GUHlF4XK/mNQi+0ENE9LZOZOxQdSI/nkr9KuEPNuUH7ozrdtzQ=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzr4kbQHtlbVg7HOnP3kb+TpEzUovry47yuuU4C4HPgum6cLyWQDzL5NXtPBW01NAOtZRMoQAc1FPwfjaPCFb5EKTTyoLkdRhSD5BvFXYTk46xo50SfOPro/7jQ2vQvOR3qzjwWvrNQ+I5xR126Ep9R2RPm4SnJEzgKc379e5r6sdoAdV7+WYppTk7wHGZZEBjgFvuhNHgvpjpxBfMd2834Wmaw1hIGaeKsufGkBdXa+LH0Muxk+M5MqH7NErWtKLQvQ8CSwR7QRPStwd7O81BnzTs8DlUY8/zikoIiSEAONWVA2dfHLHxkENnbBu/xHUqY/VYtwEizrRxNMu0Qu6jQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://24810n32z2.qicp.vip:29796/ZzyShoseShop/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://24810n32z2.qicp.vip:29796/ZzyShoseShop/Web/index.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

