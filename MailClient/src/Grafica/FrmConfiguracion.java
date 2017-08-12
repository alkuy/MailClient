package Grafica;



import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import Conectividad.FachadaCon;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class FrmConfiguracion extends JInternalFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public FrmConfiguracion() {
		
		FachadaCon FC = FachadaCon.getInstancia();
		setTitle("Configuracion");
		
	 	setBorder(new EmptyBorder(0, 0, 0, 0));
      	setBounds(100, 100, 243, 300);
      	getContentPane().setLayout(null);
		
		
		JButton btnagenda = new JButton("AGENDA");
		btnagenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnagenda.setBounds(47, 65, 143, 34);
		getContentPane().add(btnagenda);
		
		JLabel lblSoloUnPaso = new JLabel("Configuracion");
		lblSoloUnPaso.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblSoloUnPaso.setBounds(57, 11, 143, 29);
		getContentPane().add(lblSoloUnPaso);
		
		JButton btnmanual = new JButton("MANUAL USUARIO");
		btnmanual.setBounds(47, 96, 143, 34);
		getContentPane().add(btnmanual);
		
		JButton btnNewButton = new JButton("RECIBIR ANTERIORES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				FC.CorreosAntiguos();
			}
		});
		btnNewButton.setBounds(47, 126, 143, 34);
		getContentPane().add(btnNewButton);
	}
}
