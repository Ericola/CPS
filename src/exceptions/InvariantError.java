package exceptions;


public class InvariantError extends ContractError {

	private static final long serialVersionUID = 1L;

	public InvariantError(String message) {
		super("InvariantError : " + message);
	}
}
