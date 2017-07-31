package Grafica;



import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
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
import java.awt.Image;
import java.awt.event.ActionEvent;


public class FrmMuestraEnviados extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtBuscar;
	private JTable tblMuestraCorreos;

	FachadaLog FL = new FachadaLog();
	 
	public FrmMuestraEnviados() {
		
		SetTable();
		
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
      	
      	JScrollPane scrlMCMostrarCorreos = new JScrollPane(tblMuestraCorreos);
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
		
	}
	
	public void SetTable(){
		String col[] = {"Destinatario","Asunto", "Fecha"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		try{
			modelo = FL.DevCorreosEnviados();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tblMuestraCorreos = new JTable(modelo);
		tblMuestraCorreos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*Oculto columnas con Timestamp usada como clave del diccionario */
		TableColumn myTableColumn1 = tblMuestraCorreos.getColumnModel().getColumn(2);
		tblMuestraCorreos.getColumnModel().removeColumn(myTableColumn1);
		
		
	}
}
