package sk.skog.users.command.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

import sk.skog.users.command.action.AddCommand;
import sk.skog.users.command.action.Command;
import sk.skog.users.command.action.DeleteAllCommand;
import sk.skog.users.command.action.EndCommand;
import sk.skog.users.command.action.PrintAllCommand;

public class SampleCommandProducerTest {
	
	@Test
	public void test() throws InterruptedException {
		BlockingQueue<Command> sharedQueue = new LinkedBlockingQueue<>();
		SampleCommandsProducer sampleCommandsProducer = new SampleCommandsProducer(sharedQueue);
		sampleCommandsProducer.run();
		testExpectedClass(AddCommand.class, sharedQueue);
		testExpectedClass(AddCommand.class, sharedQueue);
		testExpectedClass(PrintAllCommand.class, sharedQueue);
		testExpectedClass(DeleteAllCommand.class, sharedQueue);
		testExpectedClass(PrintAllCommand.class, sharedQueue);
		testExpectedClass(EndCommand.class, sharedQueue);
		Assert.assertTrue(sharedQueue.isEmpty());
	}
	
	private void testExpectedClass(Class<? extends Command> commandClass, BlockingQueue<Command> sharedQueue) throws InterruptedException {
		Assert.assertEquals(commandClass, sharedQueue.take().getClass());
	}

}
