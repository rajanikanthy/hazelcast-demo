package demo;

public class App {
	public static void main(String[] args) {
		ConcurrentMapWithStats cs = new ConcurrentMapWithStats();
		MyItemEventListener listener = new MyItemEventListener();
		cs.registerItemListener(listener);
		for(long i = 0; i < 10000; i++) {
			cs.put(i, i * i);
		}
		listener.printTransactionsPerSecond();
	}
}
