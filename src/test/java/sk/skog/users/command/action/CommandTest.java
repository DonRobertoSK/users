package sk.skog.users.command.action;

import org.junit.Test;
import org.mockito.Mockito;

public class CommandTest {
	
	@Test
	public void test() {
		Command command = Mockito.mock(Command.class);
		command.execute();
		Mockito.verify(command, Mockito.times(1)).executeCommand();
	}

}
