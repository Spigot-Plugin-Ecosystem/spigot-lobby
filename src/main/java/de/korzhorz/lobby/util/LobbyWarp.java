package de.korzhorz.lobby.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class LobbyWarp {
    private String name;
    private Location location;
    private Material material;
    private int slot;
    private String displayName;

    public LobbyWarp() {
        this.name = "";
        this.location = null;
        this.material = null;
        this.slot = 0;
        this.displayName = "";
    }

    public LobbyWarp(String name, Location location, Material material, int slot, String displayName) {
        this.name = name;
        this.location = location;
        this.material = material;
        this.slot = slot;
        this.displayName = displayName;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Location getLocation() {
        return this.location;
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getSlot() {
        return this.slot;
    }

    public String toString() {
        return this.name + ":" +
                this.location.getWorld().getName() + ":" +
                this.location.getX() + ":" +
                this.location.getY() + ":" +
                this.location.getZ() + ":" +
                this.location.getYaw() + ":" +
                this.location.getPitch() + ":" +
                this.material.name() + ":" +
                this.slot + ":" +
                this.displayName;
    }

    public LobbyWarp fromString(String string) {
        String[] split = string.split(":");

        this.name = split[0];
        this.location = new Location(
                Bukkit.getWorld(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Double.parseDouble(split[4]),
                Float.parseFloat(split[5]),
                Float.parseFloat(split[6])
        );
        this.material = Material.valueOf(split[7]);
        this.slot = Integer.parseInt(split[8]);
        StringBuilder displayName = new StringBuilder();
        for(int i = 9; i < split.length; i++) {
            if(i != 9) {
                displayName.append(":");
            }
            displayName.append(split[i]);
        }
        this.displayName = displayName.toString();

        return this;
    }
}
