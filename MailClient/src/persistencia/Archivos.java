package persistencia;
import java.io.*;

public class Archivos {
	
	public void guarda(String carpeta, Correo correo) throws IOException{
		String emisor = correo.getEmisor_nombre()+"@"+correo.getEmisor_dominio();
		String fecha = correo.getFecha();
		String conversacion = String.valueOf(correo.getId_conversacion());		
		String nom_archivo = fecha+"$"+conversacion+"$"+emisor;
		
		try{
			FileOutputStream f = new FileOutputStream(carpeta+nom_archivo, true);
			ObjectOutputStream escribiendo = new ObjectOutputStream(f);
			escribiendo.writeObject(correo);
			escribiendo.close();
	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
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
	
	
	
	public File[] listadoDirectorio (String directorio){
		File dir = new File(directorio);
		File[]ficheros = dir.listFiles();			
		return ficheros;	 	
	}
	
	
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
