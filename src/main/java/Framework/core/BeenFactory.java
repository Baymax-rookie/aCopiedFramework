package Framework.core;

import Framework.annotation.Component;
import sun.reflect.misc.ReflectUtil;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BeenFactory {
    private static BeenFactory beenFactory;

    private ClassLoader classLoader;

    private Set<Class<?>> componentSet;

    private Map<Class<?>,Object> controllerMap;
    private Map<Class<?>,Object> componentMap;

    public static BeenFactory getInstance(){
        if (beenFactory==null){
            synchronized (BeenFactory.class){
                if (beenFactory==null){
                    beenFactory=new BeenFactory();
                }
            }
        }
        return beenFactory;
    }
    Map<Class<?>,Object> getControllerMap(){
        return controllerMap;
    }
    private BeenFactory(){
        componentMap=new HashMap<>();
        componentSet=new HashSet<>();
        controllerMap=new HashMap<>();
    }

}
