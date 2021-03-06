package logica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import persistencia.FachadaPers;
import javax.swing.table.DefaultTableModel;

public class FachadaLog {
	
	private static FachadaLog instancia;
	private FachadaPers BD = FachadaPers.getInstancia();
	//private Correos hcorreo;

	
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
		
		ArrayList<String> SPAM = configuracion.TraeListaSpam();
		Iterator<String> it = SPAM.iterator();
		while (it.hasNext()){
			String no = it.next();
			if (no.equals(emisor+"@"+e_dom)){
				directorio = configuracion.devRutaSpam();
			}
			
		}
		correo.GuardaCorreo(directorio); // carga los correso desde el disco segun la ruta que se le indique
		correos.Insertar(directorio, fecha, correo); //crea el set correspondiente en memoria

	}
	/**
	 * Print de los correos. Funcion de pruebas
	 * @param directorio
	 */
	public void Muestra_Correos(String directorio){
		correos.cargaCorreos(directorio);
		for (int i=0; i<correos.getSetCorreosEnviados().size(); i++){
			System.out.println(correos.getSetCorreosEnviados().get(i).getAsunto());
		}
	}
	
	
	// Este metodo carga los correos de todas las carpetas en memoria
	
	public void cargaTodoenMemoria(){					
		correos.cargaCorreos(this.Devuelve_Ruta_Enviados());			
		correos.cargaCorreos(this.Devuelve_Ruta_BuzonSalida());
		correos.cargaCorreos(this.Devuelve_Ruta_Recibidos());
		correos.cargaCorreos(this.Devuelve_Ruta_Borradores());
		correos.cargaCorreos(this.Devuelve_Ruta_Papelera());
		correos.cargaCorreos(this.Devuelve_Ruta_Spam());
		
		this.cargaAgendaCliente();
		}
	
	
	// metoo que devuelve una tabla para mostrar correos enviados
	public DefaultTableModel DevCorreosEnviados() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaCorreosEnviados();
		return modelo;
	}
	
	
	/**
	 * metodo que devuelve una tabla para mostrar Bandeja de Salida
	 * @return TableModel
	 * @throws SQLException
	 */
	public DefaultTableModel DevBandejaSalida() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaBandejaSalida();
		return modelo;
	}
	
	/**
	 * metodo que devuelve una tabla para mostrar Borradores
	 * @return TableModel
	 * @throws SQLException
	 */
	public DefaultTableModel DevBorradores() throws SQLException{
		DefaultTableModel modelo;
		modelo = correos.DevTablaBorradores();
		return modelo;
	}
	
	
	/**
	 *  metodo que devuelve una tabla para mostrar Recibidos
	 * @return TableModel
	 * @throws SQLException
	 */
		public DefaultTableModel DevRecibidos() throws SQLException{
			DefaultTableModel modelo;
			modelo = correos.DevTablaRecibidos();
			return modelo;
		}
		
		/**
		 *  metodo que devuelve una tabla para envio masivo de mails
		 * @return TableModel
		 * 
		 */
			public DefaultTableModel DevCorreosSalida(){
				DefaultTableModel modelo;
				modelo = correos.DevTablaSalida();
				return modelo;
			}
		
		/**
		 *  metodo que devuelve una tabla para envio masivo de mails
		 * @return TableModel
		 * 
		 */
			public DefaultTableModel DevPrioritarios(){
				Scheduling Schedul = new Scheduling();
				DefaultTableModel modelo;
				modelo = Schedul.AlgoritmoPrioridad();
				return modelo;
			}	
			
		/**
		 *  metodo que devuelve una tabla para mostrar Eliminados
		 * @return TableModel
		 * @throws SQLException
		 */
		public DefaultTableModel DevPapelera() throws SQLException{
			DefaultTableModel modelo;
			modelo = correos.DevTablaEliminados();
			return modelo;
			}
			
		/**
		 *  metodo que devuelve una tabla para mostrar Spam
		 * @return TableModel
		 * @throws SQLException
		*/
		public DefaultTableModel DevSpam() throws SQLException{
				DefaultTableModel modelo;
				modelo = correos.DevTablaSpam();
				return modelo;
				}
		
		
		/**
		 *  metodo que devuelve una tabla para mostrar la busqueda realizada de Recibidos
		 * @return TableModel
		 * @throws SQLException
		 */
			public DefaultTableModel DevBusqueda(String cuentaBusqueda) throws SQLException{
				DefaultTableModel modelo;
				modelo = correos.DevTablaBusqueda(cuentaBusqueda);
				return modelo;
			}
			
			
			/**
			 *  metodo que devuelve una tabla para mostrar la busqueda realizada de Enviados
			 * @return TableModel
			 * @throws SQLException
			 */
				public DefaultTableModel DevBusquedaEnviados(String cuentaBusqueda) throws SQLException{
					DefaultTableModel modelo;
					modelo = correos.DevTablaBusquedaEnviados(cuentaBusqueda);
					return modelo;
				}
	
	/** Metodo para pasar los datos de la cuenta a persistencia y dejar el ojeto cuenta instanciado para usar
	 * 
	 * @param Nom_us nombre de usuario de la cuenta
	 * @param Dom dominio del usuario de la cuenta
	 * @param Pas clave de la cuenta
	 */
	public void Nueva_cuenta(String Nom_us, String Dom, String Pas){
		
		Cuenta cuenta = Cuenta.getInstancia();	// se crea la instancia de la cuenta que se usara en adelante
		cuenta.setNom_us(Nom_us); // se cargan a cuenta los datos de ingreso de usuario
		cuenta.setDominio(Dom);
		cuenta.setContrase�a_cuenta(Pas);		
		cuenta.carga_cuenta(cuenta); 
			
	}
	
	
	
    public boolean Busca_cuenta(String archivo, String usuario, String dominio){
		
		String cuentabuscada = usuario+"-"+dominio;
		Cuenta cuenta = Cuenta.getInstancia();
		return cuenta.busca_cuenta(archivo, cuentabuscada); 
			
	}
    
    
    public boolean compara_passwd(String archivo, String cuentabusqueda,String domBusqueda, String passwd){
    	
    	
    	Cuenta cuenta = Cuenta.getInstancia();	
	
    	return cuenta.busca_Passwd(archivo, cuentabusqueda+"@"+domBusqueda, passwd);
    }
	
	
	/**
	 * devuelve el nombre de usuario actual logeado
	 * @return String (Nombre de usuario)
	 */
   public String Devuelve_us_cuenta(){
		Cuenta cuenta = Cuenta.getInstancia();	
		return cuenta.getNom_us();
	}
   
   /**
    *  Devuelve el dominio de la cuneta de usuario actual
    * @return String (dominio de cuenta)
    */
   
   public String Devuelve_dom_cuenta(){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		
		return cuenta.getDominio();
		
		
	}
   
   //Devuelve el passwd de la cuenta de usuario actual
   
   public String Devuelve_pas_cuenta(){
		
		Cuenta cuenta = Cuenta.getInstancia();	
		
		return cuenta.getContrase�a_cuenta();
		
		
	}
   
   // devuelve la ruta absoluta de Borradores
   public String Devuelve_Ruta_Borradores(){
	   
	   return configuracion.devRutaBorradores();
   }
   
   // devuelve la ruta absoluta de Recibidos
   public String Devuelve_Ruta_Recibidos(){
		
 		return configuracion.devRutaRecibidos();
 	}

   // devuelve la ruta absoluta de Enviados
    public String Devuelve_Ruta_Enviados(){
 		
 		return configuracion.devRutaEnviados();
 	}
   
    // devuelve la ruta absoluta de Buzon de Salida
    public String Devuelve_Ruta_BuzonSalida(){
 		
 		return configuracion.devRutaBuzonSalida();
 	}
    
    // devuelve la ruta absoluta de Configuarcion
    public String Devuelve_Ruta_Configuracion(){
 		
 		return configuracion.devRutaConfiguracion();
 	}
    
    // devuelve la ruta absoluta de Papelera
    public String Devuelve_Ruta_Papelera(){
 		
 		return configuracion.devRutaPapelera();
 	}
    
 // devuelve la ruta absoluta de Spam
    public String Devuelve_Ruta_Spam(){
 		
 		return configuracion.devRutaSpam();
 	}
    
    // devuelve la ruta absoluta de Cuentas
    public String Devuelve_Ruta_Cuentas(){
 		
 		return configuracion.devRutaCuentas();
 	}
	
    // devuelve el correo borrador seleccionado a partir de la fecha de creacion
    
    public Correo DevuelveBorrador(String Fecha){
    	 
    	return correos.DevolverCorreoBorrador(Fecha);
    	
    }
    
 /**
  *  devuelve el correo de bandeja de salida seleccionado a partir de la fecha de creacion
  * @param Fecha
  * @return Objeto Correo
  */
    
    public Correo DevuelveBandejaSalida(String Fecha){
    	  
    	return correos.DevolverCorreoBandejaSalida(Fecha);
    	
    }
    
 /**
  * devuelve el correo Enviado seleccionado a partir de la fecha de creacion
  * @param Fecha
  * @return Objeto Correo
  */
    
    public Correo DevuelveEnviado(String Fecha){
    	  
    	return correos.DevolverCorreoEnviado(Fecha);
    	
    }
    
/**
 * devuelve el correo Enviado seleccionado a partir de la fecha de creacion
 * @param Fecha
 * @return objeto Correo
 */
    
    public Correo DevuelveRecibido(String Fecha){
    	  
    	return correos.DevolverCorreoRecibido(Fecha);
    	
    }
    
    public void crearDirectorioCuenta(){
		
		cuenta.crearDirectorioCuenta();
	}
	
	/**
	 * Devuelve el array de Dominios
	 * @return String[] Lista de dominios
	 */
    public String[] GetDom(){
    	String[] Dominios;
    	Dominios = BD.GetDominio();
    	return Dominios;
    }
    
	/**
	 * Devuelve el array de Cuentas del Servidor de Correo
	 * @return String[] Lista de Cuentas
	 */
    public String[] GetCuentas(){
    	String[] Cuentas;
    	Cuentas = BD.GetCuentas();
    	return Cuentas;
    }
    
    /**
     * Elimina correo de hastable y persistencia
     *  @param clave 
     */
    public void EliminarCorreo(String directorio, String clave, String destino){
    	correos.Eliminar_Correo(directorio, clave, destino);
    	
    }
    
    /**
     * Elimina el archivo de memoria y quita del Hashtable.
     * @param directorio (String)
     * @param clave (String)
     */
    public void EliminarCorreoDefinitivo(String directorio, String clave){
    	correos.Eliminar_Definitivo(directorio, clave);
    }
    
    
    /**
     * Mueve los correos Enviados al buzon Enviados
     *  @param clave 
     */
    public void CorreoEnviado(String directorio, String clave, String destino){
    	correos.Correo_Enviado(directorio, clave, destino);
    	
    }
    
    /**
     * Restaura a la carpeta Recibidos los correos que puedan estar en papelera o Spam
     * @param directorio
     * @param clave
     */
    public void RestaurarEliminados(String directorio, String clave){
    	correos.Restaurar(directorio, clave);
    }
    
    
     //Metodo que devuelve un array list con los correos de determinada conversacion segun id
    public ArrayList<Correo> DevConveracionRecibidos(int id,String Fecha){
    	
    	return correos.DevConveracionRecibidos(id, Fecha);
    }
    
    /**
     * Metodo para guaradar mometo de la ultima actualizacion de los correos (Enviar y Recibir)
     * @param fecha
     */
    public void GuardaActualizacion(String fecha){
    	configuracion.GuardaActualizacion(configuracion.devRutaConfiguracion(), fecha);
    	
    }
    
    /**
     * Trae la ultima actualizacion que se realizo con enviar y recibir
     * @return fecha (Timestamp) de la ultima actualizacion
     */
    public String TraeUltimaActualizacion(){
    	String fecha = configuracion.devuelveUltimaActualizacion(configuracion.devRutaConfiguracion());
    	return fecha;
    }
    /**
     * Carga la lista de SPAM
     * @param correo
     */
    public void CargaListaSpam(String correo){
    	configuracion.CargaListaSpam(correo);
    }
    
    /**
     * Quita el correo del archivo de SPAM 
     * @param correo
     */
    public void QuitaCorreodeListaSPAM (String correo){
 	   ArrayList<String> SPAM = configuracion.QuitadeListaSPAM(correo);
 	   configuracion.EliminarArchivo(configuracion.devRutaConfiguracion(), "listaSpam");
 	   Iterator<String> it = SPAM.iterator();
 	   while (it.hasNext()){
 		   String carga = it.next();
 		   configuracion.CargaListaSpam(carga);
 	   }
    }
    
    
    /** Llama al metodo que Encripta o desencripta texto por Xor
     * 
     * @param texto
     * @param clave
     * @return texto encriptado o desencriptado segun lo que se le pase
     */
        
    public String encriptaOdesencripta(String texto, String clave){
       
             
        return configuracion.EncriptaDesencripta(texto, clave);
    }
    
    
    
    /** Llama al metodo que Encripta o desencripta por permutacion con clave
     * 
     * @param texto
     * @param clave
     * @param clavePermut
     * @return texto permutado
     */
    public String Permutar(String texto, String clave, String clavePermut){
   	 
   	 
   	 return configuracion.Permutar(texto, clave, clavePermut);
    }

    /** M�todo que imprime los usuarios, es para pruebas.
     */
    public void impAgenda(){
    	String[][] agenda = BD.GetAgenda();
    	System.out.println("FLoc " + agenda.length);
    	for(int i=0; i<agenda.length; i++){
    		System.out.println("Nom usu: " + agenda[i][0]);
    		System.out.println("Cuenta usu: " + agenda[i][1]);
    	}
    }
    
    /** M�todo que obtiene el nombre y apellido del usuario de la cuenta asociada.
     * @param cuenta
     * @return Nombre+ +Apellido (concatenado)
     */
    public String getNomApeUsuario(String cuenta){
    	String[][] agenda = BD.GetAgenda();
    	for(int i=0; i<agenda.length; i++){
    		if(cuenta.equals(agenda[i][1]))
    			return agenda[i][0];
    	}
    	return null;
    }
    
    /** M�todo que retorna la existencia de la cuenta en la agenda.
     * @param cuentaNom
     * @param cuentaDom
     * @return True si existe la cuenta. False si NO existe la cuenta.
     */
    public boolean existeCuentaAgenda(String cuentaNom, String cuentaDom){
    	return Cuenta.getInstancia().getAgenda().existeUsu(cuentaNom, cuentaDom);
    }
    
    /** M�todo que da de alta a un nuevo usuario y su cuenta en la agenda.
     * @param usuario
     * @param cuenta
     */
    public void altaNuevoUsu_Agenda(String usuario, String cuenta){
    	String nom = usuario.substring(0, usuario.indexOf(" "));
    	String ape = usuario.substring(usuario.indexOf(" ")+1);
    	String cuentaNom = cuenta.substring(0, cuenta.indexOf("@"));
    	String cuentaApe = cuenta.substring(cuenta.indexOf("@")+1);
    	
    	CuentaAgenda auxCuenta = new CuentaAgenda(cuentaNom, cuentaApe);
    	
    	Usuario usu = new Usuario((Cuenta.getInstancia().getAgenda().ultimoID()+1),nom,ape,auxCuenta,true);
    	
    	Cuenta.getInstancia().getAgenda().altaUsuAgenda(usu);
    	usu.guardarUsuarioBD(usuario, cuenta);
    }
    
    /** M�todo para cargar la agenda del cliente.
     */
    public void cargaAgendaCliente(){
    	String[][] agenda = BD.GetAgendaCliente();
    	for(int i=0; i<agenda.length; i++){
    		String nom = agenda[i][0].substring(0, agenda[i][0].indexOf(" "));
        	String ape = agenda[i][0].substring(agenda[i][0].indexOf(" ")+1);
        	String cuentaNom = agenda[i][1].substring(0, agenda[i][1].indexOf("@"));
        	String cuentaDom = agenda[i][1].substring(agenda[i][1].indexOf("@")+1);
        	CuentaAgenda auxCuenta = new CuentaAgenda(cuentaNom, cuentaDom);
    		
    		Usuario usu = new Usuario((Cuenta.getInstancia().getAgenda().ultimoID()+1),nom,ape,auxCuenta,true);
    		Cuenta.getInstancia().getAgenda().altaUsuAgenda(usu);
    	}
    }
    
    /** M�todo que retorna el modelo para la tabla de la agenda.
     * @return DefaultTableModel
     */
    public DefaultTableModel DevUsuAgenda(){
    	DefaultTableModel modelo;
    	modelo = cuenta.getInstancia().getAgenda().DevUsuAgenda();
    	return modelo;
    }
    
    /** M�todo para borrar la cuenta de la agenda.
     * @param usuario
     * @param cuenta
     */
    public void borrarCuentaAgenda(String usuario, String cuenta){
    	Cuenta.getInstancia().getAgenda().Borrar(usuario, cuenta);
    	BD.eliminarAgenda(configuracion.devRutaAgendaCliente(), "Agenda.txt");
    	this.recargarAgenda();
    }
    
    /** M�todo para recargar el archivo de agenda con los usuarios y cuentas en la memoria
     */
    public void recargarAgenda(){
    	Enumeration<Usuario> eUsu =	Cuenta.getInstancia().getAgenda().getCollection().elements();
    	Usuario auxUsu;
    	while(eUsu.hasMoreElements()){
    		auxUsu = eUsu.nextElement();
    		Iterator<CuentaAgenda> iteCuenta = auxUsu.getCuentas().getCollection().iterator();
    		while(iteCuenta.hasNext()){
    			CuentaAgenda auxCuenta = iteCuenta.next();
    			auxUsu.guardarUsuarioBD(auxUsu.getNombre()+" "+auxUsu.getApellido(), auxCuenta.getCuentaNom()+"@"+auxCuenta.getCuentaDom());
    		}
    	}
    }
}

