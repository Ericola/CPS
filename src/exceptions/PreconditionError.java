package exceptions;


public class PreconditionError extends ContractError {

	private static final long serialVersionUID = 1L;

	public PreconditionError(String message) {
		super("PreconditionError : " + message);
	}
}
