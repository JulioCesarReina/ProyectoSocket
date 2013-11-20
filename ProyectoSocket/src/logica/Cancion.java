package logica;

import java.util.ArrayList;

import persistencia.Archivo;

public class Cancion {
	
	private ArrayList<String> letra = new ArrayList<String>();
	private Archivo archivo;
	private String nombre;
	
	public Cancion() {
		archivo= new Archivo(nombre);
	}
	
	/**
	 * Este metodo se encarga de leer en el archivo toda la letra de la cancion y de obtenerla
	 */
	public void cargarCancion(){
		archivo.leer();
		letra= archivo.getLetraCancion();
	}

}
