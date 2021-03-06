package persistencia;

import java.io.Serializable;

// clase correo implementando la interfaz serializable para poder guardar cada correo como un objeto.

public class Correo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_conversacion;
	private String fecha;
	private String asunto;
	private String emisor_nombre;
	private String emisor_dominio;
	private String destinatario;
	private String destinatario_dominio;
	private String texto;
	
	
	public Correo(){
		
	}


	public int getId_conversacion() {
		return id_conversacion;
	}


	public void setId_conversacion(int id_conversacion) {
		this.id_conversacion = id_conversacion;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public String getEmisor_nombre() {
		return emisor_nombre;
	}


	public void setEmisor_nombre(String emisor_nombre) {
		this.emisor_nombre = emisor_nombre;
	}


	public String getEmisor_dominio() {
		return emisor_dominio;
	}

	public void setEmisor_dominio(String emisor_dominio) {
		this.emisor_dominio = emisor_dominio;
	}


	
	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public String getDestinatario_dominio() {
		return destinatario_dominio;
	}


	public void setDestinatario_dominio(String destinatario_dominio) {
		this.destinatario_dominio = destinatario_dominio;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
}
