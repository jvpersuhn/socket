import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Servidor {

	public static void main(String[] args) {
		List<PrintStream> clientes = new ArrayList<PrintStream>();
		Map<Integer, java.net.Socket> listaClientes = new HashMap<Integer, java.net.Socket> ();
		
		try {
			ServerSocket servidor = new ServerSocket(9999);
			System.out.println("Servidor lendo a porta 9999");

			while (true) {
				Socket socket = servidor.accept();
				listaClientes.put(listaClientes.size() + 1, socket);
				
				new Thread() {
					public void run() {
						System.out.println("Cliente conectou: " + socket.getInetAddress().getHostName());
						
						
						try {
							PrintStream escrita = new PrintStream(socket.getOutputStream());
							ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
							
							escrita.println("Data servidor");
							escrita.println(System.currentTimeMillis());
							
							
							PrintStream escritaCliente = new PrintStream(socket.getOutputStream());
							clientes.add(escritaCliente);
							
							
							Scanner leitura = new Scanner(socket.getInputStream());	
							
							while (leitura.hasNext()) {
								String texto = leitura.nextLine();
								System.err.println(texto);
								
								for (PrintStream cliente : clientes) {
									cliente.println(texto);
									//cliente.flush();
								}
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();


			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
