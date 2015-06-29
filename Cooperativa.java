
public class Cooperativa extends Thread{
	Monitor m;

	public Cooperativa(Monitor m) {
		this.m = m;
	}
	
	public void run() {
		// A cooperativa pega os passageiros na ordem da entrada.
		try {
		while (m.idx_ultimo_passageiro < m.passageiros.length) {
			Passageiro passageiro = m.passageiros[++m.idx_ultimo_passageiro];
			Taxi taxi = m.taxi_mais_proximo(passageiro.xi, passageiro.yi);
			
			// Indo até o passageiro.
			int dst  = taxi.calcula_distancia(passageiro.xi, passageiro.yi);
			taxi.x = passageiro.xi;
			taxi.y = passageiro.yi;
			sleep(dst);
			
			// Levando o passageiro até o destino.
			dst = taxi.calcula_distancia(passageiro.xf, passageiro.yf);
			sleep(dst);
		}
		} catch(InterruptedException e) { return; }
	}
}
