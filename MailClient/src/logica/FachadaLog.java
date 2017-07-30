package logica;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class FachadaLog {
	
	private static FachadaLog instancia;

	
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
		correos.Insertar(directorio, correo); //crea el set correspondiente en memoria

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
		
		}
	
	
	// metoo que devuelve una tabla para mostrar correos enviados
	public DefaultTableModel DevCorreosEnviados() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaCorreosEnviados();
		return modelo;
	}
	
	
	// metoo que devuelve una tabla para mostrar Bandeja de Salida
	public DefaultTableModel DevBandejaSalida() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaBandejaSalida();
		return modelo;
	}
	
	// metoo que devuelve una tabla para mostrar Borradores
	public DefaultTableModel DevBorradores() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaBorradores();
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
	
	
	   // devuelve el nombre de usuario actual logeado
   public String Devuelve_us_cuenta(){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		
		return cuenta.getNom_us();
		
		
	}
   
   // Devuelve el dominio de la cuneta de usuario actual
   
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
	
	
	
}
