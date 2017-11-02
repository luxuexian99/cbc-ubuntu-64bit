package net.loveruby.cflat.parser;
import net.loveruby.cflat.ast.Declarations;
import net.loveruby.cflat.utils.ErrorHandler;
import net.loveruby.cflat.exception.*;
import java.util.*;
import java.io.*;

/**
 * 包加载器
 */
public class LibraryLoader {
    private List<String> loadPath;
    private LinkedList<String> loadingLibraries;
    private Map<String, Declarations> loadedLibraries;

    private static List<String> defaultLoadPath() {
        List<String> paths = new ArrayList<>();
        paths.add(".");
        return paths;
    }

    public LibraryLoader() {
        this(defaultLoadPath());
    }

    public LibraryLoader(List<String> loadPath) {
        this.loadPath = loadPath;
        this.loadingLibraries = new LinkedList<>();
        this.loadedLibraries = new HashMap<>();
    }

    public void addLoadPath(String path) {
        loadPath.add(path);
    }

    Declarations loadLibrary(String libId, ErrorHandler handler) throws CompileException {
        if (loadingLibraries.contains(libId)) {
            throw new SemanticException("recursive import from " + loadingLibraries.getLast() + ": " + libId);
        }
        loadingLibraries.addLast(libId);   // stop recursive import
        Declarations declarations = loadedLibraries.get(libId);
        if (declarations != null) {
            // Already loaded import file.  Returns cached declarations.
            return declarations;
        }
        declarations = Parser.parseDeclFile(searchLibrary(libId), this, handler);
        loadedLibraries.put(libId, declarations);
        loadingLibraries.removeLast();
        return declarations;
    }

    private File searchLibrary(String libId) throws FileException {
        try {
            for (String path : loadPath) {
                File file = new File(path + "/" + libPath(libId) + ".hb");
                if (file.exists()) {
                    return file;
                }
            }
            throw new FileException(
                "no such library header file: " + libId);
        }
        catch (SecurityException ex) {
            throw new FileException(ex.getMessage());
        }
    }

    private String libPath(String id) {
        return id.replace('.', '/');
    }
}
