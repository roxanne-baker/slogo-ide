import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LogoFileLoadReadRun {
	private static final String fileName = "examples/procedures_with_parameters/house.logo";
	
	public LogoFileLoadReadRun() {
	}
	
	static String readText (File file) {
		StringBuilder sb = new StringBuilder();
		try { 
		    Scanner scan = new Scanner(file);
		    while(scan.hasNextLine()){
		        String line = scan.nextLine();
		        sb.append(line);
		        sb.append("\n");
		    }
		} catch (FileNotFoundException e) { 
			System.out.println("couldn't find the file");
		}
		return sb.toString().trim();
	}
	
	static void writeText (File file) { 
		
	}
	
	public static void main(String[] args) { 
		File f = new File(fileName);
		System.out.println(readText(f));
	}
}
