package ast;

public class ExpVar extends Exp {
    public final String varnm;

    public ExpVar(String varnm) {
        this.varnm = varnm;
    }

    @Override
    public void compile() {
        emit("LOAD " + varnm);
    }
    //@Override
    //public Object evaluate
}
