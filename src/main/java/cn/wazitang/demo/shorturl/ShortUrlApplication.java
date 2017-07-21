package cn.wazitang.demo.shorturl;

import cn.wazitang.demo.shorturl.domain.UrlMapping;
import cn.wazitang.demo.shorturl.repo.UrlMappingRepo;
import cn.wazitang.demo.shorturl.service.UrlMappingService;
import cn.wazitang.demo.shorturl.utils.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class ShortUrlApplication implements CommandLineRunner {

    @Autowired
    private UrlMappingRepo urlMappingRepo;

    @Autowired
    private UrlMappingService urlMappingService;

    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }

    @RequestMapping("/{key}")
    public String match(@PathVariable(value = "key") String key) {
        StringBuilder sb = new StringBuilder("redirect:");
        UrlMapping urlMapping = urlMappingService.checkKey(key);
        if (urlMapping != null && urlMapping.getKey().equals(key)) {
            return sb.append(urlMapping.getSourceUrl()).toString();
        }
        return sb.append("/page/404.html").toString();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>init data");
        UrlMapping lastRecord = urlMappingRepo.findFirstByOrderByIdDesc();
        Long lastId = lastRecord == null ? 0L : lastRecord.getId();
        System.out.println(">>>>>>>>>>>>>dbIndex = " + lastId);
        Base62.setDbIndex(lastId);
    }
}
