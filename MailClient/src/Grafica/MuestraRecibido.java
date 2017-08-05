package Grafica;



import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import logica.Correo;
import logica.FachadaLog;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MuestraRecibido extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrmMuestraBandejaEntrada recibido;
	FachadaLog FL = new FachadaLog();
	Verificaciones verifica = new Verificaciones();
	
	
	
	
	/**
	 * Create the frame.
	 */
	public MuestraRecibido() {
		
		
		Correo correo = FL.DevuelveRecibido(String.valueOf(recibido.fecha)); // busco el correo seleccionado segun la fecha que traigo del otro formulario or ser una variable static
		ArrayList<Correo> conversacion = new ArrayList<Correo>();
		conversacion = FL.DevConveracionRecibidos(correo.getId_conversacion());// cargo aca solo lo correos de esa conversacion
		
		
		
		getContentPane().setBackground(SystemColor.activeCaption);
		setTitle("Correo");
     	setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/imagenes/icon1.jpg")));
		setBounds(100, 100, 970, 620);
		getContentPane().setLayout(null);
		
		JLabel lblPara = new JLabel("Remite:");
		lblPara.setFont(new Font("Verdana", Font.BOLD, 13));
		lblPara.setBounds(155, 34, 87, 14);
		getContentPane().add(lblPara);
		
		
		JLabel labeldestino = new JLabel(recibido.cuenta);
		labeldestino.setBounds(245, 32, 567, 16);
		getContentPane().add(labeldestino);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setFont(new Font("Verdana", Font.BOLD, 13));
		lblAsunto.setBounds(155, 68, 70, 14);
		getContentPane().add(lblAsunto);
		
		JLabel labelasunto = new JLabel(correo.getAsunto());
		labelasunto.setBounds(245, 70, 679, 14);
		getContentPane().add(labelasunto);
		
		JTextArea textcorreo = new JTextArea();
		textcorreo.setEditable(false);
		textcorreo.setBounds(10, 95, 914, 405);
		textcorreo.setLineWrap(true);
		
		Iterator<Correo> iterador = conversacion.iterator();
		correo = iterador.next();
		textcorreo.append(System.getProperty("line.separator")); // Esto para el salto de línea
		textcorreo.append(System.getProperty("line.separator"));
	    textcorreo.append(correo.getTexto());// esto para que la primera vez no me cargue ni remitente ni asunto
	    textcorreo.append(System.getProperty("line.separator")); // Esto para el salto de línea
	    textcorreo.append(System.getProperty("line.separator")); 
	    textcorreo.append(System.getProperty("line.separator"));
	    textcorreo.append("-----------------------------------------------------------------------------------------------------------------");
	    textcorreo.append(System.getProperty("line.separator")); // Esto para el salto de línea
	    textcorreo.append(System.getProperty("line.separator"));
	    textcorreo.append(System.getProperty("line.separator"));
		while(iterador.hasNext()){
			
			correo = iterador.next();
			textcorreo.append("Remitente:"+correo.getEmisor_nombre()+"@"+correo.getEmisor_dominio());
			textcorreo.append(System.getProperty("line.separator")); 
			textcorreo.append("Asunto:"+correo.getAsunto());
			textcorreo.append(System.getProperty("line.separator")); 
			textcorreo.append(System.getProperty("line.separator")); 
		    textcorreo.append(correo.getTexto());
		    textcorreo.append(System.getProperty("line.separator")); 
		    textcorreo.append("-----------------------------------------------------------------------------------------------------------------");
		    textcorreo.append(System.getProperty("line.separator")); 
		    textcorreo.append(System.getProperty("line.separator"));
		    textcorreo.append(System.getProperty("line.separator"));
		}
		
		getContentPane().add(textcorreo);
		//textcorreo.setText(correo.getTexto());
		
		
		/* Logo en Nuevo correo*/
		
		JLabel logo = new JLabel();
		logo.setBounds(10, 0, 115, 94);
        ImageIcon imagen = new ImageIcon(principal.class.getResource("/imagenes/logo-mail.png"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(logo.getWidth(),logo.getHeight(),Image.SCALE_DEFAULT));
		logo.setIcon(icono);
		getContentPane().add(logo);
		
		
		/* BOTON PARA ELIMINAR EL CORREO */
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(810, 547, 115, 23);
		getContentPane().add(btnEliminar);
		
		
		/* BOTON PARA RESPONDER EL CORREO */
		
		JButton btnResponder = new JButton("RESPONDER");
		btnResponder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnResponder.setBounds(810, 523, 115, 23);
		getContentPane().add(btnResponder);
		
		
		
		
		
	}
}
