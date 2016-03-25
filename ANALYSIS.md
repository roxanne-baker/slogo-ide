#Design Review#

##Status##
We did a good job of separating classes into packages (i.e. model, parsing, controller, view). There were slight discrepancies in naming format (Turtle.java should be called AgentTurtle.java to indicate it is a subclass) but generally names were descriptive and followed naming conventions.

The ControllerBackground class has a very a vague name that doesn't seem to correspond with the function of the class since it changes the turtle pen color as well as the background of where the agents are displayed. It could be more accurately named ControllerColors to indicate that it controls the CustomColorPalette and ViewAgents background color.

The only dependencies exist in the Workspace class in the init() method. Models and Views need to be instantiated before Controllers which makes sense since the Controller updates the view based off the model. In addition, certain View classes are dependent on other views- for example,  user input from the ConsoleView are printed into the HistoryView. These views also require the same instance of the Interpreter class which gets the user input from the ConsoleView and prints a return value to the HistoryView. These dependencies are clearly delineated in the Workspace class, which makes it easy to modify in the future.

We designed the View class to be very easy to extend so that new Views could be added in the future. The Agent class is also easily extendable to allow for new types of agents with different behaviors. The GuiObject class is also designed to allow for new GUI elements related to Agents.

Review two classes in the program you did not write or refactor in detail:

*Interpreter.java*
I chose this method because it is one of the longer classes (448 lines). 

The tree traversal in the Run() method could have been made into another class that handles the tree. In addition, the classes that add commands to the commandsMap (i.e. initializeCommandsMap(), addControlStructureCommands(), addTurtleCommands()) have lots of repetitive code. For example:

`private void addTurtleQueries() {
  commandsMap.put("XCoordinate", new XCor(turtleController));
  commandsMap.put("YCoordinate", new YCor(turtleController));		
  commandsMap.put("Heading", new Heading(turtleController));
  commandsMap.put("IsPenDown", new PenDownQuery(turtleController));
  commandsMap.put("IsShowing", new ShowingQuery(turtleController));
  commandsMap.put("GetPenColor", new PenColorQuery(turtleController));
}`

The author could have used a Factory method to introduce some polymorphism into the code instead of hardcoding every command. This code does not seem like it should belong in an Interpreter class since it only initializes the command map and doesn't "interpret" anything. The author could have done a better job refactoring and getting rid of hardcoded values.

This code could reused in a game that requires a player to move or calculate some values. In a game, special 'power moves' could use the tree traversal methods and the commands map. 

*ViewHistory.java*
I chose this code because the author used the observer/observable design pattern. The class does have some hardcoded values in the update class so using a resource bundle would have been better.

`public void update(Observable o, Object arg) {
  if(arg=="NEWHISTORY"){
    vb.getChildren().add(((HistoryElem) o).getTextBox());
  }
  if(arg=="ERROR"){
    Interpreter ip = (Interpreter) o;
    ErrorElem errorText = new ErrorElem(ip.getErrorMessage());
    vb.getChildren().add(errorText.getTextBox());
  }
  if(arg=="CLICKED"){
    getInterpreter().run(((HistoryElem) o).getString());
  }
  if (arg=="RESULT") {
    Interpreter ip = (Interpreter) o;
    ResultElem resultText = new ResultElem(ip.getReturnResult());
    vb.getChildren().add(resultText.getTextBox());
  }

}``
The author did a good job of keeping the View class to displaying objects instead of giving it other responsibilities that don't fit its role. She did this by abstracting the view elements such as ResultElem, HistoryElem, and ErrorElem, so the class does not need to know the types of elements that are being displayed currently. 

This class could be extended to a Game to see a history of moves made by the user. New GUI elements could also be created such as PointsElem.java to show how many points were earned from a given move. To add another GUI element, you could create a new class (i.e. PointsElem.java) and add it to the update method. As a result it follows the Open/Closed Principle since it also can't be modified by other classes.


##Design##

Our design follows the Model View Controller pattern. The backend (Interpreter) updates the Model (Agent, ModelMethods, ModelVariables) so the Controller (ControllerAgents, ControllerVariables, MethodsController) can subsequently update the View (ViewAgents,ViewMethods,ViewVariables). See the previous section for description of dependencies.

The Agent class is the model for all agents/turtles in the application. The class knows the state of the agent and its properties (i.e. visibility, position, size). The agents are designed so that Agent object would only know its own properties but not have to keep track of any JavaFx objects. Instead, each Agent object has a corresponding AgentElem class, which knows its imageview, Color property, and rotation. I separated the model and view in order to encapsulate the properties/state of the Agent from its display image.

The ViewAgents class displays the Turtle views and shows which agents are active. It follows the Observer/Observable pattern and observes the created Turtle objects in order to update them. The class only displays objects, while the ViewAgentsUpdater class keeps track of the imageviews of the turtles (i.e. mapping imageview to turtle object). It also updates the views based on changes in the model (i.e. visibility, pen up/down, position) and handles user interactivity (i.e. when a user clicks on a Turtle to toggle it between active and inactive). I should have given this class a more descriptive name such as ViewAgentsUpdaterAndTracker or ViewAgentsController to indicate that it knows which agents are displayed and graphically updates them.  I delegated the actual drawing of the turtles and lines to the Drawer class, which keeps tracks of all drawn images/lines and clears the screen. The Drawer class could definitely be used in another application such as a game, since all it does is draw ImageViews and lines.

The ViewAgentPreferences class displays the agent properties, some of which the user can modify. I designed this class so that you can easily add new properties as well as change an existing property from non-editable to editable and vice versa. For example, you can make the X and Y position of the Turtle user interactive by adding "XPosition" and "YPosition" to the MUTABLE_LIST in the Turtle classes and adding the switch cases to the GuiObjectFactory so that a mutable GuiObject such as an InputBox will be created. If you don't want the user to change a property through the GUI, you similarly add the property to the OBSERVER_LIST and ObserverLabelFactory. Properties of the turtle are binded to the ObserverLabel so any changes to the Turtle model will automatically be displayed on the GUI. This design allows for a lot of flexibility in terms of how graphically interactive the application should be. In addition, the view is encapsulated from the GUI elements since it does not need to know which elements to create (no hardcoded values!).

Side note: I reused the GuiObject classes and subclasses from the CellSociety project! I was able to do this since the GuiobjectFactory allowed for polymorphism in my code. It also followed the Open/Closed principle since I could change the type of GuiObject (i.e. Slider vs. ComboBox) for a certain property without changing any code outside the GuiObjectFactory.

To allow the user to create custom colors and images for the Turtle, I created a Palette super class with CustomColorPalette and CustomImagePalette subclasses. Using commonality analysis, I saw that all palettes have the same functions of adding, removing, and replacing palette objects with the only difference being the palette object view (Color vs. ImageView). This allowed me to create generic methods except for the getPaletteObjectView() method. The CustomColorPalette creates CustomColorobjects to store the RGB value of the created color with a corresponding CustomColorElem class, which is the palette object view. These palette objects are all displayed in the ViewPalettes class, where the user can also add new images and colors. Reflecting on my code, I would have made the Palette class an external interface instead of a super class. Right now the backend adds a palette object through the ControllerBackground class. However, this seems redundant when the CustomColorPalette essentially has the same function. The ControllerBackground adds a layer of abstraction and keeps the backend from having access to all the methods in the CustomColorPalette. An interface would have solved this issue in the same way as the ControllerBackground in a much simpler way with the same amount of code.

The ViewWindowPreferences class changes the overall preferences of a workspace. Similar to the ViewAgentPreferences class, the class uses the GuiObjectFactory to populate the screen which makes it easy to add new objects as needed.

To add a new command, create a new class that implements the Executable interface and extends the correct Command supertype (i.e. MathCommand, ControlCommand, TurtleCommand, TurtleQueryCommands). Add the new command to the commandsMap in the Workspace class.

To add a new component to the view, create a new class that uses the naming convention of "View" followed by its name. The class should extend the View abstract class. In the ViewType class, add the ID of the new view. In Workspace, under initViews() add any dependencies.

We implemented all of the features in the specification, but I also added an additional feature that allowed the user to choose the imageview of a selected turtle. The dropdown selections are populated from the CustomImagePalette objects (i.e. image paths) and update whenever the user adds an image to the palette.

Additional dependencies: The ControllerTurtle, ControllerBackground,, and ViewPalettes class depend on the CustomImagePalette and CustomColorPalette classes. A possible dependency is that since he image and color index for the given Turtle must correspond to the correct index in the respective palette, the classes assumes that the palettes, as well as the agents, are updating correctly.

Describe two features from the assignment specification (at least one you did not implement) in detail:

**Preferences File Saving and Loading** (I did not implement this feature)

This feature requires the PreferencesSaver, Saver, XMLReader, Preferences, and Workspace class. The application automatically loads a default XML file upon running. The user can set a new XML file and the program will throw an error if the format is incorrect. The Preferences class introduces some dependencies since it is passed through the constructor of the classes. This makes the code less extendable since you would require an instance of Preferences to instantiate the views. Setting preferences within classes needs to be hardcoded which also introduces a lot of dependencies as the number of saved preferences grows. To solve these issues we could create a separate PreferencesSetter class that encapsulates all the preferences and removes these dependencies. A downside to this would be a large number of getters and setters within each View class.

**Select Active Turtles Graphically** (implemented by me)

This feature requires the ViewAgentsUpdater and Drawer classes. The user interactive part of the turtle images is handled by the ViewAgentsUpdater and is completely encapsulated from the ViewAgents class. Therefore, only the ViewAgentsUpdater knows which turtles are displayed on the screen. In the ControllerTurtle class there is an activeAgentListProperty that is binded to the same property in the ViewAgents class. When an image is toggled between active and inactive, the ViewAgentsUpdater updates the binded activeAgentListProperty to propagate the changes throughout the code. The Drawer, on the hand, keeps track of each imageview, stamp, and line on the screen. This graphic information is encapsulated from the ViewAgentsUpdater since it only needs to know that an imageview has been clicked. The Drawer and ViewAgentsUpdater have very clear responsibilities and follow the Open/Closed Principle which makes them very easy to reuse and extend the code. For example, you could also display a list of preferences for every active turtle by adding a changing listener to the activeAgentListProperty. 


##Alternate Designs

The ControllerTurtle class keeps track of all the turtles that have been created. It creates new turtles and removes turtles. It also knows which turtles are currently active and updates them. This deviates from the traditional MVC model since normally the backend would update the Model (which in this case is the Turtle) leading to the Controller updating the View. Our team discussed the tradeoffs of passing Turtles to the backend in accordance with this model. However this would have led to problems with giving the backend access to every method in the Turtle class including its imageview. We ultimately decided on making an interface ControllerAgents.java, which is extended by ControllerTurtle.java, and would only allow the backend to change currently active Turtles and give access to certain properties. The interface also introduces additional polymorphism since new Agents with different types of behaviors can be created and still execute the same commands through the interface.

The current design handled the extensions relatively well. Originally we kept track of the current active agent in the TurtleController. With multiple active agents, we tweaked the TurtleController to also keep track of an active agent list. When a command was executed from the backend, the TurtleController would iterate through this list by changing the current active turtle. Other extensions of allowing the user to change graphically the properties of the turtle were easy given the dynamic design of the ViewAgentPreferences and the GuiObjectFactory/ObserverLabelFactory.

We originally planned for the Drawer to be an external API but realized that only the ViewAgentsUpdater class would need access to it. The backend would instead interface with the TurtleController to change the underlying Turtle Models. In addition, the GUIMaker internal interface didn't provide the flexibility we needed for each View since some views depended on the other views and some didn't interface with the backend. As a result, we decided to extend a View abstract class and use a ViewFactory to add polymorphism and a layer of abstraction.


##Conclusions##
Describe the two best features of the overall project's current design (these could include your own or others code).

**ViewAgentPreferences and Adding GUI Elements**

As mentioned above, this code follows the Open/Closed Principle, has few dependencies, and introduces a lot of flexibility into the GUI. This class uses the GuiObjectFactory and ObserverLabelFactory to dynamically add new GUI elements that are user interactive/editable based on the MUTABLE_LIST and OBSERVER_LIST in the Turtle class. A GuiObject is editable by the user and propagates changes to the turtle model. An ObserverLabel displays information and is binded to a property in the turtle model. The factory classes add polymorphism so the view does not need to keep track of which GUI elements need to be created. The GUIObject and ObserverLabel classes also encapsulate any user interactivity so that this view only handles displaying the GUI elements. In addition, the displayed preferences dynamically update when the CurrentAgentProperty is changed in the ControllerTurtle class to reflect the model.   

**ViewHistory**

As mentioned above, this class has the perfect level of abstraction to make the code extendable and flexible. There are no hardcoded values and appropriately distributes responsibilities between the GUI elements (ResultElem, HistoryElem, ErrorElem) and the view itself. As a result, the view only handles displaying the GUI elements. It also utilizes the Observer/Observable design pattern to reduce dependencies.

Describe the two worst features that remain in the overall project's current design (these could include your own or others code).

**Interpreter**

As described in the previous sections, the code is hard to follow and very long. There are issues with hardcoded values and repeated code. The responsibilities of the class are also unclear since it seems to be working with tree traversal as well as keeping track of the commands map.

**ControllerBackground**

This class also does not have a clear purpose since it controls elements of the CustomColorPalette as well as the background color of the ViewAgents display. This class seems to be an afterthought without consideration for design.

To be a better designer, what should you start doing, keep doing, or stop doing?
*Encourage others to communicate more
*Continue setting out clear responsibilities for my classes
*Continue planning ahead the overall design of the
*Continue having design conversations with my teammates
*Start challenging myself with more complex design patterns
