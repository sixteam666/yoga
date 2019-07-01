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
	public static String app_id = "2016092900622201";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDKl/fN6oZdls3EIwfJTJ8dl8QsaVLbM5Nkj5OGzkAMXFwoOPXVhvkTQVXDJo8dtsaPTBUNHHSGeUTt4suXHcdS09Gh+uut51kEsNxPAOKfwtx1kgF1Hrkzj+jDOVH/pa07ymvuceP2isS19YVsvV0lE8MaxUSfuzlV2edsH7pEFnYvkvlHuezPyOgp+H5Q+UPxXE4Teyfw0RyIARsCObpcR3dMwoscRiDUpupQAskUJUnGIl6WdE7uv1o41MUk/xbOdoXm40Byc5kPjRpiJ02JeXH7XJjTqyJ649jf9T3GAvvjqjKZeDHOuRqJkW8ASYqkz8DJZGr/xWIlPcMvprnJAgMBAAECggEBAMloFQoYamYmYv/nl3kO/GyOEY/ZUW7sMk7Z0d9FsjTXqSKvpe4Wlg4HePxenA79Qx74k0EKlemOK/GW87GTADKetByleKHVihD4xryf86zCKyuS8HIADjxtiAh1NsjBa5wyW+ScQvY59N+0a2W0SCJxKBi4HsXxekmYLNK4JBREvscgZg+SuZV49ItoczeD0BqKomdq+GGncxXjXu0JhLqHlGp8eF5M++zQ9HtPMPNtXFze3pfXWQ25hsdsukUCYzP95zA9oIG36MZQsl63gSkgxq+Tk838HKgZ0bKIXkpSOAmgECpWOgWmFry6oWEN3AbcVSpqq5sMfaKV7ITh7xUCgYEA8PpIrtTGREYKmNIBge1CzrEBSP6cY+QGWx46DKR00Ka61W0YJd9VtsZFvER19HuPySVBfpHaYZPUIYBfnN4dKj/7IxavTbg+PgieXX/GKSZnpz+AjlYV2VPd3F/veLJBEIp4Na13ww6cVsyXfJxKwo7LrKHCvFfGw6YbfpB99icCgYEA1zke2EMlWbmia5ngmkb2alGlJgogUVKGQC8Z0F2aOBG8PihqWZC0YgSNmTOaFCgO119st8Wt2UPKCZH3D+65X61Vb9uTJWeueLwnY/TsAkRj9NiCpFw5diIbJ17PA1U1VKfmo2arkGucOw2qTB6NpsBsqpn25iBDdMyst6ZyNo8CgYEArDfi+/eanq6D4kWryCQ9a6Q6q+Uy9XzAQhxvFhvuVDPkMKWf7WkYQ+Cdc/hlaE1qLDHRBdjM/pF8uFibfGuM4mYJYEFVFbFH4mOccmvfvgbxayjKAm9pfAEg/N8fMN4L+/tJ/+qyPZfSiYH7/ZVhJHyS+RQ+LQZ6SdcLrT8sll8CgYBn85joT5YsvjKq0gnfukKum3yu4Nk3LirVuWo4b91bE4XeZ9rmI7RcW2KhCoFzFt4EPHzgTkRn2gwtjyjLszwp8CiB/IYX3PoyKn7EErojZjD5Q9Hl3P2MB3EiFPLb4z39A5OsfJyFBtTQ2QrV9TPpxKs3w2GzSQYL1gfeNDCaFQKBgQC8vlcuUEbDOPy3h9nHo8g3Y4GvHeKOj7aKdzjAGFI+xac1gVDz/nftDKYXtHwbvdNe51f1AD1Sv6KTf6imGMa0CrIwjZhrou4xTBBDEG13fFX9Umfdcs/oPq/1XEuIThS3SXxPLGjHj092qqvPYrDScHF6i/C/EASCbUrtw78irA==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqziMHirm6NQDgszk8vLy3vCzq0AxBpGrQBTVZgwfxim0pJ4jbprz8LlzhGQmw+WcOSsTvCmoTVY3K+pDDmZ+sZM8HdTZ9iCvgiisAwW23oKoCTcW0xGwJWi0nhN/qZ4K7Dj+YKE2mNnlv/hp2IM2tTV+2ceSjUljogoMQ/dyD0JoSqNB+TyxUdaVHSZqDJYbYy9ZUOhm7DWaMLsTIF/dkuNmg39HMdWPLqfPSFOutd3e8v0dALfxUeVrsxtW1N29zVT3Sl8D3hwen7JcSMOVvRUb+adQ71fC++IbrI17GAXEsn0e2aqa3Zwn3q56uRbrUPxGU2lyFvmTVJb41reUGwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://x24811z451.qicp.vip/jsp/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://x24811z451.qicp.vip/student/investMoney.do";
	
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

