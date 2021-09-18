package sk.skog.users.command.action;

/**
 * Generic command.
 * 
 * @author Robert Kristofic
 */
public abstract class Command {
	
	public final void execute() {
		System.out.printf("-> Executing %s\n", this.getClass().getSimpleName());
		
		executeCommand();
	}
	
	protected abstract void executeCommand();
	
}
