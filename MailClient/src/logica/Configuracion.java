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
	
   public String devRutaBorradores(){
		
		return FachPer.devRutaBorradores();
	}
	
   public String devRutaRecibidos(){
		
		return FachPer.devRutaRecibidos();
	}

   public String devRutaEnviados(){
		
		return FachPer.devRutaEnviados();
	}
   
   public String devRutaBuzonSalida(){
		
		return FachPer.devRutaBuzonSalida();
	}
   
   public String devRutaConfiguracion(){
		
		return FachPer.devRutaConfiguracion();
	}

}
