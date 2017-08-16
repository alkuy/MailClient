package Grafica;


import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class FrmCambioPasswd extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwd;
	private JPasswordField confirmpasswd;
	


	/**
	 * Create the frame.
	 */
	public FrmCambioPasswd() {
		getContentPane().setForeground(Color.BLACK);
		setTitle("Cambio de contrase\u00F1a");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/imagenes/logo-mail.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		setBounds(100, 100, 408, 300);
		getContentPane().setLayout(null);
		
		passwd = new JPasswordField(15);
		passwd.setBounds(174, 105, 186, 29);
		getContentPane().add(passwd);		
		
		confirmpasswd = new JPasswordField(15);
		confirmpasswd.setBounds(174, 145, 186, 29);
		getContentPane().add(confirmpasswd);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String clave = String.valueOf(passwd.getPassword());
				String confirmclave = String.valueOf(confirmpasswd.getPassword());
				
				if(!clave.matches(".*[a-zA-Z].*") || !clave.matches(".*[0-9].*")){
					
					passwd.setText("");
					confirmpasswd.setText("");
					alerta1();
				} else if (!clave.equals(confirmclave)){
				
					      passwd.setText("");
					      confirmpasswd.setText("");
					      alerta2();
					
				        } else if (clave.length()<6){
					
				        	    passwd.setText("");
						        confirmpasswd.setText("");
						        alerta3();
					
				                } else {
					
					               cambiocorrecto();
					               dispose();
					               principal.pri.setVisible(true);
				                  }
			}
		});
		btnAceptar.setBounds(144, 221, 94, 29);
		getContentPane().add(btnAceptar);
		
		JLabel lblSoloUnPaso = new JLabel("Solo un paso m\u00E1s...");
		lblSoloUnPaso.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblSoloUnPaso.setBounds(100, 52, 260, 29);
		getContentPane().add(lblSoloUnPaso);
		
		JLabel lblIngreseNuevaContrasea = new JLabel("Ingrese nueva contrase\u00F1a");
		lblIngreseNuevaContrasea.setFont(new Font("Calibri", Font.BOLD, 13));
		lblIngreseNuevaContrasea.setBounds(10, 120, 154, 14);
		getContentPane().add(lblIngreseNuevaContrasea);
		
		JLabel lblConfirmeContrasea = new JLabel("Confirme nueva contrase\u00F1a");
		lblConfirmeContrasea.setFont(new Font("Calibri", Font.BOLD, 13));
		lblConfirmeContrasea.setBounds(10, 160, 154, 14);
		getContentPane().add(lblConfirmeContrasea);
		
		JLabel lblmnimoCaracteres = new JLabel("*m\u00EDnimo 6 caracteres conteniendo numeros y letras");
		lblmnimoCaracteres.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblmnimoCaracteres.setBounds(55, 185, 303, 14);
		getContentPane().add(lblmnimoCaracteres);
	}
	
	
	/* Metodo de aviso de passwd incorrecto*/
	
	public void alerta1(){
		Object [] opciones ={"Aceptar"};
		int eleccion = JOptionPane.showOptionDialog(rootPane,"Debe introducir letras y numeros","Atencion",JOptionPane.WARNING_MESSAGE,JOptionPane.WARNING_MESSAGE,null,opciones,"Aceptar");
		
	}
	/* Metodo de aviso de passwd que no coinciden*/
	public void alerta2(){
		Object [] opciones ={"Aceptar"};
		int eleccion = JOptionPane.showOptionDialog(rootPane,"Las claves ingresadas no coinciden","Atencion",JOptionPane.WARNING_MESSAGE,JOptionPane.WARNING_MESSAGE,null,opciones,"Aceptar");
		
	}
	
	
	/* Metodo de aviso de passwd que no coinciden*/
	public void alerta3(){
		Object [] opciones ={"Aceptar"};
		int eleccion = JOptionPane.showOptionDialog(rootPane,"Debe tener mínimo 6 caracteres","Atencion",JOptionPane.WARNING_MESSAGE,JOptionPane.WARNING_MESSAGE,null,opciones,"Aceptar");
		
	}
	
	/* Metodo de aviso de cambio correcto*/
	public void cambiocorrecto(){
		Object [] opciones ={"Aceptar"};
		int eleccion = JOptionPane.showOptionDialog(rootPane,"Se ha cambiado la clave correctamente","Atencion",JOptionPane.WARNING_MESSAGE,JOptionPane.WARNING_MESSAGE,null,opciones,"Aceptar");
		
	}
	
	
}
