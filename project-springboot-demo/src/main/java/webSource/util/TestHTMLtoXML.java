package webSource.util;

import org.w3c.tidy.Tidy;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;

/**
 * 操作讲HTML文件转化成XML文件
 * */
public class TestHTMLtoXML {
    private String url;//HTML网页地址
    private String outFileName;//xml文件所在路径
    private String errOutFileName;//错误信息文件所在的路径

    public TestHTMLtoXML(String url, String outFileName, String errOutFileName){
        this.url=url;
        this.outFileName=outFileName;
        this.errOutFileName=errOutFileName;

    }
    public void convert(){
        URL u;
        BufferedInputStream in;
        FileOutputStream out;
        Tidy tidy=new Tidy();
        tidy.setXmlOut(true);//通知Tidy将HTML转化成XML
        try{
            System.out.println("begin");
            tidy.setErrout(new PrintWriter(new FileWriter(errOutFileName),true));//讲错误信息保存到文件errOutFileName中
            u=new URL(url);//根据网址创建URL对象
            in=new BufferedInputStream(u.openStream());//创建缓存输入流
            out=new FileOutputStream(outFileName);//创建文件输出流
            tidy.parse(in, out);//转换文件
            in.close();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String htmlFile="http://blog.csdn.net/javaxiaochouyu/article/details/6889140";
        String xml="C:/Users/Administrator/Desktop/p.xml";
        String errorFile="C:/Users/Administrator/Desktop/error.txt";
        TestHTMLtoXML htmltoXML=new TestHTMLtoXML(htmlFile, xml, errorFile);
        htmltoXML.convert();
        System.out.println("HTML文件装换成XML文件结束");

    }
}
