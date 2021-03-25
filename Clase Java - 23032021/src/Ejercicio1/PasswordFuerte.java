package Ejercicio1;

import java.util.regex.Pattern;

public class PasswordFuerte extends Password {
    public PasswordFuerte() {
    }

    @Override
    public void setPattern(String pattern) {
        super.setPattern(pattern);

    }

    @Override
    public void setPwd(String pwd) {
        super.setPwd(pwd);
    }

    @Override
    public void validate() throws Exception {
        super.validate();
    }

    @Override
    public Password password(Pattern pattern) {
        return super.password(pattern);
    }
}
