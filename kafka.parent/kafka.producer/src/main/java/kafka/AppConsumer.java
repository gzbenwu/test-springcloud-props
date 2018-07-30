package kafka;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kafka.producer.Consumer;
import kafka.producer.Producer;
import kafka.producer.QueueIn;

@EnableBinding({ QueueIn.class })
@SpringBootApplication(scanBasePackages = "kafka.producer")
@Configuration
public class AppConsumer implements ApplicationListener<SpringApplicationEvent> {
	public static Producer producer;
	public static String serverId;

	static int kcount = 10;
	static int icount = 100;

	// @Autowired
	public void setProducer(Producer producer) {
		AppConsumer.producer = producer;
	}

	public static void main(String[] args) {
		// kcount = Integer.parseInt(args[0]);
		// icount = Integer.parseInt(args[1]);
		serverId = UUID.randomUUID().toString();
		SpringApplication.run(AppConsumer.class, args);
	}

	@Bean
	public Consumer consumer() {
		return new Consumer();
	}

	// @Bean
	public Producer producer() {
		return new Producer();
	}

	public void onApplicationEvent(SpringApplicationEvent arg0) {
		// SpringApplication sa = (SpringApplication) arg0.getSource();
		// Iterator<?> i = sa.getSources().iterator();
		// if (i.hasNext() && i.next().equals(AppConsumer.class)) {
		// List<T> tl = new ArrayList<T>();
		// for (int k = 0; k < kcount; k++) {
		// T t = new T(k, icount);
		// tl.add(t);
		// }
		// for (T t : tl) {
		// t.start();
		// }
		// }
	}
}
