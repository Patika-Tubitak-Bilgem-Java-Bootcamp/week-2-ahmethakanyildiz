package character.player;

import item.Inventory;
import location.Location;
import location.SafeHouse;
import location.ToolStore;

import java.util.Scanner;

public class Player {

    private int damage;
    private int health;
    private int money;
    private String name;
    private String type;
    private Inventory inventory;
    private GameChar[] characterTypes;
    private Scanner input = new Scanner(System.in);

    public Player(String name){
        this.name=name;
        this.inventory=new Inventory();
    }

    public void selectType(){
        Samurai samurai = new Samurai();
        Archer archer = new Archer();
        Knight knight = new Knight();
        //Karakterler id sırasına göre listeye eklenmelidir!
        GameChar[] characters = {samurai,archer,knight};
        this.setCharacterTypes(characters);
        System.out.println("Lütfen oyuna başlamak için bir karakter seçiniz...");
        for(GameChar character: characters){
            String type= character.getType()+" \t";
            if(character.getId()==2) type+="\t";
            System.out.println("Karakter("+character.getId()+"): "+type+" Hasar: "+character.getDamage()+
                    " \t Sağlık: "+character.getHealth()+" \t Para: "+character.getMoney());

        }
        while(true){
            System.out.print("Seçim: ");
            int selectChar = input.nextInt();
            if(selectChar>characters.length || selectChar<1){
                System.out.println("Geçersiz girdi! Lütfen listeden bir numara giriniz!");
            }
            else{
                selectChar-=1;
                this.setDamage(characters[selectChar].getDamage());
                this.setHealth(characters[selectChar].getHealth());
                this.setMoney(characters[selectChar].getMoney());
                this.setType(characters[selectChar].getType());
                this.getInventory().setArmorName("Temel "+this.getType()+" Kıyafeti");
                break;
            }
        }
        System.out.println(this.getName()+", sen bir "+this.getType()+" oldun.");
    }

    public void printInfo(){
        System.out.println("Hasarınız: "+this.getDamage()+
                " \t Silahınız: "+this.getInventory().getWeaponName()+
                " \t Sağlık: "+this.getHealth()+
                " \t Bloklama: "+this.getInventory().getArmorHealth()+
                "\t Zırhınız: "+this.getInventory().getArmorName()+
                " \t Para: "+this.getMoney());
    }

    public int getDamage() {
        return damage+this.getInventory().getWeaponDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public GameChar[] getCharacterTypes() {
        return characterTypes;
    }

    public void setCharacterTypes(GameChar[] characterTypes) {
        this.characterTypes = characterTypes;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
}
