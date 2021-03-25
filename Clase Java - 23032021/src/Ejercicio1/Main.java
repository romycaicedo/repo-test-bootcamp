package Ejercicio1;

import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        Password pwd = new Password();
        PasswordSimple ps = new PasswordSimple();
        PasswordFuerte pf = new PasswordFuerte();
        PasswordIntermedia pi = new PasswordIntermedia();
        pf.setPattern("[a-zA-Z0-9]{12}");
        pf.setPwd("arun32ARUN32");
        pf.validate();
        ps.setPattern("[a-zA-Z0-9]{6}");
        ps.setPwd("arun32");
        ps.validate();
        pi.setPattern("[a-zA-Z0-9]{8}");
        pi.setPwd("arun3232");
    }
}
