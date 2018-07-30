package kafka;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kafka.producer.Consumer;
import kafka.producer.Producer;
import kafka.producer.QueueOut;

@EnableBinding({ QueueOut.class })
@SpringBootApplication(scanBasePackages = "kafka.producer")
@Configuration
public class AppProducer implements ApplicationListener<SpringApplicationEvent> {
	public static Producer producer;
	public static String serverId;

	static int kcount = 10;
	static int icount = 100;
	static int size = 1024;

	@Autowired
	public void setProducer(Producer producer) {
		AppProducer.producer = producer;
	}

	public static void main(String[] args) {
		kcount = Integer.parseInt(args[0]);
		icount = Integer.parseInt(args[1]);
		size = Integer.parseInt(args[2]);
		serverId = UUID.randomUUID().toString();
		SpringApplication.run(AppProducer.class, args);
	}

	// @Bean
	public Consumer consumer() {
		return new Consumer();
	}

	@Bean
	public Producer producer() {
		return new Producer();
	}

	public void onApplicationEvent(SpringApplicationEvent arg0) {
		SpringApplication sa = (SpringApplication) arg0.getSource();
		Iterator<?> i = sa.getSources().iterator();
		if (i.hasNext() && i.next().equals(AppProducer.class)) {
			List<T> tl = new ArrayList<T>();
			for (int k = 0; k < kcount; k++) {
				T t = new T(k, icount, size);
				tl.add(t);
			}
			for (T t : tl) {
				t.start();
			}
		}
	}
}

class T extends Thread {
	int icount = 100;
	int k = 0;
	int size = 1024;

	public T(int k, int icount, int size) {
		this.icount = icount;
		this.k = k;
		this.size = size;
	}

	public void run() {
		if (AppProducer.producer == null) {
			return;
		}
		int i = 1;
		while (true) {
			AppProducer.producer.sentOne("" + k, i++, size);
			if (i > icount) {
				break;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Server[" + AppProducer.serverId + "]-[" + k + "] Sent Done-------- total sent:"
				+ AppProducer.producer);
	}
}
