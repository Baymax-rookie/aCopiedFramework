package Framework.core;

import Framework.been.FrameContext;
import Framework.been.ResponseEntity;
import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

public class Handle {
    private static final Gson GSON=new Gson();

    private Method method;

    private Object controller;

    Handle(Object controller,Method method){
        this.controller=controller;
        this.method=method;
    }
    String invoke(FrameContext context){
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();

        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String value = request.getParameter(key);
            context.putParam(key, value);
        }

        ResponseEntity res = null;
        try {
            res = (ResponseEntity) method.invoke(controller, context);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (res != null) {
            return GSON.toJson(res);
        }
        return null;
    }
}
