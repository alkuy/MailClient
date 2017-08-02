package persistencia;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;




// guarda un correo en la carpeta que se especifique
// el correo que se le pasa es una case que implementa la interfaz serializable
public class Archivos {
	
	private static int contador = 0;
	
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
	
	//Guarda el objeto Dominio en un archivo de la cuenta
		public void guardaDominio(String Carpeta, Dominio D){
			try {
				File f = new File(Carpeta+"Dominio.txt");
				FileWriter fw;
				BufferedWriter bw;
				if (f.exists()){
					if (contador == 0){
						f.delete();
						f.createNewFile();
						fw = new FileWriter(f, true);
						bw = new BufferedWriter(fw);
						//bw.newLine();
						bw.write(D.getNombre_dominio()+"%"+D.getPrioridad());
						contador++;
					}else{
						fw = new FileWriter(f, true);
						bw = new BufferedWriter(fw);
						bw.newLine();
						bw.write(D.getNombre_dominio()+"%"+D.getPrioridad());
					}
					
				}else{
					fw = new FileWriter(f, true);
					bw = new BufferedWriter(fw);
					bw.write(D.getNombre_dominio()+"%"+D.getPrioridad());
					contador++;
				}
				bw.close();
				fw.close();
			}catch (Exception e){
				
			}
		}
		
		
		//Metodo para leer los dominios del Archivo Dominios
		public String[] LeerDom(String Carpeta){
			ArrayList<Dominio> LisDom = new ArrayList<Dominio>();
			File f = new File(Carpeta+"Dominio.txt");
			Scanner s = null;
			int Cantidad;
			
			//En primer instancia creamos un Array list para obtener los dominios con su prioridad
			try{
				s = new Scanner(f);
				while (s.hasNextLine()){
					String linea = s.nextLine();
					String [] cortarString = linea.split("%");
					Dominio D = new Dominio();
					D.setNombre_dominio(cortarString[0]);
					D.setPrioridad(Integer.parseInt(cortarString[1]));
					
					LisDom.add(D);
				}
			}catch(Exception e){
				
			}finally{
				try{
					if (s != null)
						s.close();
				}catch(Exception e2){
					
				}
			}
			
			//En segunda instancia creamos un Array de String que contenga solo los dominios
			Cantidad = LisDom.size();
			String[] Dominios = new String[Cantidad];
			for(int i=0; i < Cantidad; i++){
				Dominios[i] = LisDom.get(i).getNombre_dominio();
			}
			
			//Devuelvo el Array
			return Dominios;
		}
		
	

}
