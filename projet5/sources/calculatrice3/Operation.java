package calculatrice3;

import java.util.Stack;

public enum Operation {
    PLUS("+",2), MOINS("-",2), FOIS("*",2), DIV("/",2), PUISS("^",2), SQRT("V",1), ABS("ABS",1), NOT("NOT",1), IF("IF",3), DROP(), DUP(), SWAP(), COUNT() ;
    private final String code_operation;
    public final int arite;

    Operation(String code_operation, int arite){
	this.code_operation = code_operation;
	this.arite=arite;
    }

    Operation(){
	this.arite = 0;
	this.code_operation = name();
    }

    public String toString(){
	return code_operation;
    }

    public Double eval(Double[] operandes){
	switch (this){
	case PLUS : return operandes[0]+operandes[1];
	case MOINS : return operandes[0]-operandes[1];
	case DIV : return operandes[0]/operandes[1];
	case FOIS : return operandes[0]*operandes[1];
	case PUISS : return Math.pow(operandes[0],operandes[1]);
	case SQRT : return operandes.length==1 ? Math.sqrt(operandes[0]) : null;
	case ABS : return operandes.length==1 ? Math.abs(operandes[0]) : null;
	case NOT : return operandes[0] == 0.0 ? 1.0 : 0.0;
	case IF : return operandes[0]!=0 ? operandes[1] : operandes[2];
	default : return null;
	}
    }

    public void execute(Stack<Double> pile){
	if(this.arite != 0){
	    Double[] tab = new Double [this.arite];
	    Stack<Double> copy = new Stack<Double>();
	    int i=0;
	    while(!pile.empty()){
		tab[i] = pile.peek();
		copy.push(pile.pop());
		i++;
	    }
	    while(!copy.empty())
		    pile.push(copy.pop());
	    System.out.println(tab[0]+ "  " + tab[1]);
	    pile.push(this.eval(tab));
	}else{
	    System.out.println(this);
	    switch (this){
	    case DROP : pile.pop();
	    case DUP : pile.push(pile.peek());
	    case SWAP :
		Double tmp = pile.pop();
		Double tmp2 = pile.pop();
		pile.push(tmp);
		pile.push(tmp2);
	    case COUNT :
		Stack<Double> copy = new Stack<Double>();
		int cpt=0;
		while(!pile.empty()){
		    cpt++;
		    copy.push(pile.pop());
		}
		while(!copy.empty())
		    pile.push(copy.pop());
		pile.push(new Double(cpt));
	    }
	}
    }
}
