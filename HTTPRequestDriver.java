/**
 * Driver Very simple tests for HTTPRequest class .
 *
 */
public class HTTPRequestDriver {

	/** 
	 * Simply prints out expected value next to actual value and indicates failure.
	 * @param expected - the expected value 
	 * @param actual - the actual value
	 */
	public static void ActualVsExpected(String expected, HTTPRequest actual)
	{
		// print ut both expected and actual values ...
		System.out.println("EXPECTED:"+expected+":");
		System.out.println("  ACTUAL:"+actual+":");
		
		// if they didn't match, alert the user. 
		if (!expected.equalsIgnoreCase(actual.toString()))
		{
			System.out.println("ERROR -- MISMATCH!");
		}
		
		// print borderwhen done. 
		System.out.println("==========================================");
	}
	
	public static void main(String[] args) {

		// a sequence of HTTPRequest constructions and tests ...
		HTTPRequest request = new HTTPRequest("GET /index.html HTML/1.1");
		ActualVsExpected("GET /index.html", request);
		
		request = new HTTPRequest("GET      /index.html HTML/1.1");
		ActualVsExpected("GET /index.html", request);
		
		request = new HTTPRequest("   GET /index.html HTML/1.1");
		ActualVsExpected("GET /index.html", request);
		
		request = new HTTPRequest("         GET   /index.html  HTML/1.1");
		ActualVsExpected("GET /index.html", request);
		
		request = new HTTPRequest("PUT mydata.txt HTML/1.1");
		ActualVsExpected("PUT mydata.txt", request);

		request = new HTTPRequest("GET  /some/long/path.txt   HTML/1.1");
		ActualVsExpected("GET /some/long/path.txt", request);
		
		// example to make sure that toString isn't directly printing. 
		request = new HTTPRequest("GET /just/a/check/that/you/did/toString/Correctly  HTML/1.1");
		System.out.println("*** Nothing should appear between these two lines ***");
		String strVersion = request.toString();
		System.out.println("*** Nothing should appear between these two lines ***");
		ActualVsExpected("GET /just/a/check/that/you/did/toString/Correctly", request);
	}

}

