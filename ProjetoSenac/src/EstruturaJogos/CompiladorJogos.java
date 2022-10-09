package EstruturaJogos;
import java.util.Scanner;

import TelasAplicativo.Login;

public class CompiladorJogos {
	
	Scanner input = new Scanner(System.in);
	MenuPrincipal imprimirMenu = new MenuPrincipal();
	Conta conta = new Conta();
	
	
	
	public void login() {
		//conta.setNome("arthur"); //  BANCO DE DADOS CONTA
		imprimirMenu.menuPrincipal();
		int escolha = input.nextInt();
		
		if(escolha == 1) {
			System.out.println("Nome Login: ");
			String loginConta = input.next();
			
			if(conta.autenticarConta(loginConta) == true) {
				escolherJogo();
			}else {
				System.out.println("Nome errado ou nome não cadastrado;");
				login();
			}
		}else if(escolha == 2) {
			System.out.println("Digite o nome da sua conta nova: ");
			String nomeContaNova = input.next();
			conta.setNome(nomeContaNova);
			conta.setSaldo(500);
			login();
		}else {
			System.out.println("Opção invalida!");
			login();
		}
	}
	
	
	public void escolherJogo() {
		System.out.println("Escolha o jogo: ");
		imprimirMenu.menuJogos();
		int escolha = input.nextInt();
		if(escolha == 1) {
			iniciarFutebolEstudio();
		}else if(escolha == 2) {
			System.out.println("DADOS");
		}else {
			System.out.println("Opção Inválida");
		}
	}
	
	

	
	
	public void iniciarFutebolEstudio() {
		FutebolEstudio futEstudio = new FutebolEstudio();
		futEstudio.apresentarMenu();
		
		int escolha = input.nextInt();
		if(escolha == 1) {
			futEstudio.menuAposta();
			int casaFora = input.nextInt();
			int valorAposta = futEstudio.valorAposta(conta.getSaldo());
			String apostar = futEstudio.realizarAposta(casaFora, futEstudio.puxarCartasCasaFora());
			conta.setSaldo(futEstudio.atualizarSaldo(conta.getSaldo(), apostar, valorAposta));
			System.out.println(conta.getSaldo());
			iniciarFutebolEstudio();
		}else if(escolha == 2) {
			//INSTRUÇÕES
		}else if(escolha == 3) {
			escolherJogo();
		}else {
			System.out.println("Opção inválida!");
			iniciarFutebolEstudio();
		}
	}
}
