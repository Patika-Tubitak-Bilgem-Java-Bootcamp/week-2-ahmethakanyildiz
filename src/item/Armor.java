package item;

public class Armor {
    private int id;
    private int health;
    private int price;
    private String name;

    public Armor(int id, int health, int price, String name) {
        this.id = id;
        this.health = health;
        this.price = price;
        this.name=name;
    }

    public static Armor[] getArmors(){
        Armor[] armorList = new Armor[3];
        //Zırhlar id sırasına göre listeye eklenmelidir!
        armorList[0]= new Armor(1,1,15,"Hafif Zırh");
        armorList[1]= new Armor(2,3,25,"Orta Zırh");
        armorList[2]= new Armor(3,5,40,"Ağır Zırh");
        return armorList;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
