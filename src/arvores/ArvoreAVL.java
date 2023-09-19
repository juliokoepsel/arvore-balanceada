package arvores;

public class ArvoreAVL {

    private class Nodo {
        private int dado, altd, alte;
        private Nodo dir, esq;

        public Nodo(int dado) {
            this.dado = dado;
            dir = esq = null;
            altd = alte = 0;
        }
    }

    Nodo raiz;

    public ArvoreAVL() {
        raiz = null;
    }

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

    public void mostrarEmOrdem() {
        mostrandoOrdenado(raiz);
    }

    private void mostrandoOrdenado(Nodo raiz) {
        if (raiz != null) {
            mostrandoOrdenado(raiz.esq);
            System.out.print(raiz.dado + "(" + (raiz.altd - raiz.alte) + ")" + " ");
            mostrandoOrdenado(raiz.dir);
        }
    }

    //2) Exclusão:
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
 
                raiz.dir = deleteDado(raiz.dir, temp.dado);
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

    //3) Verificar se AVL:
    public void verificarAVL() {
        if (verificarArvoreAVL(raiz)) {
            System.out.println("A árvore é AVL!");
        } else {
            System.out.println("A árvore não é AVL!");
        }
    }
    private boolean verificarArvoreAVL(Nodo raiz) {
        if (raiz != null) {
            if (verificarArvoreAVL(raiz.esq) && verificarArvoreAVL(raiz.dir) && (raiz.altd - raiz.alte) >= -1 && (raiz.altd - raiz.alte) <= 1) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //4) Números primos:
    public void primos() {
        System.out.println(calcularPrimos(raiz) + " números primos!");
    }
    private boolean isPrime(int num) {
        if (num<=1) {
            return false;
        }
       for (int i=2; i<=num/2 ;i++) {
           if ((num % i) == 0)
               return  false;
       }
       return true;
    }
    private int calcularPrimos(Nodo raiz) {
        if (raiz != null) {
            if (isPrime(raiz.dado)) {
                return calcularPrimos(raiz.esq) + calcularPrimos(raiz.dir) + 1;
            } else {
                return calcularPrimos(raiz.esq) + calcularPrimos(raiz.dir);
            }
        }
        return 0;
    }

}
