package Grafica;



import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import logica.Correo;
import logica.FachadaLog;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ContinuarCorreoBorrador extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrmMuestraBorradores muestra;
	FachadaLog FL = new FachadaLog();
	Verificaciones verifica = new Verificaciones();
	
	
	
	
	/**
	 *  Create the frame.
	 */
	public ContinuarCorreoBorrador() {
		
		
		Correo correo = FL.DevuelveBorrador(String.valueOf(muestra.fecha));
						
	    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		 
	    addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override // sobrescribimos el metodo para preguntar antes de cerrar y que no cierre automticamente
	            public void windowClosing(java.awt.event.WindowEvent evt) {
	                cerrar();
	            }
	        });
		
		getContentPane().setBackground(SystemColor.activeCaption);
		
		setTitle("Nuevo Correo");
     	setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/imagenes/icon1.jpg")));
		setBounds(100, 100, 970, 620);
				
		getContentPane().setLayout(null);
		
		JFormattedTextField textpara = new JFormattedTextField();
	    textpara.setBounds(245, 27, 679, 25);
	    getContentPane().add(textpara);
		textpara.setText(muestra.cuenta);
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setFont(new Font("Verdana", Font.BOLD, 13));
		lblPara.setBounds(167, 32, 46, 14);
		getContentPane().add(lblPara);
		
		JFormattedTextField textasunto = new JFormattedTextField();
		textasunto.setBounds(245, 63, 679, 25);
		getContentPane().add(textasunto);
		textasunto.setText(correo.getAsunto());
	
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setFont(new Font("Verdana", Font.BOLD, 13));
		lblAsunto.setBounds(167, 70, 70, 14);
		getContentPane().add(lblAsunto);
		
		JTextArea textcorreo = new JTextArea();
		textcorreo.setBounds(10, 95, 914, 405);
		textcorreo.setLineWrap(true);
		getContentPane().add(textcorreo);
		textcorreo.setText(correo.getTexto());
		
		
		/* Logo en Nuevo correo*/
		
		JLabel logo = new JLabel();
		logo.setBounds(10, 0, 115, 94);
        ImageIcon imagen = new ImageIcon(principal.class.getResource("/imagenes/logo-mail.png"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(logo.getWidth(),logo.getHeight(),Image.SCALE_DEFAULT));
		logo.setIcon(icono);
		getContentPane().add(logo);
		
		/* boton Enviar correo */
		
		JButton btnEnviar = new JButton();
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (verifica.verificaCuentaReceptor(textpara.getText())){// aca verificamos si puso un y solo un @ en la cuenta de envio
				Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Obtengo el tiempo exacto creacion de correo
				
				String fecha = timestamp.toString();
				fecha = fecha.replace(".","-"); //Para evitar problemas en el nombre del archivo
				fecha = fecha.replace(" ","-");	//Que no haya espacios e unifique todo al una barra
				fecha = fecha.replace(":","-");
				String texto = textcorreo.getText();
				texto = verifica.tildes(texto);
				String asunto = verifica.remplazoCaracteres(textasunto.getText());
				texto = FL.Permutar(texto, principal.clave, principal.claveper);// encripta el texto del correo por permutacion con clave
				texto = FL.encriptaOdesencripta(texto,principal.clave);// toma el texto encriptado por permutacion t lo encripta por Xor
				texto = verifica.remplazoCaracteres(texto);// para remplazar si pone comillas por comillas simples para no tener problemas con el GBD en el servidor
				
				String cuenta = textpara.getText();
				int index = cuenta.indexOf("@");
				String nom_receptor = cuenta.substring(0,index);//Para separar de la cuenta que ingreso el nombre de usuario del dominio
				String dom_receptor = cuenta.substring(index+1,cuenta.length());				
				
				/*Guardamos correo en archivo y memoria*/
				FL.Guarda_Correo(FL.Devuelve_Ruta_BuzonSalida(),0,asunto,FL.Devuelve_us_cuenta(), FL.Devuelve_dom_cuenta(),nom_receptor, dom_receptor, texto, fecha);
				dispose();
				principal.apareceLogo();
				}
			}
		});
		btnEnviar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnEnviar.setBounds(861, 511, 63, 39);
        ImageIcon imagenenviar = new ImageIcon(principal.class.getResource("/imagenes/benviar.png"));
		Icon iconoenviar = new ImageIcon(imagenenviar.getImage().getScaledInstance(btnEnviar.getWidth(),btnEnviar.getHeight(),Image.SCALE_DEFAULT));
		btnEnviar.setIcon(iconoenviar);
		btnEnviar.setToolTipText("Enviar");
		getContentPane().add(btnEnviar);
		
		
		/* Boton guardar correo */
		
		JButton btnguardar = new JButton();
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if (verifica.verificaCuentaReceptor(textpara.getText())){// aca verificamos si puso un y solo un @ en la cuenta de envio	
            Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Obtengo el tiempo exacto creacion de correo
				
			String fecha = timestamp.toString();
			fecha = fecha.replace(".","-"); //Para evitar problemas en el nombre del archivo
			fecha = fecha.replace(" ","-");	//Que no haya espacios e unifique todo al una barra
			fecha = fecha.replace(":","-");	
			  	
			String cuenta = textpara.getText();
			int index = cuenta.indexOf("@");
			String nom_receptor = cuenta.substring(0,index);//Para separar de la cuenta que ingreso el nombre de usuario del dominio
			String dom_receptor = cuenta.substring(index+1,cuenta.length());
			String texto = verifica.remplazoCaracteres(textcorreo.getText());// para remplazar si pone comillas por comillas simples para no tener problemas con el GBD en el servidor
			String asunto = verifica.remplazoCaracteres(textasunto.getText());
				
			  FL.Guarda_Correo(FL.Devuelve_Ruta_Borradores(),0,asunto,FL.Devuelve_us_cuenta(), FL.Devuelve_dom_cuenta(),nom_receptor, dom_receptor, texto, fecha);
	          guardado();
			}
			
		  }
		});
		btnguardar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnguardar.setBounds(10, 511, 63, 39);
		ImageIcon imagenguardar = new ImageIcon(principal.class.getResource("/imagenes/guardar.png"));
		Icon iconoguardar = new ImageIcon(imagenguardar.getImage().getScaledInstance(btnguardar.getWidth(),btnguardar.getHeight(),Image.SCALE_DEFAULT));
		btnguardar.setIcon(iconoguardar);
		btnEnviar.setToolTipText("Guardar en borradores");
		getContentPane().add(btnguardar);
	}
		
		/** Metodo para preguntar antes de cerrar
		 * 
		 */
		
		public void cerrar(){
			Object [] opciones ={"Cerrar","Cancelar"};
			int eleccion = JOptionPane.showOptionDialog(rootPane,"Al cerrar se perderan los cambios no guardados","Atencion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
			if (eleccion == JOptionPane.YES_OPTION){
			dispose();
			principal.apareceLogo();
			}else{}
						
		}
		
        /** Metodo de aviso luego de guardar un correo
         * 
         */
		
		public void guardado(){
			Object [] opciones ={"Continuar","Salir"};
			int eleccion = JOptionPane.showOptionDialog(rootPane,"�Desea continuar editando el correo?","Se guardo en la carpeta Borradores",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
			if (eleccion == JOptionPane.NO_OPTION){
			 dispose();	
			}else{}
						
		}
}
