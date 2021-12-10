package org.market.hedge.binance;

import com.alibaba.fastjson.JSON;
import org.market.hedge.binance.option.service.BinanceOptionApiException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class UrlParamsBuilder {

    class ParamsMap {

        final Map<String, String> map = new LinkedHashMap<>();
        final Map<String, List> stringListMap = new HashMap<>();

        void put(String name, String value) {

            if (name == null || "".equals(name)) {
                throw new BinanceOptionApiException(BinanceOptionApiException.RUNTIME_ERROR, "[URL] Key can not be null");
            }
            if (value == null || "".equals(value)) {
                return;
            }

            map.put(name, value);
        }

        void put(String name, Integer value) {
            put(name, value != null ? Integer.toString(value) : null);
        }

        void put(String name, Date value, String format) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
            put(name, value != null ? dateFormatter.format(value) : null);
        }

        void put(String name, Long value) {
            put(name, value != null ? Long.toString(value) : null);
        }

        void put(String name, BigDecimal value) {
            put(name, value != null ? value.toPlainString() : null);
        }

    }

    private final ParamsMap paramsMap = new ParamsMap();
    private final ParamsMap postBodyMap = new ParamsMap();
    private String method = "GET";

    public static UrlParamsBuilder build() {
        return new UrlParamsBuilder();
    }

    private UrlParamsBuilder() {
    }

    public UrlParamsBuilder setMethod(String mode) {
        method = mode;
        return this;
    }

    public Boolean checkMethod(String mode) {
        return mode.equals(method);
    }

    public <T extends Enum> UrlParamsBuilder putToUrl(String name, T value) {
        if (value != null) {
            paramsMap.put(name, value.toString());
        }
        return this;
    }

    public UrlParamsBuilder putToUrl(String name, String value) {
        paramsMap.put(name, value);
        return this;
    }

    public UrlParamsBuilder putToUrl(String name, Date value, String format) {
        paramsMap.put(name, value, format);
        return this;
    }

    public UrlParamsBuilder putToUrl(String name, Integer value) {
        paramsMap.put(name, value);
        return this;
    }

    public UrlParamsBuilder putToUrl(String name, Long value) {
        paramsMap.put(name, value);
        return this;
    }

    public UrlParamsBuilder putToUrl(String name, BigDecimal value) {
        paramsMap.put(name, value);
        return this;
    }

    public UrlParamsBuilder putToPost(String name, String value) {
        postBodyMap.put(name, value);
        return this;
    }

    public <T extends Enum> UrlParamsBuilder putToPost(String name, T value) {
        if (value != null) {
            postBodyMap.put(name, value.toString());
        }
        return this;
    }

    public UrlParamsBuilder putToPost(String name, Date value, String format) {
        postBodyMap.put(name, value, format);
        return this;
    }

    public UrlParamsBuilder putToPost(String name, Integer value) {
        postBodyMap.put(name, value);
        return this;
    }

    public <T> UrlParamsBuilder putToPost(String name, List<String> list) {
        postBodyMap.stringListMap.put(name, list);
        return this;
    }

    public UrlParamsBuilder putToPost(String name, Long value) {
        postBodyMap.put(name, value);
        return this;
    }

    public UrlParamsBuilder putToPost(String name, BigDecimal value) {
        postBodyMap.put(name, value);
        return this;
    }

    public String buildUrl() {
        Map<String, String> map = new LinkedHashMap<>(paramsMap.map);
        StringBuilder head = new StringBuilder("");
        return "?" + AppendUrl(map, head);

    }

    public String buildSignature() {
        Map<String, String> map = new LinkedHashMap<>(paramsMap.map);
        StringBuilder head = new StringBuilder();
        return AppendUrl(map, head);

    }

    private String AppendUrl(Map<String, String> map, StringBuilder stringBuilder) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!("").equals(stringBuilder.toString())) {
                stringBuilder.append("&");
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(urlEncode(entry.getValue()));
        }
        return stringBuilder.toString();
    }

    public boolean hasPostParam() {
        return !postBodyMap.map.isEmpty() || "POST".equals(method);
    }

    public String buildUrlToJsonString() {
        return JSON.toJSONString(paramsMap.map);
    }

    /**
     * 使用标准URL Encode编码。注意和JDK默认的不同，空格被编码为%20而不是+。
     *
     * @param s String字符串
     * @return URL编码后的字符串
     */
    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new BinanceOptionApiException(BinanceOptionApiException.RUNTIME_ERROR, "[URL] UTF-8 encoding not supported!");
        }
    }


    public void putObject(Object ob) {
        Field[] fields = ob.getClass().getDeclaredFields();
        for (Field field : fields) {
            //这个是，有的字段是用private修饰的 将他设置为可读
            field.setAccessible(true);
            //获取值
            try {
                putToUrl(field.getName(), field.get(ob).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
