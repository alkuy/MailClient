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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




public class FrmMuestraBorradores extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String fecha = new String(); // variable estatica para poder mantener utilizar en otras clases como la clave del hash
	public static String cuenta = new String(); // la usamos para no tener que traerla desde el diccionari correos y juntar los campos usuario y dominio
	private JTable tblMuestraCorreos;
	private ContinuarCorreoBorrador borrador;
	FachadaLog FL = new FachadaLog();

	 
	public FrmMuestraBorradores() {
		
		SetTable();
		
		
		setTitle("Borradores");
	 	setBorder(new EmptyBorder(0, 0, 0, 0));
      	setBounds(92, 103, 813, 424);
      	getContentPane().setLayout(null);
      	
      	JScrollPane scrlMCMostrarCorreos = new JScrollPane(tblMuestraCorreos);
      	
      	
      
      	scrlMCMostrarCorreos.setEnabled(false);
      	scrlMCMostrarCorreos.setSize(793, 301);
      	scrlMCMostrarCorreos.setLocation(10, 57);
		//Agregamos el JScrollPane al contenedor
		getContentPane().add(scrlMCMostrarCorreos, BorderLayout.CENTER);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int pos = tblMuestraCorreos.getSelectedRow();
				if (pos == -1){
					JOptionPane.showMessageDialog(new JPanel(), "Debe elegir el correo que quiere editar");
				}else{
				fecha = (String) tblMuestraCorreos.getValueAt(pos, 2);
				cuenta = (String) tblMuestraCorreos.getValueAt(pos, 0);
				borrador = new ContinuarCorreoBorrador();
				borrador.setLocationRelativeTo(null);
				borrador.setVisible(true);
			    dispose();				

				}
			
				
			}
		});
		btnEditar.setBounds(714, 369, 89, 23);
		getContentPane().add(btnEditar);
		
		
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
						FL.EliminarCorreoDefinitivo(FL.Devuelve_Ruta_Borradores(), fecha);
						
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
		String col[] = {"Destinatario","Asunto"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		try{
			modelo = FL.DevBorradores();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		tblMuestraCorreos = new JTable(modelo);
		tblMuestraCorreos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*Oculto columnas con Timestamp usada como clave del diccionario */
	/*	TableColumn myTableColumn1 = tblMuestraCorreos.getColumnModel().getColumn(2);
		tblMuestraCorreos.getColumnModel().removeColumn(myTableColumn1);*/
		
		
	}
}
