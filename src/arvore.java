public class arvore {

    Node raiz;

    public arvore() {
        raiz = null;
    }

    public void Adicionar(int valor) {
        Node novo = new Node(valor);
        novo.info = valor;
        novo.direita = null;
        novo.esquerda = null;

        if (raiz == null) {
            raiz = novo;
//            System.out.println("Raiz adicionada de valor: " + novo.getInfo());
        } else {
            Node atual = raiz;
            Node anterior;
            while (true) {
                anterior = atual;
                if (valor <= atual.info) {
                    atual = atual.esquerda;
                    if (atual == null) {
//                        System.out.println("O pai desta folha eh: " + anterior.getInfo());
                        anterior.esquerda = novo;
//                        System.out.println("O valor adicionado na esquerda foi: " + anterior.esquerda.getInfo());
                        return;
                    }
                } else {
                    atual = atual.direita;
                    if (atual == null) {
//                        System.out.println("O pai desta folha eh: " + anterior.getInfo());
                        anterior.direita = novo;
//                        System.out.println("O valor adicionado na direita foi: " + anterior.direita.getInfo());
                        return;
                    }
                }
            }
        }
    }

    public void inOder(Node no) {
        if (no != null) {
            inOder(no.esquerda);
            System.out.print(no.info + " ");
            inOder(no.direita);
        }
    }

    public void preOrdem(Node atual) {
        if (atual != null) {
            System.out.println(atual.info + " ");
            preOrdem(atual.esquerda);
            preOrdem(atual.direita);
        }
    }

    public void posOrdem(Node atual) {
        if (atual != null) {
            posOrdem(atual.esquerda);
            posOrdem(atual.direita);
            System.out.println(atual.info + " ");
        }
    }


    public Node remove(Node raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.info) {
            raiz.esquerda = remove(raiz.esquerda, valor);
        } else if (valor > raiz.info) {
            raiz.direita = remove(raiz.direita, valor);
        } else {
            if (raiz.esquerda == null) {
                return raiz.direita;
            } else if (raiz.direita == null) {
                return raiz.esquerda;
            }

            raiz.info = valorMinimo(raiz.direita);
            raiz.direita = remove(raiz.direita, raiz.info);
        }

        return raiz;
    }

    private int valorMinimo(Node raiz) {
        int valorMinimo = raiz.info;
        while (raiz.esquerda != null) {
            valorMinimo = raiz.esquerda.info;
            raiz = raiz.esquerda;
        }
        return valorMinimo;
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