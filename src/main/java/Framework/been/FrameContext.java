package Framework.been;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class FrameContext {
    private HttpRequest request;
    private HttpResponse response;

    private Map<String, String> paramMap;

    private RouteInfo routeInfo;



    public HttpRequest getRequest(){
        return request;
    }
    public HttpResponse getResponse(){
        return response;
    }
    public RouteInfo getRouteInfo(){
        return routeInfo;
    }
    public FrameContext(HttpServletRequest request, HttpServletResponse response, RouteInfo routeInfo){
        this.response=response;
        this.request=request;
        this.routeInfo=routeInfo;
        this.paramMap=new HashMap<>();
    }
    public void putParam(String key,String value){
            this.paramMap.put(key,value);
    }
    public String getKey(String key){
        return this.paramMap.get(key);
    }
}
