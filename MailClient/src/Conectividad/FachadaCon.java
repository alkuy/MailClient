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
	public boolean EnviaCorreo(String NomUsu, String PassUsu, String MailUsu, String MailDest, String Asunto, String Texto) throws IOException, MessagingException, UserException, InterruptedException{
		boolean realizado;
		ConSocket S = new ConSocket();
		EnviaCorreo C = new EnviaCorreo(NomUsu, PassUsu, MailUsu, MailDest, Asunto, Texto);
		//S.UsuToServer();
		realizado = C.Enviar();
		return realizado;
	}
	
	public boolean RecibeCorreo(){
		boolean realizado;
		ConSocket S = new ConSocket();
		realizado = S.UsuToServer();
		return realizado;
	}
	
	public void IniSocket(){
		ConSocket S = new ConSocket();
		S.ConToServer();
	}
	
	public String Login(String Usu, String NomDom, String Pass){
		ConSocket S = new ConSocket();
		return S.Login(Usu, NomDom, Pass);
	}
	
	public void CorreosAntiguos(){
		ConSocket S = new ConSocket();
		S.OldToServer();
	}
	
	public boolean CambioPass(String nombre, String dominio,String Pass, String OldPass){
		boolean realizado;
		ConSocket S = new ConSocket();
		realizado = S.CambioPassToServer(nombre, dominio,OldPass,Pass);
		return realizado;
	}
}
