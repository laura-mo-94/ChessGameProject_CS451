import static org.junit.Assert.*;
import org.junit.Test;

public class NetworkTest {
	@Test
	public void testAction() {
		HttpURLConnectionTest http = new HttpURLConnectionTest();
		String a;
		String b;
		String c;
		try {
			a = http.checkStatus();
			b = http.sendAction();
			c = http.checkStatus();
		} catch(Exception exp) {
			System.out.println("Error! Failed to send button action. " + exp);
		}
		assertEquals("1",a);
		assertEquals("2",b);
		assertEquals("2",c);
	}
}
