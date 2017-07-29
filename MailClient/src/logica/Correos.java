package logica;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import persistencia.FachadaPers;

public class Correos {
	
	FachadaPers FachPer = FachadaPers.getInstancia();

	
	private static Correos instancia;
	
	private ArrayList<Correo> setCorreosEnviados;
	private ArrayList<Correo> setCorreosRecibidos;
	private ArrayList<Correo> setBandejaSalida;
	
	public static Correos getInstancia(){
		if(instancia == null)
			instancia = new Correos();
		
		return instancia;
	}
	
	/** Método constructor de la colección Correos. */
	public Correos() {
		this.setCorreosEnviados = new ArrayList<Correo>();
		this.setCorreosRecibidos = new ArrayList<Correo>();
		this.setBandejaSalida = new ArrayList<Correo>();
		
	}
		
	/** Método que retorna la coleccion Set de Correos Recibidos.
	 * <BR><b>Precondición</b>: la colección no debe ser vacía.</BR>
	 * @return Retorna el set de Correos. */
	public ArrayList<Correo> getSetCorreosRecibidos() {
		return setCorreosRecibidos;
	}
	
	/** Método que retorna la coleccion Set de la Bandeja de salida.
	 * <BR><b>Precondición</b>: la colección no debe ser vacía.</BR>
	 * @return Retorna el set de Correos. */
	public ArrayList<Correo> getSetBandejaSalida() {
		return setBandejaSalida;
	}
	
	/** Método que retorna la coleccion Set de Correos Enviados.
	 * <BR><b>Precondición</b>: la colección no debe ser vacía.</BR>
	 * @return Retorna el set de Correos. */
	public ArrayList<Correo> getSetCorreosEnviados() {
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
			
			if(directorio == FachPer.CarpetaBuzonSalida()){
				setBandejaSalida.add(correo);
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
		if(directorio == FachPer.CarpetaBuzonSalida()){
			setBandejaSalida.add(correo);
		}
	}
	
	/**
	 * ModelTable para los Correos Enviados
	 * @return modelTable Carpeta Enviados
	 */
	public DefaultTableModel DevTablaCorreosEnviados(){
		
		String col[] = {"Destinatario","Asunto"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);
		
		
		for (int i=0; i < setCorreosEnviados.size(); i++){
			String destino = setCorreosEnviados.get(i).getDestinatario()+"@"+setCorreosEnviados.get(i).getDestinatario_dominio();
			String asunto = setCorreosEnviados.get(i).getAsunto();
			
			String carga [] = {destino, asunto};	   
		   	modelo.addRow(carga);
			}
		
		return modelo;
		
	}	
	
	/**
	 * ModelTable para Bandeja de salida
	 * @return modelTable Bandeja de Salida
	 */
public DefaultTableModel DevTablaBandejaSalida(){
		
		String col[] = {"Destinatario","Asunto"};
		DefaultTableModel modelo = new DefaultTableModel(col,0);

		
		for (int i=0; i < setBandejaSalida.size(); i++){
			String destino = setBandejaSalida.get(i).getDestinatario()+"@"+setBandejaSalida.get(i).getDestinatario_dominio();
			String asunto = setBandejaSalida.get(i).getAsunto();
			
			String carga [] = {destino, asunto};	   
		   	modelo.addRow(carga);
			}
		
		return modelo;
		
	}	

	
}
