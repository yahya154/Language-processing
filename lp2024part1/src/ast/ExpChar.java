package ast;

public class ExpChar extends Exp{
public final String variable;
public ExpChar (String variable) {
    this.variable = variable;
}

@Override
    public void compile() {
    VarList.add(variable); emit("push " + variable); emit("load");
}
}
