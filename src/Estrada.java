public class Estrada {
    Cidade origem, destino;
    double custo, distancia;

    public Estrada(Cidade origem, Cidade destino, double custo, double distancia) {
        this.origem = origem;
        this.destino = destino;
        this.custo = custo;
        this.distancia = distancia;
    }
}
