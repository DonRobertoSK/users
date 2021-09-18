package sk.skog.users.command.queue;

import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

import sk.skog.users.command.action.AddCommand;
import sk.skog.users.command.action.Command;
import sk.skog.users.command.action.DeleteAllCommand;
import sk.skog.users.command.action.EndCommand;
import sk.skog.users.command.action.PrintAllCommand;

public class CommandConsumerTest {
	
	@Test
	public void test() {
		BlockingQueue<Command> sharedQueue = new LinkedBlockingQueue<>();
		sharedQueue.addAll(Arrays.asList(
				mock(AddCommand.class),
				mock(AddCommand.class),
				mock(PrintAllCommand.class),
				mock(DeleteAllCommand.class),
				mock(PrintAllCommand.class),
				mock(EndCommand.class)));
		CommandConsumer commandConsumer = new CommandConsumer(sharedQueue);
		commandConsumer.run();
		Assert.assertTrue(sharedQueue.isEmpty());
	}

}
