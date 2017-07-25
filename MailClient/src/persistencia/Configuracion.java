package persistencia;

import java.io.File;





public class Configuracion {
	
	
 
   
	private static String carpetaEnvidos = "c:\\Cuentas\\";
	private static String carpetaRecibidos = "c:\\Cuentas\\";
	private static String carpetaCuentas = "c:\\Cuentas\\";
	
	
	public Configuracion(){

	}
	
    public static String getCarpetaRecibidos() {
		
		return carpetaRecibidos;
	}

    public static String getCarpetaEnviados() {
	
	return carpetaEnvidos;
     }

	
	public static String getCarpetaCuentas() {
		
		return carpetaCuentas;
	}
	
   

	public static void crearDirectorioCuenta(){
				
		File cuentas = new File(getCarpetaCuentas()); 
		cuentas.mkdir();
				
	}
	
	
	public static void crearDirectorioConfig(String directorio){
		
	File configuracion = new File(directorio); 
	configuracion.mkdir();
	
   }
	
	
	
	public static void crearDirectorios(String dir){
		
		File enviados = new File(dir+"\\Enviados\\"); 
		enviados.mkdir();
		
		File recibidos = new File(dir+"\\Recibidos\\"); 
		recibidos.mkdir();
		
		File buzonsalida = new File(dir+"\\Buzon-Salida\\"); 
		buzonsalida.mkdir();
		

		File borradores = new File(dir+"\\Borradores\\"); 
		borradores.mkdir();
		
		File config = new File(dir+"\\Configuracion\\"); 
		config.mkdir();
		
		
	}		
	
}
