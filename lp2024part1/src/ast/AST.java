package ast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.nio.file.Files;
import java.util.Set;
import java.nio.file.Path;
import java.util.HashSet;

/**
 * The abstract parent type for all Abstract Syntax Tree classes.
 * This class provides a number of static utility methods to be used
 * by compilation methods.
 */
public abstract class AST {
public static Set<String> VarList = new HashSet<>();;
    private static final List<String> SSM_INSTR_NAMES =
            Arrays.asList("halt", "noop", "push", "pop", "dup", "swap", "add", "sub", "mul", "div", "test_z", "test_n", "load", "store", "sysc", "jump", "jump_z", "jump_n", "get_lp", "set_fp", "get_fp", "set_sp", "get_sp", "call", "ret", "ret_v");

    private static List<String> emitted;

    private static int freshNameCounter;

    static {
        initialise();
    }

    /**
     * Initialise the shared static state.
     */
    public static final void initialise() {
        freshNameCounter = 0;
        emitted = new LinkedList<>();
    }

    /**
     * Transform a name into a new name which is guaranteed
     * not to clash with any of the SSM assembly instruction names.
     * Since no SSM instruction starts with an underscore, all we
     * do is prefix the name with an underscore.
     * @param sourceName
     * @return sourceName prefixed with '_'
     */
    protected static final String makeSafe(String sourceName) {
        return "_" + sourceName;
    }

    /**
     * Each call to this method will return a fresh name which is
     * guaranteed not to clash with any SSM assembly instruction
     * names or with any name returned by makeSafe().
     * @param prefix a string to include as part of the generated name.
     * @return a fresh name.
     */
    protected static final String freshName(String prefix) {
        return "$" + prefix + "_" + (freshNameCounter++);
    }

    private static final String formatEmit(String s) {
        String[] parts = s.split(" |\t");
        if (parts.length > 0 && SSM_INSTR_NAMES.contains(parts[0])) {
            return "\t" + s;
        } else {
            return s;
        }
    }

    /**
     * Emit a sequence of SSM assembly instructions.
     * @param ss the instructions.
     */
    protected static final void emit(String ...ss) {
        for (String s: ss) emitted.add(s);
    }

    /**
     * Write the emitted SSM assembly code to a file.
     * @param path a path to the file where the assembly code is to be written
     */
    public static final void write(Path path) throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) {
            for(String s: emitted) {
                writer.write(formatEmit(s));
                writer.write("\n");
            }
        }
    }
public static void addVar(String v){VarList.add(v);}
}
