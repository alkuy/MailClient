package Grafica;



import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import logica.Correo;
import logica.FachadaLog;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MuestraRecibido extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String destinatario = new String(); // variable estatica 
	public static String asunto = new String();  
	public static String textoanterior = new String();  
	public static int id;
	private principal principal;
	private FrmMuestraBandejaEntrada recibido;
	private FrmResponder responder;
	FachadaLog FL = new FachadaLog();
	Verificaciones verifica = new Verificaciones();
	
	
	
	
	/**
	 * Create the frame.
	 */
	public MuestraRecibido() {
		
		
		Correo correo = FL.DevuelveRecibido(String.valueOf(recibido.fecha)); // busco el correo seleccionado segun la fecha que traigo del otro formulario or ser una variable static
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
		labeldestino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				labeldestino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object [] opciones ={"Añadir a contactos","Cancelar"};
				int eleccion = JOptionPane.showOptionDialog(rootPane,FachadaLog.getInstancia().getNomApeUsuario(recibido.cuenta)+"\n"+recibido.cuenta,"Añadir contacto a su agenda",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
				if (eleccion == JOptionPane.YES_OPTION){
					String cuentaNom = recibido.cuenta.substring(0, recibido.cuenta.indexOf("@"));
			    	String cuentaDom = recibido.cuenta.substring(recibido.cuenta.indexOf("@")+1);
					if(!FachadaLog.getInstancia().existeCuentaAgenda(cuentaNom, cuentaDom))
						FachadaLog.getInstancia().altaNuevoUsu_Agenda(FachadaLog.getInstancia().getNomApeUsuario(recibido.cuenta), recibido.cuenta);
					else{
						JOptionPane.showMessageDialog(null, "Cuenta existente en su agenda");
					}
				}
			}
		});
		
		//Se modifica la fuente del label para simular un link
		labeldestino.setForeground(Color.BLUE);
		labeldestino.setBounds(245, 32, 567, 16);
		Font auxFont = labeldestino.getFont();
		Map auxAtributos = auxFont.getAttributes();
		auxAtributos.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		labeldestino.setFont(auxFont.deriveFont(auxAtributos));
		getContentPane().add(labeldestino);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setFont(new Font("Verdana", Font.BOLD, 13));
		lblAsunto.setBounds(155, 68, 70, 14);
		getContentPane().add(lblAsunto);
		
		JLabel labelasunto = new JLabel(correo.getAsunto());
		labelasunto.setBounds(245, 70, 679, 14);
		getContentPane().add(labelasunto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 95, 914, 405);
		getContentPane().add(scrollPane);
		
				
		JTextArea textcorreo = new JTextArea();
		scrollPane.setViewportView(textcorreo);
		textcorreo.setEditable(false);
		textcorreo.setLineWrap(true);
		String texto = FL.encriptaOdesencripta(correo.getTexto(), principal.clave); // desencripto por Xor
		texto = FL.Permutar(texto,principal.claveper, principal.clave);//desecncripta por permutacion con clave		
		textcorreo.setText(texto);
		scrollPane.setViewportView(textcorreo);
		
		
		
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
				Object [] opciones ={"ELIMINAR","Cancelar"};
				int eleccion = JOptionPane.showOptionDialog(rootPane,"Seguro desea eliminar este correo","Atencion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
				if (eleccion == JOptionPane.YES_OPTION){					
					
					FL.EliminarCorreo(FL.Devuelve_Ruta_Recibidos(), recibido.fecha, FL.Devuelve_Ruta_Papelera());
					dispose();	
				}
			}
		});
		btnEliminar.setBounds(810, 547, 115, 23);
		getContentPane().add(btnEliminar);
		
		
		/* BOTON PARA RESPONDER EL CORREO */
		
		destinatario = correo.getEmisor_nombre()+"@"+correo.getEmisor_dominio();
		asunto = correo.getAsunto();
		textoanterior = correo.getTexto();
		id = correo.getId_conversacion();
		
		JButton btnResponder = new JButton("RESPONDER");
		btnResponder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				responder = new FrmResponder();
				responder.setLocationRelativeTo(null);
				responder.setVisible(true);
			    dispose();	
				
			}
		});
		btnResponder.setBounds(810, 523, 115, 23);
		getContentPane().add(btnResponder);
		
		
		
		
		
		
		
	}
}
