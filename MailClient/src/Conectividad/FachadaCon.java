package Conectividad;

public class FachadaCon {

	private static FachadaCon instancia;
	
	//Esta clase utiliza el patron Singleton
	public static FachadaCon getInstancia(){
		if (instancia == null)
			instancia = new FachadaCon();
			
		return instancia;
	}
	
	//Envia el correo hacia el Servidor
	public void EnviaCorreo(String NomUsu, String PassUsu, String MailUsu, String MailDest, String Asunto, String Texto){
		EnviaCorreo C = new EnviaCorreo(NomUsu, PassUsu, MailUsu, MailDest, Asunto, Texto);
		C.Enviar();
	}
	
	public void IniSocket(){
		ConSocket S = new ConSocket();
		S.ConToServer();
	}
}
