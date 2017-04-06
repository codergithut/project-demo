package library.server.impl;

import library.entity.Book;
import library.mybatis.BookMapper;
import library.server.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookDao;


    public List<Book> getAllBooks() {
        return bookDao.findAllBook();
    }


    public boolean addBook(Book book) {
        return bookDao.insertBook(book) > 0;
    }

    public boolean deleteBookById(int bookid) {
        return bookDao.deleteBookById(bookid)>0;
    }


    public Book findBookById(int bookid) {
        return bookDao.findBookById(bookid);
    }

    public boolean updateBookInfo(Book book) {
        return bookDao.updateBookInfo(book)>0;
    }

    public List<Book> findAllBookByRelationId(int readerid){
        return bookDao.findAllBookByRelationId(readerid);
    }
}
