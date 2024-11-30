import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AlgoritmoDeRotas algoritmoDeRotas = new AlgoritmoDeRotas();
        Grafo grafo = new Grafo();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        System.out.println("Bem-vindo ao sistema de rotas CargoFast!");

        while (executando) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Adicionar cidade");
            System.out.println("2. Adicionar estrada");
            System.out.println("3. Bloquear estrada");
            System.out.println("4. Calcular rota (menor custo ou menor distância)");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o \n

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome da cidade: ");
                    String nomeCidade = scanner.nextLine();
                    grafo.adicionarCidade(new Cidade(nomeCidade));
                    System.out.println("Cidade adicionada: " + nomeCidade);
                    break;

                case 2:
                    System.out.print("Digite o nome da cidade de origem: ");
                    String origemNome = scanner.nextLine();
                    System.out.print("Digite o nome da cidade de destino: ");
                    String destinoNome = scanner.nextLine();
                    System.out.print("Digite o custo da estrada: ");
                    double custo = scanner.nextDouble();
                    System.out.print("Digite a distância da estrada: ");
                    double distancia = scanner.nextDouble();
                    scanner.nextLine(); // Consumir o \n
                    Cidade origem = new Cidade(origemNome);
                    Cidade destino = new Cidade(destinoNome);
                    grafo.adicionarCidade(origem);
                    grafo.adicionarCidade(destino);
                    grafo.adicionarEstrada(origem, destino, custo, distancia);
                    System.out.println("Estrada adicionada entre " + origemNome + " e " + destinoNome);
                    break;

                case 3:
                    System.out.print("Digite o nome da cidade de origem: ");
                    String origemBloq = scanner.nextLine();
                    System.out.print("Digite o nome da cidade de destino: ");
                    String destinoBloq = scanner.nextLine();
                    grafo.removerEstrada(new Cidade(origemBloq), new Cidade(destinoBloq));
                    System.out.println("Estrada entre " + origemBloq + " e " + destinoBloq + " bloqueada.");
                    break;

                case 4:
                    System.out.print("Digite o nome da cidade de partida: ");
                    String partidaNome = scanner.nextLine();
                    System.out.print("Digite o nome da cidade de destino: ");
                    String chegadaNome = scanner.nextLine();
                    System.out.print("Deseja calcular por:\n menor custo digite (1) \n menor distancia digite (2) ");
                    int criterio = scanner.nextInt();
                    scanner.nextLine(); // Consumir o \n

                    Cidade partida = new Cidade(partidaNome);
                    Cidade chegada = new Cidade(chegadaNome);

                    if (criterio == 1) {
                        System.out.print("Digite o limite de custo: ");
                        double limiteCusto = scanner.nextDouble();
                        scanner.nextLine(); // Consumir o \n
                        List<Cidade> rotaCusto = algoritmoDeRotas.calcularMenorCusto(grafo, partida, chegada, limiteCusto);
                        if (rotaCusto.isEmpty()) {
                            System.out.println("Nenhuma rota encontrada dentro do limite de custo.");
                        } else {
                            System.out.println("Rota mais econômica: " + rotaCusto);
                        }
                    } else if (criterio == 2) {
                        List<Cidade> rotaDistancia = algoritmoDeRotas.calcularMenorDistancia(grafo, partida, chegada);
                        if (rotaDistancia.isEmpty()) {
                            System.out.println("Nenhuma rota encontrada.");
                        } else {
                            System.out.println("Rota mais curta: " + rotaDistancia );

                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 5:
                    executando = false;
                    System.out.println("Encerrando o programa. Obrigado por usar CargoFast!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

}