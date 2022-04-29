import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;

public class Utils {
	
	public void sendMessages(Map<Integer, java.net.Socket> listaClientes) {
	
	for (Iterator<Integer> iter = listaClientes.keySet().iterator(); iter.hasNext(); )
		{
		    int key = iter.next();
	
		    java.net.Socket client = listaClientes.get(key);
	
		    // Sending the response back to the client.
		    // Note: Ideally you want all these in a try/catch/finally block
		    OutputStream os;
			try {
				os = client.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
			    BufferedWriter bw = new BufferedWriter(osw);
			    
			    bw.write("Teste");
			    bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
	}
}
