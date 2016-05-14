// This entire file is part of my masterpiece.
// Roxanne Baker
// I was able to generalize the needs of the boolean commands in order to
// allow no methods to be defined in the actual subclass
// This allows the class to be the true "core" of the command
// and reduce significant amounts of duplicated code

package commands;

public class Less extends BooleanCommand implements Executable {

	public Less() {
		setBoolFunction((Double first, Double second) -> (!isEqual(first, second)) && (first < second) ? 1.0 : 0);
	}
}