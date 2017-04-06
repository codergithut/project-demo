package library.controller;

import library.entity.Book;
import library.entity.Reader;
import library.mybatis.RelationMapper;
import library.server.BookService;
import library.server.LibraryService;
import library.server.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/5
 * @description
 */
@Controller
@RequestMapping(value="/service")

public class LendController {

    @Autowired
    ReaderService readerService;

    @Autowired
    BookService bookService;

    @Autowired
    LibraryService libraryService;

    //选取所有book信息
    @RequestMapping(value = "/readservice/{readerid}", method = RequestMethod.GET)
    public ModelAndView getAllBookGET(@PathVariable int readerid) throws IOException, SQLException {
        Map<String, Object> showBookData = new HashMap<String, Object>();
        Reader reader=readerService.findReaderById(readerid);
        showBookData.put("reader",reader);
        //多表查询，根据relation中readerid的值取bookid，在根据bookid在book中取得book的所有信息
        List<Book> lendBooks= bookService.findAllBookByRelationId(readerid);
        showBookData.put("lendBooks",lendBooks);
       // List<Book> lendBooks = new ArrayList<Book>();
        if(lendBooks.size()>5){
            showBookData.put("result","false");
        }else{
            showBookData.put("result","true");
        }
        return new ModelAndView("lend/booklend", showBookData);

    }

    //选取所有book信息
    @RequestMapping(value = "/lendBook", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> lendBook(int readerid, int bookid, int adminid) throws IOException, SQLException {
        Map<String,String> result = new HashMap<String,String>();
        boolean flag = libraryService.lendBook(readerid, bookid, adminid);
        if(flag){
            result.put("msg", "ok");
        }else{
            result.put("msg", "error");
        }
        return result;
    }

}
