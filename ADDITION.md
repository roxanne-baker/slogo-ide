##### Estimation: before looking at the old code:
how long do you think it will take you to complete this new feature?

##### how many files will you need to add or update? Why?
* I will need to add two files for the two commands, because we are using the Command design pattern where each
command has their own class but are of the same interface (executable).
* I will need to update the properties file for parsing.
* I will need to update the Agent class, which can allow the

##### how long did it take you to complete this new feature?
* It took me approximately two hours, mostly becaus.

##### how many files did you need to add or update? Why?
* I added a properties file, two command class files, and needed to update the Agent class with a few new methods that would change how the turtle moved.

##### Did you get it completely right on the first try?
* Well, I just had to tweak a few things but all the methods that I created worked.
I had to change some of the

### Analysis:
##### what do you feel this exercise reveals about your project's design and documentation?
I think it reveals something interesting about the integration, and it tests the
way in which we've constructed commands. It revealed that there are even more
ways to divide tasks into model-view-culture.

##### was it as good (or bad) as you remembered?
No, I expected there to be more happening in the controller, but a lot of the actions actually happens in the model class.
##### what could be improved?
Instead of the window behavior being a part of the Agent's properties, there
should actually be a Window Model-View-Controller so that the window can actually impose its own moves rather than the turtle stopping on its own accord.
NOTE: the reflection that I added in my Slogo masterpiece would have allowed me to not need to individually instantiate Fence and Window in my Interpreter class. 

##### what would it have been like if you were not familiar with the code at all?
It would have been very easy to create the new commands that could be parsed, and it would be easy to pinpoint the movePosition method in the Agent that allows the turtle to move, but it would have been tricky to see how to divide that into different methods base don the window behavior. Again, if we had a window controller, the call would have been very simple.
