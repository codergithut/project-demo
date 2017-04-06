package webSource.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Administrator on 2016/12/21.
 */
@WebService
public interface HelloService {
    @WebMethod
    String getName(@WebParam(name = "userId") Long userId);
    @WebMethod
    Hello getUser(Long userId);
}