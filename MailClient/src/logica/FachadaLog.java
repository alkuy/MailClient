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
	
	
	public void Nueva_cuenta(String Nom_us, String Dom, String Pas){
		
		Cuenta cuenta = new Cuenta();
		
		/*cuenta.setNom_us(Nom_us);
		cuenta.setDominio(Dom);
		cuenta.setContraseña_cuenta(Pas);*/
		
		cuenta.carga_cuenta(Nom_us, Dom, Pas);
		
		
	}
	
}
