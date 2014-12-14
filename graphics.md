Pros and Cons of Different Graphics Engines
===========================================

OpenGL
------
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

[StackOverflow]: http://stackoverflow.com/questions/5371692/can-java2d-be-as-fast-as-lwjgl-and-jogl
    "Can Java2d be as fast as LWJGL and JOGL?"
[SlickForum]: http://slick.ninjacave.com/forum/viewtopic.php?t=887
    "Slick vs. OpenGL vs. Java 2D"
