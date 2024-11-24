# Project description
Our project is a 2d platformer game, similar to Super Mario. The main character is an Alien whose mission is to visit other planets and collect scientific samples for his scientific experiments. The player will have to get through several levels by running, jumping, avoiding obstacles and watching out for monsters while collecting points. If the player falls or collides against monsters they will lose lives and eventually lose the game. By getting to the spaceship (end of level) the player will advance to other levels and, eventually, win the game.

## Implemented features
* Start Screen - at the beginning of the game, there will be a menu where the player can start/quit the game or access the settings (no settings for now).
* Player Movement - the player can move the character using the arrow keys and jump.
* Level Load - level model can be loaded from a .txt file.
* Simple GUI - basic text based GUI is implemented

## Features on the way
* Better Graphics - more advanced GUI for the game and menu.
* Immersive movement - movement based on KeyAdapter implementation, not lanterna's KeyStroke.
* Enemies - implementing moving monsters and stationary obsticles.
* Collectible system - during the levels, the player will be able collect samples (points) that will improve their score.
* Death system - The player will have 3 lives. If they fall out of the map or collide against enemies they will lose lives and eventually lose the game.
* Settings - The player will be able to edit some things about the game, such as alien appearance, audio etc.
* Level Maps - There will be different maps for each level.

## Project Structure:
We decided to design our game according to MVC design patter. As consequence our code is divided in 4 main blocks ((model, viewer, controller) + state) with each of them responsible for different aspects of a game: 

### Controller:
* Controller: A base class that provides common functionality for all controllers in the game.
* GameController: Controls the main gameplay logic, handling actions like player movement and jumping.
* MenuController: Handles the logic for the game menu, including navigating through options.
### Model:
* Elements: (Alien: Represents the player or an alien character in the game.Element: A general class for game elements.Monster: Represents enemy characters in the game.Position: Defines the coordinates (x, y) of elements in the game world.Tile: Represents the individual tiles that make up the game world.)
* Level: Contains the logic and data for the game levels, including placement of elements and tiles.
* Menu: Manages the game’s menu screen options.
### States:
* GameState: Represents the current state of the game, such as playing or paused.
* MenuState: Handles the menu state, such as when the player is interacting with the menu.
* State: A base class for different game states.
### Viewer:
* GameViewer: Handles the rendering of the game world, displaying the elements to the player.
* MenuViewer: Manages rendering of the game menu on the screen.
* Viewer: A base class for all viewer-related functionality in the game.


