package persistencia;

import java.io.File;



public class Configuracion {
	private static String carpetaEnviados = "c:\\Enviados\\";
	private static String carpetaRecibidos = "c:\\Recibidos\\";
	
	
	public Configuracion(){

	}
	
	public static String getCarpetaEnviados() {
		return carpetaEnviados;
	}

	public static String getCarpetaRecibidos() {
		return carpetaRecibidos;
	}


	public static void crearDirectorios(){
		File enviados = new File(getCarpetaEnviados()); 
		enviados.mkdir();
		
		File recibidos = new File(getCarpetaRecibidos()); 
		recibidos.mkdir();
		
	}
}
