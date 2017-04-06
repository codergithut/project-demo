package library.server;

import library.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public interface BookService {

    //获取所有图书信息
    public List<library.entity.Book> getAllBooks();

    //添加图书信息
    boolean addBook(Book book);

    //删除图书信息
    boolean deleteBookById(int bookid);

    //根据图书id获取图书信息
    Book findBookById(int bookid);

    //更新图书信息
    boolean updateBookInfo(Book book);

    List<Book> findAllBookByRelationId(int readerid);
}
