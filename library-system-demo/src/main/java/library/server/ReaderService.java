package library.server;

import library.entity.Book;
import library.entity.Reader;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zlj on 2017/4/4.
 */
@Service
public interface ReaderService {
    //获取所有图书信息
    public List<Reader> getAllReaders();

    //添加图书信息
    boolean addReader(Reader reader);

    //删除图书信息
    boolean deleteReaderById(int readerid);

    //根据图书id获取图书信息
    Reader findReaderById(int readerid);

    //更新图书信息
    boolean updateReadeInfo(Reader reader);

}
