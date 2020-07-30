package org.MyProWeChat.base.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置数据，基于缓存机制实现缓存。
 * @author ShenPengL
 *
 */
public abstract class AbstractConfig {

    /**
     * 配置数据
     */
    protected Map<String, String> configMap = new ConcurrentHashMap<String, String>();
    /**
     * 获取配置组名称
     * @return
     */
    protected abstract String getGroupName();
    
    
    
    @SuppressWarnings("unused")
    private String get(String key, String desc, boolean nullException) {
        String tmpStr = configMap.get(key);
        if(tmpStr != null && tmpStr.length() > 0) {
            return tmpStr;
        }
        if(nullException) {
            throw new RuntimeException("参数：" + desc + "(" + getGroupName() + "-" + key + ")未配置。");
        }
        return null;
    }
    
}
