package main;

import java.util.ArrayList;

// generic object ,everything inherits from it.
class ZorkObject {
	public String status;
	public ArrayList<ZorkTrigger> trigger = new ArrayList<ZorkTrigger>();

	public ZorkObject() {

	}
}

// generic condition
abstract class ZorkCondition {
	String object;

	public abstract boolean evaluate(Engine zork);

}

// status conditions
class ZorkConditionStatus extends ZorkCondition {
	String status;

	@Override
	public boolean evaluate(Engine zork) {
		Engine tested = zork.Objects.get(object);
		if (tested != null && tested.status.equals(status)) {
			return true;
		} else {
			return false;
		}
		return false;
	}

}

// has conditions
class ZorkConditionHas extends ZorkCondition {
	String has;
	String owner;

	@Override
	public boolean evaluate(Engine zork) {
		// inventory is to be checked separately
		if (owner.equals("inventory")) {
			if (zork.Inventory.get(objects) != null && has.equals("yes")
					|| zork.Inventory.get(objects) == null && has.equals("no")) {
				return true;
			} else {
				return false;
			}
		} else {
			// is it a room
			ZorkRoom roomObject = zork.Rooms.get(owner);
			if (roomObject != null) {
				if ((roomObject).item.get(object) != null && has.equals("yes")
						|| (roomObject).get.item.get(object) == null && has.equals("no")) {
					return true;
				} else {
					return false;
				}
			} else {
				// is it container
				ZorkContainer containerObject = zork.Containers.get(owner);
				if (containerObject != null) {
					if ((containerObject).item.get(object) != null && has.equals("yes")
							|| (containerObject).item.get(object) == null && has.equals("no")) {
						return true;
					} else {
						return false;
					}

				}
			}
		}
		return false;
	}

}

// command condition
class ZorkCommand extends ZorkCondition {
	String command;

	@Override
	public boolean evaluate(Engine zork) {
		if (command.equals(zork.userInput)) {
			return true;
		} else {
			return false;
		}
	}

}

// trigger
class ZorkTrigger {

}

public class Engine {

	public static void main(String[] args) {

	}

}
