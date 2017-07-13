package Grafica;



import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;


public class FrmMuestraBuzonSalida extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 
	public FrmMuestraBuzonSalida() {
		
		setTitle("Buzon de Salida");
	 	setBorder(new EmptyBorder(0, 0, 0, 0));
      	setBounds(92, 103, 813, 424);
      	getContentPane().setLayout(null);
      	
      	JScrollBar scrollBar = new JScrollBar();
      	scrollBar.setBounds(10, 23, 793, 355);
      	getContentPane().add(scrollBar);
      
		
	}
}
