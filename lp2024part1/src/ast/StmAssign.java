package ast;

public class StmAssign extends Stm{

    String Constant;

    Exp Expression0;

    public StmAssign (String Constant, Exp Expression0) { this.Constant = Constant; this.Expression0 = Expression0;

    }

    @Override
    public void compile () {
        Expression0.compile();
        emit("push " + Constant);
        emit("store");
        AST.addVar(Constant);
    }


}
