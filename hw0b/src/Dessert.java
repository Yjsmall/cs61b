public class Dessert {

    private final int flavor;
    private final int price;
    private static int size;

    public Dessert(int flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        size++;
    }

    static int numDesserts() {
        return size;
    }

    public void printDessert() {
        System.out.println(flavor + " " + price + " " + size);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
