package location;

import character.player.Player;
import item.Armor;
import item.Weapon;

public class ToolStore extends NormalLocation{

    public ToolStore (Player p){
        super(2,p,"Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Mağazaya hoşgeldiniz!");
        while(true){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış Yap");
            System.out.print("Seçim: ");
            int selectCase = Location.input.nextInt();
            if(selectCase>3 || selectCase<1){
                System.out.println("Geçersiz girdi!");
            }
            else{
                if(selectCase==1){
                    buyWeapon();
                }
                else if(selectCase==2){
                    buyArmor();
                }
                else{
                    System.out.println("Mağazadan ayrıldın!");
                    break;
                }
            }
        }
        return true;
    }

    public void buyWeapon(){
        System.out.println("------------Silahlar------------");
        Weapon[] weaponList = Weapon.getWeapons();
        for(Weapon w: weaponList){
            System.out.println(w.getName()+" <Para: "+w.getPrice()+" Hasar: "+w.getDamage()+" ID: "+w.getId()+">");
        }
        while(true){
            System.out.print("Seçim: ");
            int selectWeapon=Location.input.nextInt();
            if(selectWeapon>weaponList.length || selectWeapon<1){
                System.out.println("Geçersiz girdi!");
            }
            else{
                selectWeapon-=1;
                Weapon selectedWeapon = weaponList[selectWeapon];
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Bu silahı almak için yeterli paranız yok. (Paranız: "+this.getPlayer().getMoney()+")");
                }
                else{
                    if(this.getPlayer().getInventory().getWeaponName().equals(selectedWeapon.getName())){
                        System.out.println("Zaten elinde bu silah var. İşlem gerçekleştirilemiyor!");
                    }
                    else{
                        System.out.println(selectedWeapon.getName()+" satın alındı!");
                        this.getPlayer().setMoney(this.getPlayer().getMoney()-selectedWeapon.getPrice());
                        this.getPlayer().getInventory().setWeaponDamage(selectedWeapon.getDamage());
                        this.getPlayer().getInventory().setWeaponName(selectedWeapon.getName());
                        System.out.println("Kalan paranız: "+this.getPlayer().getMoney());
                    }

                }
                break;
            }
        }
    }

    public void buyArmor(){
        System.out.println("------------Zırhlar-------------");
        Armor[] armorList = Armor.getArmors();
        for(Armor a: armorList){
            System.out.println(a.getName()+" <Para: "+a.getPrice()+" Engelleme: "+a.getHealth()+" ID: "+a.getId()+">");
        }
        while(true){
            System.out.print("Seçim: ");
            int selectArmor=Location.input.nextInt();
            if(selectArmor>armorList.length || selectArmor<1){
                System.out.println("Geçersiz girdi!");
            }
            else{
                selectArmor-=1;
                Armor selectedArmor = armorList[selectArmor];
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Bu zırhı almak için yeterli paranız yok. (Paranız: "+this.getPlayer().getMoney()+")");
                }
                else{
                    if(this.getPlayer().getInventory().getArmorName().equals(selectedArmor.getName())){
                        System.out.println("Zaten üzerinde bu zırh var. İşlem gerçekleştirilemiyor!");
                    }
                    else{
                        System.out.println(selectedArmor.getName()+" satın alındı!");
                        this.getPlayer().setMoney(this.getPlayer().getMoney()-selectedArmor.getPrice());
                        this.getPlayer().getInventory().setArmorHealth(selectedArmor.getHealth());
                        this.getPlayer().getInventory().setArmorName(selectedArmor.getName());
                        System.out.println("Kalan paranız: "+this.getPlayer().getMoney());
                    }
                }
                break;
            }
        }
    }
}
