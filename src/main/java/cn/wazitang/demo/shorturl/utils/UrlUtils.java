package cn.wazitang.demo.shorturl.utils;

import java.util.regex.Pattern;

/**
 * 作者 zcw
 * 时间 2017/7/23 20:17
 * 描述 TODO
 */
public class UrlUtils {

    private static Pattern pattern;

    public static boolean checkUrl(String url) {
        return getPattern().matcher(url).find();
    }

    private static Pattern getPattern() {
        if (pattern == null) {
            String regEx = "^((http|https)://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
            pattern = Pattern.compile(regEx);
        }
        return pattern;
    }
}
