## External API's 

#### backend to frontend: for getting info to render turtles visually 

* retrieve states of turtles: ccalculated location/orientation
* getX()
* getY()
* getOrientation()
* getSize()
* getVisibility()

#### frontend to backend: errorHandling
* checkValidCommandName()
* checkValidParam()
* checkMissingParams()
* checkParamType()
* checkParamInBounds() // need several methods


## Internal API's

backend: implementation
* addCommand(String[], lambda expression for corresponding method)
* executeCommand(String, params) // will outsource errochecking to errorhandling API
* addErrorForCommand()

#### frontend: add views and GUI elements
* createGUIObject(Object-type, eventHandler, label, etc)
* addView(Group g)
* createView(size params, color, name)
* addGUIObjectToView(Object, view)
* rearrange()

