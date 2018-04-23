# snakefinal
GAMEPLAY
-To play the game use the directional keys to move your snake through the maze without hitting the walls or tail of the snake.
-The game is scored by you hitting food and increasing the length of your snakes tail
DESCRIPTION OF PROGRAM INTERNALS
-At the beginning of our startup GameManager takes the users input and either uses that or a default map to assemble
	a new map to be displayed. This calls upon walls to construct the coordinates for the walls, snake to place a new snake in 
	an empty space, and food to place food in an empty space. Next this data is taken by the GUI (GuiFrame/GameArea)
	and displayed for the user in a JFrame with Jpanels and buttons. The user inputs directions in the keys 
	and this is in turn used to update our food and snake classes to play the game. The GUI is constantly being repainted
	while the user makes adjustments. If the user hits a wall its detected and the game is over.
-Moving and growing is handled by a deque that changes the front/back depending on how much has been eaten and where the 
	snake is being moved. This is compared against our walls which is stored in a multidimensional array and collisions
	are noted. If a collision is detected by our points/arrays the gameArea will be told which prompts the user.
EXTRAS
-None currently
BUGS
-Start/Pause button haven't been combined.
-Sometimes a rouge snake tail appears when food is eaten but it returns to normal after moving once. 
-Javs's rectangle class was going to be used originally but this was found to be too clunky for this application. Need to purge all code relating to it. 
