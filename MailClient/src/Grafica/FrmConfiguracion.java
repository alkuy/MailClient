package Grafica;




import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import Conectividad.FachadaCon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmConfiguracion extends JInternalFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrmAgenda frmagenda;

	/**
	 * Create the frame.
	 */
	public FrmConfiguracion() {
		
		FachadaCon FC = FachadaCon.getInstancia();
		setTitle("Configuracion");
		
	 	setBorder(new EmptyBorder(0, 0, 0, 0));
      	setBounds(100, 100, 243, 300);
      	getContentPane().setLayout(null);
		
		/*---BOTON AGENDA---*/
		JButton btnagenda = new JButton("AGENDA");
		btnagenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
			}
		});
		btnagenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmagenda = new FrmAgenda();
				principal.abreVentana(frmagenda);
				dispose();
			}
		});
		btnagenda.setBounds(47, 65, 143, 34);
		getContentPane().add(btnagenda);
		
		JLabel lblSoloUnPaso = new JLabel("Configuracion");
		lblSoloUnPaso.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblSoloUnPaso.setBounds(57, 11, 143, 29);
		getContentPane().add(lblSoloUnPaso);
		
		
		/*---BOTON MANUAL DE USUARIO---*/
		
		JButton btnmanual = new JButton("MANUAL USUARIO");
		btnmanual.setBounds(47, 96, 143, 34);
		getContentPane().add(btnmanual);
		
		
		/*---BOTON PARA RECIBIR LOS CORREOS ANTERIORES---*/
		
		JButton btnNewButton = new JButton("RECIBIR ANTERIORES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				FC.CorreosAntiguos();
			}
		});
		btnNewButton.setBounds(47, 126, 143, 34);
		getContentPane().add(btnNewButton);
	}
	public FrmAgenda getFrmAgenda(){
		return frmagenda;
	}
}
