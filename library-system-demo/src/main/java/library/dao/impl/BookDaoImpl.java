package library.dao.impl;


import library.dao.BookDao;
import library.entity.Book;
import library.mybatis.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public class BookDaoImpl implements BookDao {

    @Autowired
    BookMapper bookMapper;

    @Override
    public Book findBookById(int bookid) {
        return bookMapper.findBookById(bookid);
    }

    @Override
    public List<Book> findAllBook() {
        return bookMapper.findAllBook();
    }

    @Override
    public int insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    @Override
    public int deleteBookById(int bookid) {
        return bookMapper.deleteBookById(bookid);
    }

    @Override
    public int updateBookInfo(Book book) {
        return bookMapper.updateBookInfo(book);
    }
}
