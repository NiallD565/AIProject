# AIProject

### Project outline 
You are required to create an AI controlled maze game containing the set of features listed
below using a suite of stubs provided on Moodle. The objective of the game should be to escape
from a maze and either avoid or fight off the enemy characters that move around in the game
environment. The provided stubs already create a basic maze game, with a key-controlled
player and (immobile) sprite enemies.

### How to operate
The object of the game is to navigate through the maze avoiding or fighting spiders.
# Controls 
    Right Arrow   - Move left
    Left Arrow    - Move right
    Up Arrow      - Move up
    Down Arrow    - Move down
    
    Z - Toggle view of the maze
  
### Maze 
The maze contains spiders that move thoughout it using neural network and search algorithms to determine 
which direction to go and follow the player once within a certain distance and in view and move independently.

### Features
* The player is placed within the maze at random.
* The spiders are randomly gernerated and are threaded so they move independently.
* The neural net is trained in less than a minute.
* The search algorithm decides how the spiders movw throughout the maze.

### Threads
for the threaded spiders I decided to use an executor service which allowed each one to move independently,

