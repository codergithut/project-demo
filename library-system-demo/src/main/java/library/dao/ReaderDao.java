package library.dao;

import library.entity.Reader;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public interface ReaderDao {

    Reader findReaderByReaderId(int readerid);

    int deleteReaderByReaderId(int readerid);

    int updateReader(Reader reader);

    int insertReader(Reader reader);

    List<Reader> findAllReader();
}
