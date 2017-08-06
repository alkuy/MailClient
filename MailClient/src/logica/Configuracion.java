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
   
   
// Metodo que devuelve la ruta para guardar dentro de la carpeta de Eliminados
	
   public String devRutaPapelera(){
		
		return FachPer.devRutaPapelera();
	}
   
// Metodo que devuelve la ruta para guardar dentro de la carpeta Spam
	
   public String devRutaSpam(){
		
		return FachPer.devRutaSpam();
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
   
   /**
    * Para guarda el ultimo enviar/recibir realizado y que solo traiga correos que no bajaron aun
    * @param destino
    * @param fecha
    */
   public void GuardaActualizacion(String destino, String fecha){
	   FachPer.GuardaActualizacion(destino, fecha);
   }
   
   /**\
    *  Devuelve cuando fue la ultima actualzacion de Enviar/Recibir. Si es la primera devuelve 0
    * @param destino
    * @return
    */
   public String devuelveUltimaActualizacion (String destino){
	   String fecha= FachPer.devuelveUltimaActualizacion(destino);
	   return fecha;
   }

}
