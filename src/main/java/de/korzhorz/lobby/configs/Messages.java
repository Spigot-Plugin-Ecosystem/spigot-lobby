package de.korzhorz.lobby.configs;

public class Messages {
    public String get(String path) {
        return ConfigFiles.messages.getString(path);
    }
}
