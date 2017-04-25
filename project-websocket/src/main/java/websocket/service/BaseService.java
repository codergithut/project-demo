package websocket.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by tianjian on 2017/4/8.
 * 此处需要将类修饰为抽象对象 防止有人手贱加了@Servcie 注解让这个类可以进行单列处理
 */
public abstract class BaseService<T> {
    @Autowired
    protected Mapper<T> mapper;

    public int save(T entity){
        return mapper.insert(entity);
    }

    public int update(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    public int delete(T entity){
        return mapper.deleteByPrimaryKey(entity);
    }

    public List<T> getEntityByParam(Example example) {
        return mapper.selectByExample(example);
    }

    /**
     * 单表分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<T> selectPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //Spring4支持泛型注入
        return mapper.select(null);
    }

    public T slectById(Object o) {
        return mapper.selectByPrimaryKey(o);
    }
}
