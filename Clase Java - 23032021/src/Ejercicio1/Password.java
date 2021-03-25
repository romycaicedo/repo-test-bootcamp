package Ejercicio1;
import javax.xml.transform.stream.StreamSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {

    public String pwd;
    Pattern pattern ;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        Pattern str = Pattern.compile(pattern);
        this.pattern = str;
    }

    public Password() {
    }

    public Password password(Pattern pattern ) {
        this.pattern = pattern;
        return password(this.pattern);
    }

    public void setValue(String pwd){
        this.pwd = pwd;
    }

    public  void validate() throws Exception{
        boolean flag = false;

            try {
                Matcher match = pattern.matcher(pwd);
                flag = match.matches();
                if(flag== true)
                System.out.println("Password Valido");
                else
                    throw new Exception();
            } catch (Exception e){
                System.out.println("Password shoult contain only numbers and letters");
            }


    }
}
