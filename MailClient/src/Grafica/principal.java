package Grafica;

import logica.FachadaLog;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;


import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;


import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;

import javax.swing.ImageIcon;

import javax.mail.MessagingException;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.icegreen.greenmail.user.UserException;

import Conectividad.FachadaCon;

import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import java.awt.ComponentOrientation;
import java.awt.List;
import javax.swing.DefaultComboBoxModel;


public class principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private static JPanel VentPrincipal;
	private static JLabel ImagenLogo = null;
	private static JLabel lblCorreoPintituciones;
	private static JLabel lblVersion;
	private static JLabel lblCreadoEnInet;
	private JButton btnNuevoCorreo;
	private JButton btnRecibidos;
	private JButton btnBorrador;
	private JButton btnEnviados;
	private JButton btnsalida;
	private JButton btnspam;
	private JButton btnpapelera;
	private FrmMuestraBandejaEntrada frmbandejaentrada;
	private FrmMuestraEnviados frmenviados;
	private FrmMuestraBorradores frmborradores;
	private FrmMuestraBuzonSalida frmbuzon;
	private FrmMuestraSpam frmspam;
	private FrmConfiguracion config;
	private FrmMuestraEliminados frmeliminados;
	public static String clave = new String(); // variable static global utilizada como clave de encriptacion y desencriptacion
	private JComboBox comboBox;
	
	
	public static JPanel getInstancia() {
		if(VentPrincipal == null)
			VentPrincipal = new JPanel();
		
		return VentPrincipal;
	}
	
	

	/**
	 * Create the frame.
	 */
	public principal() {
		
		clave = "correoeduca"; // asignamos el valor de la clave ara encriptar y desencriptar
		FachadaCon FC = FachadaCon.getInstancia();
		FachadaLog FL = new FachadaLog();  		
  		FL.cargaTodoenMemoria();
  		
     	setResizable(false);
		/**
		 * Panel Principal*/
     	setTitle("Gestor de Correos - EduMail");
     	setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/imagenes/icon1.jpg")));
     	setBackground(new Color(0, 128, 128));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		VentPrincipal = new JPanel();
		VentPrincipal.setToolTipText("Configuraci\u00F3n");
		VentPrincipal.setBackground(new Color(176, 224, 230));
		VentPrincipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(VentPrincipal);
		VentPrincipal.setLayout(null);
		
		/*······························*/
		/*BOTONES MENU PRINCIPAL*/
		/*······························*/
		
				
		/* Boton Enviar - Recibir */
		JButton btnEnv_Rec = new JButton();
		btnEnv_Rec.setBounds(0, 0, 52, 47);		
		ImageIcon imagen = new ImageIcon(principal.class.getResource("/imagenes/enviar-recibir.png"));		
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(btnEnv_Rec.getWidth(),btnEnv_Rec.getHeight(),Image.SCALE_DEFAULT));
		btnEnv_Rec.setIcon(icono);
		btnEnv_Rec.setToolTipText("Enviar/Recibir");		
		VentPrincipal.add(btnEnv_Rec);
		
		btnEnv_Rec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				cierraVentana(frmbandejaentrada);
				cierraVentana(config);
				int i, j;
				String col[] = {"NomEmisor","PassEmisor", "CuentaEmisor", "CuentaDest", "Asunto", "Texto"};
				DefaultTableModel modelo = new DefaultTableModel(col,0);
				modelo = FL.DevCorreosSalida();
				JTable T = new JTable(modelo);
				for (i=0; i < T.getRowCount(); i++){
					try {
						FC.EnviaCorreo(T.getValueAt(i, 0).toString(), T.getValueAt(i, 1).toString(), T.getValueAt(i, 2).toString(), T.getValueAt(i, 3).toString(), T.getValueAt(i, 4).toString(), T.getValueAt(i, 5).toString());
						FL.CorreoEnviado(FL.Devuelve_Ruta_BuzonSalida(), T.getValueAt(i, 6).toString(), FL.Devuelve_Ruta_Enviados());
					} catch (IOException | MessagingException | UserException | InterruptedException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}
				}
				FC.RecibeCorreo();

			}
		});
		
		
		/* Boton nuevo correo */
		
		btnNuevoCorreo = new JButton();
		btnNuevoCorreo.setBounds(50, 0, 52, 47);		
        ImageIcon imagen2 = new ImageIcon(principal.class.getResource("/imagenes/nuevo.gif"));		
		Icon icono2 = new ImageIcon(imagen2.getImage().getScaledInstance(btnNuevoCorreo.getWidth(),btnNuevoCorreo.getHeight(),Image.SCALE_DEFAULT));		
		btnNuevoCorreo.setIcon(icono2);
		btnNuevoCorreo.setToolTipText("Nuevo Correo");		
		VentPrincipal.add(btnNuevoCorreo);		
		btnNuevoCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Nuevo_Correo correo = new Nuevo_Correo();
				correo.setLocationRelativeTo(null);
				correo.setVisible(true);
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				cierraVentana(frmbandejaentrada);
				cierraVentana(config);
			}
		});
		ImageIcon imagenrespaldo = new ImageIcon(principal.class.getResource("/imagenes/reloj.png"));
		
		
		
		
		/*······························*/
		/*BOTONES BARRA LATERAL*/
		/*······························*/
		
		// Boton para desplegar la bandeja de entrada
		
		btnRecibidos = new JButton();
		
		btnRecibidos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnRecibidos.setBounds(0, 103, 52, 47);		
        ImageIcon imagenbuzon = new ImageIcon(principal.class.getResource("/imagenes/buzon-de-correo.jpg"));		
		Icon iconobuzon = new ImageIcon(imagenbuzon.getImage().getScaledInstance(btnRecibidos.getWidth(),btnRecibidos.getHeight(),Image.SCALE_DEFAULT));		
		btnRecibidos.setIcon(iconobuzon);
		btnRecibidos.setToolTipText("Bandeja de Entrada");		
		VentPrincipal.add(btnRecibidos);
		
		btnRecibidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desapareceLogo();
				cierraVentana(frmbandejaentrada); // por si se cliquea dos veces seguidas o para refrescar
				frmbandejaentrada = new FrmMuestraBandejaEntrada();				
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				abreVentana(frmbandejaentrada);
				cierraVentana(config);
				
				}
			
		});
		
		
		//boton para desplegar los borradores
		
		btnBorrador = new JButton();
		btnBorrador.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnBorrador.setBounds(0, 151, 52, 47);		
        ImageIcon imagenborrador = new ImageIcon(principal.class.getResource("/imagenes/borrador.png"));		
		Icon iconoborrador = new ImageIcon(imagenborrador.getImage().getScaledInstance(btnBorrador.getWidth(),btnBorrador.getHeight(),Image.SCALE_DEFAULT));		
		btnBorrador.setIcon(iconoborrador);
		btnBorrador.setToolTipText("Borradores");		
		VentPrincipal.add(btnBorrador);
		
		btnBorrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desapareceLogo();
				cierraVentana(frmborradores);
				frmborradores = new FrmMuestraBorradores();				
				cierraVentana(frmenviados);
				cierraVentana(frmbandejaentrada);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				abreVentana(frmborradores);
				cierraVentana(config);
				
			}
		});
		
		
		// boton para desplegar lista de correos enviados
		
		btnEnviados = new JButton();
		btnEnviados.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnEnviados.setBounds(0, 199, 52, 47);		
		ImageIcon imagenenviados = new ImageIcon(principal.class.getResource("/imagenes/enviados.png"));			
		Icon iconoenviados= new ImageIcon(imagenenviados.getImage().getScaledInstance(btnEnviados.getWidth(),btnEnviados.getHeight(),Image.SCALE_DEFAULT));			
		btnEnviados.setIcon(iconoenviados);
		btnEnviados.setToolTipText("Enviados");				
		VentPrincipal.add(btnEnviados);
		
		btnEnviados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				desapareceLogo();	
				cierraVentana(frmenviados);
				frmenviados = new FrmMuestraEnviados();
				cierraVentana(frmbandejaentrada);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				abreVentana(frmenviados);
				cierraVentana(config);
				
			}
		});
		
		// botno para desplegar lista de correos en buzon de salida
		
				
		btnsalida = new JButton();
		btnsalida.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnsalida.setBounds(0, 248, 52, 47);
		VentPrincipal.add(btnsalida);
		ImageIcon imagensalida = new ImageIcon(principal.class.getResource("/imagenes/bandeja-salida.jpg"));		
		Icon iconsalida= new ImageIcon(imagensalida.getImage().getScaledInstance(btnsalida.getWidth(),btnsalida.getHeight(),Image.SCALE_DEFAULT));			
		btnsalida.setIcon(iconsalida);
		btnsalida.setToolTipText("Buzon de salida");				
		VentPrincipal.add(btnEnviados);
		
		btnsalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desapareceLogo();	
				cierraVentana(frmbuzon);
				frmbuzon = new FrmMuestraBuzonSalida();				
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbandejaentrada);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				abreVentana(frmbuzon);
				cierraVentana(config);
				
			}
		});
		
		
		
		// Boton para desplegar lista de correos SPAM
		
		btnspam = new JButton();
		btnspam.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnspam.setBounds(0, 297, 52, 47);
        ImageIcon imagenspam = new ImageIcon(principal.class.getResource("/imagenes/spam.png"));		
		Icon iconspam= new ImageIcon(imagenspam.getImage().getScaledInstance(btnspam.getWidth(),btnspam.getHeight(),Image.SCALE_DEFAULT));			
		btnspam.setIcon(iconspam);
		btnspam.setToolTipText("Correo no deseado");		
		VentPrincipal.add(btnspam);
		
		btnspam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desapareceLogo();	
				cierraVentana(frmspam);
				frmspam = new FrmMuestraSpam();				
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmbandejaentrada);
				cierraVentana(frmeliminados);
				abreVentana(frmspam);
				cierraVentana(config);
				
			}
		});
		
		// Boton que muestra los correos eliminados
		
		btnpapelera = new JButton();
		btnpapelera.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnpapelera.setBounds(0, 346, 52, 47);
        ImageIcon imagenpapelera = new ImageIcon(principal.class.getResource("/imagenes/papelera.jpg"));		
		Icon iconpapelera= new ImageIcon(imagenpapelera.getImage().getScaledInstance(btnpapelera.getWidth(),btnpapelera.getHeight(),Image.SCALE_DEFAULT));			
		btnpapelera.setIcon(iconpapelera);
		btnpapelera.setToolTipText("Correos eliminados");		
		VentPrincipal.add(btnpapelera);
		
		btnpapelera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desapareceLogo();	
				cierraVentana(frmeliminados);
				frmeliminados = new FrmMuestraEliminados();				
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmbandejaentrada);
				abreVentana(frmeliminados);
				cierraVentana(config);
				
			}
		});
		
		

		/*······························*/
		/*Logo y letras de fondo*/
		/*······························*/	
	
		
		lblCorreoPintituciones = new JLabel("Correo Para Instituciones Educativas");
		lblCorreoPintituciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreoPintituciones.setFont(new Font("Goudy Old Style", Font.PLAIN, 13));
		lblCorreoPintituciones.setBounds(333, 365, 250, 30);
		VentPrincipal.add(lblCorreoPintituciones);
		
		lblVersion = new JLabel("Version 1.0");
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersion.setFont(new Font("Goudy Old Style", Font.PLAIN, 13));
		lblVersion.setBounds(333, 396, 250, 30);
		VentPrincipal.add(lblVersion);
		
		lblCreadoEnInet = new JLabel("Creado en INET");
		lblCreadoEnInet.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreadoEnInet.setFont(new Font("Goudy Old Style", Font.PLAIN, 13));
		lblCreadoEnInet.setBounds(333, 423, 250, 30);
		VentPrincipal.add(lblCreadoEnInet);
		
		ImagenLogo = new JLabel(new ImageIcon(principal.class.getResource("/imagenes/logo-mail.png")));
		ImagenLogo.setBounds(279, 106, 362, 248);
		VentPrincipal.add(ImagenLogo);
		
		

		
		getLabelTitulo();
		
	}
		private void getLabelTitulo() {
			
				JLabel labelTitulo = new JLabel();
				labelTitulo.setBounds(898, 0, 46, 47);
				ImageIcon imagenconfig = new ImageIcon(principal.class.getResource("/imagenes/config.png"));
				Icon iconoconfig = new ImageIcon(imagenconfig.getImage().getScaledInstance(labelTitulo.getWidth(),labelTitulo.getHeight(),Image.SCALE_DEFAULT));
				labelTitulo.setIcon(iconoconfig);
				labelTitulo.setVisible(true);
			
				labelTitulo.setText("Soy una etiqueta clicable: ");
				labelTitulo.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
					
						
						FrmConfiguracion config = new FrmConfiguracion();
						abreVentana(config);
					
						desapareceLogo();	
						cierraVentana(frmeliminados);
						cierraVentana(frmenviados);
						cierraVentana(frmborradores);
						cierraVentana(frmbuzon);
						cierraVentana(frmspam);
						cierraVentana(frmbandejaentrada);
					
					}
					public void mouseEntered(MouseEvent arg0) {
						
					}
					public void mouseExited(MouseEvent arg0) {
					
					}
					public void mousePressed(MouseEvent arg0) {}
					public void mouseReleased(MouseEvent arg0) {}
				});
			
				VentPrincipal.add(labelTitulo);
		}



		
		
	
	
	/**
	 * Abre las ventanas sin marco
	 * @param panel
	 */
	public static void abreVentana(JInternalFrame panel){
		VentPrincipal.add(panel);
		/*BasicInternalFrameTitlePane titlePane =
			      (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) panel.getUI()).
			      getNorthPane();*/
		//panel.remove(titlePane);
		panel.setVisible(true);
		panel.toFront();
				
	}
	/**
	 * Cierra JinternalFram verificando si fue abierto
	 * @param panel
	 */
	public void cierraVentana(JInternalFrame panel){
		if (panel !=null){
			panel.dispose();
		}
	}
	/**
	 * Invisible el logo y las letras de credito
	 */
	public static void desapareceLogo(){
		ImagenLogo.setVisible(false);
		lblCorreoPintituciones.setVisible(false);
		lblVersion.setVisible(false);
		lblCreadoEnInet.setVisible(false);
	}
	/**
	 * Visible el logo y las letras de credito
	 */
	public static void apareceLogo(){
		ImagenLogo.setVisible(true);
		lblCorreoPintituciones.setVisible(true);
		lblVersion.setVisible(true);
		lblCreadoEnInet.setVisible(true);
	}
}
