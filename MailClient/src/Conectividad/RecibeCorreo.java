package Conectividad;

import java.io.IOException;
import java.util.Properties;
 
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.icegreen.greenmail.user.UserException;
import com.icegreen.greenmail.util.ServerSetupTest;

public class RecibeCorreo {
	
	private static final String LOCALHOST = "127.0.0.1";
	private String NomUsuario;
	private String PassUsu;

	
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
		 
	    	int contador, i;
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
	        	System.out.println("Nuevo e-mail de " + m.getFrom()[0].toString() + " | " + "Para: " +  m.getHeader("To")[0].toString() + " | " + "Asunto: " +  m.getSubject());
	        	System.out.println(m.getContent().toString());
	        	contador--;
	        	i++;
	        }
	    }
}
