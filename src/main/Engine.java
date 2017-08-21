package main;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.sun.xml.internal.txw2.Document;

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
						|| (roomObject).item.get(object) == null && has.equals("no")) {
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
	public ArrayList<ZorkCondition> conditions = new ArrayList<ZorkCondition>();
	String type = "single"; // by default single
	boolean hasCommand = false;
	public ArrayList<String> action = new ArrayList<>();
	public ArrayList<String> print = new ArrayList<>();

	public boolean evaluate(Engine zork) {
		for (int i = 0; i < conditions.size(); i++) {
			if (!conditions.get(i).evaluate(zork)) {
				return false;
			}
		}
		return true;

	}

}

// room
class ZorkRoom extends ZorkObject {
	public String name;
	public String type = "regular";
	public String description;
	public HashMap<String, String> border = new HashMap<>();
	public HashMap<String, String> container = new HashMap<>();
	public HashMap<String, String> item = new HashMap<>();
	public HashMap<String, String> creature = new HashMap<>();

	public ZorkRoom() {

	}
}

// item
class ZorkItem extends ZorkObject {
	public String name;
	public String description;
	public String writing;
	public ArrayList<String> turnOnAction = new ArrayList<>();
	public ArrayList<String> turnOnPoint = new ArrayList<>();

	public ZorkItem() {

	}
}

// container
class ZorkContainer extends ZorkObject {
	public String name;
	public String description;
	boolean isOpen;
	public ArrayList<String> accept = new ArrayList<>();
	public HashMap<String, String> item = new HashMap<>();

	public ZorkContainer() {

	}
}

// creature
class ZorkCreature extends ZorkObject {
	public String name;
	public String description;
	public HashMap<String, String> vulnerability = new HashMap<>();
	public ArrayList<ZorkCondition> conditions = new ArrayList<ZorkCondition>();
	public ArrayList<String> print = new ArrayList<>();
	public ArrayList<String> action = new ArrayList<>();

	public ZorkCreature() {

	}

	// to evaluate status of attack
	public boolean attack(Engine zork, String weapon) {
		if (vulnerability.get(weapon) == null) {
			return false;
		}
		for (int i = 0; i < conditions.size(); i++) {
			if (!conditions.get(i).evaluate(zork)) {
				return false;
			}
		}
		return true;
	}
}

public class Engine {
	Scanner source = new Scanner(System.in);
	public String userInput = "";

	public HashMap<String, ZorkItem> Items = new HashMap<>();
	public HashMap<String, ZorkRoom> Rooms = new HashMap<>();
	public HashMap<String, ZorkContainer> Containers = new HashMap<>();
	public HashMap<String, ZorkCreature> Creatures = new HashMap<>();
	public HashMap<String, ZorkObject> Objects = new HashMap<>();
	public HashMap<String, String> Inventory = new HashMap<>();
	public HashMap<String, String> ObjectLookup = new HashMap<>();
	public String currentRoom;
	public File file;

	public Engine(String filename) {
		int i, j, k, l, x, y, z;
		file = new File(filename);
		if (!file.canRead()) {
			System.out.println("error opening file... exiting");
			return;
		}
		
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc= builder.parse(file);
			Element rootElement = doc.getDocumentElement();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {

	}

}
