package restaurant;

import java.time.LocalDate;

public class MenuItem {

    private String name;
    private double price;
    private String description;
    private Category category;
    private LocalDate dateAdded;

    public MenuItem (String name, double price, String description, Category category, LocalDate dateAdded){
        this.name=name;
        this.price = price;
        this.description=description;
        this.category=category;
        this.dateAdded=dateAdded;
    }

    public MenuItem (String name, double price, String description, Category category){
        this.name=name;
        this.price = price;
        this.description=description;
        this.category=category;
        this.dateAdded=LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void print () {
        if (getDateAdded().isAfter(LocalDate.parse("2020-01-01"))) {
            System.out.println("*NEW*");
        }
        System.out.println(getName());
        System.out.println(getCategory());
        System.out.println(getDescription());
        System.out.println(getPrice() + "$");
    }
}
