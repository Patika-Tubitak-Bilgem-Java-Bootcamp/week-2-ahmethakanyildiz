package location;

import character.enemy.Enemy;
import character.player.Player;
import item.Armor;
import item.Weapon;

import java.util.Random;

public abstract class BattleLocation extends Location {

    private Enemy enemy;
    private String award;
    private final int MAX_ENEMY;
    private int enemyCount;

    public BattleLocation(int id, Player player, String name, Enemy enemy, String award, int maxEnemy) {
        super(id, player, name);
        this.enemy = enemy;
        this.award = award;
        this.MAX_ENEMY = maxEnemy;
        this.enemyCount = randomEnemyNumber();
    }

    @Override
    public boolean onLocation() {
        System.out.println("Şu an buradasınız: " + this.getName());
        System.out.println("Dikkatli ol! Burada " + this.getEnemyCount() + " tane " + this.getEnemy().getName() + " yaşıyor!");
        while (true) {
            System.out.println("<S>avaş veya <K>aç!");
            System.out.print("Seçim: ");
            String selectCase = input.nextLine();
            selectCase = selectCase.toUpperCase();
            if (selectCase.equals("K") || selectCase.equals("S")) {
                if (selectCase.equals("S")) {
                    boolean combatResult = combat(this.getEnemyCount());
                    if (combatResult) {
                        if(this.getAward()==null){
                            System.out.println("Bir sürü yılan öldürdün ve ödüller kazandın. Buraya yeniden gelebilirsin!");
                        }
                        else{
                            System.out.println("Tüm canavarları öldürdüğün için buradaki değerli hazineyi de kazandın.\n"+
                                    "İşte ödülün: "+this.getAward());
                            if(this.getAward().equals("Toprak ve Ateş")) this.getPlayer().getInventory().setToprakveAtes(true);
                            else if(this.getAward().equals("Tahta")) this.getPlayer().getInventory().setTahta(true);
                            else this.getPlayer().getInventory().setSu(true);
                        }
                        return true;
                    } else {
                        if (this.getPlayer().getHealth() <= 0) return false;
                        else return true;
                    }
                } else if (selectCase.equals("K")) {
                    return true;
                }
            } else {
                System.out.println("Geçersiz girdi!");
            }
        }
    }

    public void hitEnemy(){
        System.out.println("Siz vurdunuz.");
        this.getEnemy().setHealth(this.enemy.getHealth() - this.getPlayer().getDamage());
    }

    public void hitByEnemy(){
        System.out.println("Canavar size vurdu.");
        int enemyDamage = this.getEnemy().getDamage() - this.getPlayer().getInventory().getArmorHealth();
        if (enemyDamage < 0) enemyDamage = 0;
        this.getPlayer().setHealth(this.getPlayer().getHealth() - enemyDamage);
    }

    public boolean isPlayerDead(){
        if(this.getPlayer().getHealth()<=0){
            this.getPlayer().setHealth(0);
            return true;
        }
        else{
            return false;
        }
    }

    public void getAwardFromMine(int n){
        Random r = new Random();
        //1-20 arasında bir sayı üretilecek
        //1 2 3 gelirse yani 20'de 3 ihtimal (%15) ile silah kazanma vs.
        int state=r.nextInt(20)+1;
        int awardType=0; //0:hiçbir şey 1:silah 2:zırh 3:para
        if(state<=3){
            awardType=1;
        }
        else if(state>=4 && state<=6){
            awardType=2;
        }
        else if(state>=7 && state<=11){
            awardType=3;
        }
        else{
            awardType=0;
        }
        if(n==1){
            if(awardType==0) System.out.println("Hiçbir item düşmedi. Bu da öldürdüğün son canavardı :/");
            else getAwardFromMinePartTwo(awardType);
        }
        else{
            if(awardType==0) System.out.println("Hiçbir item düşmedi. Şansını diğer canavarda dene savaşçı!");
            else getAwardFromMinePartTwo(awardType);
        }
    }

    public void getAwardFromMinePartTwo(int awardType){
        Random r = new Random();
        int state=r.nextInt(10)+1;
        int awardRank=1;
        //awardRank 3 olursa en iyi, awardRank 2 olursa ortayı, awardRank 1 olursa en kötüyü kazanacak.
        if(state<=2) awardRank=3;
        else if(state>=3 && state<=5) awardRank=2;
        else awardRank=1;
        if(awardType==1){
            awardRank-=1;
            Weapon[] weaponList = Weapon.getWeapons();
            if(this.getPlayer().getInventory().getWeaponDamage()<weaponList[awardRank].getDamage()){
                this.getPlayer().getInventory().setWeaponName(weaponList[awardRank].getName());
                this.getPlayer().getInventory().setWeaponDamage(weaponList[awardRank].getDamage());
                System.out.println(weaponList[awardRank].getName()+" itemi düştü ve sendekinden daha iyi olduğu için aldın.");
            }
            else System.out.println(weaponList[awardRank].getName()+" itemi düştü ama sendeki daha iyi olduğu için almadın.");
        }
        else if(awardType==2){
            awardRank-=1;
            Armor[] armorList = Armor.getArmors();
            if(this.getPlayer().getInventory().getArmorHealth()<armorList[awardRank].getHealth()){
                this.getPlayer().getInventory().setArmorName(armorList[awardRank].getName());
                this.getPlayer().getInventory().setArmorHealth(armorList[awardRank].getHealth());
                System.out.println(armorList[awardRank].getName()+" itemi düştü ve sendekinden daha iyi olduğu için aldın.");
            }
            else System.out.println(armorList[awardRank].getName()+" itemi düştü ama sendeki daha iyi olduğu için almadın.");
        }else{
            int awardCoin=0;
            if(awardRank==1) awardCoin=1;
            else if(awardRank==2) awardCoin=5;
            else awardCoin=10;
            this.getPlayer().setMoney(this.getPlayer().getMoney()+awardCoin);
            System.out.println("Canavarı öldürdün ve "+awardCoin+" sikke kazandın. Sıra diğerinde savaşçı!");
        }
    }
    public void afterKillMonster(int n){
        if(n==0){
            if(this.getAward()==null){
                getAwardFromMine(0);
                this.getEnemy().setHealth(this.getEnemy().getDefaultHealth());
            }
            else{
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getEnemy().getPrize());
                System.out.println("Canavarı öldürdün ve "+this.getEnemy().getPrize()+" sikke kazandın. Sıra diğerinde savaşçı!");
                this.getEnemy().setHealth(this.getEnemy().getDefaultHealth());
            }
        }
        else{
            if(this.getAward()==null){
                getAwardFromMine(1);
                this.getEnemy().setHealth(this.getEnemy().getDefaultHealth());
            }
            else{
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getEnemy().getPrize());
                System.out.println("Son canavarı öldürdün ve "+this.getEnemy().getPrize()+" sikke kazandın!");
                this.getEnemy().setHealth(this.getEnemy().getDefaultHealth());
                this.setEnemyCount(randomEnemyNumber());
            }
        }
    }

    public boolean combat(int enemyNumber) {
        for (int i = 1; i <= enemyNumber; i++) {
            playerStats();
            enemyStats();
            while (this.getPlayer().getHealth() > 0 && this.getEnemy().getHealth() > 0) {
                System.out.println("<V>ur veya <K>aç!");
                System.out.print("Seçim: ");
                String selectCase = input.nextLine();
                selectCase = selectCase.toUpperCase();
                if (selectCase.equals("K") || selectCase.equals("V")) {
                    if (selectCase.equals("V")) {
                        Random r = new Random();
                        int status=r.nextInt(2);
                        if(status==0){
                            System.out.println("Hızlı davrandın ve önce sen saldırdın.");
                            hitEnemy();
                            if(this.getEnemy().getHealth()>0){
                                hitByEnemy();
                                if(isPlayerDead()){
                                    return false;
                                }
                                afterCombat();
                            }
                            else{
                                afterCombat();
                                if (i < enemyNumber) {
                                    afterKillMonster(0);
                                    break;
                                } else {
                                    afterKillMonster(1);
                                    return true;
                                }
                            }
                        }
                        else{
                            System.out.println("Yavaş davrandın ve önce canavar saldırdı.");
                            hitByEnemy();
                            if(isPlayerDead()){
                                afterCombat();
                                return false;
                            }
                            else{
                                hitEnemy();
                                afterCombat();
                                if(this.getEnemy().getHealth()<=0){
                                    if (i < enemyNumber) {
                                        afterKillMonster(0);
                                        break;
                                    } else {
                                        afterKillMonster(1);
                                        return true;
                                    }
                                }
                            }
                        }
                    } else {
                        this.getEnemy().setHealth(this.getEnemy().getDefaultHealth());
                        this.setEnemyCount(randomEnemyNumber());
                        return false;
                    }
                } else {
                    System.out.println("Geçersiz bir değer girdiniz!");
                }

            }
        }
        this.getEnemy().setHealth(this.getEnemy().getDefaultHealth());
        this.setEnemyCount(randomEnemyNumber());
        return true;
    }

    public void afterCombat() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        int enemyHealth = this.getEnemy().getHealth();
        if (enemyHealth < 0) enemyHealth = 0;
        System.out.println("Canavarın canı: " + enemyHealth + "\n");

    }

    public void playerStats() {
        System.out.println("-----------Oyuncu Değerleri-------------");
        this.getPlayer().printInfo();
    }

    public void enemyStats() {
        System.out.println("------------" + this.getEnemy().getName() + " Değerleri----------");
        System.out.println("Sağlık: " + this.getEnemy().getHealth());
        if(this.getEnemy().getName().equals("Yılan")){
            System.out.println("Hasar: " + this.getEnemy().getDamage() +" (Bu bir önceki vuruştaki/başlangıçtaki hasardı. Bir sonraki değer rastgele olacak!)");
            Random r = new Random();
            int randomDamage = r.nextInt(4)+3;
            this.getEnemy().setDamage(randomDamage);
            System.out.println("Ödül: Ne çıkarsa bahtına");
        }
        else{
            System.out.println("Hasar: " + this.getEnemy().getDamage());
            System.out.println("Ödül: " + this.getEnemy().getPrize());
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMAX_ENEMY() {
        return MAX_ENEMY;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int randomEnemyNumber() {
        Random r = new Random();
        return r.nextInt(this.getMAX_ENEMY()) + 1;
    }
}
