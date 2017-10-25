package net.loveruby.cflat.ast;
import java.io.PrintStream;

/**
 * <p>
 * 节点
 * </p>
 * 主要用在节点有关的数据结构上。
 */
public abstract class Node implements Dumpable {
    public Node() {
    }

    abstract public Location location();

    public void dump() {
        dump(System.out);
    }

    public void dump(PrintStream s) {
        dump(new Dumper(s));
    }

    public void dump(Dumper d) {
        d.printClass(this, location());
        _dump(d);
    }

    abstract protected void _dump(Dumper d);
}
