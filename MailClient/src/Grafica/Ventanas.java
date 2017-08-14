package Grafica;


import javax.swing.*;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;
import logica.FachadaLog;
import Conectividad.FachadaCon;


public class Ventanas {

	public static void main(String[] args) throws InterruptedException {
		
		
		try{
			  
			  JFrame.setDefaultLookAndFeelDecorated(true);
			  JDialog.setDefaultLookAndFeelDecorated(true);
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
			
			 
			}
			catch (Exception e)
			 {
			  e.printStackTrace();
			 }
		// TODO Auto-generated method stub
		Presentacion present = new Presentacion();
		present.setLocationRelativeTo(null);
		FachadaLog FL = new FachadaLog();
		FL.crearDirectorioCuenta(); // se crea el direccorio cuenta si es el primer ingreso
		FachadaCon FC = FachadaCon.getInstancia();
		FC.IniSocket();
		
		JTextPane txtpnIniciando = new JTextPane();
		txtpnIniciando.setBackground(UIManager.getColor("Button.background"));
		txtpnIniciando.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnIniciando.setText("INICIANDO ...");
		txtpnIniciando.setBounds(158, 261, 141, 28);
		present.getContentPane().add(txtpnIniciando);
		present.setVisible(true);
		Thread.sleep (5000); // espera 5 segundos antes de cerrar la ventana de inicio...
		Login log = new Login();
		log.setLocationRelativeTo(null); 			
		log.setVisible(true);
		
		present.dispose();
		
		
	
	}
}
