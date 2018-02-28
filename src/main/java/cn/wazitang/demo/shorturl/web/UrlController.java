package cn.wazitang.demo.shorturl.web;

import cn.wazitang.demo.shorturl.domain.UrlMapping;
import cn.wazitang.demo.shorturl.repo.UrlMappingRepo;
import cn.wazitang.demo.shorturl.service.UrlMappingService;
import cn.wazitang.demo.shorturl.utils.Base62;
import cn.wazitang.demo.shorturl.utils.UrlUtils;
import cn.wazitang.demo.shorturl.web.model.UrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者 zcw
 * 时间 2017/7/20 18:04
 * 描述 TODO
 */
@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlMappingRepo urlMappingRepo;

    @Autowired
    private UrlMappingService urlMappingService;

    @Value("${cn.wazitang.demo.shorturl.domain}")
    private String serverDomain;

    @RequestMapping("/page")
    public Page<UrlDto> page(Pageable page) {
        return urlMappingRepo.findAll(page).map(domain -> {
            UrlDto dto = new UrlDto();
            dto.setKey(domain.getKey());
            dto.setShortUrl(serverDomain + domain.getShortUrl());
            dto.setSourceUrl(domain.getSourceUrl());
            dto.setCount(domain.getCount());
            return dto;
        });
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<UrlMapping> add(String url) {
        if (!UrlUtils.checkUrl(url)) {
            return ResponseEntity.badRequest().build();
        }
        long curIndex = Base62.getDbIndex();
        String key = Base62.generateShortUrl(curIndex);
        UrlMapping domain = new UrlMapping();
        domain.setSourceUrl(url);
        domain.setShortUrl(key);
        domain.setKey(key);
        domain.setId(curIndex);
        domain.setCount(0L);
        urlMappingRepo.save(domain);
        return ResponseEntity.ok(domain);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<UrlMapping> edit(String key, String url) {
        if (!UrlUtils.checkUrl(url)) {
            return ResponseEntity.badRequest().build();
        }
        UrlMapping urlMapping = urlMappingService.checkKey(key);
        if (urlMapping == null) {
            return ResponseEntity.badRequest().build();
        }
        urlMapping.setSourceUrl(url);
        urlMappingRepo.save(urlMapping);
        return ResponseEntity.ok(urlMapping);
    }
}
