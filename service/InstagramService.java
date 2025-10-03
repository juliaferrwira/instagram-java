package service;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstagramService {

    private List<Usuario> usuarios;
    private Scanner sc;

    public InstagramService() {
        this.usuarios = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n===== INSTAGRAM: OPÇÕES =====");
            System.out.println("1. Criar usuário");
            System.out.println("2. Seguir usuário");
            System.out.println("3. Criar postagem (texto/foto)");
            System.out.println("4. Curtir postagem");
            System.out.println("5. Comentar postagem");
            System.out.println("6. Listar postagens de um usuário");
            System.out.println("7. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> criarUsuario();
                case 2 -> seguirUsuario();
                case 3 -> criarPostagem();
                case 4 -> curtirPostagem();
                case 5 -> comentarPostagem();
                case 6 -> listarPostagensUsuario();
                case 7 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    private void criarUsuario() {
        System.out.println("\n--- Criar Usuário ---");

        System.out.print("Digite o nome do usuário: ");
        String nome = sc.nextLine();

        System.out.print("Digite o email do usuário: ");
        String email = sc.nextLine();

        Usuario usuario = new Usuario(nome, email);
        usuarios.add(usuario);

        System.out.println("Usuário criado com sucesso!");
    }

    private void seguirUsuario() {
        System.out.println("\n--- Seguir Usuário ---");
        listarUsuarios();
        System.out.print("Digite o nome de quem vai seguir: ");
        String nomeSeguidor = sc.nextLine();
        Usuario seguidor = buscarUsuarioPorNome(nomeSeguidor);

        System.out.print("Digite o nome de quem será seguido: ");
        String nomeSeguido = sc.nextLine();
        Usuario seguido = buscarUsuarioPorNome(nomeSeguido);

        if (seguidor != null && seguido != null) {
            seguidor.seguirUsuario(seguido);
            System.out.println(seguidor.getNome() + " agora segue " + seguido.getNome());
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private void criarPostagem() {
        System.out.println("\n--- Criar Postagem ---");

        listarUsuarios();
        System.out.print("Digite o nome do autor: ");
        String nomeAutor = sc.nextLine();
        Usuario autor = buscarUsuarioPorNome(nomeAutor);

        if (autor == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("É foto ou vídeo? (f/v): ");
        String tipo = sc.nextLine();

        Postagem postagem = null;
        if (tipo.equalsIgnoreCase("f")) {
            System.out.print("Digite a URL da imagem: ");
            String url = sc.nextLine();
            postagem = new PostagemFoto(autor, url);
        } else if (tipo.equalsIgnoreCase("v")) {
            System.out.print("Digite a URL do vídeo: ");
            String url = sc.nextLine();
            System.out.print("Digite a duração do vídeo: ");
            int duracao = sc.nextInt();
            sc.nextLine();
            postagem = new PostagemVideo(autor, url, duracao);
        }

        if (postagem != null) {
            autor.criarPostagem(postagem);
            System.out.println("Postagem criada!");
        }
    }

    private void curtirPostagem() {
        System.out.println("\n--- Curtir Postagem ---");
        listarUsuarios();
        System.out.print("Digite o nome de quem vai curtir: ");
        String nomeCurtiu = sc.nextLine();
        Usuario curtiu = buscarUsuarioPorNome(nomeCurtiu);

        System.out.print("Digite o nome do autor da postagem: ");
        String nomeAutor = sc.nextLine();
        Usuario autor = buscarUsuarioPorNome(nomeAutor);

        if (curtiu != null && autor != null) {
            if (autor.getListaPostagens().isEmpty()) {
                System.out.println("Esse usuário não tem postagens!");
                return;
            }

            System.out.println("Postagens de " + autor.getNome() + ":");
            for (int i = 0; i < autor.getListaPostagens().size(); i++) {
                System.out.println(i + " - ");
                autor.getListaPostagens().get(i).exibir();
            }

            System.out.print("Escolha o índice da postagem: ");
            int idx = sc.nextInt(); sc.nextLine();

            if (idx >= 0 && idx < autor.getListaPostagens().size()) {
                curtiu.curtirPostagem(autor.getListaPostagens().get(idx));
                System.out.println("Curtida adicionada!");
            }
        }
    }

    private void comentarPostagem() {
        System.out.println("\n--- Comentar Postagem ---");
        listarUsuarios();
        System.out.print("Digite o nome de quem vai comentar: ");
        String nomeQuemComenta = sc.nextLine();
        Usuario quemComenta = buscarUsuarioPorNome(nomeQuemComenta);

        System.out.print("Digite o nome do autor da postagem: ");
        String nomeAutor = sc.nextLine();
        Usuario autor = buscarUsuarioPorNome(nomeAutor);

        if (quemComenta != null && autor != null) {
            if (autor.getListaPostagens().isEmpty()) {
                System.out.println("Esse usuário não tem postagens!");
                return;
            }

            System.out.println("Postagens de " + autor.getNome() + ":");
            for (int i = 0; i < autor.getListaPostagens().size(); i++) {
                System.out.println(i + " - ");
                autor.getListaPostagens().get(i).exibir();
            }

            System.out.print("Escolha o índice da postagem: ");
            int idx = sc.nextInt();
            sc.nextLine();

            if (idx >= 0 && idx < autor.getListaPostagens().size()) {
                Postagem postagem = autor.getListaPostagens().get(idx);
                System.out.print("Digite o comentário: ");
                String texto = sc.nextLine();

                // Delegando ao usuário que comenta, similar ao fluxo de curtir
                quemComenta.comentarPostagem(postagem, texto);
                System.out.println("Comentário adicionado!");
            }
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private void listarPostagensUsuario() {
        System.out.println("\n--- Listar Postagens ---");
        listarUsuarios();
        System.out.print("Digite o nome do usuário: ");
        String nome = sc.nextLine();
        Usuario usuario = buscarUsuarioPorNome(nome);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        if (usuario.getListaPostagens().isEmpty()) {
            System.out.println("Esse usuário não tem postagens!");
            return;
        }

        System.out.println("Postagens de " + usuario.getNome() + ":");
        for (Postagem p : usuario.getListaPostagens()) {
            p.exibir();
        }
    }

    private Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
    }

    private void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("Usuários cadastrados:");
        for (Usuario u : usuarios) {
            System.out.println("- " + u.getNome());
        }
    }
}
