package Grafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Presentacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Presentacion() {
		setLocationRelativeTo(null);  
		setResizable(false);
		setTitle("Bienvenidos");
		setAutoRequestFocus(false);
		setForeground(Color.RED);
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 300);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Presentacion.class.getResource("/imagenes/logo-mail.png")));
		lblNewLabel.setBounds(50, 24, 300, 249);
		contentPane.add(lblNewLabel);
		
	
		
	}
	}


