package org.MyProWeChat.base.protocol;

public interface IChannelConfig {
	
	/**
	 * 是否转换渠道响应信息
	 * @return
	 */
	boolean convertRespDesctx();
	
	/**
	 * 渠道响应信息前缀
	 * @return
	 */
	String getRespDesctxPrefix();
	
	/**
	 * 渠道名称
	 * @param channelId
	 * @return
	 */
	String getChannelName();
	
    /**
     * 是否入驻渠道
     * @param channelId
     * @return
     */
    boolean isIntoChannel();

}
