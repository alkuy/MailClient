package persistencia;

import java.util.ArrayList;



import java.io.*;

public class FachadaPers {
	
	
	private static FachadaPers instancia;
	private String correos[];
	Configuracion conf = Configuracion.getInstancia();
	
	
	public static FachadaPers getInstancia() {
		if(instancia == null){
			instancia = new FachadaPers();
		}
		return instancia;
	}
	
	
	public void carga_Correo(int conversacion, String asunto, String emisor, String e_dom,
		String receptor, String r_dom, String texto, String fecha){
		
		Correo correo = new Correo();
		correo.setId_conversacion(conversacion);
		correo.setAsunto(asunto);
		correo.setEmisor_nombre(emisor);
		correo.setEmisor_dominio(e_dom);
		correo.setDestinatario(receptor);
		correo.setDestinatario_dominio(r_dom);
		correo.setFecha(fecha);
		correo.setTexto(texto);
		
	}
	
	
	public void carga_Cuenta(String nom, String dom, String pas){
		
		Cuenta cuenta = new Cuenta();
		cuenta.setNom_us(nom);
		cuenta.setDom(dom);
		cuenta.setPasswd(pas);
		
	}
	
	/**
	 * Metodo que lista los archivos del deirectorio
	 * @param directorio que queremos leer
	 * @return Array de String con los nombres de los archivos
	 */
	public String[] traeCorreos (String directorio){	
		int dir_largo = directorio.length()-2; //El primer slash y el ultimo no los toma
		/*Esto de restarle dos se debe a que el nombre original viene con una carga de mas de slashes, y algunos termiana
		 * finalmente imprmiendose mas de una vez*/
		Archivos arch = new Archivos();
		File[]ficheros = arch.listadoDirectorio(directorio);
		String correos[] = new String[ficheros.length];
		String correo;
		int i = 0;
		for(File item: ficheros){
			correo = item.toString();     //Convierte ls ruta a string
			correo = correo.substring(dir_largo); //Deja a un lado el los directorios para quedarse solo con el nombre del archivo
			correos[i] = correo;
			i++;
			}
		
		return correos;

	}
		
	/**
	 * Metodo para traer id de conversacion de archivo mail
	 * @param directorio
	 * @param archivo
	 * @return id_conversacion de correo
	 */
	public int getConversacion(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getId_conversacion();	
	}
	
	/**
	 * Metodo para traer asunto del correo en Archivo
	 * @param directorio
	 * @param archivo
	 * @return asunto de correo
	 */
	
	public String getAsunto(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getAsunto();
	}

	/**
	 * Metodo para traer asunto del correo en Archivo
	 * @param directorio
	 * @param archivo
	 * @return asunto de correo
	 */

	public String getEmisorNom(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getEmisor_nombre();
	}
	
	/**
	 * Metodo para traer Dominio del emisor del correo en Archivo
	 * @param directorio
	 * @param archivo
	 * @return nombre emisor
	 */
	public String getEmisorDom(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getEmisor_dominio();
	}
	
	/**
	 * Metodo para traer Nombre del receptor del correo en Archivo
	 * @param directorio
	 * @param archivo
	 * @return (String) nombre de receptor
	 */
	public String getReceptorNom(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getDestinatario();
	}
	
	/**
	 * Metodo para traer Dominio del receptor del correo en Archivo
	 * @param directorio
	 * @param archivo
	 * @return (String) Dominio del receptor
	 */
	public String getReceptorDom(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getDestinatario_dominio();
	}
	
	
	public String getFecha(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getFecha();
	}
	
	public String getTexto(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getTexto();
	}
	
	
	public void GuardaCorreo (String directorio, logica.Correo correo) throws IOException{
		Archivos arch = new Archivos();
		Correo correoper = new Correo();
		
		correoper.setAsunto(correo.getAsunto());
		correoper.setDestinatario(correo.getDestinatario());
		correoper.setDestinatario_dominio(correo.getDestinatario_dominio());
		correoper.setEmisor_nombre(correo.getEmisor_nombre());
		correoper.setEmisor_dominio(correo.getEmisor_dominio());
		correoper.setFecha(correo.getFecha());
		correoper.setTexto(correo.getTexto());
		correoper.setId_conversacion(correo.getId_conversacion());
		
		arch.guarda(directorio, correoper);
	}
	
	public void creaDirectorioUsuario(String nom, String dom){
		
		conf.crearDirectorioUsuario(nom, dom);
	}
	
	
        public void crearDirectorioCuenta(){
		
		conf.crearDirectorioCuenta();
	}
	
        public void crearDirectorios(String nom, String dom){
    		
    		conf.crearDirectorios(nom, dom);
    	}
        
        
        public void creaRutaArchivos(){
        	
        	conf.cargarRutasAbsolutas();
        }
        
        
        public String devRutaBorradores(){
	
        	return conf.getCarpetaBorradores();
        	
        }
        
        public String devRutaEnviados(){
        	
        	return conf.getCarpetaEnviados();
        	
        }
        
        
        public String devRutaBuzonSalida(){
        	
        	return conf.getCarpetaBozonSalida();
        	
        }
        
        
        public String devRutaRecibidos(){
        	
        	return conf.getCarpetaRecibidos();
        	
        }
        
        public String devRutaConfiguracion(){
        	
        	return conf.getCarpetaConfiguracion();
        	
        }
        	
	public void GuardaConfiguracion (logica.Cuenta cuenta) throws IOException{
		
		
	    Archivos arch = new Archivos();
		Cuenta cuentaper = new Cuenta();
		
		cuentaper.setNom_us(cuenta.getNom_us());
		cuentaper.setDom(cuenta.getDominio());
		cuentaper.setPasswd(cuenta.getContraseña_cuenta());
		
		arch.guardaConfiguracion(cuentaper);
		//System.out.println("Guardando "+directorio);
	}
	
	public String CarpetaEnviados(){
		return conf.getCarpetaEnviados();
	}
	
	public String CarpetaRecibidos(){
		return conf.getCarpetaRecibidos();
	}
	
	public String CarpetaBuzonSalida(){
		return conf.getCarpetaBozonSalida();
	}
	
	public String CarpetaCuentas(){
		return conf.getCarpetaCuentas();
	}
	
}
