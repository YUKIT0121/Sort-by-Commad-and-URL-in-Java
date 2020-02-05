



import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HTTPRequest {
	
	private String command;
	private String url;

	
		
	/**constructor that takes a single String as an argument. It should extract 
	 * the command and URL from this String. The command is simply the first word
	 * in the parameter String(not including spaces). The url is the next word
	 * (until the next space or end of line is reached.)
	 */
	
	public HTTPRequest(String argument){
		int index = 0;
		int count = 0;
		while(index < argument.length())
		{
			String subString = "";
			while(index < argument.length() && argument.charAt(index)!=' ') {
				subString += argument.charAt(index);
				index++;
			}
			
			while(index < argument.length() && argument.charAt(index)==' ')
			{
				index++;
			}
			count++;
			if(count == 1 && subString.equals(""))
				count--;
			
			if(count == 1 && !subString.equals(""))
				command = subString;
			else if(count == 2)
				url = subString;

		}

	
	}
		
		
		
	/**Second constructor that takes one argument, namely a Scanner. The class should
	 * read only the "next Line" from the Scanner, and treat it as a String from 
	 * which to extract contents exactly as those described in the previous constructor.
	 */
	
	public HTTPRequest(Scanner in) {
		 this(in.nextLine());
	}
		
		
	/*A method called getCommand that should take no arguments and return a String
	representing the command from this HTTPRequest.class Note that the command 
	should have no spaces in it.*/
	
	public String getCommand() {
		 return command;
	}
		
		
		
	/*A method called getURL that should take no arguments and return a String
	representing the URL from this HTTPRequest. Note that the URL should have no
	spaces in it.
	*/
		
	public String getURL() {
		return url;
	}
		
		
		
	/*A method called toString that should take no parameters and should return a String 
 	that is simply the command followed by a space followed by the URL. It should 
	have no leading or trailing spaces. Note that this method is automatically
	called by Java when you print out an HTTPRequest object
	*/
			
	
	public String toString() {
		return command + " " + url;
	}

	
	
	
	
}//class HTTPRequest
