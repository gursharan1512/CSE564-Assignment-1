import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class compiler {
    public static void main(String[] args) throws IOException {

        String text = readTextFile(args[0]);
        //Vector<String> text = readTextFile(args[0]);
        Vector<StringTokenizer> lineVector = splitString(text);

        translate(lineVector);

//        for (StringTokenizer lineToken: lineVector) {
//            while (lineToken.hasMoreTokens()) {
//                System.out.print(lineToken.nextToken() + " ");
//            }
//            System.out.print("\n");
//        }
//        for (StringTokenizer lineToken: lineVector) {
//            if (lineToken.hasMoreTokens()) {
//                System.out.println(lineToken.nextToken() + " ");
//            }

//            if (lineToken.hasMoreTokens()) {
//                System.out.println(lineToken.nextToken());
//            }
        }

    public static String readTextFile (String path) throws IOException {

        String text = "";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader( file ));
        String str;
        while ( (str = br.readLine()) != null) {
            text = text + str + " \n ";
        }
        return text;
    }

//    public static Vector<String> readTextFile (String path) throws IOException {
//        String str;
//        File file = new File(path);
//        BufferedReader br = new BufferedReader(new FileReader( file ));
//        Vector<String> vector = new Vector<String>();
//        while ( (str = br.readLine()) != null) {
//            vector.add(str);
//        }
//        return vector;
//    }

    public static Vector<StringTokenizer> splitString (String text) {

        String[] lines = text.split("\n");

        Vector<StringTokenizer> vector = new Vector<StringTokenizer>();

        for ( String line: lines) {
            StringTokenizer st = new StringTokenizer(line);
            vector.add(st);
        }

        return vector;
    }

    public static void translate(Vector<StringTokenizer> lineVector) {

        for (StringTokenizer lineToken: lineVector) {
            while (lineToken.hasMoreTokens()) {
                checkIf(lineToken);
            }
        }

//        checkMethod();
//        checkIf();
//        checkWhile();
//        checkInstruction();
    }

    public static void checkMethod(StringTokenizer lineToken) {
    }

    public static void checkIf(StringTokenizer lineToken) {
        if (lineToken.nextToken().equals("if")) {
            if (lineToken.nextToken().equals("(")) {
                if (lineToken.nextToken().equals(")")) {
                    if (lineToken.nextToken().equals("{")) {

                    }
                }
            }
        }
    }

    public static void checkWhile(String currentWord) {

    }

    public static void checkInstruction(String currentWord) {

    }
}
