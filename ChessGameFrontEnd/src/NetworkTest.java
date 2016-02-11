import static org.junit.Assert.*;
import javax.swing.JLabel;
import org.junit.Test;

public class NetworkTest {
	@Test
	public void testAction() {
		HttpURLConnectionTest http = new HttpURLConnectionTest();
		JLabel label = new JLabel("Please enter your name");
		String a = null;
		String b = null;
		String c = null;
		String d = null;
		try {
			a = http.joinGame("John", label);
			b = http.checkState("http://localhost:3000/getState", "Alex vs Bill");
			c = http.sendAction("John");
			d = http.checkState("http://localhost:3000/getState", "Alex vs Bill");
		} catch(Exception exp) {
			System.out.println("Error! Failed to send button action. " + exp);
		}
		assertEquals("Waiting for opponent...",a);
		assertEquals("1",b);
		assertEquals("2",c);
		assertEquals("2",d);
	}
}
