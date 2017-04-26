package paging.mapper;

import paging.domain.Haha;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/11
 * @description 此处必须强调我们通用mapper都是需要对应的实例化的这边就是给Mapper<Haha>进行通用实例化处理，
 * 如果没有这个接口我们HahaService 自动注入Mapper<Haha>会出现通用mapper对象不能实例化的错误，
 * 本人感觉有点模板方法的意思是不是可以考虑集成到工具中呢
 */
public interface HahaCommonMapper extends Mapper<Haha> {
}
