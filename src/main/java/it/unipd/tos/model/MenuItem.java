////////////////////////////////////////////////////////////////////
// CRISTIANO PANIGHEL 1201284
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {

    public enum Item {GELATI,BUDINI,BEVANDE};

    private final Item itemType;
    private final String name;
    private final double price;

    public MenuItem(Item itemType, String name, double price) {
        this.itemType = itemType;
        this.name = name;
        this.price = price;
    }

    public Item getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}