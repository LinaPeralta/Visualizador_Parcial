import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;

import model.Dato;
import processing.core.PApplet;

public class Main extends PApplet{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");

	}
	
	private int posX,posY;
	private int r,g,b;
	private String nombre;
	private int cantidad;
	BufferedReader bfr;
	BufferedWriter bfw;
	Dato datos;
	
	private ArrayList<Dato> bolita;
	

	  public void datos (Dato datos){
			
		    //nombre
		
		    nombre=datos.getNombre();
			
		    //cantidad
		    		
		    cantidad=datos.getCantidad();
		
			//coordenadas
		    posX=datos.getPosX();
		    posY=datos.getPosY();
			
			//color
			r=datos.getR();
			g=datos.getG();
			b=datos.getB();
			
			//bolita.add (new Dato(datos.getPosX(), datos.getPosY(), bolas.g))
			
	     }
	
	public void settings() {

		size(500, 500);
	}

	public void setup() {
      
		
		bolita = new ArrayList<>(cantidad);
	
		new Thread(() -> {

			try {
				System.out.println("Iniciando servidor");
				ServerSocket ss = new ServerSocket(9000);
				Socket conexion = ss.accept(); // Detiene el flujo hastas qeu haya conexion
				System.out.println("Se conecto con el cliente");

				InputStream is = conexion.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				bfr = new BufferedReader(isr);

				OutputStream os = conexion.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				bfw = new BufferedWriter(osw);

				recibirMensaje();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		).start();

	}

	public void recibirMensaje() {

		new Thread(() -> {

			while (true) {

				try {

					String mensaje = bfr.readLine();
					System.out.println("mensaje recibido: " + mensaje);
					Gson gson =  new Gson();
					datos = gson.fromJson(mensaje, Dato.class);
					//colores();
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void draw() {

		background(255);
		
		
		for (int i = 0; i < bolita.size(); i++) {
			
			//lectura de datos
			String nom =  bolita.get(i).getNombre();
			
			//int cant = bolita.get(i).getCantidad();
			
			int x = bolita.get(i).getPosX();
			int y = bolita.get(i).getPosY();
			
			int r = bolita.get(i).getR();
			int g = bolita.get(i).getG();
			int b = bolita.get(i).getB();
			
			
			//bolita
			noStroke();
			fill(r,g,b);
			ellipse(x,y, 50, 50);
			
			//nombre del grupo de bolitas
			fill(96,24,63);
			text(nom,x-10,y+20);
		}
		
	}
	

	
	public void movimiento() {
		
		
		 
		
	}


}
