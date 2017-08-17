package Grafica;




import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import logica.FachadaLog;

/** Formulario para la agenda del cliente.
 * @author
 *
 */
public class FrmAgenda extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrlMCMuestraUsuarios;
	public MuestraRecibido muestra;
	FachadaLog FL = new FachadaLog();
	private JTable tblMuestraUsuarios;
	private Nuevo_Correo nuevocorreo;
	
	/** Se crea el formulario
	 */
	public FrmAgenda() {
		
		
		SetTableAgenda();
		setTitle("Agenda");
	 	setBorder(new EmptyBorder(0, 0, 0, 0));
      	setBounds(92, 103, 355, 424);
      	getContentPane().setLayout(null);
      	
      	scrlMCMuestraUsuarios = new JScrollPane(tblMuestraUsuarios);
      	scrlMCMuestraUsuarios.setEnabled(false);
      	scrlMCMuestraUsuarios.setSize(332, 347);
      	scrlMCMuestraUsuarios.setLocation(10, 11);
		//Agregamos el JScrollPane al contenedor
		getContentPane().add(scrlMCMuestraUsuarios, BorderLayout.CENTER);
      	
/* BOTON PARA ENVIAR CORREO A UNA CUENTA EN LA AGENDA */
		
		JButton btnEditar = new JButton("ENVIAR CORREO");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int pos = tblMuestraUsuarios.getSelectedRow();
				if (pos == -1){
					JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar primero el usuario que desea visualizar");
				}else{
					
					nuevocorreo = new Nuevo_Correo(tblMuestraUsuarios.getValueAt(pos, 1).toString());
					nuevocorreo.setLocationRelativeTo(null);
					nuevocorreo.setVisible(true);
					principal.apareceLogo();
					dispose();				

				}	
			}
		});
		btnEditar.setBounds(227, 363, 115, 23);
		getContentPane().add(btnEditar);
		
		
		/* BOTON PARA ELIMINAR LA CUENTA SELECCIONADA*/
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int pos = tblMuestraUsuarios.getSelectedRow();
				if (pos == -1){
					JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar el usuario que desea eliminar");
				}else{
					Object [] opciones ={"ELIMINAR","Cancelar"};
					int eleccion = JOptionPane.showOptionDialog(rootPane,"Seguro desea eliminar este usuario","Atencion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
					if (eleccion == JOptionPane.YES_OPTION){
						
						FL.getInstancia().borrarCuentaAgenda(tblMuestraUsuarios.getValueAt(pos, 0).toString(), tblMuestraUsuarios.getValueAt(pos, 1).toString());
						
						getContentPane().remove(scrlMCMuestraUsuarios);
						getContentPane().revalidate();
						getContentPane().repaint();
						
						SetTableAgenda();
						scrlMCMuestraUsuarios = new JScrollPane(tblMuestraUsuarios);
				      	scrlMCMuestraUsuarios.setEnabled(false);
				      	scrlMCMuestraUsuarios.setSize(332, 347);
				      	scrlMCMuestraUsuarios.setLocation(10, 57);
						//Agregamos el JScrollPane al contenedor
						getContentPane().add(scrlMCMuestraUsuarios, BorderLayout.CENTER);
					}
				}
			}
		});
		btnEliminar.setBounds(10, 363, 89, 23);
		getContentPane().add(btnEliminar);
      	
		
	}
	
	/** Se carga la tabla con el modelo de agenda.
	 */
	public void SetTableAgenda(){
		String col[] = {"Usuario","Cuenta"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		modelo = FL.getInstancia().DevUsuAgenda();
		tblMuestraUsuarios = new JTable(modelo);
		JTableHeader header = tblMuestraUsuarios.getTableHeader();
		 header.setOpaque(false);
		 header.setBackground(Color.DARK_GRAY);
	     header.setForeground(Color.white);
	     header.setFont(new Font("Consolas", Font.BOLD, 13));
	     
	    tblMuestraUsuarios.setShowVerticalLines(false);
	    tblMuestraUsuarios.setFont(new Font("Century Gothic", Font.PLAIN, 12));
	    tblMuestraUsuarios.setRowHeight(35);
	}
}
