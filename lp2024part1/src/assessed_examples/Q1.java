package assessed_examples;

import ast.*;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This example outputs 32 72, each on a separate line.
 */
public class Q1 {

    public static void main(String[] args) throws IOException {
        String exampleName = MethodHandles.lookup().lookupClass().getSimpleName();
        Path outpath = Paths.get("ssma/"+ exampleName + ".ssma");

        // Note: println outputs the standard decimal representation
        // of an integer, followed be a newline
        //
        // begin
        //  println 5 + (3 * 9);
        //  println (5 + 3) * 9;
        // end
        // ============================================================
        Program p = new Program(
                // your sequence of Stm ASTs goes here
                new StmPrintln(
                        new ExpPlus(
                                new ExpInteger(5),
                                new ExpTimes(
                                        new ExpInteger(3),
                                        new ExpInteger(9)
                                )
                        )
                ),
                new StmPrintln(
                        new ExpTimes(
                                new ExpInteger(9),
                                new ExpPlus(
                                        new ExpInteger(5),
                                        new ExpInteger(3)
                                )
                        )
                )

        );
        // ============================================================

        System.out.println("Compiling " + exampleName + "...");
        p.compile();
        System.out.println("Writing...");
        AST.write(outpath);
        System.out.println("... SSM assembly code written to " + outpath);
    }
}

