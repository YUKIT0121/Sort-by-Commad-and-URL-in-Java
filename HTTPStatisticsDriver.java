import java.awt.FileDialog; // allows GUI for selecting a file
import java.io.*;           // allows us to use files

/**
 * Test code for the HTTPStatistics class. 
 *   Note that this requires HTTPRequest to be working as well.
 */
public class HTTPStatisticsDriver {

	public static void dumpToFile(HTTPStatistics stats)
	{
		// pop up  file dialog and get file name components
		FileDialog fd = null;
		fd = new FileDialog(fd, "Name your output file!", FileDialog.SAVE);
		fd.setVisible(true);
		try {
			PrintWriter outputFile = new PrintWriter(fd.getDirectory()+fd.getFile());
			stats.printTo(outputFile); // build stats file
			outputFile.close(); // close stats file
		}
		catch(IOException e) // if user cancelled, etc ...
		{
			System.out.println("Idiot User Alert - invalid file selected \""+
							   fd.getDirectory()+fd.getFile() + "\". Cannot proceed!");
			return;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// new HTTPStatistics object to use later ...
		HTTPStatistics webServerStats = new HTTPStatistics();
		
		// incorporate some requests ,,,,
		HTTPRequest req = new HTTPRequest("GET /something");
		webServerStats.incorporateRequest(req);
		
		HTTPRequest putty = new HTTPRequest("PUT /data");
		webServerStats.incorporateRequest(putty);
		
		HTTPRequest garbage = new HTTPRequest("GARBAGE /junkity/mcjunk/junk");
		webServerStats.incorporateRequest(garbage);

		/*
		 * Resulting file should look something like:
		 * 
		 * The pct of gets is    33.333
		 * The pct of puts is    33.333
		 * The pct of others is  33.333
		 */
		dumpToFile(webServerStats); 
		
		webServerStats.reset(); // sets all counts back to 0. 
		
		/*
		 * Resulting file should look something like:
		 * 
		 * Sorry, no data yet!
		 */
		dumpToFile(webServerStats);

		webServerStats.incorporateRequestsFromFile(); // get request from a file. 
		
		/*
		 * resulting file contents vary based on provided input file. 
		 */
		dumpToFile(webServerStats);
	}

}
