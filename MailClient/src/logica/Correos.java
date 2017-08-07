package logica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.table.DefaultTableModel;
import persistencia.FachadaPers;

public class Correos  {
	
	FachadaPers FachPer = FachadaPers.getInstancia();

	
	private static Correos instancia;
	
	private Hashtable<String, Correo> setCorreosEnviados;
	private Hashtable<String, Correo> setCorreosRecibidos;
	private Hashtable<String, Correo> setBandejaSalida;
	private Hashtable<String, Correo> setPapelera;
	private Hashtable<String, Correo> setBorradores;
	private Hashtable<String, Correo> setSpam;
	
	public static Correos getInstancia(){
		if(instancia == null)
			instancia = new Correos();
		
		return instancia;
	}
	
	/** Método constructor de la colección Correos. */
	public Correos() {
		this.setCorreosEnviados = new Hashtable<String, Correo>();
		this.setCorreosRecibidos = new Hashtable<String, Correo>();
		this.setBandejaSalida = new Hashtable<String, Correo>();
		this.setBorradores = new Hashtable<String, Correo>();
		this.setPapelera = new Hashtable<String, Correo>();
		this.setSpam = new Hashtable<String, Correo>();
	}
		
	/** Método que retorna la coleccion Set de Correos Recibidos.
	 * <BR><b>Precondición</b>: la colección no debe ser vacía.</BR>
	 * @return Retorna el set de Correos. */
	public Hashtable<String, Correo> getSetCorreosRecibidos() {
		return setCorreosRecibidos;
	}
	
	/** Método que retorna la coleccion Set de la Bandeja de salida.
	 * <BR><b>Precondición</b>: la colección no debe ser vacía.</BR>
	 * @return Retorna el set de Correos. */
	public Hashtable<String, Correo> getSetBandejaSalida() {
		return setBandejaSalida;
	}
	
	/** Método que retorna la coleccion Set de Correos Enviados.
	 * <BR><b>Precondición</b>: la colección no debe ser vacía.</BR>
	 * @return Retorna el set de Correos. */
	public Hashtable<String, Correo> getSetCorreosEnviados() {
		return setCorreosEnviados;
	}
	
	// Metodo que retorna la coleccion Set de Borradores
	public Hashtable<String, Correo> getSetBorradores() {
		return setBorradores;
	}
	
	
	public Hashtable<String, Correo> getSetPapelera() {
		return setPapelera;
	}
	
	public Hashtable<String, Correo> getSetSpam() {
		return setSpam;
	}

	/**
	 * Obtiene el correo a partir del setlist y la clave
	 * @param directorio (Pasamos el directorio que va a hacer referencia al setlist)
	 * @param clave (Fecha)
	 * @return
	 */
	public Correo obtenerCorreo(String directorio, String clave){
		
		Correo correo = new Correo();
		if(directorio == FachPer.CarpetaPapelera()){
			correo = setPapelera.get(clave);
		}
		
		if(directorio == FachPer.CarpetaEnviados()){
			correo = setCorreosEnviados.get(clave);
		}
		if(directorio == FachPer.CarpetaRecibidos()){
			correo = setCorreosRecibidos.get(clave);
		}
		if(directorio == FachPer.CarpetaBuzonSalida()){
			correo = setBandejaSalida.get(clave);
		}
		
		if(directorio == FachPer.CarpetaBorradores()){
			correo = setBorradores.get(clave);
		}
		
		if(directorio == FachPer.CarpetaSpam()){
			correo = setSpam.get(clave);
		}
		
		
		return correo;
		
		
	}
	
	/**
	 * Carga los correos en memoria. 
	 * @param directorio: Directorio donde se encuentran los correos
	 * @param setCorreos: SetList donde guardarmos los correos
	 */
	public void cargaCorreos (String directorio){
		String correos_arch[] = FachPer.traeCorreos(directorio);
		for (String archivo : correos_arch){
			Correo correo = new Correo();
			correo.setId_conversacion(FachPer.getConversacion(directorio, archivo));
			correo.setAsunto(FachPer.getAsunto(directorio, archivo));
			correo.setEmisor_nombre(FachPer.getEmisorNom(directorio, archivo));
			correo.setEmisor_dominio(FachPer.getEmisorDom(directorio, archivo));
			correo.setDestinatario(FachPer.getReceptorNom(directorio, archivo));
			correo.setDestinatario_dominio(FachPer.getReceptorDom(directorio, archivo));
			correo.setFecha(FachPer.getFecha(directorio, archivo));
			correo.setTexto(FachPer.getTexto(directorio, archivo));
			
			String fecha = FachPer.getFecha(directorio, archivo);
				
			Insertar(directorio, fecha,correo);

		}
		
	}
	
	/**
	 * Inserta en la collecccion de correos
	 * @param directorio (String): Indica si es enviados, recibidos, etc.
	 * @param correo: Pasa el correo a insertar
	 */
	
	//Para insertar los correos en cada diccionario (Enviados, Borradores, etc)
	public void Insertar(String directorio, String clave, Correo correo){
		if(directorio == FachPer.CarpetaEnviados()){
			setCorreosEnviados.put(clave, correo);
		}
		if(directorio == FachPer.CarpetaRecibidos()){
			setCorreosRecibidos.put(clave, correo);
		}
		if(directorio == FachPer.CarpetaBuzonSalida()){
			setBandejaSalida.put(clave, correo);
		}
		
		if(directorio == FachPer.CarpetaBorradores()){
			setBorradores.put(clave, correo);
		}
		
		if(directorio == FachPer.CarpetaPapelera()){
			setPapelera.put(clave, correo);
		}
		
		if(directorio == FachPer.CarpetaSpam()){
			setSpam.put(clave, correo);
		}
	}
	
	/**
	 * Eliminar de hashtable.
	 * @param directorio
	 * @param clave
	 */
	public void Eliminar(String directorio, String clave){
		if(directorio == FachPer.CarpetaEnviados()){
			setCorreosEnviados.remove(clave);
		}
		if(directorio == FachPer.CarpetaRecibidos()){
			setCorreosRecibidos.remove(clave);
		}
		if(directorio == FachPer.CarpetaBuzonSalida()){
			setBandejaSalida.remove(clave);
		}
		
		if(directorio == FachPer.CarpetaBorradores()){
			setBorradores.remove(clave);
		}
		
		if(directorio == FachPer.CarpetaPapelera()){
			setPapelera.remove(clave);
		}
		
		if(directorio == FachPer.CarpetaSpam()){
			setSpam.remove(clave);
		}
		
	} 
	
	/**
	 * Envia a la papelera los correos. No es eliminacion definitiva
	 * @param directorio
	 * @param clave
	 */
	public void Eliminar_Correo(String directorio, String clave, String destino){
		String papelera = FachPer.CarpetaPapelera();
		Correo correo = new Correo();
		correo = obtenerCorreo(directorio, clave); //Cargo el correo para poder pasarlo al Hashtable papelera
		Eliminar(directorio, clave); //Lo elimina del Hashtable
		Insertar(destino, clave, correo); //Insero en Hashtable Tapelera
		
		FachPer.MoverdeCarpeta(directorio, clave, destino); //Muevo el arvhivo fisico de carpeta
	}
	
	public void Eliminar_Definitivo(String directorio, String clave){
		Eliminar(directorio, clave); //Lo elimina del Hashtable
		
		FachPer.eliminar(directorio, clave);
	}
	
	
	/**
	 * Devuelve a Enviados los correos que estan en papelera o Sapm
	 * @param directorio indica directorio origen (desde donde hago la restauracion)
	 * @param clave Hashtable Key de Correo
	 */
	public void Restaurar(String directorio, String clave){
		String recibidos = FachPer.CarpetaRecibidos();
		Correo correo = new Correo();
		correo = obtenerCorreo(directorio, clave); //Cargo el correo para poder pasarlo al Hashtable de origen
		Eliminar(directorio, clave); //Lo elimina del Hashtable
		Insertar(recibidos, clave, correo); //Insero en Hashtable Nuevamente en origen
		FachPer.MoverdeCarpeta(directorio, clave, recibidos); //Muevo el arvhivo fisico de carpeta
	}
	
	/**
	 * ModelTable para los Correos Enviados
	 * @return modelTable Carpeta Enviados
	 */
	
	// Devuelve una tabla con destintario, asunto y fecha de cada correo Enviado que se cargo en memoria anteriormente desde el disco para mostrar en la bandeja de salida
	
	public DefaultTableModel DevTablaCorreosEnviados(){
		
		String col[] = {"Destinatario","Asunto", "Fecha"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		
		Enumeration<Correo> cor = setCorreosEnviados.elements();
		Correo correo;
			
			while(cor.hasMoreElements()){
				correo = cor.nextElement();
				String destino = correo.getDestinatario()+"@"+correo.getDestinatario_dominio();
				String asunto = correo.getAsunto();
				String fecha = correo.getFecha();
				
				String carga [] = {destino, asunto, fecha};		   
		   	modelo.addRow(carga);
			}
		
		return modelo;
		
	}	
	
	/**
	 * ModelTable para Bandeja de salida
	 * @return modelTable Bandeja de Salida
	 */
	
	
	// Devuelve una tabla con destintario y asunto de cada correo del buzon de salida que se cargo en memoria anteriormente desde el disco para mostrar en la bandeja de salida
public DefaultTableModel DevTablaBandejaSalida(){
		
		String col[] = {"Destinatario","Asunto", "Fecha"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);

		Enumeration<Correo> cor = setBandejaSalida.elements();
		Correo correo;
		
		while(cor.hasMoreElements()){
			correo = cor.nextElement();
			String destino = correo.getDestinatario()+"@"+correo.getDestinatario_dominio();
			String asunto = correo.getAsunto();
			String fecha = correo.getFecha();
			
			String carga [] = {destino, asunto, fecha};		   
		   	modelo.addRow(carga);
			}
		
		return modelo;
		
	}	


   /**
    * Devuelve una tabla con destintario y asunto de cada correo de Boradores 
    * que se cargo en memoria 
    * anteriormente desde el disco para mostrar en la bandeja de salida
    * @return ModelTable
    */
   public DefaultTableModel DevTablaBorradores(){
	
	String col[] = {"Destinatario","Asunto", "Fecha"};
	DefaultTableModel modelo = new DefaultTableModel(col,0);

	Enumeration<Correo> cor = setBorradores.elements();
	Correo correo;
	
	while(cor.hasMoreElements()){
		correo = cor.nextElement();
		String destino = correo.getDestinatario()+"@"+correo.getDestinatario_dominio();
		String asunto = correo.getAsunto();
		String fecha = correo.getFecha();
		
		String carga [] = {destino, asunto, fecha};	   
	   	modelo.addRow(carga);
		}
	
	return modelo;
	
   }
   
   
   /**
    * Devuelve una tabla con remitente, asunto y fecha de cada correo recibido 
    * que se cargo en memoria anteriormente desde el disco 
    * para mostrar en la bandeja de entrada
    * @return modelTable
    */
   public DefaultTableModel DevTablaRecibidos(){
	
	String col[] = {"Remitente","Asunto", "Fecha"};
	DefaultTableModel modelo = new DefaultTableModel(col,0);

	Enumeration<Correo> cor = setCorreosRecibidos.elements();
	Correo correo;
	
	while(cor.hasMoreElements()){
		correo = cor.nextElement();
		String remitente = correo.getEmisor_nombre()+"@"+correo.getEmisor_dominio();
		String asunto = correo.getAsunto();
		String fecha = correo.getFecha();
		
		String carga [] = {remitente, asunto, fecha};	   
	   	modelo.addRow(carga);
		}
	
	return modelo;
	
   }
   
   
   /**
    * Devuelve una tabla con remitente, asunto y fecha de cada correo 
    * elimindo que se cargo en memoria anteriormente desde el disco
    *  para mostrar en la bandeja de eliminados
    * @return modelTable
    */
   public DefaultTableModel DevTablaEliminados(){
	
	String col[] = {"Remitente","Asunto", "Fecha"};
	DefaultTableModel modelo = new DefaultTableModel(col,0);

	Enumeration<Correo> cor = setPapelera.elements();
	Correo correo;
	
	while(cor.hasMoreElements()){
		correo = cor.nextElement();
		String remitente = correo.getEmisor_nombre()+"@"+correo.getEmisor_dominio();
		String asunto = correo.getAsunto();
		String fecha = correo.getFecha();
		
		String carga [] = {remitente, asunto, fecha};	   
	   	modelo.addRow(carga);
		}
	
	return modelo;
	
   }
   
   
   /**
    * Devuelve una tabla con remitente, asunto y fecha de cada 
    * correo spam que se cargo en memoria anteriormente desde 
    * el disco para mostrar en la bandeja de spams
    * @return modelTable
    */
   public DefaultTableModel DevTablaSpam(){
	
	String col[] = {"Remitente","Asunto", "Fecha"};
	DefaultTableModel modelo = new DefaultTableModel(col,0);

	Enumeration<Correo> cor = setSpam.elements();
	Correo correo;
	
	while(cor.hasMoreElements()){
		correo = cor.nextElement();
		String remitente = correo.getEmisor_nombre()+"@"+correo.getEmisor_dominio();
		String asunto = correo.getAsunto();
		String fecha = correo.getFecha();
		
		String carga [] = {remitente, asunto, fecha};	   
	   	modelo.addRow(carga);
		}
	
	return modelo;
	
   }
   
   
   // tabla de correos segun busqueda realizada
   
   /**
    * Devuelve una tabla con remitente, asunto y fecha de cada correo selecionado
    * @param cuentaBusqueda
    * @return modelTable
    */
   public DefaultTableModel DevTablaBusqueda(String cuentaBusqueda){
	
	String col[] = {"Remitente","Asunto", "Fecha"};
	DefaultTableModel modelo = new DefaultTableModel(col,0);

	Enumeration<Correo> cor = setCorreosRecibidos.elements();
	Correo correo;
	
	while(cor.hasMoreElements()){
		correo = cor.nextElement();
		String remitente = correo.getEmisor_nombre()+"@"+correo.getEmisor_dominio();
		
		if(remitente.equals(cuentaBusqueda)){
		   String asunto = correo.getAsunto();
		   String fecha = correo.getFecha();		
		   String carga [] = {remitente, asunto, fecha};	   
	       modelo.addRow(carga);
		}
		}
	
	return modelo;
	
   }
   
   /**
    * Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Borradores
    * @param Fecha
    * @return Correo (Objeto)
    */
   
   public Correo DevolverCorreoBorrador(String Fecha){
	   
	   	return this.setBorradores.get(Fecha);
	}
   
   
   /**
    * Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Bandeja de Salida
    * @param Fecha (String)
    * @return Correo (Objeto)
    */
   
   public Correo DevolverCorreoBandejaSalida(String Fecha){
	   
	   	return this.setBandejaSalida.get(Fecha);
	}
   
/**
 * Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Enviados
 * @param Fecha (String)
 * @return Correo (Objeto)
 */
   
   public Correo DevolverCorreoEnviado(String Fecha){
	   
	   	return this.setCorreosEnviados.get(Fecha);
	}
	
   
/**
 * Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Recibidos
 * @param Fecha (String)
 * @return Correo (Objeto)
 */
   
   public Correo DevolverCorreoRecibido(String Fecha){
	   
	   	return this.setCorreosRecibidos.get(Fecha);
	}
   
   
/**
 *  Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Eliminados
 * @param Fecha (String)
 * @return Correo (Objeto)
 */
   
   public Correo DevolverCorreoEliminado(String Fecha){
	   
	   	return this.setPapelera.get(Fecha);
	}
   
/**
 *  Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Spam
 * @param Fecha (String)
 * @return Correo (Objeto)
 */
   
   public Correo DevolverCorreoSpam(String Fecha){
	   
	   	return this.setSpam.get(Fecha);
	}
   
   
   
  
		public int compareTo(Correo cor1, Correo cor2) {
			return new Integer(cor1.getFecha()).compareTo(new Integer(cor2.getFecha()));
		}
	
   
   


   /**
    * Metodo que devuelve un array list con los correos de determinada conversacion segun id
    * @param idconversacion
    * @param Fecha
    * @return
    */
     public ArrayList<Correo> DevConveracionRecibidos(int idconversacion, String Fecha){

    	
	     Fecha = Fecha.replace(".",""); //Para evitar problemas en el nombre del archivo
	     Fecha = Fecha.replace(" ","");	//Que no haya espacios e unifique todo al una barra
	     Fecha = Fecha.replace(":","");
	     Fecha = Fecha.replace("-", "");	    
	     String fecha_comparar;
	     Enumeration<Correo> cor = setCorreosRecibidos.elements();// cargo todos los correos recibidos en el enumeration para recorrerlo
	     Correo correo;
	     ArrayList<Correo> corconv = new ArrayList<Correo>();
	     while(cor.hasMoreElements()){// minetras quedan objetos del tipo correo recibidos voy fijandome si pertenecen a la conversacion buscada y si es asi los cargo en el array list
		     correo = cor.nextElement();
		     fecha_comparar = correo.getFecha();
		     fecha_comparar = fecha_comparar.replace(".",""); //Para evitar problemas en el nombre del archivo
		     fecha_comparar = fecha_comparar.replace(" ","");	//Que no haya espacios e unifique todo al una barra
		     fecha_comparar = fecha_comparar.replace(":","");
		     fecha_comparar = fecha_comparar.replace("-","");
		    
		   
		     if (correo.getId_conversacion() == idconversacion && Long.parseLong(Fecha) <= Long.parseLong(fecha_comparar) ){
		    	 corconv.add(correo);
		     }
		    
		  Collections.sort(corconv); //Ordena por fecha en el array list		          
		   }
	
	   return corconv;
	
     }
     
     
     
     
	
   
}
