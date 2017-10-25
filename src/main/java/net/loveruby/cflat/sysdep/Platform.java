package net.loveruby.cflat.sysdep;
import net.loveruby.cflat.type.TypeTable;
import net.loveruby.cflat.utils.ErrorHandler;

/**
 * <p>
 * 编译的平台（OS架构）
 * </p>
 * 如：Linux-x86, OSX-x64, Win-x86
 */
public interface Platform {
    /**
     * 类型表
     * @return 返回类型表
     */
    TypeTable typeTable();

    /**
     *
     * @param opts
     * @param h
     * @return
     */
    CodeGenerator codeGenerator(CodeGeneratorOptions opts, ErrorHandler h);
    Assembler assembler(ErrorHandler h);
    Linker linker(ErrorHandler h);
}
