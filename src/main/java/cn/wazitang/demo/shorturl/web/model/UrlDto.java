package cn.wazitang.demo.shorturl.web.model;

import lombok.Data;

/**
 * 作者 zcw
 * 时间 2017/7/21 14:20
 * 描述 /api/url/list 接口返回值
 */
@Data
public class UrlDto {
    private String key;
    private String shortUrl;
    private String sourceUrl;
    private Long count;
}
