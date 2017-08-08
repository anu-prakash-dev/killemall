package be.zwaldeck.killemall.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class PackAssets {

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        settings.rotation = false;

        TexturePacker.process(settings, "../../desktop/player", "packs", "player.atlas");
        System.out.println("Done packing player assets");

        TexturePacker.process(settings, "../../desktop/hud", "packs", "hud.atlas");
        System.out.println("Done packing hud assets");

        TexturePacker.process(settings, "../../desktop/items", "packs", "items.atlas");
        System.out.println("Done packing item assets");
    }
}
