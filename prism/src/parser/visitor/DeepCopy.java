//==============================================================================
//	
//	Copyright (c) 2022-
//	Authors:
//	* Max Kurze <Max.Kurze@mailbox.tu-dresden.de> (TU Dresden)
//	
//------------------------------------------------------------------------------
//	
//	This file is part of PRISM.
//	
//	PRISM is free software; you can redistribute it and/or modify
//	it under the terms of the GNU General Public License as published by
//	the Free Software Foundation; either version 2 of the License, or
//	(at your option) any later version.
//	
//	PRISM is distributed in the hope that it will be useful,
//	but WITHOUT ANY WARRANTY; without even the implied warranty of
//	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//	GNU General Public License for more details.
//	
//	You should have received a copy of the GNU General Public License
//	along with PRISM; if not, write to the Free Software Foundation,
//	Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//	
//==============================================================================

package parser.visitor;


import parser.ast.ASTElement;
import parser.ast.Command;
import parser.ast.ConstantList;
import parser.ast.Declaration;
import parser.ast.DeclarationArray;
import parser.ast.DeclarationBool;
import parser.ast.DeclarationClock;
import parser.ast.DeclarationInt;
import parser.ast.DeclarationIntUnbounded;
import parser.ast.ExpressionBinaryOp;
import parser.ast.ExpressionConstant;
import parser.ast.ExpressionExists;
import parser.ast.ExpressionFilter;
import parser.ast.ExpressionForAll;
import parser.ast.ExpressionFormula;
import parser.ast.ExpressionFunc;
import parser.ast.ExpressionITE;
import parser.ast.ExpressionIdent;
import parser.ast.ExpressionInterval;
import parser.ast.ExpressionLabel;
import parser.ast.ExpressionLiteral;
import parser.ast.ExpressionObs;
import parser.ast.ExpressionProb;
import parser.ast.ExpressionProp;
import parser.ast.ExpressionReward;
import parser.ast.ExpressionSS;
import parser.ast.ExpressionStrategy;
import parser.ast.ExpressionTemporal;
import parser.ast.ExpressionUnaryOp;
import parser.ast.ExpressionVar;
import parser.ast.Filter;
import parser.ast.ForLoop;
import parser.ast.FormulaList;
import parser.ast.LabelList;
import parser.ast.Module;
import parser.ast.ModulesFile;
import parser.ast.Observable;
import parser.ast.ObservableVars;
import parser.ast.PropertiesFile;
import parser.ast.Property;
import parser.ast.RenamedModule;
import parser.ast.RewardStruct;
import parser.ast.RewardStructItem;
import parser.ast.SystemBrackets;
import parser.ast.SystemFullParallel;
import parser.ast.SystemHide;
import parser.ast.SystemInterleaved;
import parser.ast.SystemModule;
import parser.ast.SystemParallel;
import parser.ast.SystemReference;
import parser.ast.SystemRename;
import parser.ast.Update;
import parser.ast.UpdateElement;
import parser.ast.Updates;
import prism.PrismLangException;

import java.util.List;
import java.util.ListIterator;

/**
 * DeepCopy is a visitor that copies an AST without duplicating nodes.
 * <br>
 * After certain replacements, e.g., formula substitution,
 * the AST becomes a directed acyclic graph.
 * DeepCopy preserves that structure on copying to prevent
 * a (potentially exponential) blowup in the number of nodes.
 * <br>
 * The implementation uses an equality-based map to identify duplicates.
 * For copying, it provides the methods {@link DeepCopy#copy} and {@link DeepCopy#copyAll} and relies
 * on {@link ASTElement#deepCopy(DeepCopy)}.
 */
public class DeepCopy extends DeDupVisitor
{
	/**
	 * Copy an ASTElement or null.
	 *
	 * @param element the element to be copied or null
	 * @return copy of the element or null
	 * @throws PrismLangException
	 */
	@SuppressWarnings("unchecked")
	public <T extends ASTElement> T copy(T element) throws PrismLangException
	{
		return (element == null) ? null : (T) element.accept(this);
	}

	/**
	 * Copy all ASTElements (or null) in the collection.
	 *
	 * @param list list of elements to be copied
	 * @return the argument list with all elements copied
	 * @throws PrismLangException
	 */
	public <T extends ASTElement> List<T> copyAll(List<T> list) throws PrismLangException
	{
		if (list == null)
			return null;

		ListIterator<T> iter = list.listIterator();
		while (iter.hasNext()) {
			iter.set(copy(iter.next()));
		}
		return list;
	}

	@Override
	public Object visitNow(ModulesFile e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(PropertiesFile e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(Property e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(FormulaList e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(LabelList e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ConstantList e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(Declaration e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(DeclarationInt e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(DeclarationBool e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(DeclarationArray e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(DeclarationClock e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(DeclarationIntUnbounded e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(Module e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(Command e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(Updates e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(Update e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(UpdateElement e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(RenamedModule e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(RewardStruct e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(RewardStructItem e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ObservableVars e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(Observable e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(SystemInterleaved e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(SystemFullParallel e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(SystemParallel e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(SystemHide e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(SystemRename e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(SystemModule e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(SystemBrackets e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(SystemReference e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionTemporal e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionITE e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionBinaryOp e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionUnaryOp e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionFunc e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionIdent e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionLiteral e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionConstant e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionFormula e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionVar e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionInterval e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionProb e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionReward e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionSS e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionExists e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionForAll e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionStrategy e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionLabel e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionObs e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionProp e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ExpressionFilter e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(Filter e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}

	@Override
	public Object visitNow(ForLoop e) throws PrismLangException
	{
		return e.clone().deepCopy(this);
	}
}
