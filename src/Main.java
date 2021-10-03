import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import model.Dato;
import processing.core.PApplet;

public class Main extends PApplet{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");

	}
	
	private int x,y;
	BufferedReader bfr;
	BufferedWriter bfw;
	Dato datos;
	
	
	public void settings() {

		size(500, 500);
	}

	public void setup() {

	
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
					move();
					

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
		//fill();
		ellipse(x, y, 60, 60);
	}

	public void move() {

//		switch (datos.getMovimiento()) {
//
//		case "up":
//
//			y -= datos.getMov();
//
//			break;
//
//		case "right":
//
//			x += datos.getMov();
//
//			break;
//
//		case "left":
//
//			x -= datos.getMov();
//
//			break;
//
//		case "down":
//
//			y += datos.getMov();
//
//			break;
//
//		case "color":
//
//			r = datos.getR();
//			g = datos.getG();
//			b = datos.getB();
//
//			break;
//
//		}

	}


}
