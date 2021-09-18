package sk.skog.users.command.queue;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import sk.skog.users.command.action.AddCommand;
import sk.skog.users.command.action.Command;
import sk.skog.users.command.action.DeleteAllCommand;
import sk.skog.users.command.action.EndCommand;
import sk.skog.users.command.action.PrintAllCommand;
import sk.skog.users.data.User;

/**
 * {@link java.lang.Thread} that puts sample commands defined in a task description
 * into the shared queue.
 *  
 * @author Robert Kristofic
 */
public class SampleCommandsProducer extends Thread {
	private BlockingQueue<Command> sharedQueue;
	
	public SampleCommandsProducer(BlockingQueue<Command> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}
	
	@Override
	public void run() {
		sharedQueue.addAll(Arrays.asList(
			new AddCommand(new User(1, "a1", "Robert")),
			new AddCommand(new User(2, "a2", "Martin")),
			new PrintAllCommand(User.class),
			new DeleteAllCommand(User.class),
			new PrintAllCommand(User.class),
			new EndCommand()));
	}

}
