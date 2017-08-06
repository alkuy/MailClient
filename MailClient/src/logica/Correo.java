package logica;
import java.io.IOException;

import persistencia.FachadaPers;

public class Correo implements Comparable<Correo> {
	
	private int id_conversacion;
	private String fecha;
	private String asunto;
	private String emisor_nombre;
	private String emisor_dominio;
	private String destinatario;
	private String destinatario_dominio;
	private String texto;

	FachadaPers FachPer = FachadaPers.getInstancia();
	
	public Correo(){
		
	}

	public int getId_conversacion() {
		return id_conversacion;
	}

	public void setId_conversacion(int id_conversacion) {
		this.id_conversacion = id_conversacion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	
	/**
	 * Guarda los correos en el disco
	 * @param directorio
	 */
		
	public void GuardaCorreo(String directorio){
		
		try {
			FachPer.GuardaCorreo(directorio, this); // llama al metodo de fahcada de persistencia para guardar el correo en el directorio indicado
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	//sobreescribo el metodo para comparar por fecha
	
    @Override
    public int compareTo(Correo o) {
       String a=new String(String.valueOf(this.getFecha()));
       String b=new String(String.valueOf(o.getFecha()));
       return a.compareTo(b);
    }
	
	
}


