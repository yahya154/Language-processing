package ast;

public class ExpDivision extends Exp {
    Exp variable; Exp variable0;


    public ExpDivision(Exp variable, Exp variable0) { this.variable = variable; this.variable0 = variable0;
    }

    @Override
    public void compile() {
        variable.compile();
        variable0.compile();
        emit("div ");
    }
}
