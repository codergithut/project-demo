package library.dao;

import library.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public interface BookDao {

    //根据书籍的id获取图书
    Book findBookById(@Param("bookid") int bookid);

    //获取所有图书信息
    List<Book> findAllBook();

    //插入图书信息，假如插入成功返回1，插入失败就返回-1，这是系统默认的规则
    int insertBook(Book book);

    //删除图书信息
    int deleteBookById(@Param("bookid") int bookid);

    //更新图书信息
    int updateBookInfo(Book book);


}
