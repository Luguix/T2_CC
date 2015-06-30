import java.util.concurrent.ConcurrentLinkedQueue;



public class Monitor {

	private ConcurrentLinkedQueue<Passageiro> passageiros;
	private Taxi[] taxis;
	private boolean expediente = true;
	private int n_taxis_disponiveis;
	
	
	public Monitor(int N, int M, Taxi[] t) {
		this.taxis = t;
		this.passageiros = new ConcurrentLinkedQueue<Passageiro>();
		this.n_taxis_disponiveis = t.length;
	}
	
	public synchronized void chamada(Passageiro p) {
		passageiros.add(p);
		notifyAll();
	}
	
	public synchronized Passageiro proximo_passageiro() {
		while(expediente && passageiros.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(expediente || !passageiros.isEmpty()) return passageiros.poll();
		else return null;
	}
	
	public synchronized void encerra_expediente() {
		this.expediente = false;
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