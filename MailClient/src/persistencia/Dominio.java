package persistencia;

public class Dominio {
	private String nombre_dominio;
	private int prioridad;
	
	public Dominio(String NomDom, int P){
		this.nombre_dominio = NomDom;
		this.prioridad = P;
	}
	
	public Dominio(){
	}

	/**
	 * @return el nombre_dominio
	 */
	public String getNombre_dominio() {
		return nombre_dominio;
	}

	/**
	 * @param nombre_dominio el nombre_dominio a establecer
	 */
	public void setNombre_dominio(String nombre_dominio) {
		this.nombre_dominio = nombre_dominio;
	}

	/**
	 * @return el prioridad
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * @param prioridad el prioridad a establecer
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
	
}
