/**
 * 
 */
package logica;



import persistencia.FachadaPers;

/**Clase cuenta Singleton
 * @author 
 *
 */


// Esta clase tiene los datos de la cuenta con la que ingresa el usuario al iniciar el programa. es singleton ya que no se instancia nunca mas y se usan esos datos siempre desde memoria
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
	
	
	 public void crearDirectorioCuenta(){
			
			BD.crearDirectorioCuenta();
			BD.crearDirectorioDominios();
			BD.crearDirectorioAgenda();
		}
	
	
	
	/* Metodo que se ejecuta luego de la validacion del usuario al ingresar al progrma
	 * Lo que hace es crear la carpeta cuentas si no existe y luego los directorios segun el usuario ingresado si antes no habia ingresado
	 */
	public void carga_cuenta(Cuenta cuenta){
		
		try{
			
	       // BD.crearDirectorioCuenta();
	        BD.creaDirectorioUsuario(cuenta.getNom_us(), cuenta.getDominio());
		    BD.crearDirectorios(cuenta.getNom_us(), cuenta.getDominio());
		    BD.creaRutaArchivos();// aca guardo la ruta absoluta de cada carpeta para poder usar despues
		    BD.GuardaConfiguracion(cuenta);// aca guardo el objeto cuenta con los datos de la cuenta que se esta usando
		    
		
	    }catch (Exception e){}
	

}
	
	public boolean busca_cuenta(String archivo, String cuenta){
		
		return BD.ExisteCuenta(archivo, cuenta);
	}
	
	public boolean busca_Passwd(String archivo, String nomcuenta, String passwd){
		
		if(BD.leerCuenta(archivo, nomcuenta).getPasswd().equals(passwd)){
			
			return true;
		}else{
			
			return false;
		}
		
		
	}
	
}
