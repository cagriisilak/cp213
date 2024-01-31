package cp213;

import java.util.Collection;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-03-23
 */
public class Menu {

    // Attributes.
    private ArrayList<MenuItem> items;

    /**
     * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
     * copied into the Menu List.
     *
     * @param items an existing Collection of MenuItems.
     */
    public Menu(Collection<MenuItem> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {
        this.items = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(" ");
            double price = Double.parseDouble(parts[0]);
            String name = parts[1];
            MenuItem item = new MenuItem(name, price);
            this.items.add(item);
        }
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {
        return this.items.get(i);
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {
        return this.items.size();
    }

    /**
     * Adds a new MenuItem to the List.
     *
     * @param item The MenuItem to add.
     */
    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    /**
     * Returns true if the given itemNumber corresponds to a valid item in the
     * menu.
     *
     * @param itemNumber The item number to check.
     * @return true if the itemNumber is valid.
     */
    public boolean validItem(int itemNumber) {
        return itemNumber > 0 && itemNumber <= this.items.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
     * 5) poutine      $ 3.75
     * 6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (MenuItem item : this.items) {
            sb.append(String.format("%d) %-20s $%6.2f%n", i, item.getName(), item.getPrice()));
            i++;
        }
        return sb.toString();
    }}
