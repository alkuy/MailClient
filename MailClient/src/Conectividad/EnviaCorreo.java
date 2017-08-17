package Conectividad;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.icegreen.greenmail.util.ServerSetupTest;
import com.sun.mail.smtp.SMTPTransport;


public class EnviaCorreo {

    private String USER_PASSWORD;
    private String USER_NAME;
    private String EMAIL_USER_ADDRESS;
    private String EMAIL_TO;
    private String EMAIL_SUBJECT;
    private String EMAIL_TEXT;
    private static final String LOCALHOST = "127.0.0.1";
    
    public EnviaCorreo(String NomUsu, String PassUsu, String MailUsu, String MailDest, String Asunto, String Texto){
    	this.USER_NAME = NomUsu;
    	this.USER_PASSWORD = PassUsu;
    	this.EMAIL_USER_ADDRESS = MailUsu;
    	this.EMAIL_TO = MailDest;
    	this.EMAIL_SUBJECT = Asunto;
    	this.EMAIL_TEXT = Texto;
	}
    
    
    
	/**
	 * @return el uSER_PASSWORD
	 */
	public String getUSER_PASSWORD() {
		return USER_PASSWORD;
	}



	/**
	 * @param uSER_PASSWORD el uSER_PASSWORD a establecer
	 */
	public void setUSER_PASSWORD(String uSER_PASSWORD) {
		USER_PASSWORD = uSER_PASSWORD;
	}



	/**
	 * @return el uSER_NAME
	 */
	public String getUSER_NAME() {
		return USER_NAME;
	}



	/**
	 * @param uSER_NAME el uSER_NAME a establecer
	 */
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}



	/**
	 * @return el eMAIL_USER_ADDRESS
	 */
	public String getEMAIL_USER_ADDRESS() {
		return EMAIL_USER_ADDRESS;
	}



	/**
	 * @param eMAIL_USER_ADDRESS el eMAIL_USER_ADDRESS a establecer
	 */
	public void setEMAIL_USER_ADDRESS(String eMAIL_USER_ADDRESS) {
		EMAIL_USER_ADDRESS = eMAIL_USER_ADDRESS;
	}



	/**
	 * @return el eMAIL_TO
	 */
	public String getEMAIL_TO() {
		return EMAIL_TO;
	}



	/**
	 * @param eMAIL_TO el eMAIL_TO a establecer
	 */
	public void setEMAIL_TO(String eMAIL_TO) {
		EMAIL_TO = eMAIL_TO;
	}



	/**
	 * @return el eMAIL_SUBJECT
	 */
	public String getEMAIL_SUBJECT() {
		return EMAIL_SUBJECT;
	}



	/**
	 * @param eMAIL_SUBJECT el eMAIL_SUBJECT a establecer
	 */
	public void setEMAIL_SUBJECT(String eMAIL_SUBJECT) {
		EMAIL_SUBJECT = eMAIL_SUBJECT;
	}



	/**
	 * @return el eMAIL_TEXT
	 */
	public String getEMAIL_TEXT() {
		return EMAIL_TEXT;
	}



	/**
	 * @param eMAIL_TEXT el eMAIL_TEXT a establecer
	 */
	public void setEMAIL_TEXT(String eMAIL_TEXT) {
		EMAIL_TEXT = eMAIL_TEXT;
	}



	public boolean Enviar() {    
		boolean realizado = false;
	      // Aca estamos asumiendo que el email sale de Localhost lo cual para esta prueba es asi.
	      String host = "localhost";

	      // Se obtienen las propiedades del sistema
	      Properties properties = System.getProperties();

	      // Se realiza el setup del servidor de correo
	      properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.auth", "true");
	      properties.put("mail.smtp.port", ServerSetupTest.SMTP.getPort());

	      // Se obtiene las sesion por defecto
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Creal el objeto MimeMessage por defecto
	         MimeMessage message = new MimeMessage(session);

	         // Hace el set del cabezal para el From
	         message.setFrom(new InternetAddress(EMAIL_USER_ADDRESS));

	         // Hace el set del cabezal para el To
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(EMAIL_TO));

	         // Hace el set para el Asunto
	         message.setSubject(EMAIL_SUBJECT);

	         // Ahora seteamos el cuerpo del mensaje
	         message.setText(EMAIL_TEXT);

	         // Se envia el mensaje
	         SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
	         t.connect(LOCALHOST, EMAIL_USER_ADDRESS, USER_PASSWORD);
		     t.sendMessage(message, message.getAllRecipients());
	         t.close();
	         realizado = true;
	         return realizado;
	      }catch (MessagingException mex) {
	         return realizado;
	      }
	   }
}
