## Approach
I chose to separate the main functionality of the proder processor into 3 separate classes

1) DatabaseService -> This is used for anything dealing with adding or removing from the database. This would allow for extension like if there was a need for
finding how many of a specific product were added into the database, or different queries/inserts/deletes... just adding in new functions without changing
the already existing ones.
2) OrderRepository -> It's whole purpose is to act like a "shopping cart" where current items can be added or deleted without affecting the long term state 
(acting as a sudo cache). Again, the way it is formatted now can accomidate extention without the need to change the already created functions
3) OrderViewer -> This Class's sole purpose is to display the contents of the Order in a formatted way and nothing else. 
4) Product -> Separating it into it's own class makes it easier to find
5) OrderProcessor -> It is what talks to every other class and sort of manages the state and functionality of everything else. It acts as a single point
of reference that MAIN can use... so MAIN can be as simple as possible
6)  Also for OrderProcessor and DatabaseSrvvice, I added in parameter to the Constructor for passing in the URL to the Database so that it is not hardcoded in.

## Alternatives

For the most part I would not change the general design of separating Database, Cache, and Printing. However, I would consider making the OrderViewer static 
because there is not much of a need to instnatiate that object that does not hold state. Also I would consider using a Singleton pattern for the Database Service
and the OrderRepository... there is no real nead for multiple connections to a single database from a single client(assuming we are only using one database) and having 
multiple caches could lead to data inconsitency.

## Difficulties

Nothing with the actual assignment itself. However, for the setup, I had some issues. I am currently working on this on a computer without svn, so I had to 
create a git repo, and push the original unchanged checkout to github, then using github codespaces ont he computer without svn, figure out how to donwload svn. Then,
I had the problem of the codespace's java compiler using Java 11 and not being able to compile due to records not having been created in that version, so i had to figure
out how to change the java version in codespaces and that was a whole mess. 

Also, I did not see th need for multiple commits until after I finished.