package model;

import java.time.LocalDateTime;

public class Curtida {
    private Usuario autor;
    private LocalDateTime data;

    public Curtida(Usuario autor) {
        this.autor = autor;
        this.data = LocalDateTime.now();
    }

    public Usuario getAutor() { return autor; }
    public LocalDateTime getData() { return data; }

    @Override
    public String toString() {
        return autor.getNome() + " curtiu em " + data;
    }
}
