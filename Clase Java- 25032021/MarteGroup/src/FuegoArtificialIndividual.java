public class FuegoArtificialIndividual extends FuegoArtificial{
    public boolean exploted;
    public String ruido;

    public FuegoArtificialIndividual(boolean exploted, String ruido) {
        this.exploted = exploted;
        this.ruido = ruido;
    }


    @Override
    public void Explotar() {
        if(!exploted) {
            exploted = true;
            System.out.println(ruido);
        }
        else
            System.out.println("Fuego Artificial no disponible");
    }
}
