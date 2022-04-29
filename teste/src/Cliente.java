
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		List<PrintStream> clientes = new ArrayList<PrintStream>();
		try {
			Socket cliente = new Socket("127.0.0.1", 9999);
			System.out.println("Cliente conectado com o servidor");

			PrintStream escrita = new PrintStream(cliente.getOutputStream());

			new Thread() {
				public void run() {
					try {
						SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
						Date date = new Date(System.currentTimeMillis());
						escrita.println("Data cliente");
						escrita.println(date);
						
						Scanner leitura = new Scanner(cliente.getInputStream());	
						
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
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
