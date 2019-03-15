package stream.processing.basic.streamprocessingbasic;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
@EnableBinding(KafkaStreamsProcessor.class)
public class StreamProcessingBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamProcessingBasicApplication.class, args);
	}

	@StreamListener("input")
	@SendTo("output")
	public KStream<?, String> process(KStream<?, String> input) {
		return input.peek((k, v) -> System.out.println("Value received: " + v));
	}
}
