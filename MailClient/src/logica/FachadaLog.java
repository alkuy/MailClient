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
		
		correo.CargaCorreo(directorio);
		correos.Insertar(directorio, correo);

	}
	/**
	 * Print de los correos. Funcion de pruebas
	 * @param directorio
	 */
	public void Muestra_Correos(String directorio){
		correos.cargaCorreos(directorio);
		for (int i=0; i<correos.getSetCorreos().size(); i++){
			System.out.println(correos.getSetCorreos().get(i).getAsunto());
		}
	}
	
	public DefaultTableModel DevCorreosEnviados() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaCorreosEnviados();
		return modelo;
	}
	
	/** Metodo para pasar los datos de la cuenta a persistencia y dejar el ojeto cuenta instanciado para usar
	 * 
	 * @param Nom_us nombre de usuario de la cuenta
	 * @param Dom dominio del usuario de la cuenta
	 * @param Pas clave de la cuenta
	 */
	public void Nueva_cuenta(String Nom_us, String Dom, String Pas){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		cuenta.setNom_us(Nom_us);
		cuenta.setDominio(Dom);
		cuenta.setContraseña_cuenta(Pas);		
		cuenta.carga_cuenta(cuenta);
		
		
	}
	
   public String Devuelve_us_cuenta(){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		
		return cuenta.getNom_us();
		
		
	}
   
   public String Devuelve_dom_cuenta(){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		
		return cuenta.getDominio();
		
		
	}
   
   public String Devuelve_pas_cuenta(){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		
		return cuenta.getContraseña_cuenta();
		
		
	}
   
   
   public String Devuelve_Ruta_Borradores(){
	   
	   return configuracion.devRutaBorradores();
   }
   
   
   public String Devuelve_Ruta_Recibidos(){
		
 		return configuracion.devRutaRecibidos();
 	}

    public String Devuelve_Ruta_Enviados(){
 		
 		return configuracion.devRutaEnviados();
 	}
    
    public String Devuelve_Ruta_BuzonSalida(){
 		
 		return configuracion.devRutaBuzonSalida();
 	}
    
    public String Devuelve_Ruta_Configuracion(){
 		
 		return configuracion.devRutaConfiguracion();
 	}
	
	
	
}
