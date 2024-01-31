package cp213;

import java.math.BigDecimal;

/**
 * Defines the name and price of a menu item. Price is stored as a BigDecimal to
 * avoid rounding errors.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-03-23
 */
public class MenuItem {

    // Attributes
    private static final String ITEM_FORMAT = "%-12s $%5.2f";
    private String name = null;
    private BigDecimal price = null;

    /**
     * Constructor. Must set price to 2 decimal points for calculations.
     * 
     * @param name  Name of the menu item.
     * @param price Price of the menu item.
     */
    public MenuItem(final String name, final BigDecimal price) {
        this.name = name;
        this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Alternate constructor. Converts a double price to BigDecimal.
     * 
     * @param name  Name of the menu item.
     * @param price Price of the menu item.
     */
    public MenuItem(final String name, final double price) {
        this(name, new BigDecimal(price));
    }

    /**
     * name getter
     * 
     * @return Name of the menu item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * price getter
     * 
     * @return Price of the menu item.
     */
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Returns a MenuItem as a String in the format:
     * 
     * <pre>
     * hot dog      $ 1.25
     * pizza        $10.00
     * </pre>
     */
    @Override
    public String toString() {
        return String.format(ITEM_FORMAT, this.name, this.price);
    }

}
