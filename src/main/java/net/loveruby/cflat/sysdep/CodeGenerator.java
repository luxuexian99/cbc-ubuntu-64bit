package net.loveruby.cflat.sysdep;

/**
 * 代码生成器
 */
public interface CodeGenerator {
    AssemblyCode generate(net.loveruby.cflat.ir.IR ir);
}
