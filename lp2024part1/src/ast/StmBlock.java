package ast;

import java.util.Arrays;
import java.util.List;

public class StmBlock extends Stm {
//creating variable then constructor for variable tip
  private final List<Stm> tip; public StmBlock (Stm... tip) {this.tip = Arrays.asList(tip); }
        @Override
        public void compile() { for (Stm stm : tip) { stm.compile(); } }
}
