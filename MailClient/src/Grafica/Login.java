package Grafica;


import javax.swing.JTextField;
import javax.swing.border.Border;

import Conectividad.FachadaCon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import logica.FachadaLog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usuario;
	private JTextField textPasswd;
	String Status = "NotOk";
	String Servidor;
	FachadaCon FC = FachadaCon.getInstancia();
	FachadaLog FL = new FachadaLog();
	private JPasswordField passwordField;
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
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox comboDominio = new JComboBox(FL.GetDom());
		comboDominio.setBounds(265, 89, 121, 25);
		getContentPane().add(comboDominio);
		
		textPasswd = new JPasswordField();
		textPasswd.setBounds(44, 140, 342, 25);
		getContentPane().add(textPasswd);
		

		
		/* Boton Login */ 
		
		JButton btnLogin = new JButton("");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuario.setBorder(null);
				textPasswd.setBorder(null);
				
				if (!usuario.getText().isEmpty() && !textPasswd.getText().isEmpty()){
					
					Servidor = FC.Login(usuario.getText(),comboDominio.getSelectedItem().toString(), textPasswd.getText());
					
					if (Servidor.compareToIgnoreCase("No_Conect") == 0){
						
						if(FL.Busca_cuenta(FL.Devuelve_Ruta_Cuentas(), usuario.getText(),comboDominio.getSelectedItem().toString())){
							
							if( FL.compara_passwd("c:\\Cuentas\\"+usuario.getText()+"-"+comboDominio.getSelectedItem().toString()+"\\Configuracion\\", usuario.getText(),comboDominio.getSelectedItem().toString(), textPasswd.getText())){
							
								FL.Nueva_cuenta(usuario.getText(),comboDominio.getSelectedItem().toString(),textPasswd.getText());
							
								principal pri = new principal(usuario.getText()+"@"+comboDominio.getSelectedItem().toString());
								pri.setLocationRelativeTo(null); 			
								pri.setVisible(true);
								Status = "Ok";
								dispose();
							
							}else {
								JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña Incorrecta");
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Debe tener conexion a internet si es su primer conexion");
						}
				
					}
					else if(Servidor.compareToIgnoreCase("Valido") == 0){
						
						FL.Nueva_cuenta(usuario.getText(),comboDominio.getSelectedItem().toString(),textPasswd.getText());	
						principal pri = new principal(usuario.getText()+"@"+comboDominio.getSelectedItem().toString());
						pri.setLocationRelativeTo(null); 			
						pri.setVisible(true);
						Status = "Ok";
						dispose();
					}
					else if(Servidor.compareToIgnoreCase("NoPass") == 0){
						JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrectos");
					}
					else if(Servidor.compareToIgnoreCase("NoCuenta") == 0){
						JOptionPane.showMessageDialog(null, "Cuenta no existe o no esta habilitada");
					}
				}
			else{
				JOptionPane.showMessageDialog(null, "Debe indicar Nombre de Usuario y Password");
				Border border = BorderFactory.createLineBorder(Color.red);
				usuario.setBorder(border);
				textPasswd.setBorder(border);
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
