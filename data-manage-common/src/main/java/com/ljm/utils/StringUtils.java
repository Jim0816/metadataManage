package com.ljm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StringUtils
 * @Description
 * @Author Jim
 * @Date 2022/4/11 9:53
 **/
public class StringUtils {

    /**
     * @description 驼峰命名转换下划线
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/11 9:57
     **/
    public static String humpToLine(String str) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
