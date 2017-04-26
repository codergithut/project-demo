package paging.service;

import org.springframework.stereotype.Service;
import paging.domain.Haha;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/11
 * @description 我们对应单表操作的具体实现有点dao的意思
 */
@Service
public class HahaService extends BaseService<Haha> {

    public int save(Haha haha) {
        if (haha == null) {
            throw new NullPointerException("保存的对象不能为空!");
        }
        return super.save(haha);
    }
}
