package kafka.producer;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.cloud.stream.annotation.StreamListener;

import kafka.AppConsumer;

public class Consumer {
	Map<String, Integer> consumed = new HashMap<String, Integer>();
	Integer i = 0;

	@StreamListener(QueueIn.input)
	public void process(Message msg) throws Exception {
		System.out.println("Server[" + AppConsumer.serverId + "] Received:" + msg);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Integer num = consumed.get(msg.getKey());
		if (num == null) {
			consumed.put(msg.getKey(), msg.getNum());
		} else {
			if (num >= msg.getNum()) {
				System.out.println("!!!!! table:" + msg.getKey() + " num:" + msg.getNum() + " lastnum:" + num);
				return;
			} else {
				consumed.put(msg.getKey(), msg.getNum());
			}
		}
		i++;
	}

	@PreDestroy
	public void fl() throws Throwable {
		super.finalize();
		System.out.println(
				"Server[" + AppConsumer.serverId + "] consumed tables:" + consumed.keySet() + " total msg:" + i);
	}
}
