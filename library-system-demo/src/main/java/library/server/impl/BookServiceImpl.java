package library.server.impl;

import library.dao.BookDao;
import library.entity.Book;
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
    BookDao bookDao;

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAllBook();
    }

    @Override
    public boolean addBook(Book book) {
        return bookDao.insertBook(book) > 0;
    }

    @Override
    public boolean deleteBookById(int bookid) {
        return bookDao.deleteBookById(bookid)>0;
    }

    @Override
    public Book findBookById(int bookid) {
        return bookDao.findBookById(bookid);
    }

    @Override
    public boolean updateBookInfo(Book book) {
        return bookDao.updateBookInfo(book)>0;
    }
}
