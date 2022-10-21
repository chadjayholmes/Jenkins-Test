import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            InputStreamReader in= new InputStreamReader(System.in);
            BufferedReader input = new BufferedReader(in);
            String str;
            System.out.print("Enter your command: ");

            while ((str = input.readLine()) != "exit") {
                String[] words = str.split(" ");

                String command = words[0];
                int number1 = Integer.parseInt(words[1]);
                int number2 = 1;
                if(words.length > 2) {
                    number2 = Integer.parseInt(words[2]);
                }

                Calculator calculator = new Calculator();

                switch (command) {
                    case "add":
                        System.out.println(calculator.add(number1, number2));
                        break;
                    case "subtract":
                        System.out.println(calculator.subtract(number1, number2));
                        break;
                    case "multiply":
                        System.out.println(calculator.multiply(number1, number2));
                        break;
                    case "divide":
                        System.out.println(calculator.divide(number1, number2));
                        break;
                    case "fibonacci":
                        System.out.println(calculator.fibonacciNumberFinder(number1));
                        break;
                    case "binary":
                        System.out.println(calculator.intToBinaryNumber(number1));
                        break;
                }
            }

        } catch (IOException io) {
            io.printStackTrace();
        }


    }
}