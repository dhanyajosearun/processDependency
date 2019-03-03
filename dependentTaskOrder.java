import java.lang.*; 
import java.io.*; 
import java.util.*;

public class dependentTaskOrder {

	/**
	 * @author DhanyJ
	 */

	private ArrayList<String> dependencies = new ArrayList<String>();
	private ArrayList<String> dependenciesArray = new ArrayList<String>();
	private ArrayList<String> result = new ArrayList<String>();
	private String[] tempArray1={};
	private String[] tempArray2={};
	private String[] tempArray3={};
	private int addposition=0;
	private int addflag=0;
	private int cyclicdependencyFlag=0;

	public static void main(String[] args)  
	{ 

		dependentTaskOrder order = new dependentTaskOrder();
		if(order.inputProcessDependencies())
			try {
				if(order.dependencies.size() <= 0){
					System.out.println("Process Order:"+order.result);
				}
				else{
					if(!order.checkCyclicDependencies()){
						order.printTaskOrder();
					}
				}
			} catch (Exception e) {
				e.printStackTrace(); 
				System.out.println("Exception In Main:"+e);
			}
	} 

	public boolean inputProcessDependencies(){

		String process;
		String dependency;
		String[] parts;
		try{
			System.out.println("Enter list of Processes seperated by white space and 'FINISH' as the last elemnet to stop the input(" +
					"Max No of process should be within 50):");
			Scanner kb = new Scanner(System.in);		
			do {
				process = kb.next();
				if(!process.equals("FINISH"))
					result.add(process);
			}while(!process.equals("FINISH")&&result.size()<=50);

			System.out.println("Enter list of Dependencies in process1=>process2 form seperated by white space " +
					"and 'FINISH' as the last elemnet to stop the input:");
			Scanner pr = new Scanner(System.in);		
			do {
				dependency = pr.next();
				parts = dependency.split("=>");
				if(!dependency.equals("FINISH")){
					if(result.contains(parts[0])&&result.contains(parts[1])){				
						dependencies.add(dependency);}
					else{
						System.out.println("Non Existing Process at "+dependency+". Please start Over.");
						return false;
					}
				}
			}while(!dependency.equals("FINISH"));
			return true;
		} catch (Exception e) {
			System.out.println("An error occurred. Please start over.");
			return false;
		}
	}
	public boolean checkCyclicDependencies(){

		try{
			for(int i=0;i<dependencies.size();i++){
				tempArray1 = dependencies.get(i).split("=>");
				for(int j=i+1;j<dependencies.size();j++){
					tempArray2 = dependencies.get(j).split("=>");
					if(tempArray1[1].equals(tempArray2[0])&&
							tempArray2[1].equals(tempArray1[0])){
						System.out.println("Cyclic dependency at:"+dependencies.get(i)+","
								+dependencies.get(j));
						tempArray1=null;
						tempArray2=null;
						return true;
					}
					for(int k=j+1;k<dependencies.size();k++){
						tempArray3 = dependencies.get(k).split("=>");
						if(tempArray1[1].equals(tempArray2[0])&&
								tempArray2[1].equals(tempArray3[0])&&
								tempArray3[1].equals(tempArray1[0])){
							System.out.println("Cyclic dependency at:"+dependencies.get(i)+","
									+dependencies.get(j)+","+dependencies.get(k));
							tempArray1=null;
							tempArray2=null;
							tempArray3=null;
							return true;
						}
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.println("Exception while Checking Cyclic Dependency"+e);
			return true;
		}
	}
	public void printTaskOrder(){

		ArrayList<String> temp = new ArrayList<String>(result);
		try{
			for(int i=0;i<dependencies.size();i++){
				tempArray1 = dependencies.get(i).split("=>");
				if(i==0){
					dependenciesArray.add(tempArray1[1]);
					dependenciesArray.add(tempArray1[0]);
				}
				if(i+1<dependencies.size()){
					tempArray2 = dependencies.get(i+1).split("=>");
					if(tempArray1[1].equals(tempArray2[0])){
						if (!dependenciesArray.contains(tempArray2[1]))
							dependenciesArray.add(addposition, tempArray2[1]);
					}
					else if(tempArray1[0].equals(tempArray2[0])){
						if (!dependenciesArray.contains(tempArray2[1]))
							dependenciesArray.add(addposition, tempArray2[1]);
					}
					else{
						addposition=dependenciesArray.size();
						if (!dependenciesArray.contains(tempArray2[1])) 
							dependenciesArray.add(addposition, tempArray2[1]);
						if (!dependenciesArray.contains(tempArray2[0])) 
							dependenciesArray.add(tempArray2[0]);
					}
				}
			}
			temp.removeAll(dependenciesArray);
			if(temp.size()>0)
				dependenciesArray.addAll(0, temp);
			System.out.println("Process Order:"+dependenciesArray);
		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.println("Exception while Printing Task Orde"+e);
		}
	}
}


