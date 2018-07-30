package kafka.producer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface QueueIn {
	public static final String input = "INPUT";

	@Input(input)
	public SubscribableChannel in();
}
