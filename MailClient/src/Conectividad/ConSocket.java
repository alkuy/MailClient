package Conectividad;

import java.net.*;
import java.io.*;
import persistencia.FachadaPers;


public class ConSocket {

	private FachadaPers BD = FachadaPers.getInstancia();
	private Socket cliente;
	private int puerto = 5000;
	private String ip = "127.0.0.1";
	private DataOutputStream salida;
	private DataInputStream entrada;
	
	public ConSocket(){
		
	}
	
	public void ConToServer(){
		String NomDom, msg;
		int prioridad, num;
		
		try{
			cliente = new Socket(ip, puerto);
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
			
			
			
			msg = entrada.readUTF();
			while (msg.compareToIgnoreCase("fin") != 0){
				NomDom = msg;
				num = entrada.readInt();
				prioridad = num;
				BD.GuardaDominios(NomDom, num);
				//System.out.println("Dominio: "+NomDom+" Prioridad: "+num+"\t");
				msg = entrada.readUTF();
			}
			
			entrada.close();
			salida.close();
			cliente.close();
		}catch(Exception e){};
	}
}
