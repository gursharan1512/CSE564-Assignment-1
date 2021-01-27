import java.io.*;

/**
 * Translate the give block of code to tokens and create a symbol representation for the same.
 * @author Gursharanjit Singh Ghotra gghotra@asu.edu
 * @author Manthan Agrawal magraw12@asu.edu
 */

public class compiler {
    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            String code = readTextFile(args[0]);
            String[] tokens = getToken(code);
            translate(tokens);
        }
        else {
            throw new FileNotFoundException("Please pass a file in argument");
        }
    }

    /**
     * Reads File from given path.
     * @param path input code file path
     * @return String of code without extra spaces.
     * @throws IOException file reading exception.
     */
    public static String readTextFile (String path) throws IOException {

        StringBuilder code = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;
        while ( (str = br.readLine()) != null) code.append(str).append(" ");

        return code.toString().replaceAll("\\s+", " ");
    }

    /**
     * Creates array of token from given string.
     * @param code - code in single string
     * @return array of String tokens.
     */
    public static String[] getToken(String code) {
        return code.split(" ");
    }

    /**
     *Transforms code into required symbol representation.
     * @param tokens - Array of String Token.
     */
    public static void translate(String[] tokens) {
        if (tokens.length > 0) {
            int i = 0;
            while (i < tokens.length) {
                i = checkMethod(tokens, i);
                System.out.println("\n");
            }
        }
    }

    /**
     * Check whether given token is Method and converts it into expected symbols.
     * @param tokens - Array of token.
     * @param counter - array counter.
     * @return Int updated position of array counter.
     */
    public static int checkMethod(String[] tokens, int counter) {

        if (!tokens[counter].equals("if") && !tokens[counter].equals("while") && !tokens[counter].equals("}")) {
            ++counter;
            if (tokens[counter].equals("(")) {
                ++counter;
                if (tokens[counter].equals(")")) {
                    ++counter;
                    if (tokens[counter].equals("{")) {
                        ++counter;
                        System.out.print("[ ");
                        counter = checkInstruction(tokens, counter);
                        counter = checkIf(tokens, counter);
                        counter = checkWhile(tokens, counter);
                        if (!tokens[counter].equals("}")) {
                            while (!tokens[counter].equals("}")){
                                counter = checkInstruction(tokens, counter);
                                counter = checkIf(tokens, counter);
                                counter = checkWhile(tokens, counter);
                            }
                            System.out.print("] ");
                            ++counter;
                        } else{
                            System.out.print("] ");
                            ++counter;
                        }
                    } else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                } else {
                    throw new RuntimeException("Invalid Syntax");
                }
            }
            else {
                throw new RuntimeException("Invalid Syntax");
            }
        }
        return counter;
    }

    /**
     * Check whether given token is instruction and converts it into expected symbols.
     * @param tokens String Array of Token.
     * @param counter Array counter.
     * @return Updated Array counter.
     */
    public static int checkInstruction(String[] tokens, int counter){

        if (!tokens[counter].equals("if") && !tokens[counter].equals("while") && !tokens[counter].equals("}")) {
            if (tokens[counter].equals("(") || tokens[counter].equals(")") || tokens[counter].equals("{")) {
                throw new RuntimeException("Invalid Syntax");
            }
            ++counter;
            System.out.print("- ");
        }
        return counter;
    }

    /**
     * Checks whether given token is "if" and converts it into expected symbols.
     * @param tokens Array of String tokens.
     * @param counter Array Counter.
     * @return Updated Array Counter.
     */
    public static int checkIf(String[] tokens, int counter) {

        if (tokens[counter].equals("if")) {
            ++counter;
            if (tokens[counter].equals("(")) {
                ++counter;
                if (tokens[counter].equals(")")) {
                    ++counter;
                    if (tokens[counter].equals("{")) {
                        ++counter;
                        System.out.print("< ");
                        counter = checkInstruction(tokens, counter);
                        counter = checkIf(tokens, counter);
                        counter = checkWhile(tokens, counter);
                        if (!tokens[counter].equals("}")) {
                            while (!tokens[counter].equals("}")){
                                counter = checkInstruction(tokens, counter);
                                counter = checkIf(tokens, counter);
                                counter = checkWhile(tokens, counter);
                            }
                            System.out.print("> ");
                            ++counter;
                        } else {
                            System.out.print("> ");
                            ++counter;
                        }
                    } else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                } else {
                    throw new RuntimeException("Invalid Syntax");
                }
            }  else {
                throw new RuntimeException("Invalid Syntax");
            }
        }
        return counter;
    }

    /**
     * Check whether given token is "While" and converts it into expected symbols.
     * @param tokens Array of String tokens.
     * @param counter Array Counter.
     * @return Updated Array Counter.
     */
    public static int checkWhile(String[] tokens, int counter) {

        if (tokens[counter].equals("while")) {
            ++counter;
            if (tokens[counter].equals("(")) {
                ++counter;
                if (tokens[counter].equals(")")) {
                    ++counter;
                    if (tokens[counter].equals("{")) {
                        ++counter;
                        System.out.print("( ");
                        counter = checkWhile(tokens, counter);
                        counter = checkIf(tokens, counter);
                        counter = checkInstruction(tokens, counter);
                        if (!tokens[counter].equals("}")) {
                            while (!tokens[counter].equals("}")){
                                counter = checkInstruction(tokens, counter);
                                counter = checkIf(tokens, counter);
                                counter = checkWhile(tokens, counter);
                            }
                            System.out.print(") ");
                            ++counter;
                        } else {
                            System.out.print(") ");
                            ++counter;
                        }
                    }  else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                } else {
                    throw new RuntimeException("Invalid Syntax");
                }
            } else {
                throw new RuntimeException("Invalid Syntax");
            }
        }
        return counter;
    }
}
