package library.server.impl;

import library.entity.Relation;
import library.mybatis.BookMapper;
import library.mybatis.RelationMapper;
import library.server.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/6
 * @description
 */
@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    BookMapper bookDao;

    @Autowired
    RelationMapper lendBookDao;


    public boolean lendBook(int readerid, int bookid, int adminid) {

        Relation relation= new Relation(readerid, bookid , adminid,"5","0","0");

        return (lendBookDao.insertRelation(relation)*bookDao.increaseLendBookNumber(bookid))>0;
    }
}
