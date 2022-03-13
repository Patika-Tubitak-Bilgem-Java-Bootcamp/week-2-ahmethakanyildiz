package character.enemy;

public abstract class Enemy {
    private int id;
    private String name;
    private int damage;
    private int health;
    private int prize;
    private int defaultHealth;

    public Enemy(int id,String name, int damage, int health,int prize) {
        this.id = id;
        this.name=name;
        this.damage = damage;
        this.health = health;
        this.prize=prize;
        this.defaultHealth=health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
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

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }
}
