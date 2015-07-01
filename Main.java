import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	static final int nthreads = Runtime.getRuntime().availableProcessors();
	
	public static void main(String[] args) {

		Scanner s = null;
		
		if(args.length > 0) {
			try { 
				s = new Scanner(new File(args[0])); 
			} catch(FileNotFoundException e) { 
				System.out.println("File not found :(");
				s = new Scanner(System.in);
			}
		} else {
			s = new Scanner(System.in);
		}
		
		// Lendo as dimensões do mapa.
		int N = s.nextInt();
//		System.out.println(N);
		int M = s.nextInt();
//		System.out.println(M);

		// Lendo as informações dos passageiros.
		int P = s.nextInt();
//		System.out.println(P);
		
		Passageiro[] passageiros = new Passageiro[P];
		
		for(int i = 0; i < P; ++i) {
			passageiros[i] = new Passageiro(i,s.nextInt(),s.nextInt(),s.nextInt(),s.nextInt());
//			System.out.println(passageiros[i].xi + " " + passageiros[i].yi + " " + passageiros[i].xf + " " + passageiros[i].yf);
		}
		
		// Lendo as coordenadas iniciais dos taxistas.
		int T = s.nextInt();
//		System.out.println(T);
		Taxi[] taxis = new Taxi[T];
		
		for(int i = 0; i < T; ++i) {
			taxis[i] = new Taxi (i, s.nextInt(), s.nextInt());
//			System.out.println(taxis[i].x + " " + taxis[i].y);
		}
		
		// Iniciando o monitor e threads
		Monitor m = new Monitor(M,N,taxis);
		Chamada ch = new Chamada(passageiros,m);
		Cooperativa[] coop = new Cooperativa[nthreads];
		ch.run();
		for(int i = 0; i < nthreads; ++i) {
			coop[i] = new Cooperativa(m);
			coop[i].start();
		}
		
		// Imprimindo a posição final dos táxis.
		try {
			for (Thread t : coop) {
				t.join();
			}
			System.out.println("eu chego aqui");
			ch.join();
		} catch (InterruptedException e) { return; }
		
		System.out.println("");
		for (Taxi t : taxis) {
			System.out.println(t.x + " " + t.y);
		}
		
		// Encerrando o programa
		s.close();
	}

}
