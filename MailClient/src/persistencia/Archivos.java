package persistencia;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Archivos {
	
	private static int contador = 0;
	
	/**
	 * Guarda el objeto correo en la carpeta que se le especifique
	 * @param carpeta
	 * @param correo
	 * @throws IOException
	 */
	public void guarda(String carpeta, Correo correo) throws IOException{
		String nom_archivo = correo.getFecha();
		nom_archivo = nom_archivo.replace(".","-"); //Para evitar problemas en el nombre del archivo
		nom_archivo = nom_archivo.replace(" ","-");	//Que no haya espacios e unifique todo al una barra
		nom_archivo = nom_archivo.replace(":","-");
		try{
			FileOutputStream f = new FileOutputStream(carpeta+nom_archivo, true);
			ObjectOutputStream escribiendo = new ObjectOutputStream(f);
			escribiendo.writeObject(correo);
			escribiendo.close();
	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * lee desde disco y retorna un correo segun la ruta y nombre de archivo que se le pasa
	 * @param carpeta
	 * @param archivo
	 * @return
	 */
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
	
	
	

 /**
  * Para leer los datos da la cuenta guardada en configuracion. Este se va a usar para cuando no sea la primer conexion para el login
  * @param carpeta
  * @param archivo
  * @return
  */
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
	
	
	/**
	 * retorna todos los archivos (objetos) que hay dentro de una ruta especificada
	 * @param directorio
	 * @return
	 */
	public File[] listadoDirectorio (String directorio){
		File dir = new File(directorio);
		File[]ficheros = dir.listFiles();			
		return ficheros;	 	
	}
	
	/**
	 * guarda el objeto cuenta (que implementa la interfaz serializable) como un archivo en persistencia dentro de la carpeta configuracion.
	 * @param cuenta
	 * @throws IOException
	 */
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
		
		
		/**
		 * Elimina archivo de la carpeta especificada
		 * @param directorio
		 * @param archivo
		 */
		public void Eliminar(String directorio, String archivo){
			archivo = archivo.replace(".","-"); //Para evitar problemas en el nombre del archivo
			archivo = archivo.replace(" ","-");	//Que no haya espacios e unifique todo al una barra
			archivo = archivo.replace(":","-");
			File f = new File(directorio+archivo);
			try{
				f.delete();
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
	
		public void moverCorreos(String d_origen, String archivo, String d_destino){
			archivo = archivo.replace(".","-"); //Para evitar problemas en el nombre del archivo
			archivo = archivo.replace(" ","-");	//Que no haya espacios e unifique todo al una barra
			archivo = archivo.replace(":","-");
			File f = new File(d_origen+archivo);
			f.renameTo(new File(d_destino+archivo));
			f.delete();
		}
		
		/**
		 * Guarda la ultima actualizaion de enviar y recibir en archivo
		 * @param destino
		 * @param fecha
		 */
		public void GuardaUltimaActualizacion(String destino, String fecha){
			File f = new File(destino+"actualizacion");
			try{
				FileWriter w = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(w);
				PrintWriter wr = new PrintWriter(bw);
				wr.write(fecha);
				wr.close();
				bw.close();
			}catch(IOException e){
				
			}
		}	
			
		/**
		 * Lee el archivo de ultima actualizacion de bandeja de entrada
		 * @param destino
		 * @return
		 * @throws IOException
		 */
		public String leerActualizacion(String destino) throws IOException{
			String fecha = "0";
			File archivo = new File(destino+"actualizacion");
			if (archivo.exists()){
				try {
					FileReader f;
					f = new FileReader(destino+"actualizacion");
					BufferedReader b = new BufferedReader(f);
					fecha = b.readLine();
					b.close();	 
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			return fecha;
			
		}
		
		/**
		 * Escribe e archivo lista de spam
		 * @param destino
		 * @param correo
		 * @throws IOException 
		 */
		public void guardaListaSpam (String destino, String correo) throws IOException{
			BufferedWriter bw = null;
			FileWriter fw = null;
			try {
				File file = new File(destino+"listaSpam");
				if (!file.exists()) {
					file.createNewFile();
				}

				fw = new FileWriter(file.getAbsoluteFile(), true);
				bw = new BufferedWriter(fw);
				
				bw.write(correo);
				bw.newLine();
				

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		}
		
		/**
		 * Devuelve lista de SPAM
		 * @param origen (ruta de configuracion de spam)
		 * @return
		 */
		public ArrayList<String> CargaSpam(String origen){
			ArrayList<String> SPAM = new ArrayList<String>();
			File f = new File(origen);
			
			Scanner s = null;
			//En primer instancia creamos un Array list para obtener los dominios con su prioridad
			try{
				s = new Scanner(f);
				while (s.hasNextLine()){
					String linea = s.nextLine();	
					SPAM.add(linea);
				}
				s.close();
			}catch(Exception e){
				
			}
			return SPAM;
			
		}
		
		
		// metodo que busca si existe un archivo por nombre
		
		 public  boolean ExisteCuenta(String rutaCarpeta, String cuentabusqueda){
		   File carpet=new File(rutaCarpeta);
		    for(File archivo:carpet.listFiles()){
			   
			   if(cuentabusqueda.equals(archivo.getName())){
				   
				   return true;
			   }
		   }
		   return false;
		 }
	
		
		//Guarda el objeto Agenda en un archivo de la cuenta
			public void guardaAgenda(String Carpeta, Agenda A){
				try {
					File f = new File(Carpeta+"Agenda.txt");
					FileWriter fw;
					BufferedWriter bw;
					if (f.exists()){
						if (contador == 0){
							f.delete();
							f.createNewFile();
							fw = new FileWriter(f, true);
							bw = new BufferedWriter(fw);
							//bw.newLine();
							bw.write(A.getNomUsuario()+"%"+A.getCuentaUsuario());
							contador++;
						}else{
							fw = new FileWriter(f, true);
							bw = new BufferedWriter(fw);
							bw.newLine();
							bw.write(A.getNomUsuario()+"%"+A.getCuentaUsuario());
						}
						
					}else{
						fw = new FileWriter(f, true);
						bw = new BufferedWriter(fw);
						bw.write(A.getNomUsuario()+"%"+A.getCuentaUsuario());
						contador++;
					}
					bw.close();
					fw.close();
				}catch (Exception e){
					
				}
			}
			
			
			//Metodo para leer los Usuario del archivo Agenda
			public String[][] LeerAgenda(String Carpeta){
				ArrayList<Agenda> LisAgenda = new ArrayList<Agenda>();
				File f = new File(Carpeta+"Agenda.txt");
				Scanner s = null;
				int Cantidad;
				
				//En primer instancia creamos un Array list para obtener los Usuarios con su Cuenta respectiva
				try{
					s = new Scanner(f);
					while (s.hasNextLine()){
						String linea = s.nextLine();
						String [] cortarString = linea.split("%");
						Agenda A = new Agenda();
						A.setNomUsuario(cortarString[0]);
						A.setCuentaUsuario(cortarString[1]);
						
						LisAgenda.add(A);
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
				Cantidad = LisAgenda.size();
				String[][] Agenda = new String[Cantidad][2];
				for(int i=0; i < Cantidad; i++){
					Agenda[i][0] = LisAgenda.get(i).getNomUsuario();
					Agenda[i][1] = LisAgenda.get(i).getCuentaUsuario();
				}
				
				//Devuelvo el Array
				return Agenda;
			}
			
			
			//Metodo para leer solo las cuentas de la agenda
			public String[] LeerCtaAgenda(String Carpeta){
				ArrayList<Agenda> LisAgenda = new ArrayList<Agenda>();
				File f = new File(Carpeta+"Agenda.txt");
				Scanner s = null;
				int Cantidad;
				
				//En primer instancia creamos un Array list para obtener los Usuarios con su Cuenta respectiva
				try{
					s = new Scanner(f);
					while (s.hasNextLine()){
						String linea = s.nextLine();
						String [] cortarString = linea.split("%");
						Agenda A = new Agenda();
						A.setNomUsuario(cortarString[0]);
						A.setCuentaUsuario(cortarString[1]);
						
						LisAgenda.add(A);
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
				Cantidad = LisAgenda.size();
				String[] Cuenta = new String[Cantidad];
				for(int i=0; i < Cantidad; i++){
					Cuenta[i] = LisAgenda.get(i).getCuentaUsuario();
				}
				
				//Devuelvo el Array
				return Cuenta;
			}
		

}
