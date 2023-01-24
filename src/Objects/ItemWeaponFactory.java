package Objects;

public class ItemWeaponFactory {

    public Items getItem(int choice){
        switch(choice){
            case 1:
                return new Potion();
            case 2:
                return new Poison();
            case 3:
                return new UltraStrength();
            case 4:
                return new Hat();
        }

        return null;


    }

    public Weapons getWeapon(int choice){
        switch(choice){
            case 1:
                return new SharpSword();
            case 2:
                return new CrystalSword();
            case 3:
                return new MagicStaff();
            case 4:
                return new BasicSword();
        }

        return null;

    }


}
