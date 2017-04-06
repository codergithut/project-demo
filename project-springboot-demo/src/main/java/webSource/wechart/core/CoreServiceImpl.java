package webSource.wechart.core;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import webSource.wechart.core.entity.Info;
import webSource.wechart.core.entity.MessageTuLing;
import webSource.wechart.core.service.CoreService;
import webSource.wechart.message.resp.Article;
import webSource.wechart.message.resp.NewsMessage;
import webSource.wechart.message.resp.TextMessage;
import webSource.wechart.util.HttpClientUtils;
import webSource.wechart.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21.
 */
@Service("coreService")
public class CoreServiceImpl  implements CoreService {
    @Override
    public String processRequest(HttpServletRequest request) throws IOException {
        String respMessage = null;
        // 发送方帐号（open_id）
        String fromUserName=null;
        // 公众帐号
        String toUserName=null;
        //时间
        String createTime=null;


        try {
            // 回复文本消息
            TextMessage textMessage = null;
            NewsMessage newsMessage = null;

            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);



            fromUserName = requestMap.get("FromUserName");

            toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            //消息内容
            String content = requestMap.get("Content");

            //消息创建时间
            createTime=requestMap.get("CreateTime");

            System.out.println(fromUserName);

            System.out.println(toUserName);

            textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);

            if(content.equals("1")||content.equals("2")||content.equals("3")||content.equals("4")||content.equals("5")){
                if(MessageUtil.isQqFace(content)){
                    respContent=content;
                } else if(content.equals("1")){
                    respContent="sdfsfsldfalfk <a href=\"http://wiki.gtmap.cn/pages/viewpage.action?pageId=1343671\">公司安排学习的</a>  ";
//                    respContent=" <a href=\"http://uri.amap.com/marker?position=118.721474,32.034237\">公司安排学习的</a>  ";
                }else if (content.equals("2")){
                    respContent = getMainMenu();
                }else if(content.equals("3")){
                    respContent=MessageUtil.emoji(0x1F6B2);
                }else if(content.equals("4")){
                    respContent ="自行车\\ue136 男人\\ue138 钱袋\\ue12f 情侣\\ue428 公共汽车\\ue159";
                }else{
                    respContent="这是一条没有特征的消息！";
                }
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            List<Article> articleList = new ArrayList<Article>();
            // 单图文消息
            if ("6".equals(content)) {
                Article article = new Article();
                article.setTitle("微信公众帐号开发教程Java版");
                article.setDescription("柳峰，80后，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
                article.setPicUrl("http://tianjian3209.vicp.io/images/pic.png");
                article.setUrl("http://blog.csdn.net/lyq8479");
                articleList.add(article);
                // 设置图文消息个数
                newsMessage.setArticleCount(articleList.size());
                // 设置图文消息包含的图文集合
                newsMessage.setArticles(articleList);
                // 将图文消息对象转换成xml字符串
                respMessage = MessageUtil.newsMessageToXml(newsMessage);
            }
            // 单图文消息---不含图片
            else if ("7".equals(content)) {
                Article article = new Article();
                article.setTitle("微信公众帐号开发教程Java版");
                // 图文消息中可以使用QQ表情、符号表情
                article.setDescription("柳峰，80后，" + MessageUtil.emoji(0x1F6B9)
                            + "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");
                // 将图片置为空
                article.setPicUrl("");
                article.setUrl("http://blog.csdn.net/lyq8479");
                articleList.add(article);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respMessage = MessageUtil.newsMessageToXml(newsMessage);
            }
            // 多图文消息
            else if ("8".equals(content)) {
                Article article1 = new Article();
                article1.setTitle("微信公众帐号开发教程\n引言");
                article1.setDescription("");
                article1.setPicUrl("http://tianjian3209.vicp.io/images/pic.png");
                article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");

                Article article2 = new Article();
                article2.setTitle("第2篇\n微信公众帐号的类型");
                article2.setDescription("");
                article2.setPicUrl("http://tianjian3209.vicp.io/images/pic.png");
                article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");

                Article article3 = new Article();
                article3.setTitle("第3篇\n开发模式启用及接口配置");
                article3.setDescription("");
                article3.setPicUrl("http://tianjian3209.vicp.io/images/pic.png");
                article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");

                articleList.add(article1);
                articleList.add(article2);
                articleList.add(article3);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respMessage = MessageUtil.newsMessageToXml(newsMessage);
            }
            // 多图文消息---首条消息不含图片
            else if ("9".equals(content)) {
                Article article1 = new Article();
                article1.setTitle("微信公众帐号开发教程Java版");
                article1.setDescription("");
                // 将图片置为空
                article1.setPicUrl("");
                article1.setUrl("http://blog.csdn.net/lyq8479");

                Article article2 = new Article();
                article2.setTitle("第4篇\n消息及消息处理工具的封装");
                article2.setDescription("");
                article2.setPicUrl("http://tianjian3209.vicp.io/images/pic.png");
                article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");

                Article article3 = new Article();
                article3.setTitle("第5篇\n各种基础语言的资源");
                article3.setDescription("");
                article3.setPicUrl("http://tianjian3209.vicp.io/images/pic.png");
                article3.setUrl("http://www.runoob.com/bootstrap/bootstrap-css-overview.html");

                Article article4 = new Article();
                article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
                article4.setDescription("");
                article4.setPicUrl("http://tianjian3209.vicp.io/images/pic.png");
                article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");

                articleList.add(article1);
                articleList.add(article2);
                articleList.add(article3);
                articleList.add(article4);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respMessage = MessageUtil.newsMessageToXml(newsMessage);
            }
            // 多图文消息---最后一条消息不含图片
            else if ("10".equals(content)) {
                Article article1 = new Article();
                article1.setTitle("第7篇\n文本消息中换行符的使用");
                article1.setDescription("");
                article1.setPicUrl("http://tianjian3209.vicp.io/images/pic.png");
                article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");

                Article article2 = new Article();
                article2.setTitle("第8篇\n文本消息中使用网页超链接");
                article2.setDescription("");
                article2.setPicUrl("http://http://tianjian3209.vicp.io/images/pic.png");
                article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");

                Article article3 = new Article();
                article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
                article3.setDescription("");
                // 将图片置为空
                article3.setPicUrl("");
                article3.setUrl("http://blog.csdn.net/lyq8479");

                articleList.add(article1);
                articleList.add(article2);
                articleList.add(article3);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respMessage = MessageUtil.newsMessageToXml(newsMessage);
            }
            else if(content.contains("翻译")){
                respContent= HttpClientUtils.get("http://fanyi.youdao.com/openapi.do?keyfrom=tianjian3209&key=887017398&type=data&doctype=json&version=1.1&q="+content.replace("翻译",""),"utf-8");
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
                //respContent = "您发送的是文本消息！";
            }else if(content.contains("天气")){
                respContent=HttpClientUtils.get("http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&day=0&dfc=1&charset=utf-8&city="+content.replace("天气",""),"utf-8");
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }else if(!content.contains("翻译")&&!content.contains("天气")&&!content.contains("1")){
                respContent=HttpClientUtils.get("http://localhost:8081/talk/"+content,"utf-8");
                MessageTuLing message= JSON.parseObject(respContent,MessageTuLing.class);
                if(message.getUrl()!=null){
                    respContent=" <a href=\"url\">info</a>".replace("url",message.getUrl()).replace("info",message.getText());
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
                if(message.getUrl()==null&&message.getList()==null){
                    textMessage.setContent(message.getText());
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }


                if(message.getList()!=null){
                    int i=0;
                    for(Info info:message.getList()){
                        if(i<6&&!message.getCode().equals("308000")){
                            Article article = new Article();
                            article.setTitle(info.getArticle());
                            article.setDescription(info.getInfo()==null?"":info.getInfo());
                            article.setPicUrl(info.getIcon()==null?"":info.getIcon());
                            article.setUrl(info.getDetailurl());
                            articleList.add(article);
                            i++;
                        }else if(message.getCode().equals("308000")){
                            Article article = new Article();
                            article.setTitle(info.getName());
                            article.setDescription(info.getInfo()==null?"":info.getInfo());
                            article.setPicUrl(info.getIcon()==null?"":info.getIcon());
                            article.setUrl(info.getDetailurl());
                            articleList.add(article);
                            break;

                        }else{
                            break;
                        }
                    }
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }


            }
            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
                respContent = "您发送的是图片消息！";
            }

//             //文本消息
//            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
//                respContent = getMainMenu().toString();
//            }
//            // 图片消息
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
//                respContent = "您发送的是图片消息！";
//            }
//            // 地理位置消息
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
//                respContent = "您发送的是地理位置消息！";
//            }
//            // 链接消息
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
//                respContent = "您发送的是链接消息！";
//            }
//            // 音频消息
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
//                respContent = "您发送的是音频消息！";
//            }
//            // 事件推送
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
//                // 事件类型
//                String eventType = requestMap.get("Event");
//                // 订阅
//                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
//                    respContent = "谢谢您的关注！";
//                }
//                // 取消订阅
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
//                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
//                }
//                // 自定义菜单点击事件
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
//                    // TODO 自定义菜单权没有开放，暂不处理该类消息
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        InputStream is = new ByteArrayInputStream(respMessage.getBytes());
        OutputStream fos = new FileOutputStream("D:\\wechart\\"+fromUserName+createTime+".txt");
        //这里对is进行赋值，略
        //...

        // 文件输出流fos
        // openFile()为自定义函数，判断文件是否存在等（略）
        // 将输入流is写入文件输出流fos中
        int ch = 0;
        try {
            while((ch=is.read()) != -1){
                fos.write(ch);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally{
            //关闭输入流等（略）
            fos.close();
            is.close();
        }


        return respMessage;
    }

    public static String getMainMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");
        buffer.append("1  超链接").append("\n");
        buffer.append("2  菜单").append("\n");
        buffer.append("3  表情").append("\n");
        buffer.append("4  符号表情").append("\n");
        buffer.append("5  无聊的消息").append("\n");
        buffer.append("6  图文显示").append("\n");
        buffer.append("7  图文显示").append("\n");
        buffer.append("8  图文显示").append("\n");
        buffer.append("9  图文显示").append("\n");
        buffer.append("10  图文显示").append("\n");
        buffer.append("回复“?”显示此帮助菜单");
        return buffer.toString();
    }


}
