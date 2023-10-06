public class AVL {
    Node raiz;

    int altura(Node No) {
        if (No == null)
            return 0;
        return No.altura;
    }

    Node rotacaoDireita(Node noAtual) {
        if (noAtual == null || noAtual.esquerda == null) {
            return noAtual;
            //verifica se é possivel a rotação
        }

        Node noEsquerda = noAtual.esquerda;
        //Cria uma referência noEsquerda para o filho esquerdo do nó atual.
        Node subArvoreDireita = noEsquerda.direita;
        //Armazena a subárvore direita do nó à esquerda em subArvoreDireita.
        noEsquerda.direita = noAtual;
        //Atribui o nó atual como filho direito do nó à esquerda.
        noAtual.esquerda = subArvoreDireita;
        //Atualiza o filho esquerdo do nó atual para ser a subárvore direita.

        // Calcula as alturas
        int alturaNoAtualEsquerda = altura(noAtual.esquerda);
        int alturaNoAtualDireita = altura(noAtual.direita);
        int alturaNoEsquerdaEsquerda = altura(noEsquerda.esquerda);
        int alturaNoEsquerdaDireita = altura(noEsquerda.direita);
        //calculo das alturas

        // Atualiza alturas
        noAtual.altura = 1 + alturaMaior(alturaNoAtualEsquerda, alturaNoAtualDireita);
        noEsquerda.altura = 1 + alturaMaior(alturaNoEsquerdaEsquerda, alturaNoEsquerdaDireita);
        //retorna nó pós rotação

        return noEsquerda;
    }

    Node rotacaoEsquerda(Node noAtual) {
        if (noAtual == null || noAtual.direita == null) {
            return noAtual;
            //verifica se é possivel a rotação
        }

        Node noDireita = noAtual.direita;
        Node subArvoreEsquerda = noDireita.esquerda;
        noDireita.esquerda = noAtual;
        noAtual.direita = subArvoreEsquerda;

        // Calcula as alturas
        int alturaNoAtualEsquerda = altura(noAtual.esquerda);
        int alturaNoAtualDireita = altura(noAtual.direita);
        int alturaNoDireitaEsquerda = altura(noDireita.esquerda);
        int alturaNoDireitaDireita = altura(noDireita.direita);

        // Atualiza alturas
        noAtual.altura = 1 + alturaMaior(alturaNoAtualEsquerda, alturaNoAtualDireita);
        noDireita.altura = 1 + alturaMaior(alturaNoDireitaEsquerda, alturaNoDireitaDireita);

        return noDireita;
    }

    int alturaMaior(int altura1, int altura2) {
        if (altura1 > altura2) {
            return altura1;
        } else {
            return altura2;
        }
    }
    int fatorDeBalanceamento(Node N) {
        if (N == null)
            return 0;
        return altura(N.esquerda) - altura(N.direita);
    }

    Node inserir(Node node, int item) {
        if (node == null)
            return (new Node(item));
        //Se for nulo, cria um novo nó com o valor item e o retorna como a raiz da subárvore inserida.
        if (item < node.info)
            node.esquerda = inserir(node.esquerda, item);
        //Se for menor, a função inserir é chamada recursivamente na subárvore esquerda do nó node.
        //O resultado da inserção é atribuído ao nó esquerdo do node.
        else if (item > node.info)
            node.direita = inserir(node.direita, item);
        else
            node.direita = inserir(node.direita, item);
        node.altura = 1 + alturaMaior(altura(node.esquerda), altura(node.direita));
        int fatorBalanc = fatorDeBalanceamento(node);
        if (fatorBalanc > 1) {
            // Se o fator de balanceamento for maior que 1, isso indica que a subárvore esquerda é mais alta
            if (item < node.esquerda.info) {
                // Se o item for menor que o valor na subárvore esquerda, é realizada uma rotação simples à direita
                return rotacaoDireita(node);
            } else if (item > node.esquerda.info) {
                //Se o item for maior que o valor na subárvore esquerda, é realizada uma rotação à esquerda em node.esquerda, seguida por uma rotação simples à direita
                node.esquerda = rotacaoEsquerda(node.esquerda);
                return rotacaoDireita(node);
            }
        }
        if (fatorBalanc < -1) {
            //Se o fator de balanceamento for menor que -1, isso indica que a subárvore direita é mais alta
            if (item > node.direita.info) {
                //Se o item for maior que o valor na subárvore direita, é realizada uma rotação simples à esquerda
                return rotacaoEsquerda(node);
            } else if (item < node.direita.info) {
                //Se o item for menor que o valor na subárvore direita, é realizada uma rotação à direita em node.direita, seguida por uma rotação simples à esquerda
                node.direita = rotacaoDireita(node.direita);
                return rotacaoEsquerda(node);
            }
        }
        return node;
    }


    Node noMinimo(Node no) {
        Node atual = no;
        while (atual.esquerda != null)
            atual = atual.esquerda;
        return atual;
    }

    Node deletar(Node raiz, int item) {
        if (raiz == null)
            return raiz;
        if (item < raiz.info)
            raiz.esquerda = deletar(raiz.esquerda, item);
        else if (item > raiz.info)
            raiz.direita = deletar(raiz.direita, item);
        else {
            if ((raiz.esquerda == null) || (raiz.direita == null)) {
                Node temp = null;
                if (temp == raiz.esquerda)
                    temp = raiz.direita;
                else
                    temp = raiz.esquerda;
                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else
                    raiz = temp;
            } else {
                Node temp = noMinimo(raiz.direita);
                raiz.info = temp.info;
                raiz.direita = deletar(raiz.direita, temp.info);
            }
        }
        if (raiz == null)
            return raiz;

        raiz.altura = alturaMaior(altura(raiz.esquerda), altura(raiz.direita)) + 1;
        int balanceFactor = fatorDeBalanceamento(raiz);
        if (balanceFactor > 1) {
            if (fatorDeBalanceamento(raiz.esquerda) >= 0) {
                return rotacaoDireita(raiz);
            } else {
                raiz.esquerda = rotacaoEsquerda(raiz.esquerda);
                return rotacaoDireita(raiz);
            }
        }
        if (balanceFactor < -1) {
            if (fatorDeBalanceamento(raiz.direita) <= 0) {
                return rotacaoEsquerda(raiz);
            } else {
                raiz.direita = rotacaoDireita(raiz.direita);
                return rotacaoEsquerda(raiz);
            }
        }
        return raiz;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.info + " ");
            preOrder(node.esquerda);
            preOrder(node.direita);
        }
    }

    public void inOder(Node no) {
        if (no != null) {
            inOder(no.esquerda);
            System.out.print(no.info + " ");
            inOder(no.direita);
        }
    }

    public boolean Busca(Node no, int info) {
        if (no == null) {
            System.out.println("O valor não foi encontrado");
            return false;
        }

        if (info == no.info) {
            System.out.println("O valor foi achado " + info);
            return true;
        }

        if (info < no.info) {
            return Busca(no.esquerda, info);
        } else {
            return Busca(no.direita, info);
        }
    }
}
