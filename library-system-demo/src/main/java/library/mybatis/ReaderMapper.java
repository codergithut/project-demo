package library.mybatis;


import library.entity.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zlj on 2017/3/27.
 */
@Mapper
public interface ReaderMapper {

    Reader findReaderByReaderId(@Param("readerid") int readerid);

    int deleteReaderByReaderId(@Param("readerid") int readerid);

    int updateReader(Reader reader);

    int insertReader(Reader reader);

    List<Reader>  findAllReader();

}
