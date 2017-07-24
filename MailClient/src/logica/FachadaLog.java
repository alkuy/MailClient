package logica;

import java.io.IOException;

public class FachadaLog {
	
	private static FachadaLog instancia;

	
	public static FachadaLog getInstancia() {
		if(instancia == null)
			instancia = new FachadaLog();
		
		return instancia;
	}

	Correos correos = Correos.getInstancia();

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
		correos.cargaCorreos(directorio);

	}
	
}
