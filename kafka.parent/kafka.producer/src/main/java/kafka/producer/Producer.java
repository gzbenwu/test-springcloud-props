package kafka.producer;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;

public class Producer {
	@Autowired
	private QueueOut queue;

	AtomicInteger ai = new AtomicInteger(0);

	public void sentOne(String key, Integer num, int size) {
		boolean succe = queue.out().send(MessageBuilder.withPayload(new Message(key, num, size)).build());
		if (succe) {
			ai.incrementAndGet();
		}
	}

	@Override
	public String toString() {
		return "" + ai.get();
	}
}
