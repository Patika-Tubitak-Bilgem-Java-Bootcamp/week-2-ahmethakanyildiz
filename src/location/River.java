package location;

import character.enemy.Bear;
import character.enemy.Vampire;
import character.player.Player;

public class River extends BattleLocation{
    public River(Player player) {
        super(5, player, "Nehir",new Bear(),"Su",3);
    }
}
