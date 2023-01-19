import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static Logger logger;
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String HELP_TEXT = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        logger = LogManager.getRootLogger();
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            if (tokens[0].equals("add")) {
                try {
                    logger.info("- запрос: " + command);
                    executor.addCustomer(tokens[1]);
                } catch (CustomerException e) {
                    if (e.getDATA() == CustomerException.OVER_FOUR) {
                        System.out.println("Передано более 4 слов в строке");
                        logger.error("Передано более 4 слов в строке");
                    } else if (e.getDATA() == CustomerException.LESS_FOUR) {
                        System.out.println("Передано менее 4 слов в строке");
                        logger.error("Передано менее 4 слов в строке");
                    } else if (e.getDATA() == CustomerException.THIS_IS_NOT_AN_EMAIL) {
                        System.out.println("Почта не валидна");
                        logger.error("Почта не валидна");
                    } else if (e.getDATA() == CustomerException.THIS_IS_NOT_A_NUMBER) {
                        System.out.println("Номер не валиден");
                        logger.error("номер не валиден");
                    }
                }
            } else if (tokens[0].equals("list")) {
                executor.listCustomers();
            } else if (tokens[0].equals("remove")) {
                executor.removeCustomer(tokens[1]);
            } else if (tokens[0].equals("count")) {
                System.out.println("There are " + executor.getCount() + " customers");
            } else if (tokens[0].equals("help")) {
                System.out.println(HELP_TEXT);
            } else {
                System.out.println(COMMAND_ERROR);
            }
        }
    }
}
