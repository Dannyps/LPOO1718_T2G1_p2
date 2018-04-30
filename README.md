# LPOO1718_T2G1_p2

## BetterCodeHub

[![BCH compliance](https://bettercodehub.com/edge/badge/Dannyps/LPOO1718_T2G1_p2?branch=master&token=e6dd13487a4bdc7bdf114bce3debcb8274b9c740)](https://bettercodehub.com/)

## Classes UML Graph

### Classes list: 
 - __NPCContainer__:
 Abstract class used to create __Elevator__ and __Floor__. Can be used to manage NPCs in the two refered containers. 
 
 - __Elevator__:
 Represents the elevator in the GUI. Can be used to manage the NPCs inside it. Has a _speed_ (ranging from 1 to 5) that defines the maximum speed it can reach.
 
 - __Floor__:
 Represents the floors in the GUI. Can be used to manage the NPCs inside it.
 
 - __Building__:
 Represents the gameboard in the GUI. Has floors and elevators. There must be, at least, 3 floors, and 1 elevator.

- __NPC__:
 Represents each simley on the gameboard. Differernt NPCs have different tempers, some will get fed up of waiting sooner than others. NPCs can be moved from the floor to the elevator, if the elevator is in the specified floor.

![UML](/../assets/lpoo_t2g1_uml.png?raw=true)

### NPC Life Cycle

![NPC's Life Cycle](/../assets/npcLC.png?raw=true)

## GUI Design

The GUI will have 3 interfaces, each serving the purpose of: 
 - A: welcoming the player
 - B: allowing the user to play the game
 - C: granting aid to a new user.
 
 Form B will support interaction via mouse and keyboard.
 
### Wireframes
[PDF with links](/../assets/wireframes.pdf?raw=true)

### Help Text:

Liftimulator is an Elevator Simulation Game. Make sure that everyone gets to the desired floor as quickly as possible, in order to maximize your score!

Click the arrows (on the screen) to move the lift to the desired location.
Click each smiley to move the corresponding NPC from the lift to the floor, and vice-versa.
Press the designated keys (signaled in blue) to activate turbo-mode for the lifts.
Right click on a smiley on any floor to send it by the stairs. This will free up the capacity of that floor, but will make your score go down.

The desired destination for each NPC is represented by the number next to the smiley.
Each floor has a defined capacity, as well as each lift. 
Whenever someone arrives at a floor, but has no space to wait there, a red exclamation mark will flash.

The base speed of the elevators can be set in the *New Game* dialog.

## Test Cases:

Using Pitclipse and JUnit 4, we intend to extensively test our non-GUI code, in order to refactor with confidence, in the future. Present model classes are already passing 100% of coverage and mutation tests.
Besides these classes, we also intend on extensively testing the controller, given the somewhat accurate previsibility of our game.
