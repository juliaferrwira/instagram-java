package model;

public class PostagemVideo extends Postagem {
    private String urlVideo;
    private int duracao;

    public PostagemVideo(Usuario autor,String urlVideo,int duracao){
        super(autor);
        this.urlVideo = urlVideo;
        this.duracao = duracao;
    }

    @Override
    public void exibir() {
        System.out.println(urlVideo);
        System.out.println(duracao);
    }
}
