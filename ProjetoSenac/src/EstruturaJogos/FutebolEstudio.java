package EstruturaJogos;
import java.util.Random;
import java.util.Scanner;
import java.util.Date;
import com.sun.jdi.event.BreakpointEvent;


public class FutebolEstudio {
	Scanner input = new Scanner(System.in);
	Random aleatorio = new Random();
	Conta conta = new Conta();
	
	public String puxarCarta() {
		String carta = "";
		try {
			String numeros[] = {"2","3","4","5","6","7","8","9","10","J","K","Q","A"};
			Thread.sleep(2000);
			carta = numeros[aleatorio.nextInt(12)];
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		return carta;
	}
	
	public int valorCarta(String carta) {
		int valor = 0;
		if(carta == "J") {
			valor = 11;
		}else if(carta == "Q"){
			valor = 12;
		}else if(carta == "K") {
			valor = 13;
		}else if(carta == "A") {
			valor = 14;
		}else {
			valor = Integer.parseInt(carta);
		}
		return valor;
	}
	
	public String apostar(String timeEscolhido, int cartaCasa, int cartaFora) {		
		
		if((cartaCasa > cartaFora) && (timeEscolhido == "CASA")) {
			return "GANHOU";
		}else if((cartaFora > cartaCasa) && (timeEscolhido == "VISITANTE")) {
			return "GANHOU";
		}else if((cartaCasa == cartaFora) && (timeEscolhido == "EMPATE")) {
			return "GANHOU EMPATE";
		}else if((cartaCasa == cartaFora) && ((timeEscolhido == "CASA") | (timeEscolhido == "VISITANTE"))) {
			return "PERDEU EMPATE";
		}else {
			return "PERDEU";
		}
	}
	
	public int atualizarSaldo(int saldo, String ganhouPerdeu, int valorAposta, String nomeConta) {
		if(ganhouPerdeu == "GANHOU") {
			saldo = saldo + valorAposta;
			conta.queryAtualizarSaldo(saldo, nomeConta);
		}else if(ganhouPerdeu == "GANHOU EMPATE") {
			saldo = saldo + (valorAposta * 14);
			conta.queryAtualizarSaldo(saldo, nomeConta);
		}else if(ganhouPerdeu == "PERDEU") {
			saldo = saldo - valorAposta;
			conta.queryAtualizarSaldo(saldo, nomeConta);
		}else if(ganhouPerdeu == "PERDEU EMPATE") {
			saldo = saldo - (valorAposta / 2);
			conta.queryAtualizarSaldo(saldo, nomeConta);
		}
		return saldo;
	}
}