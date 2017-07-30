package persistencia;
import java.io.*;




// guarda un correo en la carpeta que se especifique
// el correo que se le pasa es una case que implementa la interfaz serializable
public class Archivos {
	
	public void guarda(String carpeta, Correo correo) throws IOException{
		String emisor = correo.getEmisor_nombre()+"@"+correo.getEmisor_dominio();
		String fecha = correo.getFecha();
		String conversacion = String.valueOf(correo.getId_conversacion());		
		String nom_archivo = fecha+"$"+conversacion+"$"+emisor; // se crea el nombre del archivo (objeto) con fecha, id converacion y cuenta del emisor
		
		try{
			FileOutputStream f = new FileOutputStream(carpeta+nom_archivo, true);
			ObjectOutputStream escribiendo = new ObjectOutputStream(f);
			escribiendo.writeObject(correo);
			escribiendo.close();
	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	// lee desde disco y retorna un correo segun la ruta y nombre de archivo que se le pasa
	public Correo leer(String carpeta, String archivo){
		Correo correo = new Correo();
		ObjectInputStream leyendo = null;
		try {
			leyendo = new ObjectInputStream(new FileInputStream(carpeta+archivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			correo = (Correo) leyendo.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return correo;
		
	}
	
	
	

 // Para leer los datos da la cuenta guardada en configuracion. Este se va a suar para cuando no sea la primer conexion para el login
    public Cuenta leerCuenta(String carpeta, String archivo){
	   Cuenta cuenta = new Cuenta();
	   ObjectInputStream leyendo = null;
	try {
		leyendo = new ObjectInputStream(new FileInputStream(carpeta+archivo));
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
	  try {
		cuenta = (Cuenta) leyendo.readObject();
	  } catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  return cuenta;
	
}
	
	
	//retorna todos los archivos (objetos) que hay dentro de una ruta especificada
	public File[] listadoDirectorio (String directorio){
		File dir = new File(directorio);
		File[]ficheros = dir.listFiles();			
		return ficheros;	 	
	}
	
	// guarda el objeto cuenta (que implementa la interfaz serializable) como un archivo en persistencia dentro de la carpeta configuracion.
	public void guardaConfiguracion(Cuenta cuenta) throws IOException{
		String nombre = cuenta.getNom_us()+"@"+cuenta.getDom();
		Configuracion conf = Configuracion.getInstancia();		
		try{
		
			FileOutputStream f = new FileOutputStream(conf.getCarpetaConfiguracion()+nombre, true);
			ObjectOutputStream escribiendo = new ObjectOutputStream(f);
			escribiendo.writeObject(cuenta);
			escribiendo.close();
	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

}
