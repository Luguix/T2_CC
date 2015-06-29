public class Taxi {

	public int x, y;
	public Boolean disponivel = true;
	
	public Taxi(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int calcula_distancia(int x, int y) {
		return Math.abs(this.x - x) + Math.abs(this.y - y);
	}
	
}