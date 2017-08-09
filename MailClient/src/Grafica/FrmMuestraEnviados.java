package Grafica;



import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import logica.FachadaLog;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;


public class FrmMuestraEnviados extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtBuscar;
	private JScrollPane scrlMCMostrarCorreos;
	private JTable tblMuestraCorreos;
	public static String fecha = new String(); // variable estatica para poder mantener utilizar en otras clases como la clave del hash
	public static String cuenta = new String(); // la usamos para no tener que traerla desde el diccionari correos y juntar los campos usuario y dominio
	public static String aprete = new String(); // la usamos para no tener que traerla desde el diccionari correos y juntar los campos usuario y dominio
	public MuestraCorreo mostrar;
	FachadaLog FL = new FachadaLog();
	
	 
	public FrmMuestraEnviados() {
		
		SetTable();
		aprete = "no";
		setTitle("Enviados");
	 	//setFrameIcon(new ImageIcon(FrmMuestraBandejaEntrada.class.getResource("/Imagenes/icon1.jpg")));
	 	setBorder(new EmptyBorder(0, 0, 0, 0));
      	setBounds(92, 103, 813, 424);
      	getContentPane().setLayout(null);
      	
      	txtBuscar = new JTextField();
      	txtBuscar.setText("buscar...");
      	txtBuscar.setBounds(46, 11, 762, 20);
      	getContentPane().add(txtBuscar);
      	txtBuscar.setColumns(10);
      	
      	scrlMCMostrarCorreos = new JScrollPane(tblMuestraCorreos);
      	scrlMCMostrarCorreos.setEnabled(false);
      	scrlMCMostrarCorreos.setSize(793, 301);
      	scrlMCMostrarCorreos.setLocation(10, 57);
		//Agregamos el JScrollPane al contenedor
		getContentPane().add(scrlMCMostrarCorreos, BorderLayout.CENTER);
      	
      	/* Boton para realizar la busueda por direccion de correo */
      	
      	JButton btnBuscar = new JButton();
      	btnBuscar.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent arg0) {
      		}
      	});
      	btnBuscar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      	btnBuscar.setBounds(10, 11, 26, 20);
      	ImageIcon imagenbuscar = new ImageIcon(principal.class.getResource("/imagenes/lupa.jpg"));
		Icon iconobuscar = new ImageIcon(imagenbuscar.getImage().getScaledInstance(btnBuscar.getWidth(),btnBuscar.getHeight(),Image.SCALE_DEFAULT));
		btnBuscar.setIcon(iconobuscar);
      	getContentPane().add(btnBuscar);
		
	
	
	/* BOTON PARA ACCEDER AL CORREO SELECCIONADO */
	
	JButton btnVer = new JButton("VER");
	btnVer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			aprete = "si"; // variable que utilizaremos globalmente para saber de que formulario se apreto acceder a Muestra correo
			int pos = tblMuestraCorreos.getSelectedRow();
			if (pos == -1){
				JOptionPane.showMessageDialog(new JPanel(), "Debe elegir el correo que quiere editar");
			}else{
			fecha = (String) tblMuestraCorreos.getValueAt(pos, 2);
			cuenta = (String) tblMuestraCorreos.getValueAt(pos, 0);
			mostrar = new MuestraCorreo();
			mostrar.setLocationRelativeTo(null);
			mostrar.setVisible(true);
			principal.apareceLogo();
		    dispose();				

			}	
		}
	});
	btnVer.setBounds(714, 363, 89, 23);
	getContentPane().add(btnVer);
	
	
	/* BOTON PARA ELIMINAR EL CORREO SELECCIONADO*/
	
	JButton btnEliminar = new JButton("ELIMINAR");
	btnEliminar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			int pos = tblMuestraCorreos.getSelectedRow();
			if (pos == -1){
				JOptionPane.showMessageDialog(new JPanel(), "Debe elegir el correo que quiere eliminar");
			}else{
				Object [] opciones ={"Eliminar","Cancelar"};
				int eleccion = JOptionPane.showOptionDialog(rootPane,"Seguro desea eliminar este correo","Atencion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
				if (eleccion == JOptionPane.YES_OPTION){
					fecha = (String) tblMuestraCorreos.getValueAt(pos, 2);
					FL.EliminarCorreoDefinitivo(FL.Devuelve_Ruta_Enviados(), fecha);
					
					/*Quito todo para tener un refresh all instante*/
					getContentPane().remove(scrlMCMostrarCorreos);
					getContentPane().revalidate();
					getContentPane().repaint();
					
					SetTable();
					scrlMCMostrarCorreos = new JScrollPane(tblMuestraCorreos);
			      	scrlMCMostrarCorreos.setEnabled(false);
			      	scrlMCMostrarCorreos.setSize(793, 301);
			      	scrlMCMostrarCorreos.setLocation(10, 57);
					//Agregamos el JScrollPane al contenedor
					getContentPane().add(scrlMCMostrarCorreos, BorderLayout.CENTER);
					
					
				}
			}
		}
	});
	btnEliminar.setBounds(10, 363, 89, 23);
	getContentPane().add(btnEliminar);
  
	
}
	
	public void SetTable(){
		String col[] = {"Destinatario","Asunto", "Clave","Fecha"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		try{
			modelo = FL.DevCorreosEnviados();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tblMuestraCorreos = new JTable(modelo);
		JTableHeader header = tblMuestraCorreos.getTableHeader();
		 header.setOpaque(false);
		 header.setBackground(Color.DARK_GRAY);
	     header.setForeground(Color.white);
	     header.setFont(new Font("Consolas", Font.BOLD, 13));
	    
	    tblMuestraCorreos.setShowVerticalLines(false);
	    tblMuestraCorreos.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		tblMuestraCorreos.setRowHeight(35);
		
		
		tblMuestraCorreos.getColumnModel().getColumn(2).setMinWidth(0);
		tblMuestraCorreos.getColumnModel().getColumn(2).setMaxWidth(0);
		tblMuestraCorreos.getColumnModel().getColumn(2).setWidth(0);
		tblMuestraCorreos.getColumnModel().getColumn(0).setMinWidth(250);
		tblMuestraCorreos.getColumnModel().getColumn(0).setMaxWidth(250);
		tblMuestraCorreos.getColumnModel().getColumn(0).setWidth(250);
		tblMuestraCorreos.getColumnModel().getColumn(3).setMinWidth(150);
		tblMuestraCorreos.getColumnModel().getColumn(3).setMaxWidth(150);
		tblMuestraCorreos.getColumnModel().getColumn(3).setWidth(150);


		tblMuestraCorreos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*Oculto columnas con Timestamp usada como clave del diccionario */
	/*	TableColumn myTableColumn1 = tblMuestraCorreos.getColumnModel().getColumn(2);
		tblMuestraCorreos.getColumnModel().removeColumn(myTableColumn1);
		*/
		
	}
}
