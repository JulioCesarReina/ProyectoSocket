package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {
	private File Archivo;
	private FileReader fr;
	private BufferedReader br = null;
	private String rutaArch;
	private ArrayList<String> letraCancion=new ArrayList<String>();


	public Archivo(String nombreCancion){
		Archivo = new File("");
		rutaArch = Archivo.getAbsolutePath()+"/"+nombreCancion+".txt";
	
	}

	
	public void abrir(char modo){
		try {
			if (modo=='r'){
				fr = new FileReader(rutaArch);
				br = new BufferedReader(fr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}


	public void leer(){
		String cad="";
		this.abrir('r');
		try {
			while ((cad=br.readLine())!=null){
				letraCancion.add(cad);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.cerrar();
		}



	/**
	 * Este metodo permite cerrar de forma segura el archivo.
	 */
	public void cerrar(){
		try {
			if (br!=null)
				br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public ArrayList<String> getLetraCancion() {
		return letraCancion;
	}

	public void setLetraCancion(ArrayList<String> letraCancion) {
		this.letraCancion = letraCancion;
	}





}
