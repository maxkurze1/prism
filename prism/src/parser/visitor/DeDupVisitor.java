package parser.visitor;

import parser.ast.Expression;
import parser.ast.ExpressionBinaryOp;
import parser.ast.ExpressionConstant;
import parser.ast.ExpressionFormula;
import parser.ast.ExpressionFunc;
import parser.ast.ExpressionITE;
import parser.ast.ExpressionLabel;
import parser.ast.ExpressionLiteral;
import parser.ast.ExpressionObs;
import parser.ast.ExpressionUnaryOp;
import parser.ast.ExpressionVar;
import prism.PrismLangException;

import java.util.HashMap;
import java.util.Map;

/**
 * This visitor checks all visited notes of type {@link parser.ast.Expression}
 * (in case they are a proposition) for duplicates.
 * Duplicates are then visited only once.
 *
 * By default, it uses a normal HashMap (see {@link DeDupVisitor#DeDupVisitor()}) to dedup the Proposition
 * and an IdentityHashMap (see {@link DAGVisitor#DAGVisitor()}) for every other expression.
 */
public abstract class DeDupVisitor extends DAGVisitor
{
	protected Map<Expression, Object> deDupVisited;

	protected DeDupVisitor()
	{
		this(new HashMap<>());
	}

	protected DeDupVisitor(Map<Expression, Object> visited)
	{
		if (visited == null)
			throw new IllegalArgumentException("map of visited notes must not be null");
		this.deDupVisited = visited;
	}

	protected boolean isVisited(Expression element)
	{
		return deDupVisited.containsKey(element);
	}

	protected Object getStored(Expression element)
	{
		return deDupVisited.get(element);
	}

	protected Object store(Expression element, Object result)
	{
		deDupVisited.put(element, result);
		return result;
	}

	/* ========== Types that MIGHT be propositions ========== */

	@Override
	public Object visit(ExpressionBinaryOp e) throws PrismLangException
	{
		if (e.isProposition())
			return isVisited(e) ? getStored(e) : store(e, visitNow(e));
		else
			return super.visit(e);
	}

	@Override
	public Object visit(ExpressionFormula e) throws PrismLangException
	{
		if (e.isProposition())
			return isVisited(e) ? getStored(e) : store(e, visitNow(e));
		else
			return super.visit(e);
	}

	@Override
	public Object visit(ExpressionFunc e) throws PrismLangException
	{
		if (e.isProposition())
			return isVisited(e) ? getStored(e) : store(e, visitNow(e));
		else
			return super.visit(e);
	}

	@Override
	public Object visit(ExpressionITE e) throws PrismLangException
	{
		if (e.isProposition())
			return isVisited(e) ? getStored(e) : store(e, visitNow(e));
		else
			return super.visit(e);
	}

	@Override
	public Object visit(ExpressionUnaryOp e) throws PrismLangException
	{
		if (e.isProposition())
			return isVisited(e) ? getStored(e) : store(e, visitNow(e));
		else
			return super.visit(e);
	}

	/* ========== Types that ALWAYS are be propositions ========== */

	@Override
	public Object visit(ExpressionConstant e) throws PrismLangException
	{
		assert e.isProposition();
		return isVisited(e) ? getStored(e) : store(e, visitNow(e));
	}

	@Override
	public Object visit(ExpressionLabel e) throws PrismLangException
	{
		assert e.isProposition();
		return isVisited(e) ? getStored(e) : store(e, visitNow(e));
	}

	@Override
	public Object visit(ExpressionLiteral e) throws PrismLangException
	{
		assert e.isProposition();
		return isVisited(e) ? getStored(e) : store(e, visitNow(e));
	}

	@Override
	public Object visit(ExpressionObs e) throws PrismLangException
	{
		assert e.isProposition();
		return isVisited(e) ? getStored(e) : store(e, visitNow(e));
	}

//	Somehow destroys ClockTyp ExpressionVars and creates Timelock
//	@Override
//	public Object visit(ExpressionVar e) throws PrismLangException
//	{
//		assert e.isProposition();
//		return isVisited(e) ? getStored(e) : store(e, visitNow(e));
//	}
}
