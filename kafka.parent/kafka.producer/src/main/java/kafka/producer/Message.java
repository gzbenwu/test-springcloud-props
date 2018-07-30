package kafka.producer;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 4043415508951281221L;
	private String key;
	private Integer num;
	private Date time;
	private byte[] body;

	public Message(String key, Integer num, int size) {
		super();
		this.key = key;
		this.num = num;
		this.time = new Date();
		this.body = new byte[size];
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Message [key=" + key + ", num=" + num + ", time=" + time + "]";
	}
}
