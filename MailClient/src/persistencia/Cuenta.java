package persistencia;

import java.io.Serializable;

public class Cuenta implements Serializable {

	  
		private String Nom_us;
		private String Dom;
		private String Passwd;
		
		
		
		
		/**
		 * @return the nom_us
		 */
		public String getNom_us() {
			return Nom_us;
		}
		/**
		 * @param nom_us the nom_us to set
		 */
		public void setNom_us(String nom_us) {
			Nom_us = nom_us;
		}
		/**
		 * @return the dom
		 */
		public String getDom() {
			return Dom;
		}
		/**
		 * @param dom the dom to set
		 */
		public void setDom(String dom) {
			Dom = dom;
		}
		/**
		 * @return the passwd
		 */
		public String getPasswd() {
			return Passwd;
		}
		/**
		 * @param passwd the passwd to set
		 */
		public void setPasswd(String passwd) {
			Passwd = passwd;
		}
		
		
		
		

}
