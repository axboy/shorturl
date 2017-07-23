package cn.wazitang.demo.shorturl.service;

import cn.wazitang.demo.shorturl.domain.UrlMapping;
import cn.wazitang.demo.shorturl.repo.UrlMappingRepo;
import cn.wazitang.demo.shorturl.utils.Base62;
import cn.wazitang.demo.shorturl.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者 zcw
 * 时间 2017/7/21 18:46
 * 描述 TODO
 */
@Service
public class UrlMappingService {

    @Autowired
    private UrlMappingRepo urlMappingRepo;

    public UrlMapping checkKey(String key) {
        if (key.length() > 8 || key.length() < 3) {
            return null;
        }
        long id = Base62.calDbIdByUrl(key);
        if (id == 0) {
            return null;
        }
        return urlMappingRepo.findOne(id);
    }
}
