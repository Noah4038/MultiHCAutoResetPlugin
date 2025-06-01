# MultiHCAutoReset
[日本語](README.md) | English

## About This Plugin
MultiHCAutoReset is a plugin for Minecraft hardcore servers that automatically resets the server when a player dies. It provides convenient functionality to automatically reset the server when a player dies.

## Features
- Automatic server reset when a player dies
- Special handling in the End (only resets when all players in the End die)
- Countdown display before reset
- Automatic spectator mode switch for all players during reset

## Installation and Usage

- [Installation and Usage Guide](docs/GUIDE.en.md) - For detailed installation and usage instructions

1. `.bat` Configuration:
- Set the `.bat` file content as follows:

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

2. Plugin Installation
- Download the plugin's `.jar` file from [Releases](https://github.com/Noah4038/MultiHCAutoResetPlugin/releases)
- Place the `.jar` file in the `plugins` folder

3. Server Startup
- Double-click the `.bat` file to start the server

## Configuration
In the current version, the following settings are fixed:
- Reset delay: 10 seconds

## Important Notes
- To close the server, close the server window directly instead of using the `stop` command.

## System Requirements
- Minecraft 1.20.5 or higher
- Spigot/Paper/Purpur server

## Support & Contact
If you have any issues or questions, please let us know through [Issues](https://github.com/Noah4038/MultiHCAutoResetPlugin/issues).
