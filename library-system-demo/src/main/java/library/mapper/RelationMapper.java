package library.mapper;

import library.domain.Book;
import library.domain.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zlj on 2017/3/27.
 */
@Mapper
public interface RelationMapper {

   String updaterelation(Relation relation);

   List<Relation> selectRelationByreaderid(@Param("readerid") int readerid);

   int insertRelation(Relation relation);

   List<Book>  findAllBookByRelationId(@Param("readerid") int readerid);
}
