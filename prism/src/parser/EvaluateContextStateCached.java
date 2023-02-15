package parser;

import parser.ast.Expression;

import java.util.IdentityHashMap;
import java.util.function.Predicate;

public class EvaluateContextStateCached extends EvaluateContextState
{
	protected IdentityHashMap<Expression, Object> cache = new IdentityHashMap<>();

	public EvaluateContextStateCached(State state)
	{
		super(state);
	}

	public EvaluateContextStateCached(Values constantValues, State state)
	{
		super(constantValues, state);
	}

	public EvaluateContextStateCached(Values constantValues, Predicate<String> labelValues, State state)
	{
		super(constantValues, labelValues, state);
	}

	public Object fetchResult(Expression expression)
	{
		return cache.get(expression);
	}

	public Object storeResult(Expression expression, Object result)
	{
		cache.put(expression, result);
		return result;
	}
}
