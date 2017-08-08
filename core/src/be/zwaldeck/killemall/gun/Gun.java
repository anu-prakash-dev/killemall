package be.zwaldeck.killemall.gun;

public class Gun {

    private String name;
    private int maxAmmo;
    private int maxAmmoInClip;
    private int ammo;
    private int ammoInClip;
    private int reloadTime; //in ms
    private float damage;
    private float bulletSpeed;
    private GunType gunType;
    private FireType fireType;
    private boolean owned;

    public Gun() {}

    public Gun(GunConfig config) {
        this.name = config.getName();
        this.maxAmmo = config.getMaxAmmo();
        this.maxAmmoInClip = config.getMaxAmmoInClip();
        this.ammo = maxAmmo;
        this.maxAmmo = maxAmmoInClip;
        this.reloadTime = config.getReloadTime();
        this.damage = config.getDamage();
        this.bulletSpeed = config.getBulletSpeed();
        this.gunType = config.getGunType();
        this.fireType = config.getFireType();
        this.owned = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public int getMaxAmmoInClip() {
        return maxAmmoInClip;
    }

    public void setMaxAmmoInClip(int maxAmmoInClip) {
        this.maxAmmoInClip = maxAmmoInClip;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmoInClip() {
        return ammoInClip;
    }

    public void setAmmoInClip(int ammoInClip) {
        this.ammoInClip = ammoInClip;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public GunType getGunType() {
        return gunType;
    }

    public void setGunType(GunType gunType) {
        this.gunType = gunType;
    }

    public FireType getFireType() {
        return fireType;
    }

    public void setFireType(FireType fireType) {
        this.fireType = fireType;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }
}
