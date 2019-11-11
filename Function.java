import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * A Simple Function that Declares And Initialize A Java EOI Separately.
 */
public class Function {
	
	public double EOI;
	
	public Function(int[] HRarray, int[] Sparray, int Disease) {
		// TODO Auto-generated constructor stub
		
		System.out.println(Arrays.toString(HRarray));
		System.out.println(Arrays.toString(Sparray));
		      
	      	      if (Disease == 1) {
	      	      }else {
	    	  }
	      String DiseaseString = null;
		System.out.println("The Disease is " +DiseaseString);
	     
	 	 // Data Processing
	      Map<Integer, Integer> values = new HashMap<Integer, Integer>();
//SpO2 Labeling
	      int[] definition = {19, 80, 85, 90, 92, 100}; // SpO2 Guidelines
	      int[] buckets = new int[definition.length];
	      
	      Random rnd = new Random();
	      for (int i=0; i<5; i++) {
	          int time = rnd.nextInt(101)+20; // Random 100 SpO2 Array
	      //    System.out.println( "" + time  );
	          Integer calls = values.get(time);
	          if (calls == null) {
	              calls = Integer.valueOf(0);
	          }
	          calls += 1;
	          values.put(time, calls);
	      }

	      for (int time : values.keySet()) {
	          for (int i=definition.length-1; i>=0; i--) {
	              if (time >= definition[i]) {
	                  buckets[i] += values.get(time);
	                  break;
	              }
	          }
	      }
	      // Feature Extraction SpO2
	      int sumSp =0;	double[] percentageSp = new double[definition.length-1];
	      
	      for (int i=0; i<definition.length; i++) {
	          String period = "";
	          if (i == definition.length-1) {
	              period = "greater than " + definition[i] + "ms";
	          } else {
	              period = "between " +
	                        (definition[i]+1) +
	                        " and " +
	                        definition[i+1] + "%";
	              sumSp += buckets[i]; 
	          }
	       //   System.out.println(buckets[i] + " came back " + period);
	      }
	   // Produce SpO2 Percentages
	      if (sumSp == 0)
	    	  System.out.println ("No valid HR samples were found");
	    	  else
	    	  {
	    		//  System.out.println(sumSp + " are the total SpO2 samples");
	    		  for (int i=0; i<definition.length-1; i++) {
	    			  DecimalFormat fmt = new DecimalFormat ("0.##");
	    		  percentageSp[i] = (double)buckets[i]*100/sumSp;
	    		  String period = "";
	    		  period = "between " +
	                        (definition[i]+1) +
	                        " and " +
	                        definition[i+1] + " %";
	    		//  System.out.println(fmt.format(percentageSp[i]) + " Percentage of time in " + period);
	    		  }
	    	  }
	      // Heart rate binding
	      int[] HRlevel = {39, 90, 100, 110, 120, 200}; // HR Guidelines
	      int[] bins = new int[HRlevel.length];
	      
	      Random rnd1 = new Random();
	      for (int i=0; i<5; i++) {
	          int HRArray = rnd1.nextInt(201)+40; // Random 100 Heart Rate Array
	      //    System.out.println( "" + HRArray  );
	          Integer calls = values.get(HRArray);
	          if (calls == null) {
	              calls = Integer.valueOf(0);
	          }
	          calls += 1;
	          values.put(HRArray, calls);
	      }

	      for (int HRArray : values.keySet()) {
	          for (int i=HRlevel.length-1; i>=0; i--) {
	              if (HRArray >= HRlevel[i]) {
	            	  bins[i] += values.get(HRArray);
	                  break;
	              }
	          }
	      }
	      // Feature Extraction Heart Rate
	      int sumHR =0;	double[] percentageHR = new double[HRlevel.length-1];   
	      
	      for (int i=0; i<HRlevel.length; i++) {
	          String period = "";
	          if (i == HRlevel.length-1) {
	              period = "greater than " + HRlevel[i] + " BPM";
	          } else {
	              period = "between " +
	                        (HRlevel[i]+1) +
	                        " and " +
	                        HRlevel[i+1] + "BPM";
         sumHR += bins[i];           
	          }
	   //       System.out.println(bins[i] + " came back " + period);	          
	      }
	      // Produce HR Percentages
	      if (sumHR == 0)
	    	  System.out.println ("No valid HR samples were found");
	    	  else
	    	  {
	    		 // System.out.println(sumHR + " are the total HR samples");
	    		  for (int i=0; i<HRlevel.length-1; i++) {
	    			  DecimalFormat fmt = new DecimalFormat ("0.##");
	    		  percentageHR[i] = (double)bins[i]*100/sumHR;
	    		  String period = "";
	    		  period = "between " +
	                        (HRlevel[i]+1) +
	                        " and " +
	                        HRlevel[i+1] + " BPM";
	    		//  System.out.println(fmt.format(percentageHR[i]) + " Percentage of time in " + period);
	    		  }
	    	  }
	      // Tangent Model
	      double[] raw_alpha_degree= {1, 22.5, 45, 67.5, 89};
	      double[] radians = new double[raw_alpha_degree.length];
	   // Percentages
	      double sumtan =0; double[] percentagetan = new double[raw_alpha_degree.length];   
	      
	      for (int i=0; i<raw_alpha_degree.length; i++) {
	        radians[i] = Math.toRadians(raw_alpha_degree[i]);
	        // print the tangent of these doubles
	      //  System.out.println("Tangent(" + radians[i] + ")=" + Math.tan(radians[i]));
	        sumtan += Math.tan(radians[i]);	        
	      }  
	      //System.out.println(sumtan + " is the total tangent sum");
	      for (int i=0; i<raw_alpha_degree.length; i++) {
	    	  DecimalFormat fmt = new DecimalFormat ("0.##");
  		  percentagetan[i] = (double)Math.tan(radians[i])*100/sumtan;
  		//  System.out.println(fmt.format(percentagetan[i]) + " Percentage of share for tangent");
	      }
	      
	   // EOI Generation
	      double sum_score =0, Min = 2.8551, Range = 0, betaHR, betaSp;   
	      for (int i=0; i<raw_alpha_degree.length; i++) {
	    	  	  if (Disease == 1)
			    	  {Range = 4.3833e+03;
			      betaHR= 0.4;
			    		  betaSp=0.6;
			    	  }else {
			    	    	  Range = 3.2781e+03;
			    	      betaHR= 0.5;
			    	    		  betaSp=0.5;
			    	    		  }
	    	  double score = betaHR*(percentagetan[i])*(percentageHR[i])+betaSp*(percentagetan[i])*(percentageSp[i]);
	    	//  System.out.println(score + " is the score");
	    	  sum_score += score;
	    	//  System.out.println(sum_score + " is the total sum");
	      }
	      EOI = (sum_score-Min)/Range;
              DecimalFormat fmt = new DecimalFormat ("0.##");
	      System.out.println(fmt.format(EOI) + " is the EOI");       
	}
}