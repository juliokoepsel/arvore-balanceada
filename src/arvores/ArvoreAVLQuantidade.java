package arvores;

public class ArvoreAVLQuantidade {
    
    //5) Novo Nodo com a variável quantidade:
    private class Nodo {
        private int quantidade, dado, altd, alte;
        private Nodo dir, esq;

        public Nodo(int dado) {
            this.dado = dado;
            dir = esq = null;
            altd = alte = 0;
            quantidade = 1;
        }
    }

    Nodo raiz;

    public ArvoreAVLQuantidade() {
        raiz = null;
    }

    //5) Nova inserção com quantidade:
    public void inserir(int dado) {
        raiz = inserirDado(raiz, dado);
    }
    private Nodo inserirDado(Nodo raiz, int dado) {
        if (raiz == null) {
            raiz = new Nodo(dado);
            return raiz;
        }
        if (dado < raiz.dado) {
            raiz.esq = inserirDado(raiz.esq, dado);
            if (raiz.esq.altd > raiz.esq.alte) {
                raiz.alte = raiz.esq.altd + 1;
            } else {
                raiz.alte = raiz.esq.alte + 1;
            }
            raiz = balanceamento(raiz);
        } else if (dado > raiz.dado) {
            raiz.dir = inserirDado(raiz.dir, dado);
            if (raiz.dir.altd > raiz.dir.alte) {
                raiz.altd = raiz.dir.altd + 1;
            } else {
                raiz.altd = raiz.dir.alte + 1;
            }
            raiz = balanceamento(raiz);
        } else {
            raiz.quantidade++;
        }
        return raiz;
    }

    private Nodo balanceamento(Nodo raiz) {
        int fb = raiz.altd - raiz.alte;
        int fbSubArv;
        if (fb == 2) {
            fbSubArv = raiz.dir.altd - raiz.dir.alte;
            if (fbSubArv >= 0) {
                raiz = rotacaoEsquerda(raiz);
            } else {
                raiz.dir = rotacaoDireita(raiz.dir);
                raiz = rotacaoEsquerda(raiz);
            }
        } else if (fb == -2) {
            fbSubArv = raiz.esq.altd - raiz.esq.alte;
            if (fbSubArv <= 0) {
                raiz = rotacaoDireita(raiz);
            } else {
                raiz.esq = rotacaoEsquerda(raiz.esq);
                raiz = rotacaoDireita(raiz);
            }
        }
        return raiz;
    }

    private Nodo rotacaoDireita(Nodo raiz) {
        Nodo aux1, aux2;
        aux1 = raiz.esq;
        aux2 = aux1.dir;
        raiz.esq = aux2;
        aux1.dir = raiz;
        if (raiz.esq == null) {
            raiz.alte = 0;
        } else if (raiz.esq.alte > raiz.esq.altd) {
            raiz.alte = raiz.esq.alte + 1;
        } else {
            raiz.alte = raiz.esq.altd + 1;
        }
        if (aux1.dir.alte > aux1.dir.altd) {
            aux1.altd = aux1.dir.alte + 1;
        } else {
            aux1.altd = aux1.dir.altd + 1;
        }
        return aux1;
    }

    private Nodo rotacaoEsquerda(Nodo raiz) {
        Nodo aux1, aux2;
        aux1 = raiz.dir;
        aux2 = aux1.esq;
        raiz.dir = aux2;
        aux1.esq = raiz;
        if (raiz.dir == null) {
            raiz.altd = 0;
        } else if (raiz.dir.alte > raiz.dir.altd) {
            raiz.altd = raiz.dir.alte + 1;
        } else {
            raiz.altd = raiz.dir.altd + 1;
        }
        if (aux1.esq.alte > aux1.esq.altd) {
            aux1.alte = aux1.esq.alte + 1;
        } else {
            aux1.alte = aux1.esq.altd + 1;
        }
        return aux1;
    }

    //5) Nova função para mostrar com quantidade:
    public void mostrarEmOrdem() {
        mostrandoOrdenado(raiz);
    }
    private void mostrandoOrdenado(Nodo raiz) {
        if (raiz != null) {
            mostrandoOrdenado(raiz.esq);
            System.out.print(raiz.quantidade + "x" + raiz.dado + "(" + (raiz.altd - raiz.alte) + ")" + " ");
            mostrandoOrdenado(raiz.dir);
        }
    }

    //5) Nova função para exclusão com quantidade:
    public void delete(int dado) {
        raiz = deleteDado(raiz, dado);
    }
    private Nodo minValorNodo(Nodo raiz) {
        while (raiz.esq != null)
            raiz = raiz.esq;
        return raiz;
    }
    private Nodo deleteDado(Nodo raiz, int dado) {
        if (raiz == null)
            return raiz;

        if (dado < raiz.dado) {
            raiz.esq = deleteDado(raiz.esq, dado);
        } else if (dado > raiz.dado) {
            raiz.dir = deleteDado(raiz.dir, dado);
        } else {
            if (raiz.quantidade > 1) {
                raiz.quantidade--;
            } else {
                if ((raiz.esq == null) || (raiz.dir == null)) {
                    Nodo temp = null;
                    if (temp == raiz.esq)
                        temp = raiz.dir;
                    else
                        temp = raiz.esq;
                    if (temp == null) {
                        temp = raiz;
                        raiz = null;
                    } else
                        raiz = temp;  
                } else {
                    Nodo temp = minValorNodo(raiz.dir);
    
                    raiz.dado = temp.dado;
                    raiz.quantidade = temp.quantidade;
    
                    raiz.dir = deleteDado(raiz.dir, temp.dado);
                }
            }
        }
 
        if (raiz == null)
            return raiz;

        int altura = 0;
        Nodo aux = raiz.dir;
        while (aux != null) {
            altura++;
            if (aux.alte > aux.altd) {
                aux = aux.esq;
            } else {
                aux = aux.dir;
            }
        }
        raiz.altd = altura;

        altura = 0;
        aux = raiz.esq;
        while (aux != null) {
           altura++;
            if (aux.alte > aux.altd) {
                aux = aux.esq;
            } else {
                aux = aux.dir;
            }
        }
        raiz.alte = altura;
 
        raiz = balanceamento(raiz);
 
        return raiz;
    }

}
