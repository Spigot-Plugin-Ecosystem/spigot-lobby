package de.korzhorz.lobby.enums;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.util.ColorTranslator;

public enum LobbyItem {
    NAVIGATOR,
    LOBBY_SWITCH,
    PLAYERS_VISIBLE,
    PLAYERS_INVISIBLE;

    public String getDisplayName() {
        switch(this) {
            case NAVIGATOR -> {
                return ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.navigator.name"));
            }
            case LOBBY_SWITCH -> {
                return ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.lobby-switch.name"));
            }
            case PLAYERS_VISIBLE -> {
                return ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.toggle-visibility.visible.name"));
            }
            case PLAYERS_INVISIBLE -> {
                return ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.toggle-visibility.invisible.name"));
            }
            default -> {
                return null;
            }
        }
    }

    public String getMaterial() {
        switch(this) {
            case NAVIGATOR -> {
                return ConfigFiles.items.getString("default-inventory.navigator.material");
            }
            case LOBBY_SWITCH -> {
                return ConfigFiles.items.getString("default-inventory.lobby-switch.material");
            }
            case PLAYERS_VISIBLE -> {
                return ConfigFiles.items.getString("default-inventory.toggle-visibility.visible.material");
            }
            case PLAYERS_INVISIBLE -> {
                return ConfigFiles.items.getString("default-inventory.toggle-visibility.invisible.material");
            }
            default -> {
                return null;
            }
        }
    }

    public int getSlot() {
        switch(this) {
            case NAVIGATOR -> {
                return ConfigFiles.items.getInt("default-inventory.navigator.slot");
            }
            case LOBBY_SWITCH -> {
                return ConfigFiles.items.getInt("default-inventory.lobby-switch.slot");
            }
            case PLAYERS_VISIBLE, PLAYERS_INVISIBLE -> {
                return ConfigFiles.items.getInt("default-inventory.toggle-visibility.slot");
            }
            default -> {
                return 0;
            }
        }
    }
}
