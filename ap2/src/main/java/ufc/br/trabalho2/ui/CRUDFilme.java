package ufc.br.trabalho2.ui;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ufc.br.trabalho2.dao.FilmeDAO;
import ufc.br.trabalho2.entidade.Filme;

@Component
public class CRUDFilme {
    @Autowired
    FilmeDAO filmeDAO;
    static Scanner entrada = new Scanner(System.in);
    int id;

    public void runFilme() throws Exception {
        int opcao = -1, ano, idFilme;
        do {
            Filme filme;
            List<Filme> filmes;
            menu();
            if (entrada.hasNextLine()) {
                opcao = entrada.nextInt();
                entrada.nextLine();
            }
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    filme = new Filme();
                    obterFilme(filme);
                    filmeDAO.save(filme);
                    System.out.println("Filme cadastrado!");
                    break;
                case 2:
                    if (getExistsId()) {
                        Optional<Filme> f = filmeDAO.findById(id);
                        filme = f.get();
                        obterFilme(filme);
                        filmeDAO.save(filme);
                        System.out.println("Filme atualizado!");
                    } else {
                        System.out.println("ID do filme não encontrado!");
                    }
                    break;
                case 3:
                    if (!hasAtoresOnFilmes()) {
                        filmeDAO.deleteById(id);
                        System.out.println("Filme deletado com sucesso!");
                    }
                    break;
                case 4:
                    listaFilmes(filmeDAO.findAll());
                    break;
                case 5:
                    if (getExistsId()) {
                        Optional<Filme> f = filmeDAO.findById(id);

                        filme = f.get();
                        System.out.println(filme.toString());
                    } else {
                        System.out.println("ID não encontrado");
                    }
                    break;
                case 6:
                    System.out.print("Digite o ano: ");
                    ano = entrada.nextInt();
                    filmes = filmeDAO.findFilmesPorAno(ano);
                    listarTitulos(filmes);
                    break;
                case 7:
                    System.out.println("Quantidade de filmes cadastrados: " + filmeDAO.count());
                    break;
                case 8:
                    System.out.print("Digite o título: ");
                    String titulo = entrada.nextLine();
                    listaFilmes(filmeDAO.filmeContemString(titulo));
                    break;
                case 9:
                    System.out.print("Digite o id do filme: ");
                    idFilme = entrada.nextInt();
                    filme = filmeDAO.findByIdWithAtores(idFilme).orElse(null);
                    if (filme != null) {
                        System.out.println("Elenco de: " + filme.getTitulo());
                        System.out.println(filme.getAtores());
                    } else {
                        System.out.println("Nenhum ator cadastrado nesse filme");
                    }
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);
        return;
    }

    public void menu() {
        System.out.println("1 - Cadastrar filme");
        System.out.println("2 - Editar filme");
        System.out.println("3 - Remover filme");
        System.out.println("4 - Listar filmes");
        System.out.println("5 - Buscar por filme pelo id");
        System.out.println("6 - Listar filmes por ano de lançamento");
        System.out.println("7 - Listar quantidade de filmes");
        System.out.println("8 - Listar título por string");
        System.out.println("9 - Buscar elenco de um filme");
        System.out.println("0 - Sair");
    }

    private void obterFilme(Filme filme) {
        System.out.print("Título do filme: ");
        String titulo = entrada.nextLine();
        filme.setTitulo(titulo.trim().isEmpty() ? filme.getTitulo() : titulo);

        System.out.print("Ano de lançamento: ");
        int ano = entrada.nextInt();
        filme.setAnoLancamento(Integer.toString(ano).trim().isEmpty() ? filme.getAnoLancamento() : ano);
    }

    public boolean getExistsId() {
        System.out.print("Insira o ID: ");
        id = entrada.nextInt();
        entrada.nextLine();
        return filmeDAO.existsById(id);
    }

    public static void listaFilmes(List<Filme> filmes) {
        StringBuilder listagem = new StringBuilder();
        for (Filme fil : filmes) {
            listagem.append(fil).append("\n");
        }
        System.out.println(listagem.length() == 0 ? "Nenhum filme encontrado" : listagem.toString());
    }

    public static void listarTitulos(List<Filme> filmes) {
        StringBuilder listagem = new StringBuilder();
        for (Filme fil : filmes) {
            listagem.append("Título: ").append(fil.getTitulo()).append("\n");
        }
        System.out.println(listagem.length() == 0 ? "Nenhum filme encontrado" : listagem.toString());
    }

    public boolean hasAtoresOnFilmes(){
    if(getExistsId()) {
        Filme filme = filmeDAO.findByIdWithAtores(id).orElse(null);
        if (filme.getAtores().isEmpty()) {
            return false;
        } else {
            System.out.println("Esse filme tem atores, remova-os antes de excluir");
        }

    } else {
        System.out.println("ID não encontrado");
    }
    return true;
}

}
