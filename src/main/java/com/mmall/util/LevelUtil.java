package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * Description: 只适用于当前业务的层级类
 * User: lenovo
 * Date: 2018-03-29
 * Time: 15:49
 */
public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT="0";

    /**
     * level 规则  XX.XX
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId){
        if (StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else{
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
