package webSource.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by Administrator on 2016/12/6.
 */
public class GetUrlResource {

    public String getWealthByUrlResource() throws IOException {
        Document doc = Jsoup.connect("http://nanjing.tianqi.com/").get();
        Element e=doc.getElementById("othercity");
        String value=e.getElementById("ff").val();
        if(value!=null)
            return value;
        return "there is some error in callBack!!";

    }

}
