package org.MyProWeChat.base.lang;

import java.util.HashMap;
import java.util.Map;

import org.MyProWeChat.base.utils.StringUtil;

public class Params extends HashMap<String, Object> {

    private static final long serialVersionUID = 198957983222638176L;

    public Params add(String key, Object value) {
        put(key, value);
        return this;
    }

    public Params addAll(Map<String, Object> map) {
        putAll(map);
        return this;
    }

    public Params addAll(Params map) {
        putAll(map);
        return this;
    }

    public Params() {
    }

    public Params(Map<String, Object> map) {
        putAll(map);
    }

    public String toString() {
        return StringUtil.mapToString(this);
    }
}
