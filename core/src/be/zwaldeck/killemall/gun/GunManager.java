package be.zwaldeck.killemall.gun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.HashMap;

public class GunManager {
    private static ArrayList<GunConfig> configs = null;

    public static void loadConfig() {
        if(configs == null) {
            Json json = new Json();
            configs = json.fromJson(ArrayList.class, Gdx.files.internal("config/gun_config.json"));
        }
    }

    private HashMap<String, Gun> guns;
    private Gun currentGun;

    public GunManager() {
        this.guns = new HashMap<String, Gun>();
        this.currentGun = null;

        loadGunsFromConfig();
    }

    public void loadGunsFromConfig() {
        for(GunConfig config : configs) {
            guns.put(config.getName(), new Gun(config));
        }
    }

    public void addOwnedGun(String name) {
        if(guns.containsKey(name)) {
            guns.get(name).setOwned(true);
        }
    }

    public void removeOwnedGun(String name) {
        if(guns.containsKey(name)) {
            guns.get(name).setOwned(false);
        }
    }

    public Gun getCurrentGun() {
        return currentGun;
    }

    public void setCurrentGun(String name) {
        if(guns.containsKey(name)) {
            this.currentGun = guns.get(name);
        }
    }

    public boolean canCurrentGunFire(boolean isTriggerReleased) {
        switch (currentGun.getFireType()) {
            case SEMI_AUTO:
                return currentGun.getAmmo() > 0 && isTriggerReleased;
            case AUTO:
                return currentGun.getAmmo() > 0;
            case PUMP:
                return currentGun.getAmmo() > 0;
        }

        return false;
    }

    public void reduceAmmoInClip() {
        if(currentGun.getAmmoInClip() > 0) {
            currentGun.setAmmoInClip(currentGun.getAmmoInClip() - 1);
        }
    }

    public boolean reload() {
        int ammoNeeded = currentGun.getMaxAmmoInClip() - currentGun.getAmmo();
        if(ammoNeeded <= currentGun.getAmmo()) {
            currentGun.setAmmoInClip(currentGun.getMaxAmmoInClip());
            currentGun.setAmmo(currentGun.getAmmo() - ammoNeeded);
            return true;
        }

        return false;
    }
}
