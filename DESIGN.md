SLogo Team 13: Design Document
========

Introduction
=======

We aim to implement an SLogo program that includes a user-friendly, interactive frontend that can call a reliable backend to throw specific errors and execute the commands correctly. We also aspire to make the development process as streamline as possible, integrating elegant ways to access private variables and keeping information within single instance classes.

The frontend and backend will of course work within separate domains of concerns. The former will focus on views such as the ConsoleView, HistoryView, and the world view while also supplying the backend an API to visually render turtle movements. The latter will take in inputs from the frontend, parsing the strings to call the correct Command objects that execute the user input.

We’ve considered most flexibility in how to add new features on both the frontend and backend. On the backend, we make it easy for the creation of a Command class to encompass its execution logic as well as its error-checking. The Parser API will make sure that We also separate the commands into separate Command classes, so they can be called on to run themselves - essentially, the interface Executable they all implement will be an API.
On the frontend, we make it easy to consider new types of agents to be drawers by making Drawer an interface and creating general methods, like drawLine and drawActor, to allow for different kinds of “Actors” beyond turtles to be drawn on a screen and track their movements via lines.


Design Overview
========


## User Interface (Colette and Melissa)

For our user interface, we will have several different components for the user to interact with.  Some of the primary components include the Turtle view rendering of turtle movements across a JavaFX scene,  the console below the Turtle view where the user will be able to type in and enter the commands to be rendered on the JavaFX scene, and the history log that will be contained to the right of these two components so that the user can keep track of the commands that they have inputted and re-select those commands to display them on the scene once again.  In addition, on top of the Turtle view, the user would be able to view all of the saved variables and methods from previous commands so that they can select them for use when creating new commands, and below the console, we would have buttons for if the user would like to access a help menu or change the language of the commands.  Lastly, we would have a small preference menu at the bottom for the user to select a turtle and change its styling (including the turtle’s image, the background image, and the turtle’s pen color).  Erroneous situations would be reported to the user via the history log-- error messages would display in red if the user inputted a faulty command, parameters, etc.

##### Below are some classes:
* Turtle.java (Melissa)
 * What it does: DrawLine(), Move()
 * What it knows: Color, ImageView, Position, Name, Line Visibility
 * Interacts with: TurtleTracker.java
 * This class is purposed to create turtles and keep track of their properties as new commands are entered, allowing us to meet the the assignment specification of allowing a user to enter commands to manipulate a turtle’s movements, orientation, etc. and have these displayed on a screen.  Additionally, it will keep track of turtle properties as users select different preferences, allowing us to meet the assignment specification of setting different images, colors, etc. for the turtle.


* TurtleTracker.java (Melissa)
 * What it does: addNewTurtle(); removeTurtle(), moveTurtle(); getTurtleNames()
 * What it knows: Turtle instances
 * Interacts with: ViewTurtle.java; ViewPreferences.java; Turtle.java


* View.java- Abstract class (Colette)
 * What it does: Sets new visual properties
 * What it knows: Name, Position, Color, Language, other Visual properties
 * Interacts with:
 * This class will be used as we create different components to be contained in the UI and plays a big role in our design’s flexibility, as it will be general enough to maintain information required by different UI components, but can easily be extended to be made into specific types of views with special properties to meet assignment specifications requiring different types of information to be displayed before or inputted by the user.

* ViewHistory.java- extends View.java (Colette)
 * What it does: Displays all user-inputted commands; Displays errors; User interactive to run previous command
 * What it knows: All command history
 *  with: External ErrorHandling API; ViewConsole.java
 * This class will be used to meet the assignment specification of allowing a user to see commands previously run in the environment and errors resulting from those commands.  It extends the View class to take advantage of the flexibility of our design, keeping View closed for modification but open to extension.


* ViewConsole.java - extends View.java (Melissa)
 * What it does: Allows user inputted commands
 * What it knows: User Input
 * Interacts with: External ErrorHandling API; ViewHistory.java
This class will be used to meet the assignment specification of allowing a user to enter text commands to interact with turtles on the screen.  It extends the View class to take advantage of the flexibility of our design, keeping View closed for modification but open to extension.

* ViewTurtle.java - extends View.java implements Drawer (Melissa)
 * What it does; Displays turtles; drawTurtle(); drawLine(); setNewTurtleLocation()
 * What it knows: Turtle locations
 * Interacts with: Turtle.java; External API
Part of external API: setNewTurtleLocation()
 * This class will be used to meet the assignment specification of visually displaying for the user the results of executed commands.  It extends the View class to take advantage of the flexibility of our design, keeping View closed for modification but open to extension.


* ViewPreferences.java - extends View.java (Melissa)
 * What it does: Changes Turtle properties(i.e. uploadTurtleImage(), setLinesVisible(), setTurtleColor()); Change Language Preference; Access Help Page; Change visual properties of all Views
 * What it knows: Resource packages
 * Interacts with: Turtle.java
 * This class will be used to meet the assignment specification of allowing a user to set different preferences for the visual rendering of the turtle, including a background color for the turtle’s display, an image for the turtle, a color for its pen, etc.  It extends the View class to take advantage of the flexibility of our design, keeping View closed for modification but open to extension.


* ViewVariablesAndMethods - extends View.java (Colette)
 * What it does: Displays variable; User Interactive
 * What it knows:
 * Interacts with: SavedVariables.java; SavedMethods.java
 * This class will be used to meet the assignment specification of allowing a user to see variables currently available in the environment, as well as user-defined commands that have previously been created.  It extends the View class to take advantage of the flexibility of our design, keeping View closed for modification but open to extension.


* MainView.java implements GuiMaker (Colette)
 * What it does: creates and organizes view objects as they will appear on the screen; addNewView()
 * What it knows: Position of each view; Visibility of each view;
 * Interacts with: DisplayViews.java; ViewFactory()
 * This class will be used to allow us to create and manage multiple views.  It keeps our design flexible by paving the way for the addition or elimination of views as our program grows and changes.


* ViewFactory.java (Colette)
 * What it does: createNewViewObject
 * What it knows:
 * Interacts with: ViewMain.java
 * This class will be used to allow us to create new View subclasses as we have more and more different types, to take advantage of polymorphism in our design by allowing us to create View objects while letting subclasses decide how they are instantiated.


* DisplayViews (Colette)
 * What it does: displays MainView object on screen
 * What it knows: Scene; Stage
 * Interacts with: Main.java
This class will be used to display our collection of views on a JavaFx stage so that the user can view all of the different components of our program at once and we have an easy way to set up the stage with all of its different parts.


* Main.java (Colette)
 * What it does: Initializes Timeline; Displays initial screen
 * What it knows: Time Elapsed
 * Interacts with: DisplayViews.java
 This class will be used to set up the timeline that will be driving our animation, allowing us to have a clean design, where all of the aspects related to running the animation are contained in this once class.


* SavedVariables.java (Colette/Melissa)
 * What it does: addNewVariable(); setVariableValue()
 * What it knows: Variable names
 * Interacts with: ViewVariablesAndMethods.java; External API
 * This class will be used to allow us to continually add new variables to our saved list of variables as the user inputs more and more commands, keeping our program flexible.


* SavedMethods.java  (Melissa)
 * What it does: saveMethod(); renameMethod(); getMethodNames(); GetMethodCommands()
 * What it knows: all methods and names
 * Interacts with: ViewVariablesAndMethods.java, ViewHistory.java
Part of External API: GetMethodCommands()
 * This class will be used to allow us to continually add new variables to our saved list of methods as the user inputs more and more commands, keeping our program flexible.

## API Details

### BACK END EXTERNAL: Parser API
This is responsible for taking the user input and either throwing an error or (if there is no error) executing the command.  
Details for handling errors with an invalid command name implemented entirely within this class, and calls to method from instance of Executable interface to handle any errors relating to invalid parameters.
Instance will be instantiated in ConsoleView

##### Public Methods:
* void run(String userInput);
Takes the String and checks to see if the command name is valid.  If it isn’t, throws an error; if it is, get an instance of the command type and run its execute(String userInput) method.  
Will be called from ConsoleView

* String checkIfError(String userInput);
Takes the String and runs a method to check if the command name is valid.  If it is, then looks to an instance of the command and calls “validate” to see if the parameters are valid.  Returns a String containing the error message, or null if there is no error.
Will be called from ConsoleView

* Map<String, String> getVariableMap();
returns a map where the keys are variable names as a string and the values are the values of those variables represented as a String (so a variable of the number 50 would map to “50”).
Will be called by the SavedVariablesAndMethodsView

The closed methods will be those that instantiate the commandsMap upon construction and
the addCommands method, which will add to the commandsMap (this lets the developer easily add the necessary information when we accommodate for new Command features)

### BACK END INTERNAL: Executable Interface
Handles running commands and handling errors relating to invalid parameters.  Its construction makes it easy for the developer to add new commands as only a single class needs to be written to handle both errors and execution relevant to that particular command (since different commands can have unique errors associated with them), followed by adding that command to the map contained in parser.  Should be called through the “checkIfError” method of the Parser.

##### Public Methods:
* String validate(String userInput);
should throw errors if there are problems with the userInput.  This includes incorrect number of parameters, incorrect type of parameters/use of invalid variable names, or invalid values for parameters (e.g., having ‘0’ as second parameter for division).
Will be called by the “checkIfError” method of Parser.

* void execute(String userInput)
should handle actually changing any values as a result of the command (e.g., position of turtle, add variable to map, etc.)
Will likely refer to Resources class in order to get instances of classes necessary to make changes (e.g., get TurtleTracker and call relevant method to change position of current turtle) dictated by that particular command

Below is how the two backend API’s would interact with each other.
![alt text]("backend API UML diagram.PNG")

## FRONT END: External API Drawer

``` java
public interface Drawer {

public static void drawLine(int x1, int y1, int x2, int y2);
```
This method is responsible for making lines appear on the screen to mirror turtle movements.  It will only make a line appear if the Turtle that is drawing the line has has its pen down (a property of the turtle).  It will also make a line appear in the color of the turtle’s pen (another property of the turtle)
```java
public static void drawActor(int x, int y);
```
This method is responsible for making the actor (represented by some image) appear on the screen at a specified coordinate, as when a turtle object is first created and it appears at (0,0), or to make it appear as if the turtle is moving from one coordinate to another.
}

This external API for the front-end is intended to allow a program to visually render movements of some kind of object across a scene.  In our case, it allows us to meet the assignment specification of allowing the user to see the results of the turtle after executing commands on it.  It would be used by some kind of Turtle class which would implement this interface’s methods by drawing lines for every movement a Turtle object made, and making the Turtle appear at one coordinate or another upon creation or depending on its movements.  It would rely on information passed to it from classes implementing the Parser and the commands parsed and executed to determine next coordinates, etc.  It is made very general to allow for extension by any kind of other program that needs to visually render some kind of drawing on a scene other than Turtle objects.

``` java
public interface GUIMaker {
  public static void addView(View view, Position position);
  //This method is responsible for adding views to a stage at a //certain position on the stage.
	public static void addElement(Object element, View view);
  /*This method is responsible for adding any kind of GUI element to a view so that all of the elements in a view can be contained together and treated together.*/
	public static View createView(int height, int width, String name);
  /*This method is responsible for creating a view so that new ones can continually be added to the UI as a program’s functionality is increased.*/
	public static Button createButton(String ID, String label, EventHandler<T> onButtonClick);
  /*This method is responsible for creating a new button to be used on a view */
	public static ComboBox createDropDown(String ID, String label, ArrayList<String> options, EventHandler<T> onSelect);
  /* This method is responsible for creating a new drop down menu to be used on a view */
  public static Text createText(String ID, String label);
  /* This method is responsible for creating text to be used on a view */
	public static TextArea createTextBox(String ID, EventHandler<T> onEnter);
  /*This method is responsible for creating a text field to be used as a console where users can input multiple text commands */
	public static void rearrange();
  /* This method optimizes the arrangement of the different views on a stage to adjust the overall look of the GUI every time a new view is added. */
}
```
This internal API for the front-end is designed to create a user interface for some kind of integrated development environment.  It allows any programmer to create new regions (“views”) on the UI for different components as they increase functionality of their IDE, and customize those views with different GUI elements like buttons, text, text boxes, etc. It would be used implemented by some kind of MainView class that would add different kinds of views to a stage that would display our SLogo IDE, including a console view, a history view, a turtle view, etc.  We made this API flexible to be used by programmers to create any type of GUI, not just one for our SLogo turtle assignment.


# API Example Code

Below are a few user cases and how our program would handle them.
* User types ‘fd 50’ in command window:

  * Frontend calls:
```java
HistoryView.printCommand();
Parser.checkIfError(“fd 50”);
if (error) {HistoryView.printError()}
Parser.run(“fd 50”);
```

 * BackEnd: (calls made to Parser’s public methods described above)
``` java
Forward.execute(50); // the command
TurtleTracker.moveTurtle(50)
```

 * FrontEnd:
 ``` java
TurtleView.drawLine(0,0,0,50)
TurtleView.drawActor(0,50)
```

* User chooses saved method to run
 * FrontEnd:
```java
SavedMethods.getMethodCommands(name)
for (command: result){
  HistoryView.printCommand()
  Parser.run(command) // external API call
```

 * Backend: External API Call
```java
 SavedMethods.getMethodCommands(name)
 ```

 * FrontEnd:
```java
TurtleView.drawLine()
TurtleView.drawTurtle()
```


* User creates a new variable
 * FrontEnd
 ``` java
HistoryView.printCommand()
Parser.run() // call to backend's parser API
```

 * BackEnd:
```java
Set.checkParams()
Set.execute()
CheckIfValidVariableName()
SavedVariablese.createNewVariable() // External API Call
SavedVariables.setNewVariable() // External API Call
```

 * FrontEnd:
 ```java
SavedVariablesAndMethodsView.showVariables()
```

* User saves a method
 * FrontEnd: User Prompt-> Enter Method Name
 ```java
SavedMethods.checkIfValidMethodName()
SavedMethods.createNewMethod(methodName, String[])
```

Design Considerations: Ambiguities?  Assumptions?  Dependencies?
========

One of the things we want to allow for in our program is the allowance of multiple turtles.  This is one of our reasons for ensuring that we had a separate Turtle class that would be passed between the front-end and back-end for the execution of commands.

One of the issues we discussed was how commands that were parsed by the back-end would then be received by the front-end.  The two major points considered were either passing in an instance of a “Command” object, which the front-end would then execute, or simply passing in new information about the turtle (or perhaps modifying the ‘stats’ of the existing turtle).

The pros of passing a “Command” object was that it would keep the back-end from having to know any information about the turtle.  However, this also has the con of the front-end having some sort of access to every possible command.  In addition, it was felt that the front-end should keep its focus on displaying (and possibly maintaining) the information about the program without directly executing it.  Passing a “Command” object would blur the lines between the two.  For these reasons, it was felt that having the back-end handle updating information about the turtle would be preferable.

Another point of discussion was whether to have the turtle move immediately after entering in a command (assuming it was valid), or to use an animation in order to display the movements--or somewhere in between.  The pros of not having the animation primarily lies in it being simpler to write.  With a relatively small amount of time to work on completing the project, not worrying about an animation simplifies the coding process and allows for the focus to be on implementing the “key” features of the program.  It also doesn’t take away from the core functionality of the program--users are still able to create and manipulate turtles as they wish, regardless of whether or not there is an animation.

However, having an animation would likely be preferable for many users (even if not necessary).  For some of the more intricate programs that would be possible with SLogo, users would likely enjoy being able to (more directly) see their program in action.  This could also be useful to users who need to “debug” their program if their code isn’t producing the image they had hoped.

Ultimately, it was decided to have the turtle move immediately after entering in a command.  While we would likely use an animation if time were not a factor, for the purposes of this project we felt it would result in better functionality and a cleaner design.




Team Responsibilities
=======

To divide the program components, we will have two-subteams.  Carolyn and Roxanne will be responsible for the backend, while Melissa and Colette will be responsible for the front-end.  On the backend side, one person will be primarily responsible for writing up the Parser interface and its implementation in the classes that deal with it.  The other person will be primarily responsible for working on turtle commands and error handling of those commands  On the front-end side, one person will be primarily responsible for working on the Drawer interface and the turtle implementation of it to visually render turtles and their movements on a JavaFx scene.  The other person will be responsible for working on the GUIMaker interface and its implementation in the turtle main class that displays the UI for SLogo users to play with the turtle, and the creation of all the different view classes we’ll need for the UI components.  To complete the program, we will try to complete our four main interfaces described in this document and their implementations by Wednesday or Thursday and then work on integrating them by making test cases and debugging before the due date.
