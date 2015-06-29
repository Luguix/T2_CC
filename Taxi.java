public class Taxi {

	public int id;
	public int x, y;
	public Boolean disponivel = true;
	
	public Taxi(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public int calcula_distancia(int x, int y) {
		return Math.abs(this.x - x) + Math.abs(this.y - y);
	}

	public void setDisponivel() {
		this.disponivel = true;
	}

	public void setIndisponivel() {
		this.disponivel = false;
	}
	
}