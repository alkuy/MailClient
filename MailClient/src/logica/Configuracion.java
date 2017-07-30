/**
 * 
 */
package logica;

import persistencia.FachadaPers;

/**
 * @author 
 *
 */
public class Configuracion {
	
	
   FachadaPers FachPer = FachadaPers.getInstancia();
   
   
 
	
	public Configuracion(){
		
	}	
	// Metodo que devuelve la ruta para guardar dentro de la carpeta Borradores
	
   public String devRutaBorradores(){
		
		return FachPer.devRutaBorradores();
	}
	
	// Metodo que devuelve la ruta para guardar dentro de la carpeta Recibidos
   
   public String devRutaRecibidos(){
		
		return FachPer.devRutaRecibidos();
	}
   
   
// Metodo que devuelve la ruta para guardar dentro de la carpeta Enviados

   public String devRutaEnviados(){
		
		return FachPer.devRutaEnviados();
	}
   
// Metodo que devuelve la ruta para guardar dentro de la carpeta Buzon de Salida
   
   public String devRutaBuzonSalida(){
		
		return FachPer.devRutaBuzonSalida();
	}
   
// Metodo que devuelve la ruta para guardar dentro de la carpeta Configuracion  
   
   public String devRutaConfiguracion(){
		
		return FachPer.devRutaConfiguracion();
	}

}
