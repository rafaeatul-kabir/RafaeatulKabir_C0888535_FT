import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//making an abstract Item class so that box and singleObject both can be item. So box can have list of items, and that can be any of those
abstract class Item {
}

class SingleObject extends Item {
    private final String name;

    public SingleObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name+"\n";
    }
}

class Box extends Item {
    private final int capacity; //number of items (simple items/objects or box) that the box can hold
    private final int boxNumber; // box number
    private final List<Item> items; // List to hold items (either other boxes or single objects)

    public boolean contains(String itemName){
        for(Item object:items){
            if(object instanceof SingleObject){
                return Objects.equals(((SingleObject) object).getName(), itemName); //will return true if found
            }
            else if (object instanceof Box){
                return ((Box) object).contains(itemName);
            }
        }
        return false;
    }
    public int findBoxNumberBySingleObjectName(String itemName) {
        for (Item item : items) {
            if (item instanceof SingleObject singleItem) {
                if (singleItem.getName().equals(itemName)) {
                    return boxNumber;
                }
            } else if (item instanceof Box subBox) {
                int subBoxNumber = subBox.findBoxNumberBySingleObjectName(itemName);
                if (subBoxNumber != -1) {
                    return boxNumber; // return the most outer box number
                }
            }
        }
        return -1; // returning -1 if the item is not found in the box or its sub-boxes so that it can be used in the recursive call
    }

    public Box(int capacity, int boxNumber) {
        this.capacity = capacity;
        this.boxNumber = boxNumber;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (items.size() < capacity) { //checking for capacity as the capacity is defined while creating
            items.add(item);
        } else {
            System.out.println("you can't add more than " + capacity + " objects in box number "+boxNumber+"!");
        }
    }

    @Override
    public String toString() {
        String listOfObjects = "";
        for (Item object : items) {
            listOfObjects+=object;
        }
        return listOfObjects;
    }
}
