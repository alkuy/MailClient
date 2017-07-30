package Grafica;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import logica.FachadaLog;


public class Verificaciones {
	public Verificaciones(){
		
	}
	
	private FachadaLog FCLogica = FachadaLog.getInstancia();
	
	
	
	/**
	 * Intercambia carcteres que pueden ocasionar problemas en el Sistema gestor de base de datos
	 * @param cadena
	 * @return cadena 
	 */
	public String remplazoCaracteres(String cadena){
		cadena = cadena.replace("'", "''");
		return cadena;
	}

   


/**
 * Verificador de solo minusculas  
 * @param cadena
 * @return True si son solo minusculas
 */
  public boolean solo_minusculas(String cadena){
	  if (cadena.matches("[a-z]+")){
		  return true;
	  }
	  return false;
  }
  /**
   * Verificador de maximo de caracteres aceptado
   * @param campo
   * @param max
   * @return true si es menos campo es menor que la cantida de caracteres aceptado
   */
  
  public boolean cant_caracteres(JComponent campo, int max, int min){
	  	Border bordeROJO = BorderFactory.createLineBorder(Color.RED, 2);
		Border bordeCOMUN = BorderFactory.createLineBorder(Color.BLACK, 1);
		String texto = ((JTextField)campo).getText();
			if(texto.length() > max){
				campo.setBorder(bordeROJO);
				JOptionPane.showMessageDialog(campo, "Caracteres máximos: "+max , null, JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(texto.length() < min){
				campo.setBorder(bordeROJO);
				JOptionPane.showMessageDialog(campo, "El campo requiere al menos: "+min+" caracteres" , null, JOptionPane.ERROR_MESSAGE);
				return false;	
			}
		campo.setBorder(bordeCOMUN);
		return true;
		}
  
  /**
   * Verifica si es numerico el valor ingresado	
   * @param campo
   * @return true si es numero
   */
  public boolean numerico(JComponent campo){
	  Border bordeROJO = BorderFactory.createLineBorder(Color.RED, 2);
		Border bordeCOMUN = BorderFactory.createLineBorder(Color.BLACK, 1);
	  String texto = ((JTextField)campo).getText();
	  if (texto.length() > 0){
		  try {
			  Long.parseLong(texto); //No funciona
			  campo.setBorder(bordeCOMUN);
			  return true;
		  }catch (NumberFormatException nfe){
			  campo.setBorder(bordeROJO);
			  JOptionPane.showMessageDialog(campo, "Debe ingresar valores numericos", null, JOptionPane.ERROR_MESSAGE);
			  return false;
		  }
	  }
	  return true;
  }
  
  /**
   * Verifica que los caracteres ingresados sean letras minusculas o numeros
   * @param campo
   * @return True si son correctos los caracteres
   */
  public boolean caracteres_validos(JComponent campo){
	Border bordeROJO = BorderFactory.createLineBorder(Color.RED, 2);
	Border bordeCOMUN = BorderFactory.createLineBorder(Color.BLACK, 1);
	  String texto = ((JTextField)campo).getText();
	  if (texto.matches("[a-z0-9]+")){
		  campo.setBorder(bordeCOMUN);
		  return true;
	  }else{
		  JOptionPane.showMessageDialog(campo, "Debe ingresar caracteres validos. Solo minusculas y números", null, JOptionPane.ERROR_MESSAGE);
		  campo.setBorder(bordeROJO);
		  return false;
	  }
  }

 // Metodo que verifica que la cuenta tenga un y solo un @
  
  public boolean verificaCuentaReceptor(String texto){
	    char caracter = '@';
	  	int veces=0;
		char []caracteres=texto.toCharArray();
		for(int i=0;i<=caracteres.length-1;i++){
			if(caracter == caracteres[i]){
				veces++;
			}
	     }
		
		if (veces == 1){
		 return true;
		}else {
			
			JOptionPane.showMessageDialog(null, "El destinatario no es una cuenta valida", null, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
  }		
  
}  
