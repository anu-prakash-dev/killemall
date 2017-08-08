package be.zwaldeck.killemall.gun;

public class GunConfig {

    private String name;
    private int maxAmmo;
    private int maxAmmoInClip;
    private int reloadTime; //in ms
    private float damage;
    private float bulletSpeed;
    private GunType gunType;
    private FireType fireType;

    public GunConfig() {
    }

    public GunConfig(String name, int maxAmmo, int maxAmmoInClip, int reloadTime, float damage, float bulletSpeed, GunType gunType, FireType fireType) {
        this.name = name;
        this.maxAmmo = maxAmmo;
        this.maxAmmoInClip = maxAmmoInClip;
        this.reloadTime = reloadTime;
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.gunType = gunType;
        this.fireType = fireType;
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
}
