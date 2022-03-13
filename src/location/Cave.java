package location;

import character.enemy.Zombie;
import character.player.Player;

public class Cave extends BattleLocation{
    public Cave(Player player) {
        super(3, player, "Mağara",new Zombie(),"Tahta",3);
    }
}
