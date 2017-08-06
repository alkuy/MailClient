package Conectividad;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
 
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.icegreen.greenmail.user.UserException;
import com.icegreen.greenmail.util.ServerSetupTest;

import logica.FachadaLog;

public class RecibeCorreo {
	
	private static final String LOCALHOST = "127.0.0.1";
	private String NomUsuario;
	private String PassUsu;
	FachadaLog FL = FachadaLog.getInstancia();

	
	public RecibeCorreo(String NomUsu, String PassUsu){
		this.NomUsuario = NomUsu;
		this.PassUsu = PassUsu;
	}
	
	
	/**
	 * @return el nomUsuario
	 */
	public String getNomUsuario() {
		return NomUsuario;
	}



	/**
	 * @param nomUsuario el nomUsuario a establecer
	 */
	public void setNomUsuario(String nomUsuario) {
		NomUsuario = nomUsuario;
	}



	/**
	 * @return el passUsu
	 */
	public String getPassUsu() {
		return PassUsu;
	}



	/**
	 * @param passUsu el passUsu a establecer
	 */
	public void setPassUsu(String passUsu) {
		PassUsu = passUsu;
	}



	public void getMails() throws IOException, MessagingException, UserException, InterruptedException {
		 	String Asunto, Recibidos, Emisor, Receptor;
	    	int contador, i, IdConv;
	        // fetch the e-mail from pop3 using javax.mail ..
	        Properties props = new Properties();
	        props.setProperty("mail.pop3.connectiontimeout", "6000");
	        Session session = Session.getInstance(props);
	        i = ServerSetupTest.POP3.getPort();
	        URLName urlName = new URLName("pop3", LOCALHOST, ServerSetupTest.POP3.getPort(), null, NomUsuario, PassUsu);
	        Store store = session.getStore(urlName);
	        store.connect();
	 
	        Folder folder = store.getFolder("INBOX");
	        folder.open(Folder.READ_ONLY);
	        Message[] messages = folder.getMessages();
	        contador = folder.getMessageCount();
	        i = 0;
	        while (contador > 0){
	        	Message m = messages[i];
	        	String [] cortarString = m.getSubject().split("%");
	        	IdConv = Integer.parseInt(cortarString[0]);
	        	Asunto = cortarString[1];
	        	Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Obtengo el momento en que llamo la actualizacion
				String fecha = timestamp.toString();
	        	Recibidos = FL.Devuelve_Ruta_Recibidos();
	        	Emisor = m.getFrom()[0].toString().substring(0, m.getFrom()[0].toString().indexOf("@"));
	        	Receptor = m.getHeader("To")[0].toString().substring(0, m.getHeader("To")[0].toString().indexOf("@"));
	        	String [] Edom =  m.getFrom()[0].toString().split("@");
	        	String [] Rdom =  m.getHeader("To")[0].toString().split("@");
	        	FL.Guarda_Correo(Recibidos, IdConv, Asunto, Emisor, Edom[1], Receptor, Rdom[1], m.getContent().toString(), fecha);
//	        	System.out.println("Nuevo e-mail de " + m.getFrom()[0].toString() + " | " + "Para: " +  m.getHeader("To")[0].toString() + " | " + "Asunto: " +  Asunto + " | " + "Id Conversacion: " + IdConv );
//	        	System.out.println(m.getContent().toString());
	        	contador--;
	        	i++;
	        }
	    }
}
