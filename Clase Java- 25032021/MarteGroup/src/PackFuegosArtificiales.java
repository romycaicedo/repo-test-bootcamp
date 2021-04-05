import java.util.ArrayList;
import java.util.List;

public class PackFuegosArtificiales extends FuegoArtificial{
    private List<FuegoArtificial> packs = new ArrayList<>();


    public PackFuegosArtificiales(List<FuegoArtificial> packs) {
        this.packs = packs;
    }

    @Override
    public void Explotar() {
        for (int i =0; i< packs.size();i++){
            packs.get(i).Explotar();
        }
    }
}
