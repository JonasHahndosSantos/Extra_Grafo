import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    Map<Cidade, List<Estrada>> mapa = new HashMap<>();

    public void adicionarCidade(Cidade cidade) {
        mapa.putIfAbsent(cidade, new ArrayList<>());
    }

    public void adicionarEstrada(Cidade origem, Cidade destino, double custo, double distancia) {
        mapa.putIfAbsent(origem, new ArrayList<>());
        mapa.putIfAbsent(destino, new ArrayList<>());
        mapa.get(origem).add(new Estrada(origem, destino, custo, distancia));
        mapa.get(destino).add(new Estrada(destino, origem, custo, distancia));
    }

    public void removerEstrada(Cidade origem, Cidade destino) {
        mapa.getOrDefault(origem, new ArrayList<>()).removeIf(e -> e.destino.equals(destino));
    }

    public List<Estrada> getEstradas(Cidade cidade) {
        return mapa.getOrDefault(cidade, new ArrayList<>());
    }
}