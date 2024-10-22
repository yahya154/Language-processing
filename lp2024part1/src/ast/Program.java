package ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Abstract Syntax Trees for programs.
 */
public class Program extends AST {

    public final List<Stm> statements;

    public Program(List<Stm> statements) {
        this.statements = Collections.unmodifiableList(statements);
    }

    public Program(Stm ...stms) {
        this(Arrays.asList(stms));
    }

    /**
     * Emit SSM assembly code which implements this program.
     */
    public void compile() {
        for(Stm stm: statements) {
            stm.compile();
        }
        emit("halt");
        emit(".data");
        for (String var : AST.VarList){
            emit(var + ":" + 0);
        }
        // SSM code to allocate statically allocated variables
        // should be emitted here, if needed
    }
}
