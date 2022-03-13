package location;

import character.player.Player;

public abstract class NormalLocation extends Location{

    public NormalLocation(int i, Player p, String n){
        super(i,p,n);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}
