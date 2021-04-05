import java.util.ArrayList;
import java.util.List;

public class Evento {
    private List<Invitados> listaInvitados = new ArrayList<>();
    private List<FuegoArtificial> listaFuego = new ArrayList<>();

    public Evento(List<Invitados> listaInvitados, List<FuegoArtificial> listaFuego) {
        this.listaInvitados = listaInvitados;
        this.listaFuego = listaFuego;
    }

    public void apagarVelas() {
        for(FuegoArtificial e: listaFuego)
        {
            e.Explotar();
        }
        for(Invitados i: listaInvitados)
        {
            i.comerTorta();
        }
    }
}
