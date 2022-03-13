import character.player.Player;
import location.*;

import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera oyununa hoşgeldiniz!");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("HOŞGELDİN "+player.getName()+"!");
        System.out.println("Oyunun amacaından bahsedelim. Görevin Mağara, Orman ve Nehir'den, doğada bulunan 4\n"+
                "elementi toplamak. Bunlar Ateş, Toprak, Su ve Tahta. Evet tahta, zoruna mı gitti?\n"+
                "Mağaradan tahtayı, ormandan toprak ve ateşi, nehirden suyu toplayacaksın. Ama dikkatli ol!\n" +
                "Bu bölgelerde düşmanlar var. Ayrıca Maden'e gidip rastgele para, item vs. kazanabilirsin.");
        player.selectType();
        Location location=null;
        SafeHouse safeHouse = new SafeHouse(player);
        ToolStore toolStore = new ToolStore(player);
        Cave cave = new Cave(player);
        Forest forest = new Forest(player);
        River river = new River(player);
        Mine mine = new Mine(player);
        //Lokasyonlar id sırasına göre listeye eklenmelidir!
        Location[] locations = {safeHouse,toolStore,cave,forest,river,mine};
        boolean didPlayerExit=false;
        while(true){
            player.printInfo();
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçin.");
            System.out.println("0 - Çıkış Yap");
            for(Location l:locations){
                System.out.println(l.getId()+" - "+l.getName());
            }

            while(true){
                System.out.print("Seçim:");
                int selectLoc=input.nextInt();
                if(selectLoc>locations.length || selectLoc<0){
                    System.out.println("Geçersiz girdi! Lütfen geçerli bir girdi giriniz.");
                }
                else{
                    selectLoc-=1;
                    if(selectLoc==-1){
                        didPlayerExit=true;
                        break;
                    }
                    location=locations[selectLoc];
                    break;
                }
            }
            if(didPlayerExit){
                System.out.println("Çıkış yapılıyor...");
                break;
            }
            if(location.getName()=="Mağara" && player.getInventory().isTahta()){
                System.out.println("Mağaradan tahtayı aldın zaten. Ne yapmaya gideceksin zombilerle takılmaya mı?");
                continue;
            }
            else if(location.getName()=="Orman" && player.getInventory().isToprakveAtes()){
                System.out.println("Ormandaki işimizi zaten halletik, unuttun mu?");
                continue;
            }
            else if(location.getName()=="Nehir" && player.getInventory().isSu()){
                System.out.println("Nehirdeki ayıların bizi özlediğini hiç sanmıyorum...");
                continue;
            }
            if(!location.onLocation()){
                if(location.getName().equals("Güvenli Ev")){
                    System.out.println("Oyunu kazandın!");
                    break;
                }
                else{
                    System.out.println("Oyun bitti, kaybettin!");
                    break;
                }
            }
        }
        if(didPlayerExit){
            System.out.println("Bu oyun senin gözünü korkuttu anlaşılan.\n"+
                    "Cesaretini topladığında biz burada olacağız!");
        }
    }
}
