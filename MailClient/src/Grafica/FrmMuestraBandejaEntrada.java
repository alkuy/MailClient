package Grafica;



import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import java.sql.SQLException;
import java.awt.BorderLayout;
import logica.FachadaLog;

public class FrmMuestraBandejaEntrada extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblMuestraCorreos;
	private JScrollPane scrlMCMostrarCorreos;
	private JTextField txtBuscar;
	public static String fecha = new String(); // variable estatica para poder utilizar en otras clases como la clave del hash
	public static String cuenta = new String(); // la usamos para no tener que traerla desde el diccionari correos y juntar los campos usuario y dominio	 
	public MuestraRecibido muestra;
	FachadaLog FL = new FachadaLog();
	
	public FrmMuestraBandejaEntrada() {
		
		
		SetTableRecibidos();
		setTitle("Bandeja de entrada");
	 	//setFrameIcon(new ImageIcon(FrmMuestraBandejaEntrada.class.getResource("/Imagenes/icon1.jpg")));
	 	setBorder(new EmptyBorder(0, 0, 0, 0));
      	setBounds(92, 103, 813, 424);
      	getContentPane().setLayout(null);
      	
      	txtBuscar = new JTextField();
      	txtBuscar.setToolTipText("Ingrese direccion de correo a buscar");
      	txtBuscar.setBounds(46, 11, 762, 20);
      	getContentPane().add(txtBuscar);
      	txtBuscar.setColumns(10);
      	
      	scrlMCMostrarCorreos = new JScrollPane(tblMuestraCorreos);
      	scrlMCMostrarCorreos.setEnabled(false);
      	scrlMCMostrarCorreos.setSize(793, 301);
      	scrlMCMostrarCorreos.setLocation(10, 57);
		//Agregamos el JScrollPane al contenedor
		getContentPane().add(scrlMCMostrarCorreos, BorderLayout.CENTER);
      	
/* BOTON PARA ACCEDER AL CORREO SELECCIONADO */
		
		JButton btnVer = new JButton("VER");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int pos = tblMuestraCorreos.getSelectedRow();
				if (pos == -1){
					JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar primero el correo que desea visualizar");
				}else{
				fecha = (String) tblMuestraCorreos.getValueAt(pos, 2);
				cuenta = (String) tblMuestraCorreos.getValueAt(pos, 0);
				muestra = new MuestraRecibido();
				muestra.setLocationRelativeTo(null);
				muestra.setVisible(true);
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
					Object [] opciones ={"ELIMINAR","Cancelar"};
					int eleccion = JOptionPane.showOptionDialog(rootPane,"Seguro desea eliminar este correo","Atencion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
					if (eleccion == JOptionPane.YES_OPTION){
						fecha = (String) tblMuestraCorreos.getValueAt(pos, 2);
						String correoSpam = tblMuestraCorreos.getValueAt(pos, 0).toString();
						FL.EliminarCorreo(FL.Devuelve_Ruta_Recibidos(), fecha, FL.Devuelve_Ruta_Papelera());
						
						/*Quito todo para tener un refresh all instante*/
						getContentPane().remove(scrlMCMostrarCorreos);
						getContentPane().revalidate();
						getContentPane().repaint();
						
						SetTableRecibidos();
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
      	
      	/* Boton para realizar la busueda por direccion de correo */
      	
      	JButton btnBuscar = new JButton();
      	btnBuscar.setToolTipText("buscar");
      	btnBuscar.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent arg0) {
      			
      						
      		if(txtBuscar.getText().isEmpty()){ // si no puso nada en la barra de busqueda mustro todos los correos	
      		
      			getContentPane().remove(scrlMCMostrarCorreos);
				SetTableRecibidos();
				scrlMCMostrarCorreos = new JScrollPane(tblMuestraCorreos);
		      	scrlMCMostrarCorreos.setEnabled(false);
		      	scrlMCMostrarCorreos.setSize(793, 301);
		      	scrlMCMostrarCorreos.setLocation(10, 57);
				//Agregamos el JScrollPane al contenedor
				getContentPane().add(scrlMCMostrarCorreos, BorderLayout.CENTER);	
      			
      		}else{	
      			
      			
			SetTableBusqueda(txtBuscar.getText()); // cargo el set con los correos que coinciden con la cuenta ingresada
			
			
			
			if (tblMuestraCorreos.getRowCount() == 0){ // si no hay coincidencia
				/*Quito todo para tener un refresh al instante*/
				//getContentPane().remove(scrlMCMostrarCorreos);
				JOptionPane.showMessageDialog(new JPanel(), "El correo/s asocido/s a la cuenta ingresada no existe");
			}else{
			
			   getContentPane().remove(scrlMCMostrarCorreos);
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
      	btnBuscar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      	btnBuscar.setBounds(10, 11, 26, 20);
      	ImageIcon imagenbuscar = new ImageIcon(principal.class.getResource("/imagenes/lupa.jpg"));
		Icon iconobuscar = new ImageIcon(imagenbuscar.getImage().getScaledInstance(btnBuscar.getWidth(),btnBuscar.getHeight(),Image.SCALE_DEFAULT));
		btnBuscar.setIcon(iconobuscar);
      	getContentPane().add(btnBuscar);
      	
      	/* Boton para enviar a lista de spam un correo*/
      	
      	JButton btnSpam = new JButton("SPAM");
      	btnSpam.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent arg0) {
      			int pos = tblMuestraCorreos.getSelectedRow();
				if (pos == -1){
					JOptionPane.showMessageDialog(new JPanel(), "Debe elegir el correo que quiere marcar como Spam");
				}else{
					Object [] opciones ={"Marcar SPAM","Cancelar"};
					int eleccion = JOptionPane.showOptionDialog(rootPane,"Seguro desea marcar como Spam","Atencion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
					if (eleccion == JOptionPane.YES_OPTION){
						fecha = (String) tblMuestraCorreos.getValueAt(pos, 2);
						String correoSpam = tblMuestraCorreos.getValueAt(pos, 0).toString();
						FL.EliminarCorreo(FL.Devuelve_Ruta_Recibidos(), fecha, FL.Devuelve_Ruta_Spam()); // Cambiar por  Sapm
						FL.CargaListaSpam(correoSpam);
						
						/*Quito todo para tener un refresh all instante*/
						getContentPane().remove(scrlMCMostrarCorreos);
						getContentPane().revalidate();
						getContentPane().repaint();
						
						SetTableRecibidos();
						JScrollPane scrlMCMostrarCorreos = new JScrollPane(tblMuestraCorreos);
				      	scrlMCMostrarCorreos.setEnabled(false);
				      	scrlMCMostrarCorreos.setSize(793, 301);
				      	scrlMCMostrarCorreos.setLocation(10, 57);
						//Agregamos el JScrollPane al contenedor
						getContentPane().add(scrlMCMostrarCorreos, BorderLayout.CENTER);
						
						
					}
				}
			}
		});
      	btnSpam.setBounds(109, 363, 89, 23);
      	getContentPane().add(btnSpam);
		
	}
	
	
	public void SetTableRecibidos(){
		String col[] = {"Remitente","Asunto","Clave", "Fecha"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		try{
			modelo = FL.DevRecibidos();
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
		
			
	}
	
	public void SetTableBusqueda(String cuentaBusqueda){
		String col[] = {"Remitente","Asunto", "Fecha"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		try{
			modelo = FL.DevBusqueda(cuentaBusqueda);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tblMuestraCorreos = new JTable(modelo);
		tblMuestraCorreos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
	}
}
