# LPOO1617_T3G13_SpaceCruiser - Android game app 


## GUI Design
    
   Listing of the main functionalities present on the GUI:
    
   - Display of the Main Menu with 3 main options: start new game, see high scores and exit the game
   - Display of the High Scores showing the top5 highest scores with respective user nickname and the date it was made
   - Display of the game screen, where the player can control his spaceship and see bonus to colect and obstacles to dodge
   - Display a help menu, indicating what are obstacles, bonus and the spaceship aswell as providing basic info on how to interact with them
   - Display of the settings menu, where you can for example turn on/off music or sounds
    

 MAIN MENU
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Main%20Menu.png" width="300"><br>
  
 USER NICKNAME 
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Get%20User.png" width="300"><br>
 
 GAME SCREEN
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Game.png" width="300"><br>
 
 GAME OVER SCREEN
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Game%20Over.png" width="300"><br>

 HELP SCREEN
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Help.png" width="300"><br>

 HIGH SCORES SCREEN
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/High%20Scores.png" width="300"><br>

 SETTINGS SCREEN
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Settings.png" width="300"><br>



UML
<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/uml/UML.png" width="2000"><br>

    
    
    
 
## Test Design

    Listing of the expected final test cases, and how they intend to test the application:
    
    - Test movement of the spaceship in the mobile using the accelerometer
    - Test to all collisions possible:
        - between the spaceship and other objects with or without shield
        - between the spaceship and obstacles
        - between the spaceship and the bonus
        - between obstacles         
    

    
    
## Architecture Design

As suggested in the classes, the design model used will be the well known MVC (model-view-controller) and each one of these components will be a different package to work with the main game class, SpaceCruiser.
Each of these functionalities will be implemented by one of the following classes: GameController, GameView or GameModel aswell as a package representing different entities.
This division of tasks in different packages allows for better debugging and unit testing, as well as organizing the code making it simpler to understand.

# DESIGN PATTERNS

# SINGLETON
A pattern is a general repeatable solution to a commonly occurring problem. A design pattern is a general repeatable solution to a commonly occurring software problem. It is a description or template for how to solve a problem that can be used in many different situations. Hence, a design pattern should be applied if there is the need to. It should almost occur "naturally", as a solution.

This being said, at first we thought the SINGLETON design pattern would be useful to implement since the MVC model requires the implementation of 3 classes, one for each model (in this case GameView, GameModel and GameController). Each one of these will be instantiated only once, aswell as the SpaceCruiser class (extends LibGDX Game), thus singleton seemed like a proper design pattern.

However, analyzing the singleton implementation and doing some research, we can conclude that singleton has 4 major problems.

First of singletons are global instances thus hiding dependencies of the application in the code, instead of exposing them through the interfaces. Making something global to avoid passing it around is a "code smell".

Not only but singletons also violate the standard "single responsability" rule associated with a class. Since the singleton class will take care of whatever she is supposed to but also control her own lifecycle (creation up until end of the program)

Even more, a singleton implementation will cause code to be coupled, making it more confusing and harder to test.

Last but not least, singletons imply a "state" that they carry (since they live "forever" and control their own lifecycle) thus making it even harder to test, since tests should be independent.


# CLASS DESCRIPTION    

# SpaceCruiser

Inherits from libGDX Game class.
The Game abstract class provides an implementation of ApplicationListener to use along with some helper methods to set and handle Screen rendering.
SpaceCruiser will be instancied only once and will be the entry point to the game.
It will set the Game screen with the specified GameView, which will get information to GameModel and process it using GameController.




# EntityModel

An abstract model representing an entity belonging to a game model.

# ObstacleModel
A model representing an obstacle.


# BonusModel
A model representing a bonus.

# SpaceshipModel
A model representing the user spaceship.

# GameModel 
A model representing a game.






# EntityView

An abstract view capable of holding a sprite with a certain position and rotation. This view is able to update its data based on a entity model.

# ObstacleView
A view representing the obstacle.

# BonusView
A view representing the bonus.

# SpaceshipView
A view representing the player spaceship.

# GameView
A view representing the game screen. Draws all the other views and controls the camera. Inherits from libGDX ScreenAdapter





# EntityBody class

Wrapper class that represents an abstract physical body supported by a Box2D body.

# ObstacleBody
A concrete representation of an EntityBody representing a space obstacle to avoid.


# BonusBody 
A concrete representation of an EntityBody representing a bonus to colect.

# SpaceshipBody 
A concrete representation of an EntityBody representing the player spaceship.

# GameController
Controls the physics aspect of the game.    
   

