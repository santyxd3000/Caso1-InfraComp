package caso1;

public class IDGenerator {
	private int currentId;
	
	public IDGenerator() {
		currentId = 0;
	}
	
	public synchronized int getNextId() {
		int id = currentId;
		currentId++;
		return id;
	}

}
