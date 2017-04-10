package main;

import java.util.ArrayList;

class ZorkObject {
	public String status;
	public ArrayList<ZorkTrigger> trigger = new ArrayList<ZorkTrigger>();

	public ZorkObject() {

	}
}

abstract class ZorkCondition {
	String object;

	public abstract boolean evaluate(Engine zork);

}

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

class ZorkConditionHas extends ZorkCondition {
	String has;
	String owner;

	@Override
	public boolean evaluate(Engine zork) {
		/* start here */
		return false;
	}

}

public class Engine {

	public static void main(String[] args) {

	}

}
