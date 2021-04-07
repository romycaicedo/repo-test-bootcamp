import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {

        MiFactory factory = new MiFactory();


        Sorter s = (Sorter) MiFactory.getInstance("sorter");

        String[] sArr = {"lala", "lulu", "lili", "lele", "lolo"};
        Comparator<String> c2 = (a, b) -> a.compareTo(b);
        s.sort(sArr, c2);
        for (String word : sArr) {
            System.out.println(word);
        }


    }
}
