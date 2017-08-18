package Grafica;



import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import logica.Correo;
import logica.FachadaLog;
import javax.swing.JTextArea;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MuestraCorreo extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrmMuestraBuzonSalida salida;
	private FrmMuestraEnviados enviado;
	private FrmMuestraEnviados boton;
	FachadaLog FL = new FachadaLog();
	Verificaciones verifica = new Verificaciones();
	
	
	
	
	/**
	 * Create the frame.
	 */
	public MuestraCorreo() {
		
		Correo correo = new Correo();
		
		if(boton.aprete.equals("si") ){ // si quiero ver un correo de la bandeja de enviados 
			correo = FL.DevuelveEnviado(String.valueOf(enviado.fecha));
		  
		} else { // si quiero ver un correo de la bandeja de salida
			
			correo = FL.DevuelveBandejaSalida(String.valueOf(salida.fecha));
		  }
		
				
		getContentPane().setBackground(SystemColor.activeCaption);
		
		setTitle("Correo");
     	setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/imagenes/icon1.jpg")));
		setBounds(100, 100, 970, 620);
				
		getContentPane().setLayout(null);
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setFont(new Font("Verdana", Font.BOLD, 13));
		lblPara.setBounds(167, 32, 46, 14);
		getContentPane().add(lblPara);
		
		if(boton.aprete.equals("si") ){
			JLabel labeldestino = new JLabel(enviado.cuenta);
			labeldestino.setBounds(245, 32, 567, 16);
			getContentPane().add(labeldestino);
		}else{
		    JLabel labeldestino = new JLabel(salida.cuenta);
		    labeldestino.setBounds(245, 32, 567, 16);
		    getContentPane().add(labeldestino);
		}
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setFont(new Font("Verdana", Font.BOLD, 13));
		lblAsunto.setBounds(167, 70, 70, 14);
		getContentPane().add(lblAsunto);
		
		JLabel labelasunto = new JLabel(correo.getAsunto());
		labelasunto.setBounds(245, 70, 679, 14);
		getContentPane().add(labelasunto);
		
		JTextArea textcorreo = new JTextArea();
		textcorreo.setEditable(false);
		//textcorreo.setBounds(10, 95, 914, 405);
		textcorreo.setLineWrap(true);
		getContentPane().add(textcorreo);
		String texto = FL.encriptaOdesencripta(correo.getTexto(), principal.clave); // desencripta primero por Xor
		texto = FL.Permutar(texto,principal.claveper, principal.clave);	// desencripta por permutacion	
		
		textcorreo.setText(texto);
		
		boton.aprete = "no";// vuelvo a valor original para nuevas consultas en buzon de salida o enviados
		
		/* Logo en Nuevo correo*/
		
		JLabel logo = new JLabel();
		logo.setBounds(10, 0, 115, 94);
        ImageIcon imagen = new ImageIcon(principal.class.getResource("/imagenes/logo-mail.png"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(logo.getWidth(),logo.getHeight(),Image.SCALE_DEFAULT));
		logo.setIcon(icono);
		getContentPane().add(logo);
		
		
		JScrollPane scroll = new JScrollPane(textcorreo);    
        scroll.setBounds(10, 95, 914, 405);                                                   
        getContentPane().add(scroll);                   
        getContentPane().show(true); 
		
		
		/* BOTON PARA ELIMINAR EL CORREO */
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(835, 526, 89, 23);
		getContentPane().add(btnEliminar);
		
		
		
		
		
	}
}