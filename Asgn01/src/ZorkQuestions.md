# Answers to the Zork Questions

1. What is written on the door in the living room of the house?

There are gothic lettering's that translate to "This space intentionally left blank"

2. What is on the table in the attic? (You have to solve a bit of a puzzle for this one).

There was a knife and rope

3. What is written on the door in the living room of the house?

(This question was asked twice)
There are gothic lettering's that translate to "This space intentionally left blank"

4. Create a short outline as described below.

Thinking about the “Zork” game, the intent here is to get everyone thinking about “decomposing” a problem into “objects/classes” (classes, interfaces, associated methods, fields, etc.) and the relationships between them.  While you should strive to be as correct as possible based on what you already know, DO not worry about your ideas being “correct” or refine your own understanding so we can then “refine” that understanding as we move forward!

In other words, a list of all the “different kinds” of objects that might need to exist.  Again, you do not need to include the very specific "room/area" or "item"; just a few representative samples will suffice.  (For example, “Room” would be a "category", whereas "kitchen" or "living room" would be specific instances.)

Place -> Would have a list of references to other places(associated with a direction) for the user to go to and a list of items within the place 
Item -> A thing that is part of a room -> depending on the inheretence on interface structure it can be moveable, non-moveable, useable, changeable, etc
Entity -> This would be things like the user or any monsters within the game who are able to change positions and have items as well... (would that mean that they could be represemted as a moveable place by this definition)?


5. What are the main objects and their associated “responsibilities”?
   * In general, what “purpose” does the object serve?
   * Not every “Object” is necessarily something the user directly interacts with; they could easily be things like an internal game “Map,” which contains all the rooms, etc.
   
   The objects tend to be used for holding onto information an enrich the game. They can also be useable, like the rug or lantern, changing aspects of the game and advance progress.
   The rug was used for hiding the trapdoor. The lantern was used for allowing me to see in the attic. The leaflet was used as an introduction to the game.

6. What can the object do?
   * How does each object interact with other objects in the game?
   
   Depends on the object. Some objects are there purely for information, like the leaflet. Some objects are used to contain other objects, such as the mailbox which contains the leaflet. Some objects are moveable and are allowed to be placed in the inventory, again like the leaflet. Some objects change the enviroment, like the lantern or rug. I would imagine that for some objects they have a reference to another object that specifies a special event that happens or a special set of changes that can change the enviroment/person/other objects.

7. What objects might be extensions of other objects?

I would imagine that every object has one central object claass that they all extend from. There are also objects that contain object, so I guess that would be a container object and that probably extends object. 

8. What objects might be contained/nested within other objects?

Maybe the table has nested objects like the rope and knife. Also the inventory is definetly a container object, because there are a list of objects within it.

9. What types of functionality might be best represented as interfaces instead of classes?

The moveability of objects... mailbox would impliment non-moveable while leafelt would implement moveable. Another one would be if an object is useable, like the lantern. 

10. What types of functionality might be best represented as a “strategy” instead of inheritance?  (HAS-A vs. IS-A).

Assuming there are weapons and fighting within this game, an attck strategy for each weapons would make sense because a sword attack is different from a bow and arrow attack and the results of themn could lead to very different circumstances for the receiver. Also, different items that affect the enviorment or the user should havea  strategy pattern. Like how turning the lantern on changed the state of the attic, having a lantern-on stretegy vs a lantern-off strategy when looking at the room would make sense.