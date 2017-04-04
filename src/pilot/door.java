package pilot;

public class door {
	String name;
	lock l;
	doorScrew ds;

	public door(String name, lock l, doorScrew ds) {
		this.ds = ds;
		this.l = l;
		this.name = name;
	}
}
