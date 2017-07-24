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
	
	public File[] listadoDirectorio (String directorio){
		File dir = new File(directorio);
		File[]ficheros = dir.listFiles();			
		return ficheros;	 	
	}

}
