package library.controller;

import library.entity.Book;
import library.entity.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

    //选取所有book信息
    @RequestMapping(value = "/readservice/{readerid}", method = RequestMethod.GET)
    public ModelAndView getAllBookGET(@PathVariable int readerid) throws IOException, SQLException {
        Map<String,Object> showBookData = new HashMap<String,Object>();

        //TODO 查询用户信息 如果用户不存在就跳过
        Reader reader = new Reader();
        reader.setPassword("123");
        reader.setReaderid(123);
        reader.setUsername("345");
        showBookData.put("reader",reader);

        //TODO 查询该用户所有借阅的书籍
        List<Book> lendBooks = new ArrayList<Book>();
        Book book = new Book();
        book.setBookid(22);
        book.setBookname("haha");
        book.setClassification("stes");
        book.setNumber(33);
        book.setStatement("true");
        lendBooks.add(book);
        showBookData.put("lendBooks",lendBooks);
        if(lendBooks.size()>5){
            showBookData.put("result","false");
        }else{
            showBookData.put("result","true");
        }
        return new ModelAndView("lend/booklend", showBookData);
    }

    //选取所有book信息
    @RequestMapping(value = "/lendBook", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> lendBook(int readerid, int bookid, int adminid) throws IOException, SQLException {
        Map<String,String> result = new HashMap<String,String>();
        //跟新数据信息并返回msg
        result.put("msg","ok");
        return result;
    }


}
