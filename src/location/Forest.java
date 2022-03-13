package location;

import character.enemy.Vampire;
import character.player.Player;

public class Forest extends BattleLocation{
    public Forest(Player player) {
        super(4, player, "Orman",new Vampire(),"Toprak ve Ateş",3);
    }
}
