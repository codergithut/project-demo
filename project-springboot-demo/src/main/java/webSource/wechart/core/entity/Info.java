package webSource.wechart.core.entity;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/23
 * @description
 */
public class Info {

    private String article;
    private String source;
    private String icon;
    private String detailurl;
    private String info;
    private String name;

    public String getArticle() {
        return article;
    }

    public String getSource() {
        return source;
    }

    public String getIcon() {
        return icon;
    }

    public String getDetailurl() {
        return detailurl;
    }

    public String getInfo() {
        return info;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
