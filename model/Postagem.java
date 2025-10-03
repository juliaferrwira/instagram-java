package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Postagem {
    private static int contadorId = 1;

    private int id;
    private Usuario autor;
    private LocalDateTime data;
    private List<Curtida> curtidas;
    private List<Comentario> comentarios;

    public Postagem(Usuario autor) {
        this.id = contadorId++;
        this.autor = autor;
        this.data = LocalDateTime.now();
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public void adicionarCurtida(Usuario u) {
        curtidas.add(new Curtida(u));
    }

    public void adicionarComentario(Comentario c) {
        comentarios.add(c);
    }

    public abstract void exibir();

    // Getters
    public int getId() { return id; }
    public Usuario getAutor() { return autor; }
    public LocalDateTime getData() { return data; }
    public List<Curtida> getCurtidas() { return curtidas; }
    public List<Comentario> getComentarios() { return comentarios; }
}
