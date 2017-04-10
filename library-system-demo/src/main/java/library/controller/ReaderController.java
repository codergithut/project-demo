package library.controller;

import library.domain.Reader;
import library.server.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/3.
 */
@Controller
@RequestMapping(value="/rest")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    //页面search功能跳出来的页面--------------------------------------------------------------------------------
    @RequestMapping(value = "/search/{readerid}", method = RequestMethod.POST)
    public ModelAndView getReaderInfo(@PathVariable int readerid) throws IOException, SQLException {

        //todo: 根据信息查询信息并返回到前端页面

        Map<String,Object> showDatas = new HashMap<String,Object>();
        Reader reader=readerService.findReaderById(readerid);
        showDatas.put("readers", reader);
        return new ModelAndView("reader/showallreader", showDatas);
    }

    //页面点击读者信息管理跳出来的页面
    @RequestMapping(value = "/getreaderall", method = RequestMethod.GET)
    public ModelAndView getReader() throws IOException, SQLException {

        //todo: 根据信息查询信息并返回到前端页面

        Map<String,Object> showReaderData = new HashMap<String,Object>();

        List<Reader> datas2 = readerService.getAllReaders();

        //showDatas.put("datas", datas1);
        showReaderData.put("readers", datas2);
        return new ModelAndView("reader/showallreaderinfo", showReaderData);
    }

    //删除reader用户信息
    @RequestMapping(value = "/deleteReader/{readerid}", method = RequestMethod.GET)
    public String deleteReader(@PathVariable int readerid) throws IOException, SQLException {
        readerService.deleteReaderById(readerid);
        return "redirect:/rest/getreaderall";

    }
    //增加reader用户信息
    @RequestMapping(value = "/addreader", method = RequestMethod.GET)
    public String addReader() throws IOException, SQLException {
        return "reader/addreader";

    }

    @RequestMapping(value = "/addreader", method = RequestMethod.POST)
    public String addReader(Reader reader) throws IOException, SQLException {
        readerService.addReader(reader);
        return "redirect:/rest/getreaderall";
    }

    //选择readerid 更新book信息，跟下面的方式方法一样，但是GET,POST不一样就可以
    @RequestMapping(value = "/updateReader/{readerid}", method = RequestMethod.GET)
    public ModelAndView findByreaderId(@PathVariable int readerid) throws IOException, SQLException {
        Reader reader= readerService.findReaderById(readerid);
        Map<String,Object> showBookData = new HashMap<String,Object>();
        showBookData.put("reader", reader);
        return new ModelAndView("reader/updatereader", showBookData);

    }
    //根据上面readerid 更新reader用户信息
    @RequestMapping(value = "/updateReader", method = RequestMethod.POST)
    public String updateReader(Reader reader) throws IOException, SQLException {
        readerService.updateReadeInfo(reader);
        return "redirect:/rest/getreaderall";
    }

}
