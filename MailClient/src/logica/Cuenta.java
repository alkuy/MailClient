/**
 * 
 */
package logica;



import persistencia.FachadaPers;

/**Clase cuenta Singleton
 * @author 
 *
 */
public class Cuenta {
	
	
  private static Cuenta instancia;
  private String nom_us; 
  private String contrase�a_cuenta;
  private String dominio;
  
  
  private Cuenta(){};
	
  public static Cuenta getInstancia() {
	if(instancia == null){
		instancia = new Cuenta();
	}
	return instancia;
}

  FachadaPers BD = FachadaPers.getInstancia();
	
	
	
		
  /*   public Cuenta(String nom, String dom, String pas){
    	 
    	nom_us = nom;
    	contrase�a_cuenta= pas;
        dominio=dom;
		
	}*/
	
		/**
	 * @return the nom_us
	 */
	public String getNom_us() {
		return nom_us;
	}

	/**
	 * @param nom_us the nom_us to set
	 */
	public void setNom_us(String nom_us) {
		this.nom_us = nom_us;
	}

	/**
	 * @return the contrase�a_cuenta
	 */
	public String getContrase�a_cuenta() {
		return contrase�a_cuenta;
	}

	/**
	 * @param contrase�a_cuenta the contrase�a_cuenta to set
	 */
	public void setContrase�a_cuenta(String contrase�a_cuenta) {
		this.contrase�a_cuenta = contrase�a_cuenta;
	}

	/**
	 * @return the dominio
	 */
	public String getDominio() {
		return dominio;
	}

	/**
	 * @param dominio the dominio to set
	 */
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	
	public void carga_cuenta(Cuenta cuenta){
		
		try{
			
	        BD.crearDirectorioCuenta();
	        BD.creaDirectorioUsuario(cuenta.getNom_us(), cuenta.getDominio());
		    BD.crearDirectorios(cuenta.getNom_us(), cuenta.getDominio());
		    BD.creaRutaArchivos();// aca guardo la ruta absoluta de cada cuenta para poder usar despues
		    BD.GuardaConfiguracion(cuenta);// aca guardo el objeto cuenta con los datos de la cuenta que se esta usando
		    
		
	    }catch (Exception e){}
	

}
	
}
