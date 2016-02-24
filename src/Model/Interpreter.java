package Model;

public class Interpreter {

	private static final String WHITESPACE = "\\p{Space}";
	public Interpreter() {
		// TODO Auto-generated constructor stub
	}
	
//    private static String readFileToString (String filename) throws FileNotFoundException {
//        final String END_OF_FILE = "\\z";
//        Scanner input = new Scanner(new File(filename));
//        input.useDelimiter(END_OF_FILE);
//        String result = input.next();
//        input.close();
//        return result;
//    }
    
    private static void parseText (Parser lang, String text) {
    	String[] split = text.split(WHITESPACE);
        for (String s : split) {
            if (s.trim().length() > 0) {
                System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) { 
        final String WHITESPACE = "\\p{Space}";
//        String[] examples = {
//            "",
//            "# foo",
//            "foo #",
//            "#",
//            "fd",
//            "FD",
//            "forwardd",
//            "equalp",
//            "equal?",
//            "equal??",
//            "+",
//            "SuM",
//            "-",
//            "*",
//            "/",
//            "%",
//            "~",
//            "+not",
//            "not+",
//            "++",
//            "+*+",
//            "or",
//            "FOR",
//            "allOrNothing",
//            "all_or_nothing",
//            "allOr_nothing?",
//            "allOr?nothing_",
//            ":allornothing",
//            "PI",
//            "90",
//            "9.09",
//            "9.0.0",
//            "[",
//            "]",
//            "(",
//            ")", 
//            "fd (sum 10 3)"
//        };
        Parser lang = new Parser();
        lang.addPatterns("resources/languages/English");
        lang.addPatterns("resources/languages/Syntax");
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        String userInput2 = "fd + 10 div 6 2";
		//String fileInput = readFileToString("square.logo");
		//parseText(lang, examples);
		parseText(lang, userInput);

		parseText(lang, userInput);
		parseText(lang, userInput2);
		//parseText(lang, fileInput.split(WHITESPACE));
    	
    }

}
