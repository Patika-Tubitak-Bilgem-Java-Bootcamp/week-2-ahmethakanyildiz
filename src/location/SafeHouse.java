package location;

import character.player.GameChar;
import character.player.Player;
import item.Inventory;

public class SafeHouse extends NormalLocation{

    public SafeHouse(Player p){
        super(1,p,"Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz.");
        int newHealth=0;
        for(GameChar character:this.getPlayer().getCharacterTypes()){
            if(this.getPlayer().getType()==character.getType()){
                newHealth=character.getHealth();
                break;
            }
        }
        Inventory inventory = this.getPlayer().getInventory();
        if(inventory.isSu() && inventory.isToprakveAtes() && inventory.isTahta()){
            return false;
        }else{
            this.getPlayer().setHealth(newHealth);
            System.out.println("Canınız yenilendi. Yeni sağlık değeriniz: "+this.getPlayer().getHealth());
            return true;
        }
    }
}
