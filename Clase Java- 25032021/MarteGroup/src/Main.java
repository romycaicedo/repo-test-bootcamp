import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args ){
        List<Invitados> listaInvitado = new ArrayList<>();
        List<FuegoArtificial> listaFuegos = new ArrayList<>();
        listaInvitado.add(new InvitadoMeli());
        listaInvitado.add(new InvitadoMeli());
        listaInvitado.add(new InvitadoMeli());
        listaInvitado.add(new InvitadoMeli());
        listaInvitado.add(new InvitadoMeli());
        listaInvitado.add(new InvitadoStandard());
        listaInvitado.add(new InvitadoStandard());
        listaInvitado.add(new InvitadoStandard());
        listaInvitado.add(new InvitadoStandard());
        listaInvitado.add(new InvitadoStandard());
        listaFuegos.add(new FuegoArtificialIndividual(false,"Boom"));
        listaFuegos.add(new FuegoArtificialIndividual(false,"Boom"));
        listaFuegos.add(new FuegoArtificialIndividual(false,"Boom"));
        listaFuegos.add(new FuegoArtificialIndividual(false,"Boom"));
        listaFuegos.add(new FuegoArtificialIndividual(false,"Boom"));
        List<FuegoArtificial> listaPacks = new ArrayList<>();
        listaPacks.add(new FuegoArtificialIndividual(false,"kaboom"+Math.random()));
        listaPacks.add(new FuegoArtificialIndividual(false,"kaboom"+Math.random()));
        listaPacks.add(new FuegoArtificialIndividual(false,"kaboom"+Math.random()));
        listaPacks.add(new FuegoArtificialIndividual(false,"kaboom"+Math.random()));
        listaPacks.add(new FuegoArtificialIndividual(false,"kaboom"+Math.random()));
        listaFuegos.add(new PackFuegosArtificiales(listaPacks));
        listaFuegos.add(new FuegoArtificialIndividual(true,"Pumm"));
        Evento evento = new Evento(listaInvitado,listaFuegos);
        evento.apagarVelas();

    }
    }
