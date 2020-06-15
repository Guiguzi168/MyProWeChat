package org.MyProWeChat.base.protocol;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.ssl.SslHandler;

/**
 * 调用外部系统的客户端接口
 * @author ShenPengL
 *
 */
public interface IClient {
    /**
     * 初始化方法
     */
    void init();
    /**
     * 解析配置
     * @param config
     */
    void parserConfig(String config);
    
    SslHandler getSslHander(String host, int port);
    /**
     * 构造Http请求
     * @param request 请求对象
     * @param sendBytes 请求报文
     * @param serviceUrl 请求地址
     * @return
     */
    DefaultFullHttpRequest buildRequest(IClientRequest request, byte[] sendBytes, String serviceUrl);
    /**
     * 获取链接通道
     * @param serviceUrl
     * @return
     */
    Channel getChannel(String serviceUrl);
    /**
     * 获取链接
     * @param connectUrl
     * @return
     */
    Channel connect(String connectUrl);
    /**
     * 发送
     */
    void send();
    
//    void send(final IClientRequest request, String serviceUrl);

    void shutdown();

}
