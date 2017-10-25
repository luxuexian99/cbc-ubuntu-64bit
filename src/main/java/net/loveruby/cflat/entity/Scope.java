package net.loveruby.cflat.entity;
import net.loveruby.cflat.exception.*;
import java.util.List;
import java.util.ArrayList;

/**
 * 作用域
 */
abstract public class Scope {
    protected List<LocalScope> children;

    public Scope() {
        children = new ArrayList<LocalScope>();
    }

    public abstract boolean isTopLevel();
    public abstract TopLevelScope topLevel();
    public abstract Scope parent();

    protected void addChild(LocalScope s) {
        children.add(s);
    }

    abstract public Entity get(String name) throws SemanticException;
}
