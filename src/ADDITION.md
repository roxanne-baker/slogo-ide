###Estimation

* About 2-3 hours
* Add two files, update another ~4 in addition to that - the two new files are the commands, the updated ones are to allow the turtle to accommodate different turtle types since border changes are currently not a supported feature - the classes will be line drawer (since the turtle still draws lines outside the window although it's not supposed to) and the Agent class.  The Interpreter class and (at least the English.properties) files will also need to be modified to accommodate the addition of the new commands.

###Review

* Around 3 hours
* Added three files, updated five.  The third file added was just to make calculations for coordinates a bit easier for figuring out what the position of the turtle should be for the fence border.  The five files were those mentioned above, as well as modifying the ViewAgentsUpdater (although this was because, unbeknownst to me, some code fixes I had made before finishing the project were overwritten).
* While the basic idea of the command worked on the first try (window not being limited by the border, where fence is limited) there were some errors at first with the exact position for fence and the turtle still being visible past the border for window.

###Analysis
* I think that this project showed that our code was relatively easy to extend, although our documentation wasn't great (it took me a while to figure out what file I needed to use for the changes that were made to ViewAgentsUpdater"
* It was similar to what I remembered - I feel like I was able to understand the code well and pretty quickly understood what I would need to do in order to implement the new commands
* The biggest improvement I'd want to make is to improve documentation.
* If I were not familiar with the code at all, I feel that the exercise may have been fairly difficult--again, mainly due to the lack of documentation.  I'm not sure if it would be intuitive for a user to implement different border types as separate methods within the Agent class or for these to be represented as numbers.  Ultimately, though, I feel that the structure of the new commands was similar enough to the old one for a 'newcomer' to get the hang of it fairly easily.  The most difficult aspect was fixing the pre-existing bug anyway, and not related to the actual implementing of the new commands.
