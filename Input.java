import java.util.HashMap;
import java.util.Map;

public class Input {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Disease = 1; // COPD 1 and Asthma 0
		int[] HRarray = {100, 190, 110};
		int[] SParray = {100, 90, 91};

		Function obj1= new Function(HRarray, SParray, Disease);

		//System.out.println(obj1.EOI+ " is the EOI");
		
		//Disease severity
		Map<Double, Integer> values = new HashMap<Double, Integer>();

		//Severity Labeling
		int[] Severitydefinition = {-1, 1, 10, 30, 50}; // labels
		  int[] buckets = new int[Severitydefinition.length];
		  String [] Labels = {"NOR","MIL","MOD","SEV","VES"};
		  double EOI = 3.89;
		  
		  	          Integer calls = values.get(EOI);
		      if (calls == null) {
		          calls = Integer.valueOf(0);
		      }
		      calls += 1;
		      values.put(EOI, calls);

		  	    	          for (int i=Severitydefinition.length-1; i>=0; i--) {
		          if (EOI >= Severitydefinition[i]) {
		              buckets[i] += values.get(EOI);
		              break;
		          
		      }
		  }
		  for (int i=0; i<Severitydefinition.length; i++) {
		      String period = "";
		      if (i == Severitydefinition.length-1) {
		          period = "greater than " + Severitydefinition[i] + " EOI";
		      } else {
		          period = "between " +
		                    (Severitydefinition[i]+1) +
		                    " and " +
		                    Severitydefinition[i+1] + " EOI";
		          	    	              	    	          }
		      if (buckets[i]!=0) {
		    	  String name = Labels[i];
		      System.out.println(buckets[i] + " Subject came back " + period+ " : Severity "+name);}
		     	        	  
		  }	   
	}

}
