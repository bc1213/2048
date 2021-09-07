
# 2048

2048 game is played on a gray 4×4 grid, with numbered tiles that slide when a player moves them
using the four arrow keys. Every turn, a new tile will randomly appear in an empty spot on the
board with a value of either 2 or 4. Tiles slide as far as possible in the chosen direction until they
are stopped by either another tile or the edge of the grid. If two tiles of the same number collide
while moving, they will merge into a tile with the total value of the two tiles that collided. The
resulting tile cannot merge with another tile again in the same move. Higher-scoring tiles emit a
soft glow.

If a move causes three consecutive tiles of the same value to slide together, only the two tiles
farthest along the direction of motion will combine. If all four spaces in a row or column are filled with tiles of the same value, a move parallel to that row/column will combine the first two and
last two.

The game is won when a tile with a value of 2048 appears on the board, hence the name of the
game. After reaching the 2048 tile, players can continue to play (beyond the 2048 tile) to reach
higher scores. When the player has no legal moves (there are no empty spaces and no
adjacent tiles with the same value), the game ends.
## Documentation

- Design Principles Used : Clear architecture

* Open Points : 

        1. Currently the solution is developed using Brute force technique. Needs to be enhanced using different alogrithm.
        2. Using swipe action moving cell from row 4 to row 1 is not happening in one shot: Needs to add sort technique.



* Problems faced while designing solution :
    Spent little more time in creating the UI elements than desiging the game logic

## Code Walkthrough

#### GameDashboardActivity.kt

```http
  Single Activity application
```

* Basic UI elements are created using Table row inside the ConstraintLayout
* Used TextView for creating cell
* Textview id mapping with Activity is done using kotlin-android extension
* During initial loading of the game: 2 numbers are placed randomly inside the 4*4 GameDashboardActivity
* Used random number generator to choose the position where to placed
* Used probability function to choose 2 or 4 number as In the 2048 game, 2's appear 90% of the time; 4's appear 10% of the time
* During swiping action checking the consective row for swipe up/down and consecutive column for swipe left/right to see if they have same elements then will add and remove the other elements based on swipe
* After swapping and addition, re-arranging the tiles to match the swipe [left, right, top, bottom]
* Adding the background colours according to the number present in the textview/cell


#### OnSwipeTouchListener.kt

```http
  Listener for handling swipe gesture
```

  
## Demo

[![YouTube](https://www.gstatic.com/youtube/img/creator/yt_studio_logo_white.svg)](https://youtu.be/iNo0EgqH5bU/)

  

  
# Hi, I'm Bharath! 👋

  
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/bharath-shet/)

  
