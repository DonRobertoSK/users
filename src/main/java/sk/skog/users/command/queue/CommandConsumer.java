package sk.skog.users.command.queue;

import java.util.concurrent.BlockingQueue;

import sk.skog.users.command.action.Command;
import sk.skog.users.command.action.EndCommand;

/**
 * {@link sk.skog.user.command.action.Command} consumer that consumes commands from
 * shared queue and executes them. It finishes when takes
 * {#link sk.skog.user.command.action.EndCommand} from shared queue.
 * 
 * @author Robert Kristofic
 */
public class CommandConsumer extends Thread {
	private BlockingQueue<Command> sharedQueue;
	
	public CommandConsumer(BlockingQueue<Command> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Command command = sharedQueue.take();
				if (command instanceof EndCommand) {
					break;
				}
				command.execute();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

}
