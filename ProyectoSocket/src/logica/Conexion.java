package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Conexion implements Runnable{

	private Socket socketConexion;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private Thread thread;
	private boolean ejecutando;
	private boolean pausado;
	private int opcion;
	
	
	public Conexion(Socket socket) {
		this.socketConexion = socket;
		try {
			dataInputStream= new DataInputStream(socketConexion.getInputStream());
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Error creando canales de entrada");
		}	
		
		try {
			dataOutputStream= new DataOutputStream(socketConexion.getOutputStream());
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Error creando canales de salida");
		}
		ejecutando=true;
		thread= new Thread(this);
		thread.start();
		iniciarConexion();
	}
	
	
	public void cerrarConexion(){
		try {
			dataInputStream.close();
			dataOutputStream.close();
			socketConexion.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarConexion(){
		try {
			dataOutputStream.writeInt(1);
			dataOutputStream.writeUTF("nuevo mensaje hola");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void run() {
		while(ejecutando){
			
			try {
				opcion=dataInputStream.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch (opcion) {
			case 1:
				try {
					System.out.println("se recibio");
					System.out.println(dataInputStream.readUTF());
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.println("se desconecto");
					
				}
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				break;
			}
			
		}
	}

}
