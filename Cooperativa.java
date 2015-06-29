public class Cooperativa extends Thread {
	Monitor m;

	public Cooperativa(Monitor m) {
		this.m = m;
	}

	public void run() {
		// A cooperativa pega os passageiros na ordem da entrada.
		try {
			for(Passageiro passageiro = m.proximo_passageiro(); passageiro != null; passageiro = m.proximo_passageiro()) {				
				Taxi taxi = m.taxi_mais_proximo(passageiro.xi, passageiro.yi);
				
				// Indo até o passageiro.
				System.out.println("Taxi #" + taxi.id + " sai de (" + taxi.x + ", " + taxi.y + ") para buscar o passageiro #" + passageiro.id + " lá em (" + passageiro.xi + ", " + passageiro.yi + ")");
				int dst  = taxi.calcula_distancia(passageiro.xi, passageiro.yi);
				sleep(dst*100);
				taxi.x = passageiro.xi;
				taxi.y = passageiro.yi;
				
				// Levando o passageiro até o destino.
				System.out.println("Taxi #" + taxi.id + " sai de (" + taxi.x + ", " + taxi.y + ") para levar o passageiro #" + passageiro.id + " para (" + passageiro.xf + ", " + passageiro.yf + ")");
				dst = taxi.calcula_distancia(passageiro.xf, passageiro.yf);
				sleep(dst*100);
				taxi.x = passageiro.xf;
				taxi.y = passageiro.yf;
				
			System.out.println("Passageiro #" + passageiro.id + " chegou a (" + taxi.x + ", " + taxi.y + "), liberando o taxi #" + taxi.id + ".");
				m.notifica(taxi);
			}
		} catch(InterruptedException e) { return; }
	}
}
