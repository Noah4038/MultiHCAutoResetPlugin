# MultiHCAutoReset Plugin Guide

## Overview
MultiHCAutoReset is a plugin for Minecraft hardcore servers that automatically resets the server when a player dies. It provides convenient functionality to automatically reset the server when a player dies.

## Requirements
- **Server**: Purpur / Paper / Spigot (1.20.5 or higher recommended)
- **Plugin**: `MultiHCAutoReset.jar`
- **OS**: Windows (uses `.bat` file)
- **Java**: 21

## Server Setup Instructions

> ⚠️ For more detailed server setup instructions and port forwarding, please research on your own

### 1. Java Installation
1. Download Java 21 from the [Oracle official website](https://www.oracle.com/java/technologies/downloads/)
2. Run the installer and follow the instructions
3. After installation, verify by running `java -version` in the command prompt

### 2. Server Preparation
1. Create a new folder (e.g., `minecraft_server`)
2. Download one of the following servers:
    - [Purpur](https://purpurmc.org/downloads) (Recommended)
    - [Paper](https://papermc.io/downloads)
    - [Spigot](https://getbukkit.org/download/spigot)

### 3. Initial Server Setup
1. Place the downloaded `.jar` file in the server folder
2. Create `start_server.bat` with the following content:

```bat
@echo off
:RESTART
java -Xmx2G -jar purpur-1.21.5.jar nogui

echo Server has stopped.

if exist reset.flag (
    echo Resetting worlds...
    rmdir /s /q world
    rmdir /s /q world_nether
    rmdir /s /q world_the_end
    del reset.flag
)

goto RESTART
```

> ⚠️ Change `purpur-1.21.5.jar` to match your server's `.jar` filename.

3. Run `start_server.bat` to start the server
4. On first run, `eula.txt` will be created - change `eula=false` to `eula=true`
5. Restart the server

### 4. Basic Server Configuration
Edit `server.properties` with the following settings:
- `difficulty=hard` (Difficulty)
- `spawn-protection=0` (Disable spawn protection)
- `hardcore=true` (Set to hardcore mode)

### 5. Plugin Installation
1. Place the plugin's `.jar` file in the `plugins` folder
2. Restart the server

## Usage

### Server Startup
**Always** start the server by double-clicking `start_server.bat`.
Starting the `.jar` directly will prevent the world reset from functioning correctly.

### Death Behavior

#### Normal Worlds (Overworld & Nether)
1. Reset countdown starts immediately when a player dies
2. Death notification sent to all players
3. All players switch to spectator mode after 2 seconds
4. Server restarts after 10 seconds

#### The End
1. Player death is recorded
2. Reset only starts when all players in the End have died
3. Follows the same behavior as normal worlds afterward

### Reset Process
1. Death Notification
   ```
   [Player Name] has died. Starting world reset. Switching to spectator mode in 2 seconds.
   ```

2. Spectator Mode Switch
   - All players automatically switch to spectator mode

3. Countdown
   ```
   Server restart in [seconds] seconds
   ```

4. Server Restart
   ```
   Restarting server...
   ```

## Important Notes
- To completely stop the server, always close the server window directly
- Using the `stop` command will cause the server to restart

## Behavior Verification Table

| Situation | Result |
|-----------|--------|
| Death in Overworld | Auto-reset after 10 seconds |
| Death in Nether | Auto-reset after 10 seconds |
| Death in End | Wait until all players die |

## Frequently Asked Questions

### Q: Can I change the reset delay time?
A: In the current version, it is fixed at 10 seconds.

## Troubleshooting

### Plugin Not Loading
- Verify server version is 1.20.5 or higher
- Check if the `.jar` file is correctly placed in the `plugins` folder
- Check for error messages in the server console

### World Not Resetting
- Verify server was started with `start_server.bat`
- Check if world folders are not locked
