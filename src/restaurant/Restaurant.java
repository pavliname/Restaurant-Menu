package restaurant;

import restaurant.Category;
import restaurant.Menu;
import restaurant.MenuItem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Restaurant {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = "01/01/2019";

        // date of old items
        LocalDate dateOfOldItem = LocalDate.parse(date, formatter);
        // old item
        MenuItem lasagnaFritta = new MenuItem
                ("Lasagna Fritta",
                        9.99,
                        "Fried parmesan-breaded lasagna.",
                        Category.APPETIZER, dateOfOldItem);
        // old item
        MenuItem chickenAlfredo = new MenuItem
                ("Chicken Alfredo",
                        17.29,
                        "Classic Chicken Alfredo.",
                        Category.MAIN_COURSE, dateOfOldItem);
        // new item
        MenuItem zeppoli = new MenuItem
                ("Zeppoli",
                        6.99,
                        "Soft, traditional Italian doughnuts dusted with powdered sugar",
                        Category.DESSERT);
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(lasagnaFritta);
        items.add(chickenAlfredo);
        items.add(zeppoli);
        // menu
        Menu menu = new Menu(items);

        // SET ROLE
        boolean validRole = false;
        String role = "";
        do {
            System.out.println("Login as:\n0 - User\n1 - Admin");
            int roleIndx = scanner.nextInt();
            scanner.nextLine();
            if (roleIndx < 0 || roleIndx > 1) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validRole = true;
                if (roleIndx == 0) role = "user";
                else role = "admin";
            }
        } while (!validRole);

//      AVAILABLE ACTIONS
        HashMap<String, String> actions = new HashMap<>();
        actions.put("0", "Exit");
        actions.put("1", "Print menu");
        if (role.equals("admin")) {
            actions.put("2", "Add new item to a menu");
            actions.put("3", "Delete item");
        }

        // OPERATIONS WITH MENU
        while (true) {
            System.out.println(actions);
            int actionInx = scanner.nextInt();
            System.out.println();
            scanner.nextLine();
            if (actionInx < 0 || actionInx >= actions.size()) {
                System.out.println("Invalid choice. Try again.");
            }
            // EXIT
            else if (actionInx == 0) {
                break;
            }
            // PRINT
            else if (actionInx == 1) {
                menu.print();
            }
            // ADD
            else if (actionInx == 2) {
                boolean valid = false;
                String newName = "";
                do {
                    System.out.println("Set name: ");
                    newName = scanner.nextLine();
                    // checking for duplicates
                    if (menu.checkForDuplicates(newName)) {
                        System.out.println("The menu already has this item! Try add another item.");
                    }
                    else {
                        valid = true;
                    }
                } while (!valid);
                System.out.println("Set price: ");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Set description: ");
                String newDescription = scanner.nextLine();
                Category newCategory = Category.DESSERT;
                valid = false;
                do {
                    System.out.println("Choose category: 0 - APPETIZER, 1 - MAIN COURSE, 2 - DESSERT");
                    int catIndx = scanner.nextInt();
                    scanner.nextLine();
                    if (catIndx < 0 || catIndx > 2) {
                        System.out.println("Invalid choice. Try again.");
                    } else {
                        valid = true;
                        if (catIndx == 0) newCategory = Category.APPETIZER;
                        else if (catIndx == 1) newCategory = Category.MAIN_COURSE;
                    }
                } while (!valid);
                MenuItem newItem = new MenuItem(newName, newPrice, newDescription, newCategory);
                System.out.println("New Item:");
                newItem.print();
                menu.addItem(newItem);
            }
            // DELETE
            else if (actionInx == 3) {
                System.out.println("Print name of the item: ");
                String delete = scanner.nextLine();
                // checking for duplicate
                if (!menu.checkForDuplicates(delete)) {
                    System.out.println("The menu doesn't have this item!");
                }
                else {
                    menu.deleteItem(delete);
                    System.out.println("The item has been deleted successfully!");
                }
            }
        }
        scanner.close();
    }
}
