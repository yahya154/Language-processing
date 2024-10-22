package ast;

public class StmStateIf extends Stm{

    //Creating my variables
    private final Exp c; private final Stm ib; private final Stm eb;

    //contructor for variables
    public StmStateIf (Exp c, Stm ib, Stm eb) { this.c = c; this.ib = ib; this.eb = eb;}


    @Override
    public void compile() {
        // Labelling the start of the else and if condition and a label for the end
        String ifCon = freshName("if_start"), elseCon = freshName("else_start"), endCon = freshName("if_end");

        compileCondition(ifCon, elseCon);
        compileIfBlock(endCon, elseCon);
        compileElseBlock(endCon);
    }

    private void compileCondition(String ifCon, String elseCon) {
        // Compiling the condition of the if statement,
        // this pushes an else label onto the stack then jumps to the else stack
        c.compile(); emit("push " + elseCon); emit("jump_z ");
    }

    private void compileIfBlock(String endCon, String elseCon) {
        // Compiling the body of the if block
        // this pushes an end label onto the stack then jumps to the else stack
        // then jumps to the end of the if-else block, bypassing the else block
        ib.compile(); emit("push " + endCon); emit("jump "); emit(elseCon + ":"); }

    private void compileElseBlock(String endCon) {
        // Compiles the body of the else block
        // this assumes there's an eb object that can compile itself..
        eb.compile(); emit(endCon + ":"); }
}
