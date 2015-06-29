public class Monitor {

	public Passageiro[] passageiros;
	public Taxi[] taxis;
	public int idx_ultimo_passageiro;
	public int n_taxis_disponiveis;
	
	public Monitor(int N, int M, Passageiro[] p, Taxi[] t) {
		this.passageiros = p;
		this.taxis = t;
		this.idx_ultimo_passageiro = -1;
		this.n_taxis_disponiveis = t.length;
	}
	
	public synchronized Passageiro proximo_passageiro() {
		if(idx_ultimo_passageiro >= passageiros.length-1) return null;
		else return passageiros[++idx_ultimo_passageiro];
	}
	
	public synchronized Taxi taxi_mais_proximo(int x, int y) {
		int idx = -1;
		int dst = 8000;
		try {
			while (this.n_taxis_disponiveis == 0) wait();
			
			for (int i = 0; i < taxis.length; i++) {
				if (taxis[i].disponivel && taxis[i].calcula_distancia(x, y) < dst) {
					idx = i;
					dst = taxis[i].calcula_distancia(x, y);
				}
			}
			
			taxis[idx].setIndisponivel();
			this.n_taxis_disponiveis--;
			return taxis[idx];
		} catch (InterruptedException e) { return null; }
	}
	
	public synchronized void notifica(Taxi t) {
		t.setDisponivel();
		n_taxis_disponiveis++;
		notifyAll();
	}
}