package arvores;

public class TesteArvore {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        arvore.inserir(30);
        arvore.inserir(25);
        arvore.inserir(20);
        arvore.inserir(22);
        arvore.inserir(40);
        arvore.inserir(27);
        arvore.inserir(45);

        arvore.mostrarEmOrdem();
    }
}
