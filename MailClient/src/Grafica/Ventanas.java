package Grafica;


import javax.swing.*;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;


public class Ventanas {

	public static void main(String[] args) throws InterruptedException {
		
		
		try{
			  
			  JFrame.setDefaultLookAndFeelDecorated(true);
			  JDialog.setDefaultLookAndFeelDecorated(true);
			  //  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			  //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // el que mas me gusto
			 // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			 
			}
			catch (Exception e)
			 {
			  e.printStackTrace();
			 }
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
