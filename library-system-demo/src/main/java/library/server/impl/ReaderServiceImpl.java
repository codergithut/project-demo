package library.server.impl;

import library.entity.Book;
import library.entity.Reader;
import library.mybatis.ReaderMapper;
import library.server.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zlj on 2017/4/4.
 */
@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    ReaderMapper readerDao;
    //获取所有图书信息
    public List<Reader> getAllReaders(){return readerDao.findAllReader();}




    //添加图书信息
    public boolean addReader(Reader reader){return readerDao.insertReader(reader)>0;}

    //删除图书信息
    public  boolean deleteReaderById(int readerid){return readerDao.deleteReaderByReaderId(readerid)>0;};

    //根据图书id获取图书信息
    public  Reader findReaderById(int readerid){return readerDao.findReaderByReaderId(readerid);};

    //更新图书信息
    public boolean updateReadeInfo(Reader reader){return readerDao.updateReader(reader)>0;};
}
