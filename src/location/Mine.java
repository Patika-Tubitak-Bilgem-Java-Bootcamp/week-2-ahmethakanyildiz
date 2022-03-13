package location;

import character.enemy.Snake;
import character.enemy.Vampire;
import character.player.Player;

public class Mine extends BattleLocation{
    public Mine(Player player) {
        super(6, player, "Maden",new Snake(),null,5);
    }
}
