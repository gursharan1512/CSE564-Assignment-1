import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class compiler {
    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            String text = readTextFile(args[0]);
            //Vector<String[]> lineVector = splitString(text);
            String[] lineVector = splitString(text);
            translate(lineVector);
        }
        else {
            //raise exception - argument not passed
        }

    }

    public static String readTextFile (String path) throws IOException {

        String text = "";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader( file ));
        String str;
        while ( (str = br.readLine()) != null) {
            text = text + str + " ";
        }
        String text2 = text.replaceAll("\\s+", " ");
        System.out.println(text2);
        return text2;
    }

    public static String[] splitString (String text) {
        return text.split(" ");
    }

    public static void translate(String[] lineVector) {
        if (lineVector.length > 0) {
            Integer i = 0;
            while (i < lineVector.length) {
                i = checkMethod(lineVector, i);
                System.out.println("\n");
            }
        }
    }


    public static Integer checkMethod(String[] lineToken, Integer i) {

        if (!lineToken[i].equals("if") && !lineToken[i].equals("while") && !lineToken[i].equals("}")) {
            ++i;
            if (lineToken[i].equals("(")) {
                ++i;
                if (lineToken[i].equals(")")) {
                    ++i;
                    if (lineToken[i].equals("{")) {
                        ++i;
                        System.out.print("[ ");
                        i = checkMethod(lineToken, i);
                        i = checkIf(lineToken, i);
                        i = checkWhile(lineToken, i);
                        if (lineToken[i].equals("}")) {
                            System.out.print("] ");
                            ++i;
                        }
                    }
                }
            }
            else {
                System.out.print("- ");
            }
        }
        return i;
    }

    public static Integer checkIf(String[] lineToken, Integer i) {

        if (lineToken[i].equals("if")) {
            ++i;
            if (lineToken[i].equals("(")) {
                ++i;
                if (lineToken[i].equals(")")) {
                    ++i;
                    if (lineToken[i].equals("{")) {
                        ++i;
                        System.out.print("< ");
                        i = checkMethod(lineToken, i);
                        i = checkIf(lineToken, i);
                        i = checkWhile(lineToken, i);
                        if (lineToken[i].equals("}")) {
                            System.out.print("> ");
                            ++i;
                        }
                    }
                }
            }
        }
        return i;
    }

    public static Integer checkWhile(String[] lineToken, Integer i) {

        if (lineToken[i].equals("while")) {
            ++i;
            if (lineToken[i].equals("(")) {
                ++i;
                if (lineToken[i].equals(")")) {
                    ++i;
                    if (lineToken[i].equals("{")) {
                        ++i;
                        System.out.print("( ");
                        i = checkWhile(lineToken, i);
                        i = checkIf(lineToken, i);
                        i = checkMethod(lineToken, i);
                        if (lineToken[i].equals("}")) {
                            System.out.print(") ");
                            ++i;
                        }
                    }
                }
            }
        }
        return i;
    }
}
