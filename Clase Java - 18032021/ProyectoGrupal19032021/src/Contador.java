public class Contador {

    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Contador() {
    }

    public Contador(Contador counter) {
        this.counter = counter.getCounter();

    }

    public int incrementar (int valor){
        this.counter = counter+ valor;
        return counter;
    }
    public int decrementar (int valor){
        this.counter = counter- valor;
        return counter;

    }

}
