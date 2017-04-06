package library.mybatis;

import library.entity.Relation;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zlj on 2017/3/27.
 */
@Mapper
public interface LendBookMapper {
   String updaterelation(Relation relation);
}
