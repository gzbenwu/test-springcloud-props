package kafka.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface QueueOut {
	public static final String output = "OUTPUT";

	@Output(output)
	public MessageChannel out();
}
