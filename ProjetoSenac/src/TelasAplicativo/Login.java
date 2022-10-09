package TelasAplicativo;

import EstruturaJogos.Conta;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Login extends JFrame {

	
	private JPanel contentPane;
	private JTextField fieldNomeConta;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		Conta conta = new Conta();
		
		setTitle("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Casino Las Venturas");
		lblNewLabel.setBounds(144, 38, 648, 37);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JLabel lblEfutuarLogin = new JLabel("Efutuar Login:");
		lblEfutuarLogin.setBounds(144, 219, 648, 37);
		lblEfutuarLogin.setForeground(new Color(255, 255, 255));
		lblEfutuarLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEfutuarLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblEfutuarLogin);
		
		JLabel lblNomeConta = new JLabel("Nome conta: ");
		lblNomeConta.setBounds(294, 268, 109, 29);
		lblNomeConta.setForeground(new Color(255, 255, 255));
		lblNomeConta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeConta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNomeConta);
		
		fieldNomeConta = new JTextField();
		fieldNomeConta.setBounds(401, 273, 161, 19);
		fieldNomeConta.setHorizontalAlignment(SwingConstants.CENTER);
		fieldNomeConta.setColumns(10);
		contentPane.add(fieldNomeConta);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setBounds(583, 273, 63, 19);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomeConta = fieldNomeConta.getText();
				String temConta = conta.bancoDeDados(conta.queryConsultaNome(nomeConta), "Nome");
				if(nomeConta.equalsIgnoreCase(temConta)) {
					MenuJogos telaMenuJogos = new MenuJogos(nomeConta);
					dispose();
					telaMenuJogos.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(btnLogin, "Nome incorreto ou não cadastrado!");
				}
			}
		});
		contentPane.add(btnLogin);
		
		JLabel lblAindaNoTem = new JLabel("Ainda não tem conta? Cadastre-se:");
		lblAindaNoTem.setBounds(144, 462, 648, 24);
		lblAindaNoTem.setForeground(new Color(255, 255, 255));
		lblAindaNoTem.setHorizontalAlignment(SwingConstants.CENTER);
		lblAindaNoTem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblAindaNoTem);
		
		JButton btnCadastro = new JButton("Cadastrar-se");
		btnCadastro.setBounds(413, 491, 99, 29);
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro telaCadastro = new Cadastro();
				dispose();
				telaCadastro.setVisible(true);
			}
		});
		btnCadastro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(btnCadastro);
	}
}
