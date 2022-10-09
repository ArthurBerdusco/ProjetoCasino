package TelasAplicativo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import EstruturaJogos.FutebolEstudio;
import EstruturaJogos.Conta;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.Choice;
import javax.swing.Timer;

public class FutebolEstudioFrame extends JFrame {
	Conta conta = new Conta();
	FutebolEstudio futebolEstudio = new FutebolEstudio();
	String cartaCasaStr;
	String cartaForaStr;
	static String nomeConta;
	Timer t;
	
	String timeSelecionado;
	private JTextField fieldValorAposta;
	private JRadioButton radioEmpate;
	private JRadioButton radioVisitante;
	private JRadioButton radioCasa;
	private JLabel labelCartaCasa;
	private JLabel labelCartaFora;
	private JLabel lblSaldo;
	private JLabel lblSegundos;
	private JLabel labelPuxandoFora;
	private JLabel labelPuxandoCasa;
	private JButton btnApostar;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FutebolEstudioFrame frame = new FutebolEstudioFrame(nomeConta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FutebolEstudioFrame(String nomeConta) {
		this.nomeConta = nomeConta;
		
		
		t = new Timer(1000, new ActionListener() {
			
			int segundos = 15;

			@Override
			public void actionPerformed(ActionEvent e) {
				//**** MOMENTO DE APOSTA ****
				btnApostar.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						String saldoStr = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
						int saldo = Integer.parseInt(saldoStr);
						String valorApostaStr = "";
						int valorAposta = 0;
						
						valorApostaStr = fieldValorAposta.getText();
						
						if(fieldValorAposta.getText().isEmpty()) {
							valorApostaStr = "0";
							valorAposta = Integer.parseInt(valorApostaStr);
						}else {
							valorAposta = Integer.parseInt(valorApostaStr);
						}
						
						saldo = Integer.parseInt(saldoStr);
						
						if((timeSelecionado != null) & ((valorAposta < saldo) & (valorAposta > 0))) {
							
							saldoStr = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
							saldo = Integer.parseInt(saldoStr);
							
							valorApostaStr = fieldValorAposta.getText();
							valorAposta = Integer.parseInt(valorApostaStr);
							int attSaldoAposta = saldo - valorAposta;
							lblSaldo.setText(String.valueOf(attSaldoAposta));
							
						}else if((timeSelecionado == null) & ((valorAposta < saldo) & (valorAposta > 0))) {
							JOptionPane.showMessageDialog(btnApostar, "Por favor, selecione um time!");
						}else if((timeSelecionado != null) & ((valorAposta > saldo) & (valorAposta > 0))) {
							JOptionPane.showMessageDialog(btnApostar, "Você não tem saldo sufiente.");
						}else if((timeSelecionado != null) & ((valorAposta < saldo) & (valorAposta <= 0))) {
							JOptionPane.showMessageDialog(btnApostar, "Valor da aposta inválido!");
						}
					}
				});
			
				labelPuxandoCasa.setText("");
				labelPuxandoFora.setText("");
				
				if(segundos <= 0) {
					String saldoStr = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
					int saldo = Integer.parseInt(saldoStr);
					String valorApostaStr = fieldValorAposta.getText();
					int valorAposta;
					
					if(fieldValorAposta.getText().isEmpty()) {
						valorApostaStr = "0";
						valorAposta = Integer.parseInt(valorApostaStr);
					}else {
						valorAposta = Integer.parseInt(valorApostaStr);
					}
					
					labelPuxandoCasa.setText("Puxando carta da casa...");
					labelPuxandoFora.setText("Puxando carta da visitante...");
					String cartaCasa = futebolEstudio.puxarCarta();
					labelCartaCasa.setText(cartaCasa);
					String cartaFora = futebolEstudio.puxarCarta();
					labelCartaFora.setText(cartaFora);
					
					int valorCartaCasa = futebolEstudio.valorCarta(cartaCasa);
					int valorCartaFora = futebolEstudio.valorCarta(cartaFora);
					
					String ganhouPerdeu = futebolEstudio.apostar(timeSelecionado, valorCartaCasa, valorCartaFora);
					int novoSaldo = futebolEstudio.atualizarSaldo(saldo, ganhouPerdeu, valorAposta, nomeConta);
					
					lblSaldo.setText(String.valueOf(novoSaldo));
					
					segundos = 15;
					radioVisitante.setSelected(false);
					radioEmpate.setSelected(false);
					radioCasa.setSelected(false);
					timeSelecionado = null;
					fieldValorAposta.setText("");
					valorAposta = 0;
				}
				lblSegundos.setText(String.valueOf(segundos));
				segundos--;
			}
		});
		t.start();
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelCartaCasa = new JLabel(cartaCasaStr);
		labelCartaCasa.setFont(new Font("Arial", Font.PLAIN, 99));
		labelCartaCasa.setBounds(122, 188, 148, 127);
		contentPane.add(labelCartaCasa);
		
		labelCartaFora = new JLabel(cartaForaStr);
		labelCartaFora.setFont(new Font("Arial", Font.PLAIN, 99));
		labelCartaFora.setBounds(780, 188, 148, 127);
		contentPane.add(labelCartaFora);
		
		radioCasa = new JRadioButton("");
		radioCasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioCasa.isSelected()) {
					radioVisitante.setSelected(false);
					radioEmpate.setSelected(false);
					timeSelecionado = "CASA";
				}else {
					timeSelecionado = null;
				}
			}
		});
		radioCasa.setFont(new Font("Tahoma", Font.PLAIN, 42));
		radioCasa.setBounds(150, 422, 21, 48);
		contentPane.add(radioCasa);
		
		JLabel lblCasa = new JLabel("CASA");
		lblCasa.setFont(new Font("Arial", Font.PLAIN, 40));
		lblCasa.setBounds(103, 330, 148, 127);
		contentPane.add(lblCasa);
		
		radioVisitante = new JRadioButton("");
		radioVisitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioVisitante.isSelected()) {
					radioCasa.setSelected(false);
					radioEmpate.setSelected(false);
					timeSelecionado = "VISITANTE";
				}else {
					timeSelecionado = null;
				}
			}
		});
		radioVisitante.setFont(new Font("Tahoma", Font.PLAIN, 42));
		radioVisitante.setBounds(809, 422, 21, 48);
		contentPane.add(radioVisitante);
		
		JLabel lblVisitante = new JLabel("VISITANTE");
		lblVisitante.setFont(new Font("Arial", Font.PLAIN, 40));
		lblVisitante.setBounds(715, 330, 213, 127);
		contentPane.add(lblVisitante);
		
		radioEmpate = new JRadioButton("");
		radioEmpate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioEmpate.isSelected()) {
					radioVisitante.setSelected(false);
					radioCasa.setSelected(false);
					timeSelecionado = "EMPATE";
				}else {
					timeSelecionado = null;
				}
			}
		});
		radioEmpate.setFont(new Font("Tahoma", Font.PLAIN, 42));
		radioEmpate.setBounds(478, 422, 21, 48);
		contentPane.add(radioEmpate);
		
		JLabel lblEmpate = new JLabel("EMPATE");
		lblEmpate.setFont(new Font("Arial", Font.PLAIN, 40));
		lblEmpate.setBounds(400, 330, 181, 127);
		contentPane.add(lblEmpate);
		
		fieldValorAposta = new JTextField();
		fieldValorAposta.setBounds(397, 524, 189, 33);
		contentPane.add(fieldValorAposta);
		fieldValorAposta.setColumns(10);
		
		JLabel lblValorAposta = new JLabel("Valor Aposta:");
		lblValorAposta.setFont(new Font("Arial", Font.PLAIN, 28));
		lblValorAposta.setBounds(223, 522, 191, 33);
		contentPane.add(lblValorAposta);
		
		labelPuxandoCasa = new JLabel("");
		labelPuxandoCasa.setFont(new Font("Arial", Font.PLAIN, 25));
		labelPuxandoCasa.setBounds(25, 130, 406, 48);
		contentPane.add(labelPuxandoCasa);
		
		labelPuxandoFora = new JLabel("");
		labelPuxandoFora.setFont(new Font("Arial", Font.PLAIN, 25));
		labelPuxandoFora.setBounds(669, 130, 406, 48);
		contentPane.add(labelPuxandoFora);
		
		btnApostar = new JButton("Apostar");	
		//ACTION LISTENER NO CONTADOR
		btnApostar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApostar.setBounds(596, 525, 85, 32);
		contentPane.add(btnApostar);
		
		JLabel lblSeuSaldo = new JLabel("Seu Saldo: ");
		lblSeuSaldo.setFont(new Font("Arial", Font.PLAIN, 28));
		lblSeuSaldo.setBounds(223, 575, 191, 33);
		contentPane.add(lblSeuSaldo);
		
		String saldo = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
		lblSaldo = new JLabel(saldo);
		lblSaldo.setFont(new Font("Arial", Font.PLAIN, 28));
		lblSaldo.setBounds(400, 575, 191, 33);
		contentPane.add(lblSaldo);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuJogos telaMenuJogos = new MenuJogos(nomeConta);
				dispose();
				telaMenuJogos.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(872, 611, 104, 32);
		contentPane.add(btnNewButton);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login telaLogin = new Login();
				dispose();
				telaLogin.setVisible(true);
			}
		});
		btnLogout.setBounds(891, 10, 85, 21);
		contentPane.add(btnLogout);
		
		lblSegundos = new JLabel("New label");
		lblSegundos.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSegundos.setBounds(172, 52, 45, 13);
		contentPane.add(lblSegundos);
		
		JLabel lblNewLabel = new JLabel("Proxima rodada em: ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(57, 52, 155, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblS = new JLabel("s");
		lblS.setFont(new Font("Arial", Font.PLAIN, 12));
		lblS.setBounds(223, 52, 45, 13);
		contentPane.add(lblS);
	}
}
