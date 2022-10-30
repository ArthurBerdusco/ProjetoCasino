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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import EstruturaJogos.FutebolEstudio;
import EstruturaJogos.Conta;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.Choice;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Button;
import java.awt.Canvas;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.util.Date;

public class FutebolEstudioFrame extends JFrame {
	Conta conta = new Conta();
	FutebolEstudio futebolEstudio = new FutebolEstudio();
	String cartaCasaStr;
	String cartaForaStr;
	int valorCartaCasa = 0;
	int valorCartaFora = 0;
	static String nomeConta;
	Timer tempoAposta;
	Timer tempoPuxarCartaCasa;
	Timer tempoPuxarCartaFora;
	int valorAposta = 0;
	int ficha10 = 10;
	int ficha20 = 20;
	int ficha100 = 100;
	int ficha500 = 500;
	String timeSelecionado;
	boolean momentoApostas;
	int valorCasa = 0;
	int valorFora = 0;
	int valorEmpate = 0;

	private JLabel labelCartaCasa;
	private JLabel labelCartaFora;
	private JLabel lblSaldo;
	private JLabel lblSegundos;
	private JLabel labelPuxandoFora;
	private JLabel labelPuxandoCasa;
	private JButton btnEmpate;
	private JButton btnVisitante;
	private JToggleButton tglbtn10;
	private JToggleButton tglbtn20;
	private JToggleButton tglbtn100;
	private JToggleButton tglbtn500;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblValorEmpate;
	private JLabel lblValorVisitante;
	private JButton btnCasa;
	private JLabel lblValorCasa;
	private JButton btnCancelarAposta;
	private JLabel labelMomentoAposta;
	private JToggleButton tglSemMoeda;

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

	public int valorTotalAposta() {
		if((tglbtn10.isSelected() == true && momentoApostas == true)) {
			valorAposta = ficha10;
		}else if(tglSemMoeda.isSelected() == true && momentoApostas == true) {
			valorAposta = 0;
		}else if(tglbtn20.isSelected() == true && momentoApostas == true) {
			valorAposta = ficha20;
		}else if(tglbtn100.isSelected() == true && momentoApostas == true) {
			valorAposta = ficha100;
		}else if(tglbtn500.isSelected() == true && momentoApostas == true) {
			valorAposta = ficha500;
		}return valorAposta;
	}

	public int valorApostaTime() {
		int valorTime = 0;
		if((tglbtn10.isSelected() == true && momentoApostas == true)) {
			
			if(ficha10 == 0) {
				JOptionPane.showMessageDialog(tglbtn10, "Você não tem saldo suficiente!");
			}else {
				valorTime = ficha10;
			}
			
		}else if(tglbtn20.isSelected() == true && momentoApostas == true) {
			if(ficha20 == 0) {
				JOptionPane.showMessageDialog(tglbtn20, "Você não tem saldo suficiente!");
			}else {
				valorTime = ficha20;
			}
		}else if(tglbtn100.isSelected() == true && momentoApostas == true) {
			if(ficha100 == 0) {
				JOptionPane.showMessageDialog(tglbtn100, "Você não tem saldo suficiente!");
			}else {
				valorTime = ficha100;
			}
		}else if(tglbtn500.isSelected() == true && momentoApostas == true) {
			if(ficha500 == 0) {
				JOptionPane.showMessageDialog(tglbtn500, "Você não tem saldo suficiente!");
			}else {
				valorTime = ficha500;
			}
		}
		

		return valorTime;
	}

	public void resetApostas() {
		valorAposta = 0;
		valorCasa = 0;
		valorEmpate = 0;
		valorFora = 0;
		lblValorCasa.setText("");
		lblValorEmpate.setText("");
		lblValorVisitante.setText("");
		btnCancelarAposta.setVisible(false);
		atualizarMoeda();
	}

	public void apostaPuxarCartas(int valorAposta, String time) {
		String saldoStr = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
		int saldo = Integer.parseInt(saldoStr);
		if(valorAposta > 0) {
			String ganhouPerdeu = futebolEstudio.apostar(time, valorCartaCasa, valorCartaFora);
			int novoSaldo = futebolEstudio.atualizarSaldo(saldo, ganhouPerdeu, valorAposta, nomeConta);
			lblSaldo.setText(String.valueOf(novoSaldo));
		}
	}

	public void desabilitarBotaoAposta() {
		btnCasa.setEnabled(false);
		btnEmpate.setEnabled(false);
		btnVisitante.setEnabled(false);
	}

	public void habilitarBotaoAposta() {
		btnCasa.setEnabled(true);
		btnEmpate.setEnabled(true);
		btnVisitante.setEnabled(true);
	}

	public void atualizarMoeda() {
		System.out.println(valorAposta);
		int saldo = Integer.parseInt(conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo")) - valorAposta;
		if(saldo < 500) {
			
			ficha500 = 0;
			tglbtn500.setEnabled(false);
		}else {
			tglbtn500.setEnabled(true);
			ficha500 = 500;
		}
		if(saldo < 100) {
			ficha100 = 0;
			tglbtn100.setEnabled(false);
		}else {
			ficha100 = 100;
			tglbtn100.setEnabled(true);
		}

		if(saldo < 20) {
			ficha20 = 0;
			tglbtn20.setEnabled(false);
		}else {
			ficha20 = 20;
			tglbtn20.setEnabled(true);
		}

		if(saldo < 10) {
			ficha10 = 0;
			tglbtn10.setEnabled(false);
		}else {
			ficha10 = 10;
			tglbtn10.setEnabled(true);
		}
		tglSemMoeda.setSelected(false);

	}


	public FutebolEstudioFrame(String nomeConta) {
		this.nomeConta = nomeConta;
		timeSelecionado = "";
		momentoApostas = true;
		
		tempoAposta = new Timer(1000, new ActionListener() {

			int segundos = 18;

			@Override

			public void actionPerformed(ActionEvent e) {

				if(segundos > 0) {
					habilitarBotaoAposta();
					labelMomentoAposta.setText("APOSTAS ABERTAS!");
					labelMomentoAposta.setForeground(Color.GREEN);
					labelPuxandoCasa.setText("");
					labelPuxandoFora.setText("");
					momentoApostas = true;
				}

				if(segundos == 0) {
					desabilitarBotaoAposta();
					btnCancelarAposta.setVisible(false);
					tempoAposta.stop();
					labelMomentoAposta.setText("APOSTAS FECHADAS!");
					labelMomentoAposta.setForeground(Color.RED);
					tempoPuxarCartaCasa = new Timer(1000, new ActionListener() {

						int segundosCasa = 4;

						@Override

						public void actionPerformed(ActionEvent e) {
							segundosCasa--;

							if(segundosCasa == 3) {
								labelPuxandoCasa.setText("Puxando carta time casa...");
							}

							if(segundosCasa == -1) {

								String cartaCasa = futebolEstudio.puxarCarta();
								labelCartaCasa.setText(cartaCasa);
								valorCartaCasa = futebolEstudio.valorCarta(cartaCasa);
							}
						}

					});tempoPuxarCartaCasa.start();


					tempoPuxarCartaFora = new Timer(1000, new ActionListener() {

						int segundosFora = 8;

						@Override
						public void actionPerformed(ActionEvent e) {

							segundosFora--;

							if(segundosFora == 1) {
								labelPuxandoCasa.setText("");
								labelPuxandoFora.setText("Puxando carta time visitante...");
							}
							if(segundosFora == -1) {

								String cartaFora = futebolEstudio.puxarCarta();
								labelCartaFora.setText(cartaFora);
								valorCartaFora = futebolEstudio.valorCarta(cartaFora);
								momentoApostas = false;
								apostaPuxarCartas(valorCasa, "CASA");
								apostaPuxarCartas(valorFora, "VISITANTE");
								apostaPuxarCartas(valorEmpate, "EMPATE");
								tempoAposta.start();
								resetApostas();
								segundos = 18;
							}

						}

					});tempoPuxarCartaFora.start();


				}else if(segundos == 12) {
					labelCartaCasa.setText("");
					labelCartaFora.setText("");
				}
				lblSegundos.setText(String.valueOf(segundos));
				segundos--;
			}
		});
		tempoAposta.start();


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tglbtn20 = new JToggleButton("");
		tglbtn20.setForeground(Color.WHITE);
		buttonGroup_1.add(tglbtn20);
		tglbtn20.setIcon(new ImageIcon("C:\\20.png"));
		tglbtn20.setBackground(Color.WHITE);
		tglbtn20.setBounds(408, 535, 80, 80);
		contentPane.add(tglbtn20);

		labelCartaCasa = new JLabel(cartaCasaStr, SwingConstants.CENTER);
		labelCartaCasa.setForeground(new Color(0, 153, 255));
		labelCartaCasa.setFont(new Font("Arial", Font.PLAIN, 99));
		labelCartaCasa.setBounds(86, 157, 148, 127);
		contentPane.add(labelCartaCasa);

		labelCartaFora = new JLabel(cartaForaStr, SwingConstants.CENTER);
		labelCartaFora.setForeground(new Color(255, 51, 51));
		labelCartaFora.setFont(new Font("Arial", Font.PLAIN, 99));
		labelCartaFora.setBounds(750, 157, 148, 127);
		contentPane.add(labelCartaFora);

		labelPuxandoFora = new JLabel("");
		labelPuxandoFora.setFont(new Font("Arial", Font.PLAIN, 20));
		labelPuxandoFora.setBounds(692, 77, 272, 48);
		contentPane.add(labelPuxandoFora);

		JLabel lblSeuSaldo = new JLabel("Saldo: ");
		lblSeuSaldo.setFont(new Font("Arial", Font.PLAIN, 26));
		lblSeuSaldo.setBounds(10, 611, 191, 33);
		contentPane.add(lblSeuSaldo);

		String saldo = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
		lblSaldo = new JLabel(saldo);
		lblSaldo.setFont(new Font("Arial", Font.PLAIN, 26));
		lblSaldo.setBounds(95, 611, 134, 33);
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
		btnLogout.setBounds(896, 10, 80, 21);
		contentPane.add(btnLogout);

		lblSegundos = new JLabel("10");
		lblSegundos.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSegundos.setBounds(165, 35, 24, 13);
		contentPane.add(lblSegundos);

		JLabel lblNewLabel = new JLabel("Proxima rodada em: ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(54, 35, 115, 13);
		contentPane.add(lblNewLabel);

		JLabel lblS = new JLabel("s");
		lblS.setFont(new Font("Arial", Font.PLAIN, 12));
		lblS.setBounds(179, 35, 13, 13);
		contentPane.add(lblS);

		btnCasa = new JButton("CASA");
		btnCasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				valorAposta = valorAposta + valorTotalAposta();

				String consultaSaldo = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");

				
				if(valorAposta <= Integer.parseInt(consultaSaldo)) {
					int attSaldo = Integer.parseInt(consultaSaldo) - valorAposta;
					lblSaldo.setText(String.valueOf(attSaldo));
					valorCasa = valorCasa + valorApostaTime();
					lblValorCasa.setText(String.valueOf(valorCasa));
					atualizarMoeda();
					btnCancelarAposta.setVisible(true);
				}else {					
					JOptionPane.showMessageDialog(btnCasa, "Você não tem saldo suficiente!");
				}
			}
		});
		btnCasa.setBackground(new Color(0, 153, 255));
		btnCasa.setForeground(new Color(0, 0, 0));
		btnCasa.setFont(new Font("Arial", Font.PLAIN, 24));
		btnCasa.setBounds(35, 315, 251, 93);
		contentPane.add(btnCasa);

		btnEmpate = new JButton("EMPATE");
		btnEmpate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valorAposta = valorAposta + valorTotalAposta();
				String consultaSaldo = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
				
				if(valorAposta <= Integer.parseInt(consultaSaldo)) {
					int attSaldo = Integer.parseInt(consultaSaldo) - valorAposta;
					
					lblSaldo.setText(String.valueOf(attSaldo));
					valorEmpate = valorEmpate + valorApostaTime();
					lblValorEmpate.setText(String.valueOf(valorEmpate));
					atualizarMoeda();
					btnCancelarAposta.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(btnEmpate, "Você não tem saldo suficiente!");
				}
			}
		});
		btnEmpate.setForeground(new Color(0, 0, 0));
		btnEmpate.setFont(new Font("Arial", Font.PLAIN, 24));
		btnEmpate.setBackground(new Color(255, 255, 204));
		btnEmpate.setBounds(369, 315, 251, 93);
		contentPane.add(btnEmpate);

		btnVisitante = new JButton("VISITANTE");
		btnVisitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valorAposta = valorAposta + valorTotalAposta();
				String consultaSaldo = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
				
				if(valorAposta <= Integer.parseInt(consultaSaldo)) {
					int attSaldo = Integer.parseInt(consultaSaldo) - valorAposta;
					lblSaldo.setText(String.valueOf(attSaldo));
					valorFora = valorFora + valorApostaTime();
					lblValorVisitante.setText(String.valueOf(valorFora));
					atualizarMoeda();
					btnCancelarAposta.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(btnVisitante, "Você não tem saldo suficiente!");
				}
			}
		});
		btnVisitante.setForeground(new Color(0, 0, 0));
		btnVisitante.setFont(new Font("Arial", Font.PLAIN, 24));
		btnVisitante.setBackground(new Color(255, 0, 51));
		btnVisitante.setBounds(699, 315, 251, 93);
		contentPane.add(btnVisitante);

		tglbtn10 = new JToggleButton("");
		tglbtn10.setForeground(Color.WHITE);
		buttonGroup_1.add(tglbtn10);
		tglbtn10.setBackground(Color.WHITE);
		tglbtn10.setIcon(new ImageIcon("C:\\10.png"));
		tglbtn10.setBounds(291, 535, 80, 80);
		contentPane.add(tglbtn10);

		tglbtn100 = new JToggleButton("");
		tglbtn100.setForeground(Color.WHITE);
		buttonGroup_1.add(tglbtn100);
		tglbtn100.setIcon(new ImageIcon("C:\\100.png"));
		tglbtn100.setBackground(Color.WHITE);
		tglbtn100.setBounds(527, 535, 80, 80);
		contentPane.add(tglbtn100);

		tglbtn500 = new JToggleButton("");
		tglbtn500.setForeground(Color.WHITE);
		buttonGroup_1.add(tglbtn500);
		tglbtn500.setIcon(new ImageIcon("C:\\500.png"));
		tglbtn500.setBackground(Color.WHITE);
		tglbtn500.setBounds(646, 535, 80, 80);
		contentPane.add(tglbtn500);

			
		lblValorCasa = new JLabel("", SwingConstants.CENTER);
		lblValorCasa.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblValorCasa.setBounds(101, 416, 115, 57);
		contentPane.add(lblValorCasa);

		lblValorEmpate = new JLabel("", SwingConstants.CENTER);
		lblValorEmpate.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblValorEmpate.setBounds(439, 416, 115, 57);
		contentPane.add(lblValorEmpate);

		lblValorVisitante = new JLabel("", SwingConstants.CENTER);
		lblValorVisitante.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblValorVisitante.setBounds(767, 418, 115, 57);
		contentPane.add(lblValorVisitante);

		btnCancelarAposta = new JButton("Cancelar Aposta");
		btnCancelarAposta.setVisible(false);
		btnCancelarAposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				resetApostas();
				String consultaSaldo = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
				int attSaldo = Integer.parseInt(consultaSaldo) + valorAposta;
				lblSaldo.setText(String.valueOf(attSaldo));

			}
		});
		btnCancelarAposta.setBounds(454, 632, 125, 21);
		contentPane.add(btnCancelarAposta);

		labelMomentoAposta = new JLabel("", SwingConstants.CENTER);
		labelMomentoAposta.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelMomentoAposta.setBounds(291, 10, 405, 75);
		contentPane.add(labelMomentoAposta);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(80, 135, 161, 170);
		contentPane.add(panel);

		labelPuxandoCasa = new JLabel("");
		labelPuxandoCasa.setFont(new Font("Arial", Font.PLAIN, 20));
		labelPuxandoCasa.setBounds(26, 77, 272, 48);
		contentPane.add(labelPuxandoCasa);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(744, 135, 161, 170);
		contentPane.add(panel_1);
		
		tglSemMoeda = new JToggleButton("New toggle button");
		buttonGroup_1.add(tglSemMoeda);
		tglSemMoeda.setBounds(449, 483, 0, 0);
		contentPane.add(tglSemMoeda);
		

		atualizarMoeda();
	}
}
