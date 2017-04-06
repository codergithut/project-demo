package server.model;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/4/1
 * @description 封装mybatis插件单表操作插入数据的结果
 */
public class SimpleTableResult {
    private boolean result;
    private String resultInfo;

    public String getResultInfo() {
        return resultInfo;
    }

    public boolean isResult() {
        return result;
    }


    public void setResult(boolean result) {
        this.result = result;
    }


    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }
}
