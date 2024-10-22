package ast;

public class StmPrintln extends Stm {
    //creatiing my variable and created constructor
    public final Exp exp; public StmPrintln(Exp exp) {
        this.exp = exp;
    }
    @Override
    public void compile() { //Compiler class created for variable
        exp.compile(); emit("push " + 3); emit("sysc"); emit("push " + 2); emit("sysc");}
}
