package training;

public class Main {

	public static void main(String[] args) {		
		final int shipCount = 3;
		Ship ship;
		
		try(Dockyard dockyard = new Dockyard(2)) {
			for (int i = 0; i < shipCount; i++) {
				ship = new Ship("Ship-" + i, 10, 500);
				dockyard.unloadShip(ship);
			}
		}
	}
}