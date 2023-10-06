public class Node {
    int info;
    int altura;
    Node esquerda;
    Node direita;
    int frequencia;

    public Node(int info) {
        this.info = info;
        this.altura = 1;
        this.esquerda = null;
        this.direita = null;
        this.frequencia = 1;
    }

}


