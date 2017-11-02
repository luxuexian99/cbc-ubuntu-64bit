package net.loveruby.cflat.sysdep;

import net.loveruby.cflat.exception.IPCException;

import java.util.List;

/**
 * 链接器
 */
public interface Linker {
    /**
     * 生成可执行文件
     *
     * @param args     参数
     * @param destPath 目标目录
     * @param options  配置选项
     * @throws IPCException IPCException
     */
    void generateExecutable(List<String> args, String destPath, LinkerOptions options) throws IPCException;

    /**
     * 生成共享包
     *
     * @param args     参数
     * @param destPath 目标目录
     * @param options  配置选项
     * @throws IPCException IPCException
     */
    void generateSharedLibrary(List<String> args, String destPath, LinkerOptions options) throws IPCException;
}
