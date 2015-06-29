import java.util.Scanner;

public class Monitor {

	private Passageiro[] passageiros;
	private Taxi[] taxis;
	
	public Monitor(int N, int M, Passageiro[] p, Taxi[] t) {
		this.passageiros = p;
		this.taxis = t;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		// Lendo as dimensões do mapa.
		int N = s.nextInt();
		int M = s.nextInt();

		// Lendo as informações dos passageiros.
		int P = s.nextInt();
		
		Passageiro[] passageiros = new Passageiro[P];
		
		for(int i = 0; i < P; ++i) {
			passageiros[i].xi = s.nextInt();
			passageiros[i].yi = s.nextInt();
			passageiros[i].xf = s.nextInt();
			passageiros[i].yf = s.nextInt();
		}
		
		// Lendo as coordenadas iniciais dos taxistas.
		int T = s.nextInt();
		Taxi[] taxis = new Taxi[T];
		
		for(int i = 0; i < T; ++i) {
			taxis[i].x = s.nextInt();
			taxis[i].y = s.nextInt();
		}
		
		// Iniciando o monitor, produtor(es) e consumidor(es)
		Monitor m = new Monitor(M,N,passageiros,taxis);
		
	}

}
