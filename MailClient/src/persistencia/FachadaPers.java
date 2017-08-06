package persistencia;

import java.io.*;
import java.util.ArrayList;

public class FachadaPers {
	
	
	private static FachadaPers instancia;
	
	Configuracion conf = Configuracion.getInstancia();
	
	
	public static FachadaPers getInstancia() {
		if(instancia == null){
			instancia = new FachadaPers();
		}
		return instancia;
	}
	
	/**
	 * Metodo para invocar el metodo constructor de la clase correo y cargar todos los datos
	 * @param conversacion
	 * @param asunto
	 * @param emisor
	 * @param e_dom
	 * @param receptor
	 * @param r_dom
	 * @param texto
	 * @param fecha
	 */
	
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
	
	/**
	 * Metodo para invocar el metodo constructor de la clase en persistencia y cargar los datos
	 * @param nom
	 * @param dom
	 * @param pas
	 */
	
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
	
	/**
	 * Metodo que gurda un correo en disco (tomando los datos del correo instanciado en la capa logica) en el directorio indicado
	 * @param directorio
	 * @param correo
	 * @throws IOException
	 */
	public void GuardaCorreo (String directorio, logica.Correo correo) throws IOException{
		Archivos arch = new Archivos();
		Correo correoper = new Correo();
		// cargo todos los dtos del correo de la capa logica a correo de la capa de persistencia.
		correoper.setAsunto(correo.getAsunto()); 
		correoper.setDestinatario(correo.getDestinatario());
		correoper.setDestinatario_dominio(correo.getDestinatario_dominio());
		correoper.setEmisor_nombre(correo.getEmisor_nombre());
		correoper.setEmisor_dominio(correo.getEmisor_dominio());
		correoper.setFecha(correo.getFecha());
		correoper.setTexto(correo.getTexto());
		correoper.setId_conversacion(correo.getId_conversacion());
		
		arch.guarda(directorio, correoper);// aca o guarda en el disco por medio de la clase archivo
	}
	
	//Guarda los dominios en la persistencia
	public void GuardaDominios(String directorio, String Dom, int Prioridad){
		Archivos arch = new Archivos();
		Dominio D = new Dominio(Dom, Prioridad);
		arch.guardaDominio(directorio, D);
	}
	
	public void creaDirectorioUsuario(String nom, String dom){
		
		conf.crearDirectorioUsuario(nom, dom);
	}
	
	
        public void crearDirectorioCuenta(){
		
		conf.crearDirectorioCuenta();
	}
	
        public void crearDirectorioDominios(){
            
        	conf.crearDirectorioDominios();
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
        
         public String devRutaPapelera(){
        	
        	return conf.getCarpetaPapelera();
        	
        }
         
         public String devRutaSpam(){
         	
         	return conf.getCarpetaSpam();
         	
         }
    
    /**
     * Metodo que gurda una cuenta en disco (tomando los datos de la cuenta instanciada en la capa logica) en el directorio indicado
     * @param cuenta
     * @throws IOException
     */
	public void GuardaConfiguracion (logica.Cuenta cuenta) throws IOException{
		
		
	    Archivos arch = new Archivos();
		Cuenta cuentaper = new Cuenta();
		// cargo todos los datos de la cuenta de la capa logica a la clase cuenta de la capa de persistencia.
		cuentaper.setNom_us(cuenta.getNom_us());
		cuentaper.setDom(cuenta.getDominio());
		cuentaper.setPasswd(cuenta.getContraseña_cuenta());
		
		arch.guardaConfiguracion(cuentaper);// guarado los datos de la cuenta en persistencia por medio de la clase archivo
		
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
	
	
	public String CarpetaBorradores(){
		return conf.getCarpetaBorradores();
	}
	
	public String CarpetaPapelera(){
		return conf.getCarpetaPapelera();
	}
	
	public String CarpetaSpam(){
		return conf.getCarpetaSpam();
	}
	
	/**
	 * Carga los dominios en un archivo
	 * @param Dom
	 * @param P
	 */
	public void GuardaDominios(String Dom, int P){
		Dominio D = new Dominio(Dom, P);
		Archivos Arch = new Archivos();
		String CarpetaDom = conf.getCarpetaDominios();
		Arch.guardaDominio(CarpetaDom, D);
	}
	/**
	 * Trae los dominios
	 * @return String[]
	 */
	public String[] GetDominio(){
		String[] Dom;
		Archivos Arch = new Archivos();
		Dom = Arch.LeerDom(conf.getCarpetaDominios());
		return Dom;
	}
	/**
	 * Metodo que instancia la eliminacion de un archivo dado
	 * @param directorio
	 * @param archivo
	 */
	public void eliminar(String directorio, String archivo){
		Archivos arch = new Archivos();
		arch.Eliminar(directorio, archivo);
	}
	
	public void MoverdeCarpeta(String d_origen, String archivo, String d_destino){
		Archivos arch = new Archivos();
		arch.moverCorreos(d_origen, archivo, d_destino);
	}
	
	/**
	 * Metodo para leer actualizacion
	 * @param destino
	 * @param fecha
	 */
	public void GuardaActualizacion(String destino, String fecha){
		Archivos arch = new Archivos();
		arch.GuardaUltimaActualizacion(destino, fecha);
		
	}
	/**
	 * Devuelve cuando fue la ultima actualzacion de Enviar/Recibir. Si es la primera devuelve 0
	 * @param destino
	 * @return
	 */
	
	public String devuelveUltimaActualizacion (String destino){
		Archivos arch = new Archivos();
		String fecha = "0";
		try {
			fecha = arch.leerActualizacion(destino);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fecha;
		
	}
	
	/**
	 * Agrega que va a la lista de spam
	 * @param destino
	 * @param correo
	 */
	public void CargaenListaSpam(String destino, String correo){
		Archivos arch = new Archivos();
		try {
			arch.guardaListaSpam(destino, correo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve listado de coreos para SPAM
	 * @param origen
	 * @return
	 */
	public ArrayList<String> TraeListaSpam(String origen){
		Archivos arch = new Archivos();
		ArrayList<String> SPAM = arch.CargaSpam(origen);
		return SPAM;
		
	}
	
}
