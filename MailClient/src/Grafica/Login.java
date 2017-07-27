package Grafica;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import logica.FachadaLog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	private JTextField usuario;
	private JTextField textPasswd;
	boolean verifica_servidor = true; // por ahora queda forzado hasta que se conecte y compruebe la cuenta en el servidor
	FachadaLog FL = new FachadaLog();
	/**
	 * Create the panel.
	 */
	public Login() {
		
		setTitle("Login");
     	setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/imagenes/icon1.jpg")));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);  
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 300);
		
		usuario = new JTextField();
		usuario.setBounds(44, 89, 200, 25);
		getContentPane().add(usuario);
		usuario.setColumns(10);
		
		JLabel label = new JLabel("@");
		label.setFont(new Font("Verdana", Font.BOLD, 16));
		label.setBounds(247, 84, 24, 28);
		getContentPane().add(label);
		
		JComboBox comboDominio = new JComboBox();
		comboDominio.setBounds(265, 89, 121, 25);
		getContentPane().add(comboDominio);
		
		textPasswd = new JTextField();
		textPasswd.setBounds(44, 140, 342, 25);
		getContentPane().add(textPasswd);
		textPasswd.setColumns(10);
		
		JButton btnLogin = new JButton("");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if ( verifica_servidor){
					
					
				FL.Nueva_cuenta(usuario.getText(),"inet",textPasswd.getText());	
				principal pri = new principal();
				pri.setLocationRelativeTo(null); 			
				pri.setVisible(true);
				dispose();
				
				}
				
			}
		});
		btnLogin.setBounds(146, 222, 133, 38);
		ImageIcon imagenlogin = new ImageIcon(principal.class.getResource("/imagenes/LOGIN.jpg"));
		Icon iconoLogin = new ImageIcon(imagenlogin.getImage().getScaledInstance(btnLogin.getWidth(),btnLogin.getHeight(),Image.SCALE_DEFAULT));
		btnLogin.setIcon(iconoLogin);
		getContentPane().add(btnLogin);
		
         /* Logo */
		
		JLabel logo = new JLabel();
		logo.setBounds(146, 11, 115, 77);
        ImageIcon imagen = new ImageIcon(principal.class.getResource("/imagenes/logo-mail.png"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(logo.getWidth(),logo.getHeight(),Image.SCALE_DEFAULT));
		logo.setIcon(icono);
		getContentPane().add(logo);
		
		JLabel label_1 = new JLabel();
		label_1.setBounds(10, 87, 24, 25);
		ImageIcon imagenusu = new ImageIcon(principal.class.getResource("/imagenes/userLog.png"));
		Icon iconousu = new ImageIcon(imagenusu.getImage().getScaledInstance(label_1.getWidth(),label_1.getHeight(),Image.SCALE_DEFAULT));
		label_1.setIcon(iconousu);
		getContentPane().add(label_1);
		
		JLabel lblPas = new JLabel();
		lblPas.setBounds(10, 140, 24, 22);
		ImageIcon imagenpas = new ImageIcon(principal.class.getResource("/imagenes/keyLog.png"));
		Icon iconopas = new ImageIcon(imagenpas.getImage().getScaledInstance(lblPas.getWidth(),lblPas.getHeight(),Image.SCALE_DEFAULT));
		lblPas.setIcon(iconopas);
		getContentPane().add(lblPas);

	}
}
