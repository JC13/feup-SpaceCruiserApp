# SpaceCruiser - Android game app 


# Pre-development ideas

## GUI DESIGN
    
   Listing of the main functionalities present on the GUI:
    
   - Display of the Main Menu with 3 main options: start new game, see high scores and exit the game
   - Display of the High Scores showing the top5 highest scores with respective user nickname and the date it was made
   - Display of the game screen, where the player can control his spaceship and see bonus to colect and obstacles to dodge
   - Display a help menu, indicating what are obstacles, bonus and the spaceship aswell as providing basic info on how to interact with them
   - Display of the settings menu, where you can for example turn on/off music or sounds
    

 MAIN MENU<br>
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Main%20Menu.png" width="300"><br>
  
 USER NICKNAME<br>
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Get%20User.png" width="300"><br>
 
 GAME SCREEN<br>
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Game.png" width="300"><br>
 
 GAME OVER SCREEN<br>
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Game%20Over.png" width="300"><br>

 HELP SCREEN<br>
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Help.png" width="300"><br>

 HIGH SCORES SCREEN<br>
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/High%20Scores.png" width="300"><br>

 SETTINGS SCREEN<br>
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/mockups/Settings.png" width="300"><br>



 ## BASIC GAME DESIGN<br>
 <img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/images/uml/UML.png" width="2000"><br>

    
    
    
 
## TEST DESIGN

   Listing of the expected final test cases, and how they intend to test the application:
    
   - Test movement of the spaceship in the mobile using the accelerometer
   - Test to all collisions possible:
       - between the spaceship and other objects with or without shield
       - between the spaceship and obstacles
       - between the spaceship and the bonus
       - between obstacles         
    

    
    
## ARCHITECTURE DESIGN

As suggested in the classes, the design model used will be the well known MVC (model-view-controller) and each one of these components will be a different package to work with the main game class, SpaceCruiser.
Each of these functionalities will be implemented by one of the following classes: GameController, GameView or GameModel aswell as a package representing different entities.
This division of tasks in different packages allows for better debugging and unit testing, as well as organizing the code making it simpler to understand.


### DESIGN PATTERNS

### SINGLETON
A pattern is a general repeatable solution to a commonly occurring problem. A design pattern is a general repeatable solution to a commonly occurring software problem. It is a description or template for how to solve a problem that can be used in many different situations. Hence, a design pattern should be applied if there is the need to. It should almost occur "naturally", as a solution.

This being said, at first we thought the SINGLETON design pattern would be useful to implement since the MVC model requires the implementation of 3 classes, one for each model (in this case GameView, GameModel and GameController). Each one of these will be instantiated only once, aswell as the SpaceCruiser class (extends LibGDX Game), thus singleton seemed like a proper design pattern.

However, analyzing the singleton implementation and doing some research, we can conclude that singleton has 4 major problems.

First of singletons are global instances thus hiding dependencies of the application in the code, instead of exposing them through the interfaces. Making something global to avoid passing it around is a "code smell".

Not only but singletons also violate the standard "single responsability" rule associated with a class. Since the singleton class will take care of whatever she is supposed to but also control her own lifecycle (creation up until end of the program)

Even more, a singleton implementation will cause code to be coupled, making it more confusing and harder to test.

Last but not least, singletons imply a "state" that they carry (since they live "forever" and control their own lifecycle) thus making it even harder to test, since tests should be independent.


## CLASS DESCRIPTION    

### SpaceCruiser
Inherits from libGDX Game class.
The Game abstract class provides an implementation of ApplicationListener to use along with some helper methods to set and handle Screen rendering.
SpaceCruiser will be instancied only once and will be the entry point to the game.
It will set the Game screen with the specified GameView, which will get information to GameModel and process it using GameController.




### MODEL PACKAGE

### EntityModel
An abstract model representing an entity belonging to a game model.

### ObstacleModel
A model representing an obstacle.

### BonusModel
A model representing a bonus.

### SpaceshipModel
A model representing the user spaceship.

### GameModel 
A model representing a game.




### VIEW PACKAGE

### EntityView

An abstract view capable of holding a sprite with a certain position and rotation. This view is able to update its data based on a entity model.

### ObstacleView
A view representing the obstacle.

### BonusView
A view representing the bonus.

### SpaceshipView
A view representing the player spaceship.

### GameView
A view representing the game screen. Draws all the other views and controls the camera. Inherits from libGDX ScreenAdapter




### CONTROLLER PACKAGE

### EntityBody class
Wrapper class that represents an abstract physical body supported by a Box2D body.

### ObstacleBody
A concrete representation of an EntityBody representing a space obstacle to avoid.

### BonusBody 
A concrete representation of an EntityBody representing a bonus to colect.

### SpaceshipBody 
A concrete representation of an EntityBody representing the player spaceship.

### GameController
Controls the physics aspect of the game.    
   



# Final project

## Setup procedure for both project and game/app (how to install the development environment and how to install/run the game/app).

In order to be able to edit and work with the code one needs to install Android Studio (most recent version) and create the desired run configuration: the game runs both on desktop and android (we recommend the use of a real android device and not an emulator in order to use the gyroscope properly).


## Development documentation ( work distribution amongst team members, etc...)

The updated UML<br>
<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/UML/UML.png" width="300"><br>
  
### Design Patterns 
  
  For design patterns we ended up using the MVC (model-view-controller). This allows for the correct division of the app into 3 interconnected sections or packages and along with it several benefits such as easier unti testing of the code as you can test only the controller methods and the respective changes to the model without needing the view. It also provides much needed structure to the code as well as making it easier to understand and extend in the future.
  
  Being a very graphical game, the project main dificulties were working with the provided layout managers by LibGDX which are the Table's. It is not as easy or intuitive as exepected thus giving a little bit more work than we planned. This resulted in lack of time to implement other funcionalities (like the highscores) giving the deadline.
  
  Overall, we spent over 80 hours developing (either writting code or designing graphical components). 
  The work distribution was as it follows:
  
    - Nelson Costa: code documentation, mobile movement (gyroscope), collisions filtering, creation of GUI components and UML, unit testing
    
    - João Conde: code design & refactoring, screens & screen manager implementation, collisions, design of GUI components, github repository setup
  
## User manual (with screenshots explaining how to play/use the game/app).
      
MainMenu Screen<br>
<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/UML/UML.png" width="300"><br>

In the main menu you are allowed to either start/continue the game (if you didn't lose the last time it continues the same game), change options (control game volume), check game instructions or exit the application.



Game Screen<br>
<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/UML/UML.png" width="300"><br>

In the game screen you can play our awesome game, interacting with all game elements aswell as being able to check your current score, displayed on a HUD. To go back to main menu you can use the back button and resume your game later via the play button on main menu.



Options Screen<br>
<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/UML/UML.png" width="300"><br>

In the options screen we only implemented a volume slider, allowing the user to select desired volume, with the minimum being sound muted or maximum being... well... pretty loud.



Help Screen<br>
<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/UML/UML.png" width="300"><br>

In the help screen we display basic game instructions such as what to collect and what to avoid, how to control the ship...



Gameover Screen<br>
<img src="https://github.com/JC13/LPOO1617_T3G13_SpaceCruiser/blob/master/UML/UML.png" width="300"><br>

Unfortunately, if you lose, you get to see our gameover screen, which provides basic game statistics such as asteroids destroyed, shields and point bonus picked up and your score.
