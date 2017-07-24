package logica;
import java.util.ArrayList;

import persistencia.FachadaPers;

public class Correos {
	
	FachadaPers FachPer = FachadaPers.getInstancia();

	
	private static Correos instancia;
	
	private static ArrayList<Correo> setCorreosEnviados;
	private ArrayList<Correo> setCorreosRecibidos;
	
	public static Correos getInstancia(){
		if(instancia == null)
			instancia = new Correos();
		
		return instancia;
	}
	
	/** Método constructor de la colección Correos. */
	public Correos() {
		this.setCorreosEnviados = new ArrayList<Correo>();
	}
		
	/** Método que retorna la coleccion Set de Correos Enviados.
	 * <BR><b>Precondición</b>: la colección no debe ser vacía.</BR>
	 * @return Retorna el set de Correos. */
	public ArrayList<Correo> getSetCorreos() {
		return setCorreosEnviados;
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
			
			if(directorio == FachPer.CarpetaEnviados()){
				setCorreosEnviados.add(correo);
			}
			if(directorio == FachPer.CarpetaRecibidos()){
				setCorreosRecibidos.add(correo);
			}
			 
		}
		
	}

	
}
