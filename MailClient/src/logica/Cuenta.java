/**
 * 
 */
package logica;


import persistencia.FachadaPers;

/**
 * @author 
 *
 */
public class Cuenta {
	
	
FachadaPers BD = FachadaPers.getInstancia();
	
	
	private String nom_us; 
	private String contrase�a_cuenta;
	private String dominio;
	
	
    
	/**
	 * Constructor de la clase vacio
	 * */
	public Cuenta(){
		
	}
	
     public Cuenta(String nom, String dom, String pas){
    	 
    	nom_us = nom;
    	contrase�a_cuenta= pas;
        dominio=dom;
		
	}
	
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
	
	
	public void carga_cuenta(String nom, String dom, String pas){
		
		try{
			
	    BD.crearDirectorioCuenta();	
		String directorio;		 
		directorio ="c:\\Cuentas\\"+nom+"-"+dom+"\\";
		String directorio_cuenta = directorio+"\\";
		Cuenta cuenta = new Cuenta(nom,dom,pas);
		BD.creaDirectorioConfig(directorio);
		BD.crearDirectorios(directorio);
		String directorio2 ="c:\\Cuentas\\"+nom+"-"+dom+"\\Configuracion\\";
		BD.GuardaConfiguracion(directorio2, cuenta); // aca guardo el objeto cuento con los datos de la cuenta que se esta usando
	}catch (Exception e){}
	

}
	
}
