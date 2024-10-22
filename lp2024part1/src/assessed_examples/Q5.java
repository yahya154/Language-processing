package assessed_examples;

import ast.*;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This example outputs the following line:
 *      To Be, or not To Be?
 */
public class Q5 {

    public static void main(String[] args) throws IOException {
        String exampleName = MethodHandles.lookup().lookupClass().getSimpleName();
        Path outpath = Paths.get("ssma/"+ exampleName + ".ssma");

        // Note: printstr outputs a string
        //
        // begin
        //  tobe := "To Be";
        //  ornot := ", or not ";
        //  printstr tobe;
        //  printstr ornot;
        //  ornot := tobe;
        //  printstr ornot;
        //  printstr "?";
        //  newline;
        // end
        // ============================================================
        Program p = new Program(
            // your sequence of Stm ASTs goes here
                new StmAssign("tobe", new ExpChar("To Be")),
                new StmAssign("ornot", new ExpChar(", or not ")),
                new StmPrint(new ExpVar("tobe")),
                new StmPrint(new ExpVar("ornot")),
                new StmAssign("ornot", new ExpVar ("tobe")),
                new StmPrint(new ExpVar("ornot")),
                new StmPrint(new ExpChar("?")),
                new StmNewLine()
                );
        // ============================================================

        System.out.println("Compiling " + exampleName + "...");
        p.compile();
        System.out.println("Writing...");
        AST.write(outpath);
        System.out.println("... SSM assembly code written to " + outpath);
    }
}
