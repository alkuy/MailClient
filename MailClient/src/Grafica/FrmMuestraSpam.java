package Grafica;




import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.FachadaLog;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;


public class FrmMuestraSpam extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrlMCMostrarCorreos;
	private JTable tblMuestraCorreos;
	public static String fecha = new String(); // variable estatica para poder utilizar en otras clases como la clave del hash
	public static String cuenta = new String(); // la usamos para no tener que traerla desde el diccionari correos y juntar los campos usuario y dominio	 
	FachadaLog FL = new FachadaLog();

	 
	public FrmMuestraSpam() {
		
		
		SetTable();
		setTitle("Lista de Spam");
	 	setBorder(new EmptyBorder(0, 0, 0, 0));
      	setBounds(92, 103, 813, 424);
      	getContentPane().setLayout(null);
      	
      	scrlMCMostrarCorreos = new JScrollPane(tblMuestraCorreos);
      	scrlMCMostrarCorreos.setEnabled(false);
      	scrlMCMostrarCorreos.setSize(793, 301);
      	scrlMCMostrarCorreos.setLocation(10, 57);
		//Agregamos el JScrollPane al contenedor
		getContentPane().add(scrlMCMostrarCorreos, BorderLayout.CENTER);
      	
/* BOTON PARA REGRESAR CORREO A BANDEJA DE ENTRADA Y MARCAR REMITENTE COMO SEGURO */
		
		JButton btnVer = new JButton("ES SEGURO");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int pos = tblMuestraCorreos.getSelectedRow();
				if (pos == -1){
					JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar primero el correo que quiere visualizar");
				}else{
					cuenta = (String) tblMuestraCorreos.getValueAt(pos, 0);
					String clave = (String) tblMuestraCorreos.getValueAt(pos, 2);
					FL.RestaurarEliminados(FL.Devuelve_Ruta_Spam(), clave);
					FL.QuitaCorreodeListaSPAM(cuenta);
				
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
		});
		btnVer.setBounds(714, 363, 89, 23);
		getContentPane().add(btnVer);
		
		
		/* BOTON PARA ELIMINAR PERMANENTEMENTE EL CORREO SELECCIONADO*/
		
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
						FL.EliminarCorreoDefinitivo(FL.Devuelve_Ruta_Spam(), fecha);
						
						/*Quito todo para tener un refresh all instante*/
						getContentPane().remove(scrlMCMostrarCorreos);
						getContentPane().revalidate();
						getContentPane().repaint();
						
						SetTable();
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
		btnEliminar.setBounds(10, 363, 89, 23);
		getContentPane().add(btnEliminar);
      	
	}
      
	
	
	public void SetTable(){
		String col[] = {"Remitente","Asunto", "Fecha"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		try{
			modelo = FL.DevSpam();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tblMuestraCorreos = new JTable(modelo);
		tblMuestraCorreos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*Oculto columnas con Timestamp usada como clave del diccionario */
	/*	TableColumn myTableColumn2 = tblMuestraCorreos.getColumnModel().getColumn(2);
		tblMuestraCorreos.getColumnModel().removeColumn(myTableColumn2);*/
		
      
		
	}
}
