import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class MiFactory {

    public static Object getInstance(String objName) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Properties properties= new Properties();
        try {
            properties.load(new FileInputStream(new File("MiFactory.properties")));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
    }
        return Class.forName((String) properties.get("sorter")).getConstructor(String.class).newInstance(objName);

    }

}
