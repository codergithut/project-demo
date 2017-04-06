package webSource.jpa.repository;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import webSource.jpa.entry.User;
import webSource.jdbc.sql.HqlData;

/**
 * Created by Administrator on 2016/11/30.
 */

@Transactional
public interface JpaRepositoryBean extends CrudRepository<User, Long>,HqlData {

    //User 是对应的类名 把数据库的表当成对象来写sql语句
    @Cacheable(value="user1")
    @Query(SQL_SERVER)
    User readUserByQueryAndCache(long id);

    @Query(SQL_SERVER)
    User readUserByQuery(long id);

    //系统已提供实现方式无需关心
    @Query(SQL_SERVER)
    User findOne(long id);

    @CachePut
    @Cacheable(value="user2")
    User save(User user);

    @Cacheable(value="user1")
    Iterable<User> findAll();

    @Query(SQL_USER)
    User findByUsername(String username);
}
