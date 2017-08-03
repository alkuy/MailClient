package persistencia;

import java.io.File;

/** Clase Configuracion Singleton
 * 
 * @author 
 *
 */

// Esta clase tiene los datos de la direccion de cada carpeta. es singleton ya que una vez que se cargan a ella los dtos y es instanciada no se necesita modificar ni insertar nada mas en ella
public class Configuracion {
	
	
	    private static Configuracion instancia;
	   	private static String carpetaEnviados;
		private static String carpetaRecibidos;
		private static String carpetaBorradores;
		private static String carpetaBozonSalida;
		private static String carpetaPapelera;
		private static String carpetaConfiguracion;
		private static String carpetaUsuario;
		private static String carpetaSpam;
		private static String carpetaCuentas = "c:\\Cuentas\\";
		private static String carpetaDominio = "c:\\Cuentas\\Dominios\\";
		
	  
	  
	  
	  private Configuracion(){};
		
	     public static Configuracion getInstancia() {
		   if(instancia == null){
			 instancia = new Configuracion();
		 }
	   return instancia;
	  }
	
	
	// devuelve carpeta cuentas (ruta absoluta)
	public static String getCarpetaCuentas() {
		
		return carpetaCuentas;
	}
	
	//Devuelce la carpeta Dominios
	public static String getCarpetaDominios(){
		return carpetaDominio;
	}
	
		/**
	 * @return the carpetaEnviados
	 */
	public static String getCarpetaEnviados() {
		return carpetaEnviados;
	}


	/**
	 * @param carpetaEnviados the carpetaEnviados to set
	 */
	public static void setCarpetaEnviados(String carpetaEnviados) {
		Configuracion.carpetaEnviados = carpetaEnviados;
	}
	
	/**
	 * @return the carpetaRecibidos
	 */
	public static String getCarpetaRecibidos() {
		return carpetaRecibidos;
	}


	/**
	 * @return the carpetaBorradores
	 */
	public static String getCarpetaBorradores() {
		return carpetaBorradores;
	}

	/**
	 * @param carpetaBorradores the carpetaBorradores to set
	 */
	public static void setCarpetaBorradores(String carpetaBorradores) {
		Configuracion.carpetaBorradores = carpetaBorradores;
	}

	/**
	 * @return the carpetaBozonSalida
	 */
	public static String getCarpetaBozonSalida() {
		return carpetaBozonSalida;
	}

	/**
	 * @param carpetaBozonSalida the carpetaPapelera to set
	 */
	public static void setCarpetaPapelera(String carpetaPapelera) {
		Configuracion.carpetaPapelera = carpetaPapelera;
	}
	
	public static String getCarpetaPapelera() {
		return carpetaPapelera;
	}

	/**
	 * @param carpetaBozonSalida the carpetaBozonSalida to set
	 */
	public static void setCarpetaBozonSalida(String carpetaBozonSalida) {
		Configuracion.carpetaBozonSalida = carpetaBozonSalida;
	}


	/**
	 * @return the carpetaConfiguracion
	 */
	public static String getCarpetaConfiguracion() {
		return carpetaConfiguracion;
	}

	/**
	 * @param carpetaConfiguracion the carpetaConfiguracion to set
	 */
	public static void setCarpetaConfiguracion(String carpetaConfiguracion) {
		Configuracion.carpetaConfiguracion = carpetaConfiguracion;
	}

	/**
	 * @param carpetaRecibidos the carpetaRecibidos to set
	 */
	public static void setCarpetaRecibidos(String carpetaRecibidos) {
		Configuracion.carpetaRecibidos = carpetaRecibidos;
	}

	/**
	 * @param carpetaCuentas the carpetaCuentas to set
	 */
	public static void setCarpetaCuentas(String carpetaCuentas) {
		Configuracion.carpetaCuentas = carpetaCuentas;
	}
	
	
	/**
	 * @return the carpetaUsuario
	 */
	public static String getCarpetaUsuario() {
		return carpetaUsuario;
	}

	/**
	 * @param carpetaUsuario the carpetaUsuario to set
	 */
	public static void setCarpetaUsuario(String carpetaUsuario) {
		Configuracion.carpetaUsuario = carpetaUsuario;
	}
	
	
	/**
	 * @return the carpetaSpam
	 */
	public static String getCarpetaSpam() {
		return carpetaSpam;
	}

	/**
	 * @param carpetaSpam the carpetaSpam to set
	 */
	public static void setCarpetaSpam(String carpetaSpam) {
		Configuracion.carpetaSpam = carpetaSpam;
	}

	// crea la carpeta cuenta en el disco ubicada en C:\\Cuentas
	public static void crearDirectorioCuenta(){
				
		File cuentas = new File(getCarpetaCuentas()); 
		cuentas.mkdir();
				
	}
	
	//Crea la carpeta Domionios dentro de la Carpeta Cuentas C:\\Cuentas\Dominios
	public static void crearDirectorioDominios(){
		
		File Dominio = new File(getCarpetaDominios());
		Dominio.mkdir();
	}
	
	//Crea el directorio del usuario segun la cuenta con la que se logeo si es que no ingreso antes con la misma
	public static void crearDirectorioUsuario(String nom, String dom){
		
	File configuracion = new File(Configuracion.getCarpetaCuentas()+nom+"-"+dom+"\\"); 
	configuracion.mkdir();
	Configuracion.setCarpetaUsuario(getCarpetaCuentas()+"\\"+nom+"-"+dom+"\\");
	
   }
	
	
	/** Metodo que despues de haber creado los archivos carga en cada nombre de carpeta su ruta absuluta para poder usar
	 * 
	 */
	
	public static void cargarRutasAbsolutas(){
		
		Configuracion.setCarpetaBozonSalida(Configuracion.getCarpetaUsuario()+"\\Buzon-Salida\\");
		Configuracion.setCarpetaBorradores(Configuracion.getCarpetaUsuario()+"\\Borradores\\");
		Configuracion.setCarpetaEnviados(Configuracion.getCarpetaUsuario()+"\\Enviados\\");
		Configuracion.setCarpetaRecibidos(Configuracion.getCarpetaUsuario()+"\\Recibidos\\");
		Configuracion.setCarpetaPapelera(Configuracion.getCarpetaUsuario()+"\\Papelera\\");
		Configuracion.setCarpetaSpam(Configuracion.getCarpetaUsuario()+"\\Spam\\");
		Configuracion.setCarpetaConfiguracion(Configuracion.getCarpetaUsuario()+"\\Configuracion\\");
		
	}
	
	// Crea los directorios dentro de la carpeta del usuario si es que es la primera vez que ingresa desde esa pc
	
	public static void crearDirectorios(String nom, String dom){
		
		File enviados = new File(Configuracion.getCarpetaUsuario()+"\\Enviados\\"); 
		enviados.mkdir();
		
		File recibidos = new File(Configuracion.getCarpetaUsuario()+"\\Recibidos\\"); 
		recibidos.mkdir();
		
		File buzonsalida = new File(Configuracion.getCarpetaUsuario()+"\\Buzon-Salida\\"); 
		buzonsalida.mkdir();
		

		File borradores = new File(Configuracion.getCarpetaUsuario()+"\\Borradores\\"); 
		borradores.mkdir();
		
		File papelera = new File(Configuracion.getCarpetaUsuario()+"\\Papelera\\"); 
		papelera.mkdir();
		
		File config = new File(Configuracion.getCarpetaUsuario()+"\\Configuracion\\"); 
		config.mkdir();
		
		File spam = new File(Configuracion.getCarpetaSpam()+"\\Spam\\"); 
		spam.mkdir();
		
		
	}		
	
}
