package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static int contadorId = 1;

    private int id;
    private String nome;
    private String email;
    private List<Usuario> listaSeguidores;
    private List<Usuario> listaSeguindo;
    private List<Postagem> listaPostagens;


    public Usuario(String nome, String email) {
        this.id = contadorId++;
        this.nome = nome;
        this.email = email;
        this.listaSeguidores = new ArrayList<>();
        this.listaSeguindo = new ArrayList<>();
        this.listaPostagens = new ArrayList<>();
    }


    public void seguirUsuario(Usuario u) {
        if (!listaSeguindo.contains(u)) {
            listaSeguindo.add(u);
            u.getListaSeguidores().add(this);
        }
    }

    public void criarPostagem(Postagem p) {
        listaPostagens.add(p);
    }

    public void curtirPostagem(Postagem p) {
        p.adicionarCurtida(this);
    }

    public void comentarPostagem(Postagem p, String texto) {
        Comentario c = new Comentario(this, texto);
        p.adicionarComentario(c);
    }


    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public List<Usuario> getListaSeguidores() { return listaSeguidores; }
    public List<Usuario> getListaSeguindo() { return listaSeguindo; }
    public List<Postagem> getListaPostagens() { return listaPostagens; }
}
