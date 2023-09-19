package arvores;

public class TesteArvore {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        arvore.inserir(17);
        arvore.inserir(21);
        arvore.inserir(5);
        arvore.inserir(19);
        arvore.inserir(20);
        arvore.inserir(1);
        arvore.inserir(15);
        arvore.inserir(6);
        arvore.inserir(3);
        arvore.inserir(9);
        arvore.inserir(12);

        arvore.mostrarEmOrdem();
        System.out.println();

        arvore.somaNodoNivelImpar();
        System.out.println();
    }
}
