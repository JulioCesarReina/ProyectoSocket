package logica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor implements Runnable{

	private ServerSocket serverSocket;
	private ArrayList<Conexion> listaConexiones;
	private Socket auxSocket;
	private String ip;
	private int puerto;
	private Thread thread;
	private boolean ejecutando;
	private boolean pausado;
	
	
	
	public Servidor() {
		
		puerto=3900;
		listaConexiones= new ArrayList<Conexion>();
		thread= new Thread(this);
		ejecutando=true;
		//thread.start();
	}
	
	public void iniciarServidor(){
		if(serverSocket==null){
			try {
				serverSocket = new ServerSocket(puerto);
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void cerrar(){
		for (int i = 0; i < listaConexiones.size(); i++) {
			listaConexiones.get(i).cerrarConexion();
			
		}
	}
	
	
	public void run() {
		while(ejecutando){
			System.out.println("esperando conexiones");
			try {
				auxSocket= serverSocket.accept();
				listaConexiones.add(new Conexion(auxSocket));
				System.out.println("nueva conexion aceptada");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
