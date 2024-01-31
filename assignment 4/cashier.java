package cp213;
import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-03-23
 */
public class Cashier {

    // Attributes
    private Menu menu = null;

    /**
     * Constructor.
     * 
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
        this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
        System.out.println("\nMenu:");
        System.out.println(menu.toString());
        System.out.println("Press 0 when done.");
        System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     * 
     * @return the completed Order.
     */
    public Order takeOrder() {
        System.out.println("Welcome to WLU Foodorama!");

        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        Order order = new Order();

        while (!done) {
            printCommands();

            String input = scanner.nextLine();
            try {
                int command = Integer.parseInt(input);

                if (command == 0) {
                    done = true;
                } else if (menu.isValid(command)) {
                    MenuItem item = menu.getItem(command);
                    System.out.println("How many " + item.getName() + "s?");
                    input = scanner.nextLine();
                    int quantity = Integer.parseInt(input);
                    order.addItem(item, quantity);
                } else {
                    System.out.println("Invalid command.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid command.");
            }
        }

        System.out.println("\nOrder summary:\n");
        for (OrderItem orderItem : order.getItems()) {
            System.out.printf("%-4d  %-12s $%5.2f%n", orderItem.getQuantity(), orderItem.getItem().getName(),
                    orderItem.getTotalCost());
        }
        System.out.println("\nTotal: $" + order.getTotalCost());

        return order;
    }

}
