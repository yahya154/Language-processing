package ast;

public class StmNewLine extends Stm {
    public StmNewLine () { }
    @Override
    public void compile() { emit("push " + 2); emit("sysc"); }
}
