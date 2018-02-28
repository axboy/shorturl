package cn.wazitang.demo.shorturl.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 作者 zcw
 * 时间 2017/7/21 11:22
 * 描述 短链接实体类
 */
@Data
@Entity
@Table(name = "url_mapping")
public class UrlMapping {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "_key")
    private String key;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "md5")
    private String md5;

    @Column(name = "count")
    private Long count;
}
