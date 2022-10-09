package TelasAplicativo;

import java.awt.EventQueue;
import EstruturaJogos.Conta;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class MenuJogos extends JFrame {

	static String nomeConta;
	
	Conta conta = new Conta();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuJogos frame = new MenuJogos(nomeConta);
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
	public MenuJogos(String nomeConta) {
		this.nomeConta = nomeConta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Olá");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(122, 71, 30, 20);
		contentPane.add(lblNewLabel);
		
		JLabel labelNomeUser = new JLabel(nomeConta);
		labelNomeUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNomeUser.setBackground(new Color(0, 64, 0));
		labelNomeUser.setBounds(162, 71, 155, 20);
		contentPane.add(labelNomeUser);
		
		JLabel lblNewLabel_2 = new JLabel(", o seu saldo atual é: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(346, 71, 201, 20);
		contentPane.add(lblNewLabel_2);
		
		String saldo = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Saldo");
		JLabel labelSaldoUser = new JLabel(saldo);
		labelSaldoUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelSaldoUser.setBounds(554, 71, 87, 20);
		contentPane.add(labelSaldoUser);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(163, 374, 1, 2);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("Futebol Estúdio");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FutebolEstudioFrame telaFutebolEstudio = new FutebolEstudioFrame(nomeConta);
				dispose();
				telaFutebolEstudio.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(301, 304, 309, 72);
		contentPane.add(btnNewButton);
		
		JButton btnJogarDadosDesafio = new JButton("Dados");
		btnJogarDadosDesafio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dados telaDados = new Dados(nomeConta);
				dispose();
				telaDados.setVisible(true);
			}
		});
		btnJogarDadosDesafio.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnJogarDadosDesafio.setBounds(301, 418, 309, 72);
		contentPane.add(btnJogarDadosDesafio);
		
		JLabel lblNewLabel_1 = new JLabel("Escolha o jogo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(370, 232, 169, 62);
		contentPane.add(lblNewLabel_1);
		
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
	}
}
