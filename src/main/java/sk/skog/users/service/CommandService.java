package sk.skog.users.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import sk.skog.users.command.action.Command;
import sk.skog.users.command.queue.CommandConsumer;
import sk.skog.users.command.queue.SampleCommandsProducer;

public class CommandService {

	public void fireSampleCommands() throws InterruptedException {
		BlockingQueue<Command> sharedQueue = new LinkedBlockingQueue<>();

		Thread producer = new SampleCommandsProducer(sharedQueue);
		Thread consumer = new CommandConsumer(sharedQueue);

		try {
			producer.start();
			consumer.start();

			producer.join();
			consumer.join();
		} finally {
			PersistenceService.releaseResources();
		}
	}

}
