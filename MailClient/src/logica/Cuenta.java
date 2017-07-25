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
	private String contraseña_cuenta;
	private String dominio;
	
	
    
	/**
	 * Constructor de la clase vacio
	 * */
	public Cuenta(){
		
	}
	
     public Cuenta(String nom, String dom, String pas){
    	 
    	nom_us = nom;
    	contraseña_cuenta= pas;
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
	 * @return the contraseña_cuenta
	 */
	public String getContraseña_cuenta() {
		return contraseña_cuenta;
	}

	/**
	 * @param contraseña_cuenta the contraseña_cuenta to set
	 */
	public void setContraseña_cuenta(String contraseña_cuenta) {
		this.contraseña_cuenta = contraseña_cuenta;
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
