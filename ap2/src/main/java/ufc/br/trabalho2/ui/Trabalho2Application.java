package ufc.br.trabalho2.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("ufc.br.trabalho2")
@EntityScan("ufc.br.trabalho2.entidade")
@EnableJpaRepositories("ufc.br.trabalho2.dao")
public class Trabalho2Application implements CommandLineRunner {
	@Autowired
	private CRUDFilme crudFilme;
	@Autowired
	private CRUDAtor crudAtor;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Trabalho2Application.class);
		builder.headless(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		int opcao = -1;
		Scanner entrada = new Scanner(System.in);
		do {
			menu();
			if (entrada.hasNextLine()) {
				opcao = entrada.nextInt();
			}
			switch (opcao) {
				case 0:
					break;
				case 1:
					crudFilme.runFilme();
					break;
				case 2:
					crudAtor.runAtor();
					break;
				default:
					System.out.println("Opção inválida");
					break;
			}
		} while (opcao != 0);
		entrada.close();
	}

	public static void menu() {
		System.out.println("1 - CRUD Filmes");
		System.out.println("2 - CRUD Atores");
		System.out.println("0 - Sair");

	}

}
