package Framework.core;

import Framework.annotation.HttpMethod;
import Framework.annotation.RequestMapper;
import Framework.been.RouteInfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RouteEngine {
    private static RouteEngine singleton;

    private BeenFactory beenFactory=null;

    private Map<RouteInfo,Handle>routeMap;

    public static RouteEngine getInstance(){
        if (singleton==null){
            synchronized (RouteEngine.class){
                if (singleton==null){
                    singleton=new RouteEngine();
                }
            }
        }
        return singleton;
    }

    private RouteEngine(){
        init();
    }

    private void init(){
        beenFactory=BeenFactory.getInstance();
        routeMap=new HashMap<>(30);
        loadRoute();
    }

    private void loadRoute() {
        Map<Class<?>, Object> controlleMap = beenFactory.getControllerMap();
        String prefix = "";
        for (Map.Entry<Class<?>, Object> entry : controlleMap.entrySet()) {
            Class<?> clazz = entry.getKey();
            Object controller = entry.getValue();
            RequestMapper controllerMapper = clazz.getAnnotation(RequestMapper.class);
            if (controllerMapper != null) {
                controllerMapper.value();
                prefix = controllerMapper.value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                RequestMapper mapper = method.getAnnotation(RequestMapper.class);
                if (mapper != null) {
                    String uri = mapper.value();
                    HttpMethod httpMethod = mapper.method();
                    RouteInfo route = new RouteInfo(httpMethod, prefix + uri);
                    Handle handle = new Handle(controller, method);
                    routeMap.put(route, handle);
                }
            }

        }
    }

    public Handle getHandle(RouteInfo route) {
        return routeMap.get(route);
    }
}
