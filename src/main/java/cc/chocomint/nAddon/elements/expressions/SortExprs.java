package cc.chocomint.nAddon.elements.expressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.google.common.collect.Lists;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.Variable;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.Pair;

public class SortExprs extends SimpleExpression<String> {
	
	private Variable<Number> ex_variables;
	private Expression<String> ex_format;
	
	private int pattern;
	
	static {
		Skript.registerExpression(SortExprs.class, String.class, ExpressionType.SIMPLE, "sorted %numbers% from highest to lowest with (output|format) %string%", "sorted %numbers% from lowest to highest with (output|format) %string%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {

		this.ex_variables = (Variable<Number>) exprs[0];
		this.ex_format = (Expression<String>) exprs[1];
		
		this.pattern = matchedPattern;
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "SortExprs";
	}

	@Override
	protected @Nullable String[] get(Event e) {
		
		String format = (String) this.ex_format.getSingle(e);
		Iterator<Pair<String, Object>> iterator = this.ex_variables.variablesIterator(e);
		List<Pair<String, Object>> list = new ArrayList<>();
		
		while(iterator.hasNext()) {
			list.add(iterator.next());
		}
		list.sort(Comparator.comparing(p -> Double.valueOf(p.getValue().toString())));
		List<String> out_list = new ArrayList<>();
		
		for(Pair<String, Object> content : list) {
			out_list.add(format.replace("@value", content.getValue() + "").replace("@index", (CharSequence) content.getKey()));
		}
		if(this.pattern == 0) {
			return (String[]) Lists.reverse(out_list).toArray((Object[]) new String[out_list.size()]);
		} else if(this.pattern == 1) {
			return out_list.<String>toArray(new String[out_list.size()]);
		}
		
		return null;
	}

}
