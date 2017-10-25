package net.loveruby.cflat.compiler;

import java.util.Map;
import java.util.HashMap;

/**
 * 编译模式
 */
enum CompilerMode {
    // 检查语法
    CheckSyntax("--check-syntax"),
    // 导出符合
    DumpTokens("--dump-tokens"),
    // 导出抽象语法树
    DumpAST("--dump-ast"),
    // 导出语句
    DumpStmt("--dump-stmt"),
    // 导出表达式
    DumpExpr("--dump-expr"),
    // 导出语言
    DumpSemantic("--dump-semantic"),
    // 导出引用
    DumpReference("--dump-reference"),
    // 导出中间代码
    DumpIR("--dump-ir"),
    // 导出ASM
    DumpAsm("--dump-asm"),
    // 打印ASM
    PrintAsm("--print-asm"),
    // 编译
    Compile("-S"),
    // 组装
    Assemble("-c"),
    // 链接
    Link("--link");

    static private Map<String, CompilerMode> modes;

    static {
        modes = new HashMap<>();
        modes.put("--check-syntax", CheckSyntax);
        modes.put("--dump-tokens", DumpTokens);
        modes.put("--dump-ast", DumpAST);
        modes.put("--dump-stmt", DumpStmt);
        modes.put("--dump-expr", DumpExpr);
        modes.put("--dump-semantic", DumpSemantic);
        modes.put("--dump-reference", DumpReference);
        modes.put("--dump-ir", DumpIR);
        modes.put("--dump-asm", DumpAsm);
        modes.put("--print-asm", PrintAsm);
        modes.put("-S", Compile);
        modes.put("-c", Assemble);
    }

    public static boolean isModeOption(String opt) {
        return modes.containsKey(opt);
    }

    public static CompilerMode fromOption(String opt) {
        CompilerMode m = modes.get(opt);
        if (m == null) {
            throw new Error("must not happen: unknown mode option: " + opt);
        }
        return m;
    }

    private final String option;

    CompilerMode(String option) {
        this.option = option;
    }

    public String toOption() {
        return option;
    }

    boolean requires(CompilerMode m) {
        return ordinal() >= m.ordinal();
    }
}
