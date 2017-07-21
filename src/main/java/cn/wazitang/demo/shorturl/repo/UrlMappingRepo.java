package cn.wazitang.demo.shorturl.repo;

import cn.wazitang.demo.shorturl.domain.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 作者 zcw
 * 时间 2017/7/21 12:42
 * 描述 TODO
 */
@Repository
public interface UrlMappingRepo extends JpaRepository<UrlMapping, Long> {

    UrlMapping findFirstByOrderByIdDesc();
}
