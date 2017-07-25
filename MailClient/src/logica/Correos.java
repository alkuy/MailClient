package logica;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import persistencia.FachadaPers;

public class Correos {
	
	FachadaPers FachPer = FachadaPers.getInstancia();

	
	private static Correos instancia;
	
	private ArrayList<Correo> setCorreosEnviados;
	private ArrayList<Correo> setCorreosRecibidos;
	
	public static Correos getInstancia(){
		if(instancia == null)
			instancia = new Correos();
		
		return instancia;
	}
	
	/** M�todo constructor de la colecci�n Correos. */
	public Correos() {
		this.setCorreosEnviados = new ArrayList<Correo>();
	}
		
	/** M�todo que retorna la coleccion Set de Correos Enviados.
	 * <BR><b>Precondici�n</b>: la colecci�n no debe ser vac�a.</BR>
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
	
	/**
	 * Inserta en la collecccio de correos
	 * @param directorio (String): Indica si es enviados, recibidos, etc.
	 * @param correo: Pasa el correo a insertar
	 */
	public void Insertar(String directorio, Correo correo){
		if(directorio == FachPer.CarpetaEnviados()){
			setCorreosEnviados.add(correo);
		}
		if(directorio == FachPer.CarpetaRecibidos()){
			setCorreosRecibidos.add(correo);
		}
	}
	
	
	public DefaultTableModel DevTablaCorreosEnviados(){
		
		String col[] = {"Destinatario","Asunto"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		
		
		for (int i=0; i < setCorreosEnviados.size(); i++){
			String destino = setCorreosEnviados.get(i).getDestinatario()+setCorreosEnviados.get(i).getDestinatario_dominio();
			String asunto = setCorreosEnviados.get(i).getAsunto();
			
			String carga [] = {destino, asunto};	   
		   	modelo.addRow(carga);
			}
		
		return modelo;
		
	}	

	
}
