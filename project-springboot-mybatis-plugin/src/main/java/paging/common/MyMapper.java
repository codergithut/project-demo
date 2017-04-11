package paging.common;

import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 * 自己定义的通用mapper 貌似不能被扫描到就是不能通过@Service来修饰
 * @author liuzh_3nofxnp
 * @since 2015-09-06 21:53
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
