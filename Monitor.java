public class Monitor {

	public Passageiro[] passageiros;
	public Taxi[] taxis;
	public int idx_ultimo_passageiro;
	
	public Monitor(int N, int M, Passageiro[] p, Taxi[] t) {
		this.passageiros = p;
		this.taxis = t;
	}
	
	public synchronized Taxi taxi_mais_proximo(int x, int y) {
		int idx = 0;
		int dst = 8000;
		
		for (int i = 0; i < taxis.length; i++) {
			if (taxis[i].disponivel && taxis[i].calcula_distancia(x, y) < dst) {
				idx = i;
				dst = taxis[i].calcula_distancia(x, y);
			}
		}
		
		return taxis[idx];
	}
}