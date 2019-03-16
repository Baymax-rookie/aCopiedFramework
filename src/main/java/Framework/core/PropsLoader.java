package Framework.core;

import Framework.utils.CastUtil;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsLoader {
        private static PropsLoader singleton;

        private Properties properties;

        private static final String PROPS_PATH="/WEB-INF/application.properties";

        public static PropsLoader getInstance() {
            if (singleton != null) {
                return singleton;
            }
            throw new RuntimeException("PropsLoader null");
        }
        public static void init(ServletContext context){
            if (singleton==null){
                synchronized (PropsLoader.class){
                    if (singleton==null){
                        singleton=new PropsLoader(context);
                    }
                }
            }
        }
        private PropsLoader() throws IllegalAccessException {
            throw new IllegalAccessException();
        }

        private PropsLoader(ServletContext context){
            InputStream in=context.getResourceAsStream(PROPS_PATH);
            if (in!=null){
                properties=new Properties();
                try {
                    properties.load(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                throw new RuntimeException("application.properties Not found");
            }
        }
        public String getStringkey(String key){
            String res;
            if (key!=null){
                res=properties.getProperty(key);
                if (res!=null){
                    return res;
                }
                return null;
            }
            throw new RuntimeException("properties"+key+" Not found");
        }
        public int getInt(String  key){
            return CastUtil.castInt(getStringkey(key),0);
        }
        public long getLong(String key){
            return CastUtil.castLong(getStringkey(key),0L);
        }
}
