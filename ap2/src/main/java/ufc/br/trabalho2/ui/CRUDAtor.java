package ufc.br.trabalho2.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ufc.br.trabalho2.dao.AtorDAO;
import ufc.br.trabalho2.dao.FilmeDAO;
import ufc.br.trabalho2.entidade.Ator;
import ufc.br.trabalho2.entidade.Filme;

@Component
public class CRUDAtor {
    @Autowired
    AtorDAO atorDAO;
    @Autowired
    FilmeDAO filmeDAO;

    static Scanner entrada = new Scanner(System.in);
    int id, idAtor;

    public void runAtor() throws Exception {
        int opcao = -1;
        do {
            Ator ator;
            menu();
            if (entrada.hasNextLine()) {
                opcao = entrada.nextInt();
                entrada.nextLine();
            }
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    ator = new Ator();
                    if (obterAtor(ator)) {
                        atorDAO.save(ator);
                        System.out.println("Ator cadastrado!");
                    }
                    break;
                case 2:
                    ator = getAtor();
                    if (ator != null) {
                        if (obterAtor(ator)) {
                            atorDAO.save(ator);
                            System.out.println("Ator atualizado!");
                        }
                    } else {
                        System.out.println("Ator não encontrado!");
                    }
                    break;
                case 3:
                    if (!hasFilmesOnAtores()) {
                        atorDAO.deleteById(id);
                        System.out.println("Ator deletado com sucesso!");
                    }
                    break;
                case 4:
                    listaAtors(atorDAO.findAll());
                    break;
                case 5:
                    ator = getAtor();
                    if (ator != null) {
                        System.out.println(ator);
                    } else {
                        System.out.println("ID não encontrado");
                    }
                    break;
                case 6:
                    System.out.print("Digite o ano (aaaa): ");
                    int ano = entrada.nextInt();

                    LocalDate anoInicial = LocalDate.of(ano, 1, 1);
                    LocalDate anoFinal = LocalDate.of(ano, 12, 1);

                    anoFinal = anoFinal.withDayOfMonth(anoFinal.getMonth().length(anoFinal.isLeapYear()));
                    listaAtors(atorDAO.getPorAno(anoInicial, anoFinal));

                    break;
                case 7:
                    lerIdAtor();
                    ator = atorDAO.findByIdWithFilmes(idAtor).orElse(null);
                    if (ator != null) {
                        System.out.println(ator.getNome() + " fez parte do elenco de: ");
                        System.out.println(ator.getFilmes());
                    } else {
                        System.out.println("Esse ator não está cadastrado em nenhum filme");
                    }
                    break;
                case 8:
                    lerIdAtor();
                    ator = atorDAO.findById(idAtor).orElse(null);
                    if (ator != null) {
                        System.out.print("Digite o id do filme: ");
                        int idFilme = entrada.nextInt();
                        Filme filme = filmeDAO.findByIdWithAtores(idFilme).orElse(null);
                        if (filme != null) {
                            filme.getAtores().add(ator);
                            filmeDAO.save(filme);
                            System.out.println(filme);
                            System.out.println(filme.getAtores());
                        } else {
                            System.out.println("Filme não encontrado");
                        }
                    } else {
                        System.out.println("Ator não encontrado");
                    }
                    break;
                case 9:
                    lerIdAtor();
                    ator = atorDAO.findById(idAtor).orElse(null);
                    if (ator != null) {
                        System.out.print("Digite o id do filme: ");
                        int idFilme = entrada.nextInt();
                        Filme filme = filmeDAO.findByIdWithAtores(idFilme).orElse(null);
                        if (filme != null) {
                            for (Ator a : filme.getAtores()) {
                                if (a.getId() == ator.getId()) {
                                    filme.getAtores().remove(a);
                                    break;
                                }
                            }
                            filmeDAO.save(filme);
                            System.out.println(filme);
                            System.out.println(filme.getAtores());
                        } else {
                            System.out.println("Filme não encontrado");
                        }
                    } else {
                        System.out.println("Ator não encontrado");
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
        System.out.println("1 - Cadastrar ator");
        System.out.println("2 - Editar ator");
        System.out.println("3 - Remover ator");
        System.out.println("4 - Listar atores");
        System.out.println("5 - Buscar por ator pelo id");
        System.out.println("6 - Listar atores por ano");
        System.out.println("7 - Buscar filmes de um ator");
        System.out.println("8 - Vincular ator à um filme");
        System.out.println("9 - Desvincular ator de um filme");
        System.out.println("0 - Sair");
    }

    private boolean obterAtor(Ator ator) {
        System.out.print("Nome do ator: ");
        String nome = entrada.nextLine();
        ator.setNome(nome.trim().isEmpty() ? ator.getNome() : nome);

        System.out.print("Data de nascimento (dd/MM/aaaa): ");
        String tempData = entrada.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate format = LocalDate.parse(tempData, formatter);
            ator.setDataNascimento(format);
        } catch (DateTimeParseException es) {
            System.out.println("Formato inválido!\n");
            return false;
        }
        return true;
    }

    public Ator getAtor() {
        lerId();
        return atorDAO.findById(id).orElse(null);
    }

    public static void listaAtors(List<Ator> ators) {
        StringBuilder listagem = new StringBuilder();
        for (Ator fil : ators) {
            listagem.append(fil).append("\n");
        }
        System.out.println(listagem.length() == 0 ? "Nenhum ator encontrado" : listagem.toString());
    }

    public boolean hasFilmesOnAtores() {
        lerId();
        Ator ator = atorDAO.findByIdWithFilmes(id).orElse(null);
        if (ator != null) {
            if (ator.getFilmes().isEmpty()) {
                return false;
            } else {
                System.out.println("Esse ator faz parte do elenco de algum filme, remova-o antes de excluir");
                return true;
            }
        }
        System.out.println("ID não encontrado");
        return true;
    }

    public void lerId() {
        System.out.print("Insira o ID: ");
        id = entrada.nextInt();
        entrada.nextLine();
    }

    public void lerIdAtor() {
        System.out.print("Digite o id do ator: ");
        idAtor = entrada.nextInt();
        entrada.nextLine();
    }
}