package TelasAplicativo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JLabel;

public class TimerTeste extends JFrame {

	Timer t;
	private JPanel contentPane;
	private JButton btnStartContador;
	private JLabel lblContador;
	private JButton btnStopContador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimerTeste frame = new TimerTeste();
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
	public TimerTeste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnStartContador = new JButton("Start");
		btnStartContador.addActionListener(new ActionListener() {
			int k = 0;
			public void actionPerformed(ActionEvent e) {
				
				t = new Timer(1000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(k == 5) {
							k = 0;
						}
						lblContador.setText(String.valueOf(k));
						k++;
						
					}
				});
				t.start();
			}
		});
		btnStartContador.setBounds(58, 124, 85, 21);
		contentPane.add(btnStartContador);
		
		btnStopContador = new JButton("Stop");
		btnStopContador.setBounds(282, 124, 85, 21);
		contentPane.add(btnStopContador);
		
		lblContador = new JLabel("New label");
		lblContador.setBounds(183, 54, 45, 13);
		contentPane.add(lblContador);
	}
}
