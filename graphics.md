Pros and Cons of Different Graphics Engines
===========================================

OpenGL (LWJGL)
--------------
### Pros ###
- High fill rate [StackOverflow]
- 3d effect [StackOverflow] ... (We don't really need these though)
- Use GPU for rendering [SlickForum]

### Cons ###
- All users need a 3d card [StackOverflow]
- Users have to have OpenGL drivers installed, and installing them is a pain [StackOverflow]

Java2d
------
### Pros ###
- Simple to use, higher level than OpenGL [SlickForum]
- Predefined shapes [SlickForum]

### Cons ###
- No depth, only 2d [SlickForum]

Slick
-----
### Pros ###
- User **nibuen** says, [SlickForum]
  > Not sure how avid of board gamer you are, but I have had good success using slick to recreate
  > board games and should hopefully have a demo soon up. I have been able to make a Carcassonne
  > style game with tiles (if you don't know this reference as a board gamer :cry: , but check it
  > out here http://www.boardgamegeek.com). The only thing that I am wanting seems to be already
  > on the way, which is network capabilities.
- Contains almost everything needed to make a game, vs Java2D and OpenGL, which just do graphics.
  [SlickForum]
### Cons ###


[StackOverflow]: http://stackoverflow.com/questions/5371692/can-java2d-be-as-fast-as-lwjgl-and-jogl
    "Can Java2d be as fast as LWJGL and JOGL?"
[SlickForum]: http://slick.ninjacave.com/forum/viewtopic.php?t=887
    "Slick vs. OpenGL vs. Java 2D"

