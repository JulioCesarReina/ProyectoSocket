package logica;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

public class Cliente implements Runnable {

	
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private String ip;
	private int puerto;
	private Thread thread;
	private boolean ejecutando;  
	private boolean pausado;
	private int opcion;
	BufferedImage bufferedImage;
	
	
	public Cliente() {
		try {
		//socket= new Socket("localhost", 3900);
		socket= new Socket("10.0.39.225", 3500);
		} catch (UnknownHostException e) {
//			e.printStackTrace();
			System.out.println("host no creado");
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("puerto no disponible");
		}
		
		try {
			dataInputStream= new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		try {
			dataOutputStream= new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ejecutando=true;
		thread= new Thread(this);
		thread.start();
		
	}
	
	
	public void cerrarConexion(){
		try {
			dataInputStream.close();
			dataOutputStream.close();
			socket.close();
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
					System.out.println(dataInputStream.readUTF());
					
					 dataOutputStream.writeInt(2);
					dataOutputStream.writeUTF("hola nicolas");
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				break;
			case 3:
				
				
				try {
					bufferedImage = ImageIO.read(new File("/home/leyer/lsm.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					ImageIO.write(bufferedImage, "png", socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					socket.getOutputStream().flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}

}
