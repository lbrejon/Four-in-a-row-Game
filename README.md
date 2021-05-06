# Four-in-a-row Game [CLOSED]
Play four-in-a-row from the terminal against people or AI.

## Table of contents
* [My goals](#my-goals)
* [Technologies](#technologies)
* [Description](#description)
* [Launch the program](#launch-the-program)
* [Acquired skills](#acquired-skills)
* [Informations](#informations)

## My goals
- Manipulate object-oriented programming tools

## Technologies
The project is developped with **JAVA**.

## Description
Four-in-a-row is a board game where the goal is to align 4 pieces on a grid of 6 rows and 7 columns. A game is played in 3 winning rounds. During each round, each player must choose a column (insert the column number in the terminal) to place his piece (X or O), which will slide to the lowest free position in the grid, leaving the hand to his opponent. **The winner of the round will be the one who manages to align at least 4 of his pieces** (the alignment must be horizontal, vertical or diagonal). 
Otherwise, if all the squares of the grid are occupied and no one has managed to align 4 checkers, the round will be declared a **draw**.

In both cases, a new round will be started. It should be noted that Player 1 starts in the first round, and then in the next rounds there is a rotation so that each player can start.

![grid](https://user-images.githubusercontent.com/56866008/117222257-580c3380-ae0b-11eb-8341-b8b7dd2cb421.PNG)

_Grid example ('0' won)_

#### 1. Implementation of the "Menu" interface
A **"Menu" interface** has been implemented. Several sections are proposed to the user when launching the game. To access one of these items, you just have to type the number corresponding to it. Here is a more detailed description of the sections:
1. Play (to launch the game)
2. Rules (to display the game rules)
3. Settings (to change some game settings)
4. Information (to display the game rules)
5. Statistics (to display the simulations made between the different types of players)

![welcome](https://user-images.githubusercontent.com/56866008/117222267-593d6080-ae0b-11eb-8498-08374f3b712f.PNG)

_Menu interface_

In the "**Settings**" section, a confirmation message [Yes/No] to modify the parameters is requested. In the case of a validation, the user can choose to modify :
- A - **Number of players** (2-6 players)
- B - **Size of the grid** (number of rows > 2, number of columns > 4, and number of rows x number of columns >= 8)
- C - **Number of rounds** (3 rounds min)
- D - **Number of tokens to align to win** 1 round (4 tokens min with the condition: number of tokens <= number of rows and columns)
- E - **Players' symbols** (provided that the new symbol is not used by any other player)
- Z - **All the above mentioned settings** (changes can be made at once!) To access any of these categories, just type the letter corresponding to it.
To return to the main menu, the user simply needs to type the 'menu' command (a message is displayed when this is the case). In the case of a modification of one or more parameters, a green message is displayed to confirm the modification of the parameter(s).

![increase_players](https://user-images.githubusercontent.com/56866008/117222259-58a4ca00-ae0b-11eb-926a-e485a5da2932.PNG)

_Grid example with 6 players ('Y' won)_

#### 2. Creation of "parameters" command in game
This command (usable only during the game) allows to **visualize the state of the game** (victory/steps/score/parameters of the grid/parameters of the players). The user just has to type the command '**parameters**' in the terminal.

![parameters_command](https://user-images.githubusercontent.com/56866008/117222261-593d6080-ae0b-11eb-9467-28de656088ba.PNG)

_Command "parameters" to visualize the state of the game_

#### 3. Implementation of IA players
By default, the AI considered is "Random". However, 3 types of AI are available:
- **Random** (just enter ia 'nickname'): always plays randomly, according to a uniform law (all columns have the same probability of being played). This AI is the most basic, and therefore the least good.

- **Monkey** (you have to enter ia:monkey 'pseudo'): this AI has understood the rules of the game, if it sees three aligned chips of its own color or the color of its opponent, it will put the fourth chip if the move is playable (to win or block the opponent). If it doesn't find a line up of three chips, it plays like the random AI, in a uniform way

- **Pro** (you have to enter ia:pro 'nickname'): like monkey, it can detect the alignments of three chips and play the fourth one if possible. However, if it does not find a three-chip alignment, it will play randomly according to a Gaussian distribution centered in the middle of the board, because moves in the middle of the board are more likely to make alignments than moves on the sides. This simple improvement allows the Pro AI to be better than the Monkey AI as shown by the statistics: **Random vs Monkey: 81% for Monkey - Random vs Pro: 90% for Pro - Monkey vs Pro: 66% for Pro**

![stats](https://user-images.githubusercontent.com/56866008/117222265-593d6080-ae0b-11eb-9f68-c18ab7052436.PNG)

_Statistics realized during the project_

## Launch the program
Follow instructions below :
```
javac Menu.java
```
```
java Menu
```
Then press "**1**" to start playing. You will have to fill in the name and type for each player in the following form : "**type pseudo**" with type = {human; ia; ia:monkey; ia:pro}.
  
Have fun !

## Acquired skills
- Concepts and tips from object-oriented programming

## Informations
This project is an academic project for "PG220 - Projet de programmation orientée objet" during my 2nd year of engineering school at ENSEIRB-MATMECA.

