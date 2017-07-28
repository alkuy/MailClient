package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.DropMode;
import javax.swing.UIManager;

import logica.FachadaLog;

import persistencia.Configuracion;

public class Ventanas {

	public static void main(String[] args) throws InterruptedException {
		
		// TODO Auto-generated method stub
		Presentacion present = new Presentacion();
		present.setLocationRelativeTo(null);  
		
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
