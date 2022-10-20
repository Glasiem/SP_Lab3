import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Automaton automaton = new Automaton();
        File file = new File("E:\\in.txt");
        Scanner scanner = new Scanner(file);
        int state;
        int flag;
        String text;
        StringBuilder token = new StringBuilder();

        while (scanner.hasNextLine()){
            text = scanner.nextLine();
            int i = 0;
            do{
                flag = automaton.nextState(text.charAt(i));
//                System.out.println("flag " + flag);
                if (flag == -1) {
                    token.append(text.charAt(i));
                    System.out.println(token + " - unrecognized");
                    token = new StringBuilder("");
                    i++;
                } else if (flag == 1) {
                    state = automaton.getState();
                    switch (state) {
                        case 1 -> System.out.println(token + " - decimal");
                        case 25 -> System.out.println(token + " - hexadecimal");
                        case 5, 24 -> System.out.println(token + " - floating point");
                        case 16 -> System.out.println(token + " - reserved word or identificator");
                        case 23 -> System.out.println(token + " - operator");
                        case 99, 100 -> System.out.println(token + " - unrecognized");
                    }
                    token = new StringBuilder("");
                } else if (flag == 2) {
                    state = automaton.getState();
                    token.append(text.charAt(i));
                    switch (state) {
                        case 27,9,11 -> System.out.println(token + " - string literal");
                        case 17 -> System.out.println(token + " - delimiter");
                        case 23 -> System.out.println(token + " - operator");
                        case 26 -> System.out.println(token + " - commentary");
                    }
                    token = new StringBuilder("");
                    i++;
                } else if (flag == 3){
                    i++;
                }else {
                    token.append(text.charAt(i));
                    i++;
                }
            }while (i < text.length());
            state = automaton.getState();
            if (state == 13){
                System.out.println(token + " - commentary");
                token = new StringBuilder("");
            }
            if ((state != 14) && (state != 7)){
                if (!token.isEmpty()){
                    System.out.println(token + " - unrecognized");
                }
                token = new StringBuilder("");
            } else {
                token.append("\n");
            }
        }
        if (!token.isEmpty()){
            token.deleteCharAt(token.length()-1);
            System.out.println(token + " - unrecognized");
        }
    }
}