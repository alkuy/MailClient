package Conectividad;

import java.net.*;

import logica.FachadaLog;

import java.io.*;
import persistencia.FachadaPers;


public class ConSocket {

	private FachadaPers BD = FachadaPers.getInstancia();
	private FachadaLog LN = FachadaLog.getInstancia();
	private Socket cliente;
	private int puerto = 6000;
	private String ip = "127.0.0.1";
	private DataOutputStream salida;
	private DataInputStream entrada;
	
	public ConSocket(){
		
	}
	
	public void ConToServer(){
		String NomDom, msg, NomUsu, CuentaUsu;
		int prioridad, num;
		
		try{
			cliente = new Socket(ip, puerto);
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
			
			msg = "Conn";
			salida.writeUTF(msg);
			
			msg = entrada.readUTF();
			while (msg.compareToIgnoreCase("finDom") != 0){
				NomDom = msg;
				num = entrada.readInt();
				prioridad = num;
				BD.GuardaDominios(NomDom, num);
				//System.out.println("Dominio: "+NomDom+" Prioridad: "+num+"\t");
				msg = entrada.readUTF();
			}
			
			msg = entrada.readUTF();
			while (msg.compareToIgnoreCase("fin") != 0){
				NomUsu = msg;
				CuentaUsu = entrada.readUTF();
				BD.GuardaUsuariosAgenda(NomUsu, CuentaUsu);
				//System.out.println("Dominio: "+NomDom+" Prioridad: "+num+"\t");
				msg = entrada.readUTF();
			}
			
			entrada.close();
			salida.close();
			cliente.close();
		}catch(Exception e){};
	}
	
	public String Login(String Usu, String NomDom, String Pass){
		String msg, result;
		result = "No_Conect";
		try{
			cliente = new Socket(ip, puerto);
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
			
			msg = "Auth";
			salida.writeUTF(msg);
			
			salida.writeUTF(Usu);
			salida.writeUTF(NomDom);
			salida.writeUTF(Pass);

			result = entrada.readUTF();
			
			entrada.close();
			salida.close();
			cliente.close();
			
			return result;
		}catch(Exception e){
			return result;
		}
		
	}
	
	
	public void UsuToServer(){
		String msg;
		String Usu, Pass;
		
		try{
			cliente = new Socket(ip, puerto);
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
			
			msg = "Usu";
			salida.writeUTF(msg);
			
			Usu = LN.Devuelve_us_cuenta();
			salida.writeUTF(Usu);
			
			msg = entrada.readUTF();
			Pass = LN.Devuelve_pas_cuenta();
			RecibeCorreo R = new RecibeCorreo(Usu, Pass);
			R.getMails();
			
			msg = "OK";
			salida.writeUTF(msg);
			
			entrada.close();
			salida.close();
			cliente.close();
		}catch(Exception e){
			System.out.println("Imposible loguearse al POP3 Server");
		};
	}
	
	public void OldToServer(){
		String msg;
		String Usu, Pass;
		
		try{
			cliente = new Socket(ip, puerto);
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
			
			msg = "Old";
			salida.writeUTF(msg);
			
			Usu = LN.Devuelve_us_cuenta();
			salida.writeUTF(Usu);
			
			msg = entrada.readUTF();
			Pass = LN.Devuelve_pas_cuenta();
			RecibeCorreo R = new RecibeCorreo(Usu, Pass);
			R.getMails();
			
			msg = "OK";
			salida.writeUTF(msg);
			
			entrada.close();
			salida.close();
			cliente.close();
		}catch(Exception e){
			System.out.println("Imposible loguearse al POP3 Server");
		};
	}
}
