# Lobby
Dieses Plugin ist ein einfaches Lobby-System wie man es von vielen Minigame-Servern kennt.
Es umfasst unter anderen die folgenden Funktionen:
- Navigator-GUI (im Spiel konfigurierbar)
- Lobby-Selector-GUI (mit Verbindung zu anderen BungeeCord-Unterservern)
- Premium-Lobby möglich
- Andere Spieler verstecken oder anzeigen
- Mit Echtzeit synchronisierte Tageszeit
- Vollständig konfigurierbar

## Befehle
#### `/setlobby` (Berechtigung: `lobby.setup`)
Setzt die aktuelle Position als Lobby-Spawn. Spieler, die den Server betreten, werden dort hin teleportiert.

#### `/setwarp <name> <slot> <displayname>` (Berechtigung: `lobby.setup`)
Setzt an der aktuellen Position einen Warp für den Navigator. Der Name (`name`) identifiziert den Warp und sollte eindeutig sein. Der Slot (`slot`) gibt an, an welcher Stelle und der Anzeigename (`displayname`) unter welchem Namen der Warp im Navigator angezeigt wird. Der Anzeigename kann Leerzeichen und Farbcodes (`&<code>`) enthalten. Als Item wird das Item verwendet, das der Spieler in der Hand hält.

#### `/delwarp <name>` (Berechtigung: `lobby.setup`)
Löscht Warps mit dem angebenen Namen (`name`).

## Weitere Berechtigungen
- `lobby.premium` - Erlaubt das Betreten von Premium-Lobbies
- `lobby.chat` - Erlaubt das Schreiben im Lobby-Chat, wenn dies nur bestimmten Rängen vorbehalten sein soll

## Konfiguration
#### `config.yml`
Enthält die allgemeinen Einstellungen des Plugins.

#### `messages.yml`
Enthält die Nachrichten, die das Plugin an Spieler sendet.

#### `updates.yml`
Interne Datei, mit der das Plugin überprüft, ob eine neuere Version verfügbar ist. Diese Datei sollte nicht händisch bearbeitet werden.

#### `locations.yml`
Enthält die Positionen der Lobbys und Warps. Dise Datei sollte nicht händisch bearbeitet werden.

#### `lobbies.yml`
Enthält die Konfiguration der Lobbies, die im Lobby-Selector angezeigt werden.
Die Datei enthält in der Regel bereits einen Beispiel-Eintrag für den aktuellen Server. Um einen weiteren Server hinzuzufügen, müssen die folgenden Schritte durchgeführt werden:
1. Finde den BungeeCord-Namen des Servers heraus, den du als Lobby hinzufügen möchtest. Im Folgenden wird dieser nur noch als "Name" bezeichnet.
2. Erweitere die Liste `lobbies-list` in der Konfigurationsdatei `lobbies.yml` um den Namen des Servers.
3. Erstelle unter `lobbies` einen neuen "Block" mit dem Namen des Servers. Falls bereits ein Block vorhanden ist, kann dieser einfach kopiert und angepasst werden.
4. Füge zu dem Block die folgenden Einträge hinzu oder passe sie an:
   - `displayname` - Anzeigename des Servers im Lobby-Selector
   - `lobby-switch-slot` - Slot im Navigator, in dem das Item zum Wechseln der Lobby angezeigt wird
   - `premium` - Ob der Server nur für Premium-Spieler zugänglich sein soll

<details>
<summary>Beispiel-Datei</summary>

Die folgende Datei zeigt eine Beispiel-Konfiguration mit einigen Lobbies unter der annahme, dass der aktuell konfigurierte Server unter `lobby-1` in BungeeCord erreichbar ist.
```yaml
lobbies-list:
  - lobby-1
  - lobby-2
  - lobby-3
  - lobby-premium
lobbies:
  lobby-1:
    displayname: '&6&lLobby 1'
    lobby-switch-slot: 12
    premium: false
  lobby-2:
    displayname: '&6&lLobby 2'
    lobby-switch-slot: 13
    premium: false
  lobby-3:
    displayname: '&6&lLobby 3'
    lobby-switch-slot: 14
    premium: false
  lobby-premium:
    displayname: '&5&lPremium-Lobby'
    lobby-switch-slot: 4
    premium: true
active: lobby-1
```
</details>

#### `items.yml`
Enthält die Konfiguration der Items, die fest im Plugin verwendet werden, Items für den Navigator sind hier nicht enthalten.

## Technische Details
#### Unterstützte Minecraft-Versionen
1.20 - 1.20.1

#### Abhängige Plugins
Um dieses Plugin nutzen zu können, müssen auch die folgenden Plugins installiert sein:
- [Build](https://github.com/Spigot-Plugin-Ecosystem/spigot-build)
