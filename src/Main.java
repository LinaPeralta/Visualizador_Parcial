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
	

	
	public void settings() {

		size(500, 500);
	}

	public void setup() {
      
		
		bolita = new ArrayList<>();
	
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
			
					crear();

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
			
			noStroke();
         	fill(bolita.get(i).getR(),bolita.get(i).getG(),bolita.get(i).getB());
  			ellipse(bolita.get(i).getPosX(),bolita.get(i).getPosY(), 50, 50);
			
			//nombre del grupo de bolitas
 			//fill(96,24,63);
 			//text(bolita.get(i).getNombre(),bolita.get(i).getPosX()-10,bolita.get(i).getPosY()+20);
			
		}

	}
	
	public void crear(){
		
		   
		   //pintar bolita
	    for (int i = 0; i <datos.getCantidad(); i++) {
			
			//lectura de datos
			String nom =  datos.getNombre();
			
			int cant = datos.getCantidad();
			
			int x = datos.getPosX();
			int y = datos.getPosY();
			
			
			int r = datos.getR();
			int g = datos.getG();
			int b = datos.getB();
			
	
			bolita.add (new Dato (r, g, b, nom,cant,x,y));
			
			
		}

	}

	
	public void movimiento() {
		
		
		 
		
	}


}
