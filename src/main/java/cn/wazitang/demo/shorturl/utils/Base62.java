package cn.wazitang.demo.shorturl.utils;

import java.util.Arrays;

/**
 * 作者 zcw
 * 时间 2017/7/20 18:20
 * 描述 62进制转化
 */
public class Base62 {

    /**
     * 当前数据库中的递增值
     */
    private static long dbIndex = 0;

    /**
     * 62位字符
     */
    private static char[] CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z'
    };

    /**
     * 62
     */
    private static int SIZE = CHARS.length;

    /**
     * 生成一个短链接
     *
     * @return str
     */
    public static String generateShortUrl(long index) {
        return getRandomStr(2) + convertDecTo62(index);
    }

    /**
     * 根据短链接key计算数据库的id
     *
     * @param url;
     * @return
     */
    public static long calDbIdByUrl(String url) {
        long id = convert62ToDec(url.substring(2));
        return id > dbIndex ? 0 : id;
    }

    /**
     * 修改当前的起始值
     *
     * @param v;
     */
    public static void setDbIndex(long v) {
        dbIndex = v;
    }

    public synchronized static long getDbIndex() {
        return ++dbIndex;
    }

    /**
     * 获取随机字符串
     *
     * @param length 长度
     * @return str
     */
    private static String getRandomStr(int length) {
        StringBuilder sb = new StringBuilder();
        while (length > 0) {
            int i = (int) (Math.random() * SIZE);
            sb.append(CHARS[i]);
            length--;
        }
        return sb.toString();
    }

    /**
     * 将10进制数转为62进制字符串
     *
     * @param num;
     * @return str
     */
    private static String convertDecTo62(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int i = (int) (num % SIZE);
            sb.append(CHARS[i]);
            num /= SIZE;
        }
        return sb.reverse().toString();
    }

    /**
     * 将62进制转为10进制
     *
     * @param num;
     * @return dec
     */
    private static long convert62ToDec(String num) {
        char[] arr = num.toCharArray();
        int length = arr.length;
        long result = 0;
        for (char c : arr) {
            length--;
            int position = Arrays.binarySearch(CHARS, c);
            result += (long) Math.pow(SIZE, length) * position;
        }
        return result;
    }
}
