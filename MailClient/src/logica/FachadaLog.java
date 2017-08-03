package logica;

import java.io.IOException;
import java.sql.SQLException;
import persistencia.FachadaPers;
import javax.swing.table.DefaultTableModel;

public class FachadaLog {
	
	private static FachadaLog instancia;
	private FachadaPers BD = FachadaPers.getInstancia();
	//private Correos hcorreo;

	
	public static FachadaLog getInstancia() {
		if(instancia == null)
			instancia = new FachadaLog();
		
		return instancia;
	}

	Configuracion configuracion = new Configuracion();
	Cuenta cuenta = Cuenta.getInstancia();
	Correos correos = Correos.getInstancia();

	/**
	 * Guarda el correo en archivo y lo carga en SetList
	 * @param directorio (String):carpeta donde se va a guardar
	 * @param conversacion(Int):
	 * @param asunto(String):
	 * @param emisor(String): Nombre del emisor
	 * @param e_dom(String)
	 * @param receptor(String)
	 * @param r_dom(String)
	 * @param texto(String)
	 * @param fecha(String)
	 */
	public void Guarda_Correo(String directorio, int conversacion, String asunto, String emisor, String e_dom,
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
		
		correo.GuardaCorreo(directorio); // carga los correso desde el disco segun la ruta que se le indique
		correos.Insertar(directorio, fecha, correo); //crea el set correspondiente en memoria

	}
	/**
	 * Print de los correos. Funcion de pruebas
	 * @param directorio
	 */
	public void Muestra_Correos(String directorio){
		correos.cargaCorreos(directorio);
		for (int i=0; i<correos.getSetCorreosEnviados().size(); i++){
			System.out.println(correos.getSetCorreosEnviados().get(i).getAsunto());
		}
	}
	
	
	// Este metodo carga los correos de todas las carpetas en memoria
	
	public void cargaTodoenMemoria(){					
		correos.cargaCorreos(this.Devuelve_Ruta_Enviados());			
		correos.cargaCorreos(this.Devuelve_Ruta_BuzonSalida());
		correos.cargaCorreos(this.Devuelve_Ruta_Recibidos());
		correos.cargaCorreos(this.Devuelve_Ruta_Borradores());
		correos.cargaCorreos(this.Devuelve_Ruta_Papelera());
		correos.cargaCorreos(this.Devuelve_Ruta_Spam());
		}
	
	
	// metoo que devuelve una tabla para mostrar correos enviados
	public DefaultTableModel DevCorreosEnviados() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaCorreosEnviados();
		return modelo;
	}
	
	
	/**
	 * metodo que devuelve una tabla para mostrar Bandeja de Salida
	 * @return TableModel
	 * @throws SQLException
	 */
	public DefaultTableModel DevBandejaSalida() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaBandejaSalida();
		return modelo;
	}
	
	/**
	 * metodo que devuelve una tabla para mostrar Borradores
	 * @return TableModel
	 * @throws SQLException
	 */
	public DefaultTableModel DevBorradores() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaBorradores();
		return modelo;
	}
	
	
	/**
	 *  metodo que devuelve una tabla para mostrar Recibidos
	 * @return TableModel
	 * @throws SQLException
	 */
		public DefaultTableModel DevRecibidos() throws SQLException{
			DefaultTableModel modelo;
			modelo = correos.DevTablaRecibidos();
			return modelo;
		}
		
		/**
		 *  metodo que devuelve una tabla para mostrar Eliminados
		 * @return TableModel
		 * @throws SQLException
		 */
		public DefaultTableModel DevPapelera() throws SQLException{
			DefaultTableModel modelo;
			modelo = correos.DevTablaEliminados();
			return modelo;
			}
			
		/**
		 *  metodo que devuelve una tabla para mostrar Spam
		 * @return TableModel
		 * @throws SQLException
		*/
		public DefaultTableModel DevSpam() throws SQLException{
				DefaultTableModel modelo;
				modelo = correos.DevTablaSpam();
				return modelo;
				}
		
		
		/**
		 *  metodo que devuelve una tabla para mostrar la busqueda realizada
		 * @return TableModel
		 * @throws SQLException
		 */
			public DefaultTableModel DevBusqueda(String cuentaBusqueda) throws SQLException{
				DefaultTableModel modelo;
				modelo = correos.DevTablaBusqueda(cuentaBusqueda);
				return modelo;
			}
	
	/** Metodo para pasar los datos de la cuenta a persistencia y dejar el ojeto cuenta instanciado para usar
	 * 
	 * @param Nom_us nombre de usuario de la cuenta
	 * @param Dom dominio del usuario de la cuenta
	 * @param Pas clave de la cuenta
	 */
	public void Nueva_cuenta(String Nom_us, String Dom, String Pas){
		
		Cuenta cuenta = Cuenta.getInstancia();	// se crea la instancia de la cuenta que se usara en adelante
		cuenta.setNom_us(Nom_us); // se cargan a cuenta los datos de ingreso de usuario
		cuenta.setDominio(Dom);
		cuenta.setContraseña_cuenta(Pas);		
		cuenta.carga_cuenta(cuenta); 
			
	}
	
	
	/**
	 * devuelve el nombre de usuario actual logeado
	 * @return String (Nombre de usuario)
	 */
   public String Devuelve_us_cuenta(){
		Cuenta cuenta = Cuenta.getInstancia();	
		return cuenta.getNom_us();
	}
   
   /**
    *  Devuelve el dominio de la cuneta de usuario actual
    * @return String (dominio de cuenta)
    */
   
   public String Devuelve_dom_cuenta(){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		
		return cuenta.getDominio();
		
		
	}
   
   //Devuelve el passwd de la cuenta de usuario actual
   
   public String Devuelve_pas_cuenta(){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		
		return cuenta.getContraseña_cuenta();
		
		
	}
   
   // devuelve la ruta absoluta de Borradores
   public String Devuelve_Ruta_Borradores(){
	   
	   return configuracion.devRutaBorradores();
   }
   
   // devuelve la ruta absoluta de Recibidos
   public String Devuelve_Ruta_Recibidos(){
		
 		return configuracion.devRutaRecibidos();
 	}

   // devuelve la ruta absoluta de Enviados
    public String Devuelve_Ruta_Enviados(){
 		
 		return configuracion.devRutaEnviados();
 	}
   
    // devuelve la ruta absoluta de Buzon de Salida
    public String Devuelve_Ruta_BuzonSalida(){
 		
 		return configuracion.devRutaBuzonSalida();
 	}
    
    // devuelve la ruta absoluta de Configuarcion
    public String Devuelve_Ruta_Configuracion(){
 		
 		return configuracion.devRutaConfiguracion();
 	}
    
    // devuelve la ruta absoluta de Papelera
    public String Devuelve_Ruta_Papelera(){
 		
 		return configuracion.devRutaPapelera();
 	}
    
 // devuelve la ruta absoluta de Spam
    public String Devuelve_Ruta_Spam(){
 		
 		return configuracion.devRutaSpam();
 	}
	
    // devuelve el correo borrador seleccionado a partir de la fecha de creacion
    
    public Correo DevuelveBorrador(String Fecha){
    	 
    	return correos.DevolverCorreoBorrador(Fecha);
    	
    }
    
 /**
  *  devuelve el correo de bandeja de salida seleccionado a partir de la fecha de creacion
  * @param Fecha
  * @return Objeto Correo
  */
    
    public Correo DevuelveBandejaSalida(String Fecha){
    	  
    	return correos.DevolverCorreoBandejaSalida(Fecha);
    	
    }
    
 /**
  * devuelve el correo Enviado seleccionado a partir de la fecha de creacion
  * @param Fecha
  * @return Objeto Correo
  */
    
    public Correo DevuelveEnviado(String Fecha){
    	  
    	return correos.DevolverCorreoEnviado(Fecha);
    	
    }
    
/**
 * devuelve el correo Enviado seleccionado a partir de la fecha de creacion
 * @param Fecha
 * @return objeto Correo
 */
    
    public Correo DevuelveRecibido(String Fecha){
    	  
    	return correos.DevolverCorreoRecibido(Fecha);
    	
    }
    
    public void crearDirectorioCuenta(){
		
		cuenta.crearDirectorioCuenta();
	}
	
	/**
	 * Devuelve el array de Dominios
	 * @return String[]
	 */
    public String[] GetDom(){
    	String[] Dominios;
    	Dominios = BD.GetDominio();
    	return Dominios;
    }
    
    /**
     * Elimina correo de hastable y persistencia
     *  @param clave 
     */
    public void EliminarCorreo(String directorio, String clave){
    	correos.Eliminar_Correo(directorio, clave);
    	
    }
    
    public void EliminarCorreoDefinitivo(String directorio, String clave){
    	correos.Eliminar_Definitivo(directorio, clave);
    }
}
