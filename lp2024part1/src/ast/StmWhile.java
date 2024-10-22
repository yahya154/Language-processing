

package ast;

public class StmWhile extends Stm {

    public final Stm block; public final Exp condition;

    // This is my constructor
    public StmWhile(Exp condition, Stm block) { this.block = block; this.condition = condition; }

    @Override
    public void compile() {
        // Define loop start and end labels
        String loopStart = freshName("loop_start"); String loopEnd = freshName("loop_end");
        // Emit the start loop label
        emitLoopStartLabel(loopStart);
        // Compile the condition and decide whether to continue the loop
        compileAndEvaluateCondition(loopEnd);
        // Compile the loop's body and loop back to the start
        compileLoopBodyAndLoopBack(loopStart, loopEnd);
    }

    private void emitLoopStartLabel(String loopStart) { emit(loopStart + ":"); }

    private void compileAndEvaluateCondition(String loopEnd) {
        condition.compile(); // Compile the loop condition
        emit("push " + loopEnd); // Prepare to jump out of the loop if the condition is false
        emit("jump_z "); // Conditionally jump out of the loop
    }

    // this compiles the loop body then jumps back to the start of the loop, then jumps back to the start
    private void compileLoopBodyAndLoopBack(String loopStart, String loopEnd) {
        block.compile();
        emit("push " + loopStart); emit("jump "); emit(loopEnd + ":");
    }
}
