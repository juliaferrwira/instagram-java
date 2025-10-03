package model;

import java.time.LocalDateTime;

public class Comentario {
    private Usuario autor;
    private String texto;
    private LocalDateTime data;

    public Comentario(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
        this.data = LocalDateTime.now();
    }

    public Usuario getAutor() { return autor; }
    public String getTexto() { return texto; }
    public LocalDateTime getData() { return data; }

    @Override
    public String toString() {
        return autor.getNome() + " comentou: \"" + texto + "\" em " + data;
    }
}
