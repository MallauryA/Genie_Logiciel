package calculatrice;

import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculatrice{
    Stack<Double> resultat;
    HashMap<String, Operation> operations;

    public Calculatrice(){
	resultat = new Stack<Double>();
	operations = new HashMap<String, Operation>();
	for(Operation o : Operation.values())
	    operations.put(o.toString(), o);
    }

    public Double calculer(String s){
	StringTokenizer st = new StringTokenizer(s);
	while(st.hasMoreTokens()){
	    String token = st.nextToken();
	    if(operations.containsKey(token))
		resultat.push(operations.get(token).eval(resultat.pop(), resultat.pop()));
	    else{
		Double tmp = Double.parseDouble(token);
		resultat.push(tmp);
	    }
				
	}
	return resultat.peek();
    }
}
