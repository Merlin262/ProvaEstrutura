import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class mainj {
    public static void main(String[] args) {
        AVL avl = new AVL();
        arvore ar = new arvore();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        List<Integer> listaNumerosAleatorios = new ArrayList<>();

        int numeroElementos = 10000;

        for (int i = 0; i < numeroElementos; i++) {
            // Gere um número aleatório e adicione à lista
            int numeroAleatorio = random.nextInt(numeroElementos); // Números aleatórios entre 0 e 99
            listaNumerosAleatorios.add(numeroAleatorio);
        }


        while (true) {
            System.out.println("Menu Arvore binaria genérica");
            System.out.println("0 - Iniciar");
            System.out.println("1 - Adicionar item");
            System.out.println("2 - remover item");
            System.out.println("3 - imprimir arvore em preOrdem");
            System.out.println("4 - busca");
            System.out.println("5 - Sair");
            int opc1 = scanner.nextInt();
            if (opc1 == 0) {
                long startTime = System.currentTimeMillis();
                for (int numero : listaNumerosAleatorios) {
                    ar.Adicionar(numero);
                }
                System.out.println("Valores adicionados");
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");

            } else if (opc1 == 1) {
                System.out.println("Qual valor deseja adicionar: ");
                long startTime = System.currentTimeMillis();
                int valor = scanner.nextInt();
                ar.Adicionar(valor);
                System.out.println("Valor adicionado");
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");

            } else if (opc1 == 2) {
                System.out.println("Qual valor deseja remover: ");
                long startTime = System.currentTimeMillis();
                int remocao = scanner.nextInt();
                ar.remove(ar.raiz, remocao);
                System.out.println("Valor removido");
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");

            } else if (opc1 == 3) {
                long startTime = System.currentTimeMillis();
                ar.preOrdem(ar.raiz);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");

            } else if (opc1 == 4) {
                System.out.println("Qual numero deseja buscar: ");
                long startTime = System.currentTimeMillis();
                int valor = scanner.nextInt();
                ar.Busca(ar.raiz, valor);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");

            } else if (opc1 == 5) {
                System.out.println("Saindo...");
                break;
            }
        }

        while (true) {
            System.out.println("Menu Arvore AVL");
            System.out.println("0 - Iniciar");
            System.out.println("1 - Adicionar item");
            System.out.println("2 - remover item");
            System.out.println("3 - imprimir arvore em InOrdem");
            System.out.println("4 - busca");
            int opc = scanner.nextInt();

            if (opc == 0) {
                long startTime = System.currentTimeMillis();
                for (int numero : listaNumerosAleatorios) {
                    avl.raiz = avl.inserir(avl.raiz, numero);
                }
                System.out.println("Valores adicionados");
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");

            } else if (opc == 1) {
                System.out.println("Qual valor deseja adicionar: ");
                long startTime = System.currentTimeMillis();
                int valor = scanner.nextInt();
                avl.raiz = avl.inserir(avl.raiz, valor);
                System.out.println("Valor adicionado");
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");

            } else if (opc == 2) {
                long startTime = System.currentTimeMillis();
                System.out.println("Qual valor deseja remover: ");
                int remocao = scanner.nextInt();
                avl.raiz = avl.deletar(avl.raiz, remocao);
                System.out.println("Valor removido");
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");

            } else if (opc == 3) {
                long startTime = System.currentTimeMillis();
                avl.inOder(avl.raiz);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");
            } else if (opc == 4) {
                System.out.println("Qual numero deseja buscar: ");
                long startTime = System.currentTimeMillis();
                int valor = scanner.nextInt();
                avl.Busca(avl.raiz, valor);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução: " + elapsedTime + " milissegundos");
            }
        }
    }
}
