package Entities;

public class Greeting {

    long id;
    String message;
    String name;


    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Greeting(long id, String message, String name) {
        this.id = id;
        this.message = message;
        this.name = name;
    }
}
