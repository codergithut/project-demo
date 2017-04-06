package library.dao.impl;

import library.dao.ReaderDao;
import library.entity.Reader;
import library.mybatis.Readermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public class ReaderDaoImpl implements ReaderDao {

    @Autowired
    Readermapper readerMapper;

    @Override
    public Reader findReaderByReaderId(int readerid) {
        return readerMapper.findReaderByReaderId(readerid);
    }

    @Override
    public int deleteReaderByReaderId(int readerid) {
      return  readerMapper.deleteReaderByReaderId(readerid);
    }

    @Override
    public int updateReader(Reader reader) {
        return readerMapper.updateReader(reader);

    }

    @Override
    public int insertReader(Reader reader) {
       return readerMapper.insertReader(reader);

    }

    @Override
    public List<Reader> findAllReader() {
        return readerMapper.findAllReader();
    }
}
