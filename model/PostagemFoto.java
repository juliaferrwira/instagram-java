package model;

public class PostagemFoto extends Postagem{
    private String urlImagem;


    public PostagemFoto(Usuario autor,String urlImagem){
        super(autor);
        this.urlImagem = urlImagem;
    }

    @Override
    public void exibir() {
        System.out.println(urlImagem);
    }
}
