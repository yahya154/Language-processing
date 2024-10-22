package ast;

public class ExpPlus extends Exp {

    Exp variable; Exp variable0;


    public ExpPlus (Exp variable, Exp variable0) {   this.variable = variable;  this.variable0 = variable0; }

    @Override
    public void compile () {
        variable.compile();
        variable0.compile();
        emit("add ");
    }
}
