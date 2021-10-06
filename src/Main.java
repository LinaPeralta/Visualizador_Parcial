import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import com.google.gson.Gson;

import model.Dato;
import processing.core.PApplet;

public class Main extends PApplet{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");

	}
	
	private int x,y;
	private int r,g,b;
	BufferedReader bfr;
	BufferedWriter bfw;
	Dato datos;
	
	
	public void settings() {

		size(500, 500);
	}

	public void setup() {
      
		x=250;
		y=250;
	
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
					colores();
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void draw() {

		background(255);
		noStroke();
		fill(r,g,b);
		ellipse(x,y, 50, 50);
	}

	public void colores() {

		switch (datos.getColor()) {

		case "rojo":

			 r = 122;
			 g = 9;
			 b = 9;

			break;

		case "verde":

			 r = 71;
			 g = 88;
			 b = 23;

			break;

		case "azul":

			 r = 50;
			 g = 89;
			 b = 135;

			break;


		}

	}
	
	public void movimiento() {
		
		
		 
		
	}


}
