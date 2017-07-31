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
	private Hashtable<String, Correo> setBorradores;
	
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
				
			if(directorio == FachPer.CarpetaEnviados()){		
				setCorreosEnviados.put(fecha, correo);
			}
			if(directorio == FachPer.CarpetaRecibidos()){
				setCorreosRecibidos.put(fecha, correo);
			}
			
			if(directorio == FachPer.CarpetaBuzonSalida()){
				setBandejaSalida.put(fecha, correo);
			}
			
			if(directorio == FachPer.CarpetaBorradores()){
				setBorradores.put(fecha, correo);
			}
			 
		}
		
	}
	
	/**
	 * Inserta en la collecccion de correos
	 * @param directorio (String): Indica si es enviados, recibidos, etc.
	 * @param correo: Pasa el correo a insertar
	 */
	
	//Para insertar los correos en cada secuencia (Enviados, Borradores, etc)
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
	}
	
	/**
	 * ModelTable para los Correos Enviados
	 * @return modelTable Carpeta Enviados
	 */
	
	// Devuelve una tabla con destintario y asunto de cada correo Enviado que se cargo en memoria anteriormente desde el disco para mostrar en la bandeja de salida
	
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
	
}
