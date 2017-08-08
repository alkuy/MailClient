package Conectividad;

import java.io.IOException;

import javax.mail.MessagingException;

import com.icegreen.greenmail.user.UserException;

import logica.FachadaLog;

public class FachadaCon {

	private FachadaLog FL = FachadaLog.getInstancia();
	private static FachadaCon instancia;
	
	//Esta clase utiliza el patron Singleton
	public static FachadaCon getInstancia(){
		if (instancia == null)
			instancia = new FachadaCon();
			
		return instancia;
	}
	
	//Envia el correo hacia el Servidor
	public void EnviaCorreo(String NomUsu, String PassUsu, String MailUsu, String MailDest, String Asunto, String Texto) throws IOException, MessagingException, UserException, InterruptedException{
		ConSocket S = new ConSocket();
		EnviaCorreo C = new EnviaCorreo(NomUsu, PassUsu, MailUsu, MailDest, Asunto, Texto);
		S.UsuToServer();
		C.Enviar();	
	}
	
	public void IniSocket(){
		ConSocket S = new ConSocket();
		S.ConToServer();
	}
	
	public String Login(String Usu, String NomDom, String Pass){
		ConSocket S = new ConSocket();
		return S.Login(Usu, NomDom, Pass);
	}
}
