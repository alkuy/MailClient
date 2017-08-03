package logica;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.table.DefaultTableModel;
import persistencia.FachadaPers;

public class Correos {
	
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
	
	/** M�todo constructor de la colecci�n Correos. */
	public Correos() {
		this.setCorreosEnviados = new Hashtable<String, Correo>();
		this.setCorreosRecibidos = new Hashtable<String, Correo>();
		this.setBandejaSalida = new Hashtable<String, Correo>();
		this.setBorradores = new Hashtable<String, Correo>();
		this.setPapelera = new Hashtable<String, Correo>();
		this.setSpam = new Hashtable<String, Correo>();
	}
		
	/** M�todo que retorna la coleccion Set de Correos Recibidos.
	 * <BR><b>Precondici�n</b>: la colecci�n no debe ser vac�a.</BR>
	 * @return Retorna el set de Correos. */
	public Hashtable<String, Correo> getSetCorreosRecibidos() {
		return setCorreosRecibidos;
	}
	
	/** M�todo que retorna la coleccion Set de la Bandeja de salida.
	 * <BR><b>Precondici�n</b>: la colecci�n no debe ser vac�a.</BR>
	 * @return Retorna el set de Correos. */
	public Hashtable<String, Correo> getSetBandejaSalida() {
		return setBandejaSalida;
	}
	
	/** M�todo que retorna la coleccion Set de Correos Enviados.
	 * <BR><b>Precondici�n</b>: la colecci�n no debe ser vac�a.</BR>
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
		
		if(directorio == FachPer.CarpetaBorradores()){
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
	} 
	
	/**
	 * Envia a la papelera los correos. No es eliminacion definitiva
	 * @param directorio
	 * @param clave
	 */
	public void Eliminar_Correo(String directorio, String clave){
		String papelera = FachPer.CarpetaPapelera();
		Correo correo = new Correo();
		correo = obtenerCorreo(directorio, clave); //Cargo el correo para poder pasarlo al Hashtable papelera
		Eliminar(directorio, clave); //Lo elimina del Hashtable
		Insertar(papelera, clave, correo); //Insero en Hashtable Tapelera
		FachPer.MoverdeCarpeta(directorio, clave, papelera); //Muevo el arvhivo fisico de carpeta
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


   //Devuelve una tabla con destintario y asunto de cada correo de Boradores que se cargo en memoria anteriormente desde el disco para mostrar en la bandeja de salida
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
   
   
   //Devuelve una tabla con remitente, asunto y fecha de cada correo recibido que se cargo en memoria anteriormente desde el disco para mostrar en la bandeja de entrada
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
   
   
   //Devuelve una tabla con remitente, asunto y fecha de cada correo elimindo que se cargo en memoria anteriormente desde el disco para mostrar en la bandeja de eliminados
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
   
   
   //Devuelve una tabla con remitente, asunto y fecha de cada correo spam que se cargo en memoria anteriormente desde el disco para mostrar en la bandeja de spams
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
   
   // Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Borradores
   
   public Correo DevolverCorreoBorrador(String Fecha){
	   
	   	return this.setBorradores.get(Fecha);
	}
   
   
   // Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Bandeja de Salida
   
   public Correo DevolverCorreoBandejaSalida(String Fecha){
	   
	   	return this.setBandejaSalida.get(Fecha);
	}
   
// Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Enviados
   
   public Correo DevolverCorreoEnviado(String Fecha){
	   
	   	return this.setCorreosEnviados.get(Fecha);
	}
	
   
// Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Recibidos
   
   public Correo DevolverCorreoRecibido(String Fecha){
	   
	   	return this.setCorreosRecibidos.get(Fecha);
	}
   
   
// Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Eliminados
   
   public Correo DevolverCorreoEliminado(String Fecha){
	   
	   	return this.setPapelera.get(Fecha);
	}
   
// Metodo que devuelve el objeto correo seleccionado segun fecha de la coleccion Spam
   
   public Correo DevolverCorreoSpam(String Fecha){
	   
	   	return this.setSpam.get(Fecha);
	}
	
   
}
