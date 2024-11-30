import java.util.*;

public class AlgoritmoDeRotas {
    Cidade origem, destino;
    double custo, distancia;


    // Métodos de cálculo de rotas
    public static List<Cidade> calcularMenorCusto(Grafo grafo, Cidade origem, Cidade destino, double limiteCusto) {
        Map<Cidade, Double> custos = new HashMap<>();
        Map<Cidade, Cidade> predecessores = new HashMap<>();
        PriorityQueue<Cidade> fila = new PriorityQueue<>(Comparator.comparingDouble(custos::get));

        // Inicializar os custos
        for (Cidade cidade : grafo.mapa.keySet()) {
            custos.put(cidade, Double.MAX_VALUE);
        }
        custos.put(origem, 0.0);
        fila.add(origem);

        // Algoritmo de Dijkstra Modificado
        while (!fila.isEmpty()) {
            Cidade atual = fila.poll();

            if (atual.equals(destino)) {
                break; // Já encontramos o menor custo
            }

            for (Estrada estrada : grafo.getEstradas(atual)) {
                Cidade vizinho = estrada.destino;
                double novoCusto = custos.get(atual) + estrada.custo;

                if (novoCusto < custos.get(vizinho) && novoCusto <= limiteCusto) {
                    custos.put(vizinho, novoCusto);
                    predecessores.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }

        // Construir o caminho de origem a destino
        List<Cidade> caminho = new ArrayList<>();
        Cidade passo = destino;

        while (passo != null) {
            caminho.add(0, passo);
            passo = predecessores.get(passo);
        }

        if (caminho.isEmpty() || !caminho.get(0).equals(origem)) {
            return new ArrayList<>(); // Nenhuma rota viável encontrada
        }

        return caminho;

    }

    public static List<Cidade> calcularMenorDistancia(Grafo grafo, Cidade origem, Cidade destino) {
        Map<Cidade, Double> distancias = new HashMap<>();
        Map<Cidade, Cidade> predecessores = new HashMap<>();
        PriorityQueue<Cidade> fila = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));

        // Inicializar as distâncias
        for (Cidade cidade : grafo.mapa.keySet()) {
            distancias.put(cidade, Double.MAX_VALUE);
        }
        distancias.put(origem, 0.0);
        fila.add(origem);

        // Algoritmo de Dijkstra
        while (!fila.isEmpty()) {
            Cidade atual = fila.poll();

            if (atual.equals(destino)) {
                break; // Já encontramos o menor caminho
            }

            for (Estrada estrada : grafo.getEstradas(atual)) {
                Cidade vizinho = estrada.destino;
                double novaDistancia = distancias.get(atual) + estrada.distancia;

                if (novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    predecessores.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }

        // Construir o caminho de origem a destino
        List<Cidade> caminho = new ArrayList<>();
        Cidade passo = destino;

        while (passo != null) {
            caminho.add(0, passo);
            passo = predecessores.get(passo);
        }

        if (caminho.isEmpty() || !caminho.get(0).equals(origem)) {
            return new ArrayList<>(); // Nenhuma rota encontrada
        }

        return caminho;
    }

}

