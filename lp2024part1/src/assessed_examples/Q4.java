package assessed_examples;

import ast.*;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This example outputs 88.
 */
public class Q4 {

    public static void main(String[] args) throws IOException {
        String exampleName = MethodHandles.lookup().lookupClass().getSimpleName();
        Path outpath = Paths.get("ssma/"+ exampleName + ".ssma");

        // begin
        //  x := 20;
        //  if (x) {
        //      x := x - 7;
        //      if (x - 13) {
        //          println x;
        //      } else {
        //          println 88;
        //      }
        //  } else {
        //      println 99;
        //  }
        // end
        // ============================================================
        Program p = new Program(
                new StmAssign("x", new ExpInteger(20)),
                new StmStateIf(
                        new ExpChar("x"),
                        new StmBlock(
                                new StmAssign("x", new ExpSubtract(new ExpChar("x"), new ExpInteger(7))),
                                new StmStateIf(
                                        new ExpSubtract(new ExpChar("x"), new ExpInteger(13)),
                                        new StmPrintln(new ExpChar("x")),
                                        new StmPrintln(new ExpInteger(88))
                                )
                        ),
                        new StmPrintln(new ExpInteger(99))
                )
        );
        //========================================================

        System.out.println("Compiling " + exampleName + "...");
        p.compile();
        System.out.println("Writing...");
        AST.write(outpath);
        System.out.println("... SSM assembly code written to " + outpath);
    }
}
