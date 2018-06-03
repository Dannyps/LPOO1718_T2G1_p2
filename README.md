# LPOO1718_T2G1_p2

## BetterCodeHub

![BCH compliance](https://bettercodehub.com/edge/badge/Dannyps/LPOO1718_T2G1_p2?branch=master&token=e6dd13487a4bdc7bdf114bce3debcb8274b9c740)

## Classes UML Graph

### Classes list: 
- __Model__

    - __NPCContainerModel__:
    Abstract class used to create __ElevatorModel__ and __FloorModel__. Can be used to manage NPCs in the two referred containers. 
 
    - __ElevatorModel__:
    Represents the elevator in the GUI. Can be used to manage the NPCs inside it. Has a _speed_ (ranging from 1 to 5) that defines the maximum speed it can reach.

    - __ElevatorBotModel__:
    An extension of __ElevatorModel__, is used to implement the bot behaviour.
 
    - __FloorModel__:
    Represents the floors in the GUI. Can be used to manage the NPCs inside it.
    
    - __BuildingModel__:
    Represents the gameboard in the GUI. Has floors and elevators. There must be, at least, 3 floors, and 1 elevator.

    - __NPCModel__:
    Represents each simley on the gameboard. Differernt NPCs have different tempers, some will get fed up of waiting sooner than others. NPCs can be moved from the floor to the elevator, if the elevator is in the specified floor.

- __Controller__

     - __Controler__:
     Used as the main controller class. Makes the comunication between the model and the view for the most part. Is responsible for making the game happen.

     - __Highscore__:
     This game supports submitting high scores to the central server, thus increasing the competitivity between players.

 - __View__

     - __HelpWindow__:
     A window displaying help.

     - __WelcomeScreen__:
     THe screen present to a new player.

     - __HighScores__:
     A window displaying the high scores retrieved from the server.

     - __GameView__:
     A window displaying the running game. Makes use of the subpackage _GameWindowComponents_ for its creation.

     - ___GameWindowComponents___:
        - ElevatorContainerView
        - FloorView
        - NPCView

![UML](/../assets/lpoo_t2g1_uml.png?raw=true)

### NPC Life Cycle

![NPC's Life Cycle](/../assets/npcLC.png?raw=true)

## GUI Design

The GUI will have 5 interfaces, each serving the purpose of: 
 - A: welcoming the player
 - B: allowing the user to play the game
 - C: granting aid to a new user.
 - D: showing the highscores
 - E: requesting the player's name (modal) for score submission.
 
 Form B will support interaction via mouse and keyboard.
 
### Wireframes
[PDF with links](/../assets/wireframes.pdf?raw=true)


## Design Patterns:

We made use of the **Singleton** design pattern to aid us simplifying the coordination of access to the game. See https://github.com/Dannyps/LPOO1718_T2G1_p2/blob/master/src/model/GameModel.java#L11


## Test Cases:

Using Pitclipse and JUnit 4, we intend to extensively test our non-GUI code, in order to refactor with confidence.