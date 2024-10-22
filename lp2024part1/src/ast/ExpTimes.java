package ast;

public class ExpTimes extends Exp {
    Exp variable; Exp variable0;


    public ExpTimes(Exp variable, Exp variable0) { this.variable = variable; this.variable0 = variable0;
    }

    @Override
    public void compile() {
        variable.compile();
        variable0.compile();
        emit("mul ");
    }
}
