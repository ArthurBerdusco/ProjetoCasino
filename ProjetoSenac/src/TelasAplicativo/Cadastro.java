package TelasAplicativo;
import EstruturaJogos.Conta;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.TextField;



public class Cadastro extends JFrame {
	
	Conta conta = new Conta();
	
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnCriarConta;
	private JTextField fieldNomeContaNova;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
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
	public Cadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do usuário: ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(291, 261, 166, 28);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Abrir nova conta");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 32));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(339, 114, 253, 46);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Receba 500 Reais de bonus ao criar sua conta!!");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 32));
		lblNewLabel_2.setBounds(172, 447, 717, 56);
		contentPane.add(lblNewLabel_2);
		
		
		btnCriarConta = new JButton("Criar Conta");
		btnCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomeContaNova = fieldNomeContaNova.getText();
				conta.queryInserirConta(nomeContaNova);
				JOptionPane.showMessageDialog(btnCriarConta, "Conta criada com sucesso!!");
				Login telaLogin = new Login();
				dispose();
				telaLogin.setVisible(true);
			}
		});
		btnCriarConta.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCriarConta.setBounds(642, 268, 107, 19);
		contentPane.add(btnCriarConta);
		
		fieldNomeContaNova = new JTextField();
		fieldNomeContaNova.setBounds(467, 269, 125, 19);
		contentPane.add(fieldNomeContaNova);
		fieldNomeContaNova.setColumns(10);
		
		
	}
}
