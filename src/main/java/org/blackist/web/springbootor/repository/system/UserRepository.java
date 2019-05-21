package org.blackist.web.springbootor.repository.system;

import org.blackist.web.springbootor.model.entity.system.User;
import org.blackist.web.springbootor.repository.BaseRepository;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends BaseRepository<User, Long> {

    @Cacheable(key = "#p0")
    User getByUsername(String username);

    @CacheEvict(key = "#p0")
    @Transactional
    @Modifying
    @Query("update User set name=:name where username=:username")
    Integer updateUserSetNameForUsername(@Param("username") String username, @Param("name") String name);
}
