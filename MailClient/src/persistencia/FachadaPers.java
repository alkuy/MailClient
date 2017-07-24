package persistencia;

import java.util.ArrayList;
import java.io.*;

public class FachadaPers {
	
	
	private static FachadaPers instancia;
	private String correos[];
	Configuracion conf = new Configuracion();
	
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
	
	/**
	 * Metodo que lista los archivos del deirectorio
	 * @param directorio que queremos leer
	 * @return Array de String con los nombres de los archivos
	 */
	public String[] traeCorreos (String directorio){
		int dir_largo = directorio.length();
		Archivos arch = new Archivos();
		File[]ficheros = arch.listadoDirectorio(directorio);
		String correos[] = new String[ficheros.length];
		String correo;
		int i = 0;
		for(File item: ficheros){
			correo = item.toString();
			correo = correo.substring(dir_largo);
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
	
	public String getEmisorDom(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getEmisor_dominio();
	}
	
	public String getReceptorNom(String directorio, String archivo){
		Archivos arch = new Archivos();
		Correo correo = new Correo();
		correo = arch.leer(directorio, archivo);
		return correo.getDestinatario();
	}
	
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
		System.out.println("Guardando "+directorio);
	}
	
	public String CarpetaEnviados(){
		return conf.getCarpetaEnviados();
	}
	
	public String CarpetaRecibidos(){
		return conf.getCarpetaRecibidos();
	}
	
}
