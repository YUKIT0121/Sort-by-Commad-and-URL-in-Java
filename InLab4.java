import java.awt.FileDialog;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Driver Very simple tests for HTTPRequest class .
 */
public class InLab4 {

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
		
		// print border when done. 
		System.out.println("==========================================");
	}
	
	/** 
	 * Simply prints out expected value next to actual value and indicates failure.
	 * @param expected - the expected value 
	 * @param actual - the actual value
	 */
	public static void ActualVsExpected(String expected, String actual)
	{
		// print ut both expected and actual values ...
		System.out.println("EXPECTED:"+expected+":");
		System.out.println("  ACTUAL:"+actual+":");
		
		// if they didn't match, alert the user. 
		if (!expected.equalsIgnoreCase(actual))
		{
			System.out.println("ERROR -- MISMATCH!");
		}
		
		// print border when done. 
		System.out.println("==========================================");
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		HTTPRequest testy = new HTTPRequest("    POST    challenge    did  you get it?");
		ActualVsExpected("POST", testy.getCommand());
		ActualVsExpected("challenge", testy.getURL());

		
		// example to make sure that toString isn't directly printing. 
		System.out.println("*** Nothing should appear between these two lines ***");
		String strVersion = testy.toString();
		System.out.println("*** Nothing should appear between these two lines ***");
		ActualVsExpected("POST challenge", testy);
		
		Scanner kbd = new Scanner(System.in);
		System.out.print(">");
		testy=new HTTPRequest(kbd);
		while(!testy.getCommand().equalsIgnoreCase("quit"))
		{
			System.out.println(testy);
			System.out.print(">");
			testy=new HTTPRequest(kbd);
		}

	
		HTTPStatistics stats = new HTTPStatistics();
		stats.incorporateRequestsFromFile();
		
		FileDialog fd = null;
		fd = new FileDialog(fd, "Name your output file!", FileDialog.SAVE);
		fd.setVisible(true);
		try {
			PrintWriter outputFile = new PrintWriter(fd.getDirectory()+fd.getFile());
			stats.printTo(outputFile); // build stats file
			outputFile.close(); // close stats file
			fd.dispose(); // get rid of GUI thread
		}
		catch(IOException e) // if user cancelled, etc ...
		{
			System.out.println("Idiot User Alert - invalid file selected \""+
							   fd.getDirectory()+fd.getFile() + "\". Cannot proceed!");
			return;
		}

		
		
	}

}
