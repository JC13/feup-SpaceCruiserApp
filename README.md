# LPOO1617_T3G13_SpaceCruiser - Android game app 

<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Game%20Over.png" width="300"><br>

<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Game.png" width="300"><br>

<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Get%20User.png" width="300"><br>

<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Help.png" width="300"><br>

<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/High%20Scores.png" width="300"><br>

<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Main%20Menu.png" width="300"><br>

<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Settings.png" width="300"><br>





## GUI Design
    
    Listing of the main functionalities present on the GUI:
    
    - Display of the Main Menu with 3 main options: start new game, see high scores and exit the game
    - Display of the High Scores showing the top5 highest scores with respective user nickname and the date it was made
    - Display of the game screen, where the player can control his spaceship and see bonus to colect and obstacles to dodge
    - Display a help menu, indicating what are obstacles, bonus and the spaceship aswell as providing basic info on how to interact with them
    - Display of the settings menu, where you can for example turn on/off music or sounds
    
    
    [ADD MOCKUPS HERE]
    
    
    
 
## Test Design

    Listing of the expected final test cases, and how they intend to test the application:
    
    -
    -
    -
    -
    -
    -
    -
    
    
## Architecture Design

As suggested in the classes, the design model used will be the well known MVC (model-view-controller) and each one of these components will be a different package to work with the main game class, SpaceCruiser.
Each of these functionalities will be implemented by one of the following classes: GameController, GameView or GameModel aswell as a package representing different entities.
This division of tasks in different packages allows for better debugging and unit testing, as well as organizing the code making it simpler to understand.
    
---SpaceCruiser class---

Inherits from libGDX Game class.
The Game abstract class provides an implementation of ApplicationListener to use along with some helper methods to set and handle Screen rendering.
SpaceCruiser will be instancied only once and will be the entry point to the game.
It will set the Game screen with the specified GameView, which will get information to GameModel and process it using GameController.




---EntityModel class---

An abstract model representing an entity belonging to a game model.

---ObstacleModel class---
A model representing an obstacle.


---BonusModel class---
A model representing a bonus.

---SpaceshipModel class---
A model representing the user spaceship.

---GameModel class---
A model representing a game.






---EntityView class---

An abstract view capable of holding a sprite with a certain position and rotation. This view is able to update its data based on a entity model.

---ObstacleView class---
A view representing the obstacle.

---BonusView class---
A view representing the bonus.

---SpaceshipView class---
A view representing the player spaceship.

---GameView class---
A view representing the game screen. Draws all the other views and controls the camera. Inherits from libGDX ScreenAdapter





---EntityBody class---

Wrapper class that represents an abstract physical body supported by a Box2D body.

---ObstacleBody class---
A concrete representation of an EntityBody representing a space obstacle to avoid.


---BonusBody class---
A concrete representation of an EntityBody representing a bonus to colect.

---SpaceshipBody class---
A concrete representation of an EntityBody representing the player spaceship.

---GameController---
Controls the physics aspect of the game.    
   

