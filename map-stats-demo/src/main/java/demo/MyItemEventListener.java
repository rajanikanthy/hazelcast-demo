package demo;

import java.util.concurrent.atomic.AtomicLong;

public class MyItemEventListener implements ItemListener {
	
	private Long opStartTime = null;
	private AtomicLong transactionCount = new AtomicLong();
	private Long elapsedTime = 0L;
	
	@Override
	public void before(ItemEvent event) {
		opStartTime = System.currentTimeMillis();
	}

	@Override
	public void after(ItemEvent event) {
		transactionCount.incrementAndGet();
		elapsedTime = elapsedTime + (System.currentTimeMillis() - opStartTime);
	}
	
	public void printTransactionsPerSecond() {
		System.out.println("Transactions : " + transactionCount.get());
		System.out.println("Elapsed Time: " + elapsedTime);
		System.out.println("Transaction Per Millisecond: " + (transactionCount.get() / elapsedTime ));
	}

}
