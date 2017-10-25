package net.loveruby.cflat.parser;
import net.loveruby.cflat.ast.Declarations;
import net.loveruby.cflat.utils.ErrorHandler;
import net.loveruby.cflat.exception.*;
import java.util.*;
import java.io.*;

public class LibraryLoader {
    private List<String> loadPath;
    private LinkedList<String> loadingLibraries;
    private Map<String, Declarations> loadedLibraries;

    private static List<String> defaultLoadPath() {
        List<String> pathes = new ArrayList<>();
        pathes.add(".");
        return pathes;
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

    Declarations loadLibrary(String libid, ErrorHandler handler)
            throws CompileException {
        if (loadingLibraries.contains(libid)) {
            throw new SemanticException("recursive import from "
                                        + loadingLibraries.getLast()
                                        + ": " + libid);
        }
        loadingLibraries.addLast(libid);   // stop recursive import
        Declarations decls = loadedLibraries.get(libid);
        if (decls != null) {
            // Already loaded import file.  Returns cached declarations.
            return decls;
        }
        decls = Parser.parseDeclFile(searchLibrary(libid), this, handler);
        loadedLibraries.put(libid, decls);
        loadingLibraries.removeLast();
        return decls;
    }

    private File searchLibrary(String libid) throws FileException {
        try {
            for (String path : loadPath) {
                File file = new File(path + "/" + libPath(libid) + ".hb");
                if (file.exists()) {
                    return file;
                }
            }
            throw new FileException(
                "no such library header file: " + libid);
        }
        catch (SecurityException ex) {
            throw new FileException(ex.getMessage());
        }
    }

    private String libPath(String id) {
        return id.replace('.', '/');
    }
}
