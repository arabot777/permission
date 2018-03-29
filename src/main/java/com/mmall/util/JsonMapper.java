package com.mmall.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *    json 与 类对象互转
 * User: lenovo
 * Date: 2018-03-29
 * Time: 13:40
 */

@Slf4j
public class JsonMapper {

    private final static Logger log = LoggerFactory.getLogger(JsonMapper.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //conifg 变量初始化
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     * 对象转成字符串
     * @param  src
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T src){
        if (src == null)
            return null;
        try {
            return src instanceof String ? (String)src : objectMapper.writeValueAsString(src);
        }catch (Exception e){
            log.warn("parse object to String exception ,error:{}", e);
            return null;
        }
    }

    /**
     * 字符串转对象
     * @param src
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String src, TypeReference<T> typeReference){
        if (src == null || typeReference == null)
            return null;
        try {
            return (T)(typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src , typeReference));
        }catch (Exception e){
            log.warn("parse  String to object exception, String:{},TypeReference:{},error:{}", src,typeReference.getType(),e);
            return null;
        }
    }
}
