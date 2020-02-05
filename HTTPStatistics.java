import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HTTPStatistics {
	
	/**has a default constructor that takes no arguments. This should initialize
	 * all required instance variables.
	 */
	private int get;
	private int put;
	private int other;
	
	public HTTPStatistics() {
		get = 0;
		put = 0;
		other = 0;
	}
	
	
	
	/*A method called incorporateRequest that takes one parameter of type HTTPRequest.
	 This method should figure out which type of command the HTTPRequest is and 
	 increment an internal count of how many get, put, or "other" commands
	 have been seen since being constructed or reset. Note that commands should be 
	 case insensitive.
	 */
	
	public void incorporateRequest(HTTPRequest set) {
		
		if(set.getCommand().equalsIgnoreCase("get"))
			get++;
		else if(set.getCommand().equalsIgnoreCase("put"))
			put++;
		else
			other++;

	}
	
	
	
	
	/*A method called incorporateRequestFromFile which takes no arguments and returns
	nothing. This should, through utilizing a FileDialog, prompt the user to select
	an input file. This input file will contain a sequence of HTTPRequests, one 
	per line. Each such request should be incorporated into this HTTPStatistics object.
	*/
	
	public void incorporateRequestsFromFile() {
		
		FileDialog fd = null;
        fd = new FileDialog(fd, "Choose a Data File", FileDialog.LOAD);
        fd.setVisible(true);
            
        // gets input file information.
        String filename = fd.getFile();
        String pathname = fd.getDirectory();
        String fullname = pathname + filename;
        
        // input file gets selected .
        File myFile = new File(fullname);
            
        // if such input could not open or does not exist, 
        // deals with exception by printing the catch condition and quits the method.
        Scanner myReader = null;
        try 
        {   
            myReader = new Scanner(myFile);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File is not found: " + fullname);
            return;
        } 
        while( myReader.hasNextLine() )
		{
        	String str = myReader.nextLine();
			HTTPRequest req = new HTTPRequest(str);
			incorporateRequest(req);
		}
        myReader.close();

	}
	
	
	
	
	/*A method called reset which takes no arguments and returns nothing.
	 It should simply set all counts of command types to 0.
	 */
	
	public void reset() {
		get = 0;
		put = 0;
		other = 0;
	}
	
	
	
	
	/*A method called printTo that takes a single argument of type PrintWriter and
	returns nothing. The method should print, to the PrintWriter parameter, the 
	percentage of gets, puts, and other commands among those incorporated since
	construction or the most recent reset call. The percentages should each be 
	printed on their own line with identifying text. Each percentage value should
	have exactly three spaces after the decimal point and the decimal points
	should all line up.
	*/
	
	public void printTo(PrintWriter write) throws FileNotFoundException {
		
		double sum, getp, putp, otherp;
		sum = get + put + other;
		getp = (get/sum)*100;
		putp = (put/sum)*100;
		otherp = (other/sum)*100;
		
         if(get==0 && put==0 && other==0)
         {
        	 write.println("Sorry! No data yet");
         }
         else
         {
        	 write.printf("%s %10.3f\n","The pct of get is: ", getp);
        	 write.printf("%s %10.3f\n", "The pct of put is: ", + putp);
        	 write.printf("%s %9.3f\n", "The pct of other is: ", + otherp);
         }
         write.close();  
	}

	
	

		
}//class HTTPStatistics
