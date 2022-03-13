package item;

public class Inventory {
    private int weaponDamage;
    private String weaponName;
    private int armorHealth;
    private String armorName;
    private boolean toprakveAtes;
    private boolean tahta;
    private boolean su;

    public Inventory(){
        this.weaponDamage=0;
        this.weaponName="Yumruk";
        this.armorHealth=0;
        this.armorName="Köylü Kıyafeti";
        this.toprakveAtes=false;
        this.tahta=false;
        this.su=false;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getArmorHealth() {
        return armorHealth;
    }

    public void setArmorHealth(int armorHealth) {
        this.armorHealth = armorHealth;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public boolean isToprakveAtes() {
        return toprakveAtes;
    }

    public void setToprakveAtes(boolean toprakveAtes) {
        this.toprakveAtes = toprakveAtes;
    }

    public boolean isTahta() {
        return tahta;
    }

    public void setTahta(boolean tahta) {
        this.tahta = tahta;
    }

    public boolean isSu() {
        return su;
    }

    public void setSu(boolean su) {
        this.su = su;
    }
}
