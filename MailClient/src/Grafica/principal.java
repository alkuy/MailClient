package Grafica;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import logica.FachadaLog;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Insets;
import java.awt.Panel;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;

public class principal extends JFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private static JPanel VentPrincipal;
	private static JLabel ImagenLogo = null;
	private static JMenuBar menuBar;
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
	private FrmMuestraEliminados frmeliminados;
	
	
	public static JPanel getInstancia() {
		if(VentPrincipal == null)
			VentPrincipal = new JPanel();
		
		return VentPrincipal;
	}
	
	

	/**
	 * Create the frame.
	 */
	public principal() {
		
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
		VentPrincipal.setBackground(new Color(176, 224, 230));
		VentPrincipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(VentPrincipal);
		VentPrincipal.setLayout(null);
		
		/*������������������������������*/
		/*BOTONES MENU PRINCIPAL*/
		/*������������������������������*/
		
				
		/* Boton Enviar / Recibir */
		JButton btnEnv_Rec = new JButton();
		btnEnv_Rec.setBounds(0, 0, 52, 47);
		
		
		ImageIcon imagen = new ImageIcon(principal.class.getResource("/imagenes/enviar-recibir.png"));		
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(btnEnv_Rec.getWidth(),btnEnv_Rec.getHeight(),Image.SCALE_DEFAULT));		
		btnEnv_Rec.setIcon(icono);
		btnEnv_Rec.setToolTipText("Enviar/Recibir");		
		VentPrincipal.add(btnEnv_Rec);
		
		btnEnv_Rec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		/* Boton nuevo correo */
		
		btnNuevoCorreo = new JButton();
		btnNuevoCorreo.setBounds(51, 0, 52, 47);		
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
			}
		});
		
		
		/*������������������������������*/
		/*BOTONES BARRA LATERAL*/
		/*������������������������������*/
		
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
				frmbandejaentrada = new FrmMuestraBandejaEntrada();
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				abreVentana(frmbandejaentrada);
				
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
				frmborradores = new FrmMuestraBorradores();
				cierraVentana(frmenviados);
				cierraVentana(frmbandejaentrada);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				abreVentana(frmborradores);
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
				frmenviados = new FrmMuestraEnviados();
				cierraVentana(frmbandejaentrada);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				abreVentana(frmenviados);
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
				frmbuzon = new FrmMuestraBuzonSalida();
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbandejaentrada);
				cierraVentana(frmspam);
				cierraVentana(frmeliminados);
				abreVentana(frmbuzon);
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
				frmspam = new FrmMuestraSpam();
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmbandejaentrada);
				cierraVentana(frmeliminados);
				abreVentana(frmspam);
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
				frmeliminados = new FrmMuestraEliminados();
				cierraVentana(frmenviados);
				cierraVentana(frmborradores);
				cierraVentana(frmbuzon);
				cierraVentana(frmspam);
				cierraVentana(frmbandejaentrada);
				abreVentana(frmeliminados);
			}
		});
		
		

		/*������������������������������*/
		/*Logo y letras de fondo*/
		/*������������������������������*/	
	
		
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