/**
 * 
 */
package logica;

import java.util.ArrayList;
import java.util.Iterator;

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
   
   
// Metodo que devuelve la ruta para guardar dentro de la carpeta Cuentas  
   
   public String devRutaCuentas(){
		
		return FachPer.devRutaCuentas();
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
   
   /**
    * Carga el correo en la lista de SPAM
    * @param correo
    */
   public void CargaListaSpam (String correo){
	   FachPer.CargaenListaSpam(FachPer.devRutaConfiguracion(), correo);
   }
   
   /**
    * Trae en un array la lista de direcciones SPAM
    * @return
    */
   public ArrayList<String> TraeListaSpam(){	
		ArrayList<String> SPAM = FachPer.TraeListaSpam(FachPer.devRutaConfiguracion()+"listaSpam");
		return SPAM;
		
	}
   
   /**
    * Elimina cualquier archivo
    * @param directorio
    * @param archivo
    */
   public void EliminarArchivo(String directorio, String archivo){
	   FachPer.eliminar(directorio, archivo);
   }
   
   /**
    * Quitar un correo de la lista de SPAM
    * @param correo
    * @return
    */
   public ArrayList<String> QuitadeListaSPAM (String correo){
	   ArrayList<String> SPAM = FachPer.TraeListaSpam(FachPer.devRutaConfiguracion()+"listaSpam");
	   ArrayList<String> SPAMnuevo = new ArrayList<String>(); 
	   Iterator<String> it = SPAM.iterator();
	   while (it.hasNext()){
		   String actual = it.next();
		   if (!actual.equals(correo)){
			   SPAMnuevo.add(actual);
		   }
		   
	   }
	return SPAMnuevo;
	   
   }
   
   /** Metodo para encriptar por permutacion en base a una clave seteada y segun la permutacion de ese clave (la clave no puede tener letrs repetidas)
    *  
    * @param texto
    * @param clave
    * @param clavePermut
    * @return texto permutado
    */
   
  public static String Permutar(String texto, String clave, String clavePermut){
 	   
 	   
 	   int valor = clave.length();//guardo el valor del largo de la clave
 	   
 	   	  	   
 	   // igualo inicialmente un arraypermutado al texto original
 	   char[] ArrayTextoPermut = texto.toCharArray();	
 	   
 	   // pasamos el texto a un array de caracteres
 	   char[] ArrayTexto = texto.toCharArray();
 	   
	  // pasamos la clave a un array de caracteres
	  char[] ArrayClave = clave.toCharArray();
	 	   
	   // pasamos la clave permutada a un array de caracteres
	   char[] ArrayClavePermut = clavePermut.toCharArray();
 	   
 	  
 	   
 	   int h = 0; 
 	   int j = 0;
 	   
 	   
 	   if (!(texto.length()<valor)){// si el texto no es menor que el largo de la clave
 		   
 		   		   
 	      for (int i = 0; i<texto.length(); i++){ // este for es para recorrer hasta el final el texto e ir permutando		  
 		   
 		   while(ArrayClavePermut[h] != ArrayClave[j] && h<=valor){ // recorro hasta que el caracter en la clave permutada y la clave original coinciden y hasta que llegue al largo de la clave
 			   j++;
 		   }
 		   
 		   ArrayTextoPermut[i] = ArrayTexto[j];	
 		   j = 0;
 		   h++;	
 		  
 		   if( h == valor){ // si llegue al largo de la clave
 			   texto = texto.substring(valor); // saco del texto lo caracteres que ya se permutaron y guardaron
 			   ArrayTexto = texto.toCharArray(); // actualizo el array de caracteres para seguir permutando
 			   h = 0;			   
 			   
 		   }  		  	 
 		  
 	    }
 	   
 	  }
 	   
 	  texto = String.valueOf(ArrayTextoPermut); // cargo un String con todo el texto permutado
       return texto;
 	   
 	
     }// fin Permutar.
    
    
    
    
    
    /** Metodo de encriptacion por Xor utilizando los caracteres de la clave seteada 
     * 
     * @param dato
     * @param clave
     * @return texto encriptado o desencriptado
     * 
     */
    
    public static String EncriptaDesencripta(String texto, String clave){
        
 	   String textoEncriptado="";
 	   

 	   
 	   // Realizamos una encriptacion utilizando los caracteres de la clave y cifrando por Xor

        if (texto.length() > clave.length()) { // si el largo del texto supera el de la clave duplicamos esta ultima hasta que me alcance
            for (int i=0; i<texto.length(); i++)
                clave+=clave.charAt(i);
        }

        for (int i=0; i<texto.length(); i++){
            textoEncriptado+=(char) (texto.charAt(i)^clave.charAt(i)); // aplicamos cifrado por Xor
        }

        return textoEncriptado;
    }//Fin Encriptar por Xor


}
