
public class Chamada extends Thread {
	private Passageiro[] passageiros; 
	private Monitor m;
	
	public Chamada(Passageiro[] p, Monitor m) { 
		passageiros = p;
		this.m = m;
	}
	
	public void run() {
		for(Passageiro p : passageiros) {
			m.chamada(p);
		}
		m.encerra_expediente();
	}
}
