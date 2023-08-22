import java.util.ArrayList;
import java.util.Scanner;


public class runner {
    
    private static ArrayList<ArrayList<Integer>> lastArr;

    public static void main(String[] args) {

		//1 is up
		//2 is right
		//3 is down
		//4 is left
		
		int dim = 4;
		
		ArrayList<ArrayList<Integer>> myGrid = GridGenerator(dim);
		
		Scanner sc = new Scanner(System.in);
		
		while(!(isThereRoom(myGrid)==false && myGrid.equals(lastArr)==false)) {
			for(int j = 0;j<dim;j++) {
				System.out.println(myGrid.get(j));
			}
			myGrid = Swipe(myGrid, sc.nextInt());
		}
			
	}

	
	public static ArrayList<ArrayList<Integer>> GridGenerator(int d){
		ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
		for(int i = 0;i<d;i++) {
			grid.add(new ArrayList<Integer>());
			ArrayList<Integer> tal = grid.get(i);
			for(int j = 0;j<d;j++) {
				int x = (int)(Math.random()*3);
				if(x==0) {tal.add(0);}
				if(x==1) {tal.add(2);}
				if(x==2) {tal.add(4);}
			}
		}
		
		return grid;

	}
	
	public  static ArrayList<Integer> weightedSwipe(ArrayList<Integer> arr, int d) {
		int zc = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) == 0) {
				arr.remove(i);
				i--;
				zc++;
			}
		}
		for (int i = 0; i < zc; i++) {
			if (d == 1) {
				arr.add(0, 0);
			}
			if (d == 0) {
				arr.add(0);
			}
		}
		return arr;
	}

	public static ArrayList<ArrayList<Integer>> SwipeHorizontally(ArrayList<ArrayList<Integer>> arrcomp, int d) {

		for(int a = 0; a<arrcomp.size();a++) {
			ArrayList<Integer> arr = arrcomp.get(a);
					
			arr = weightedSwipe(arr, d);
	
			if (d == 0) {
				for (int i = 0; i < arr.size() - 1; i++) {
	
					if (arr.get(i).equals(arr.get(i + 1))) {
	
						arr.set(i, arr.get(i) * 2);
	
						arr.set(i + 1, 0);
					}
				}
			}
			else if(d==1) {
				for (int i = arr.size()-1; i >=1; i--) {
	
					if (arr.get(i).equals(arr.get(i - 1))) {
	
						arr.set(i, arr.get(i) * 2);
	
						arr.set(i - 1, 0);
					}
				}
			}
			
		arr=weightedSwipe(arr, d);
		
		}			
	return arrcomp;
	}

	public static ArrayList<ArrayList<Integer>> SwipeVertically(ArrayList<ArrayList<Integer>> arr, int d) {
		
		int dim = arr.size();
		ArrayList<ArrayList<Integer>> tempArr = new ArrayList<ArrayList<Integer>> ();
		
		for(int i = 0; i<dim; i++) {
			tempArr.add(new ArrayList<Integer>());
			ArrayList<Integer> STA = tempArr.get(i);
			for(int j = 0; j<dim; j++) {
				STA.add(arr.get(j).get(dim-i-1));
			}
		}
		
		tempArr = SwipeHorizontally(tempArr,(1-d));
		
		ArrayList<ArrayList<Integer>> newArr = new ArrayList<ArrayList<Integer>> ();
		for(int i = 0; i<dim; i++) {
			newArr.add(new ArrayList<Integer>());
			ArrayList<Integer> STA = newArr.get(i);
			for(int j = 0; j<dim; j++) {
				STA.add(tempArr.get(dim-j-1).get(i));
			}
		}
		
		return newArr;
		
	}

	public static ArrayList<ArrayList<Integer>> Swipe(ArrayList<ArrayList<Integer>> arr, int d){
        ArrayList<ArrayList<Integer>> tempArr = null;
		if(d==1) {
            tempArr = SwipeVertically(arr, 1);
		}
		else if(d==2) {
            tempArr = SwipeHorizontally(arr, 1);
		}
		else if(d==3) {
            tempArr = SwipeVertically(arr, 0);
		}
		else if(d==4) {
            tempArr = SwipeHorizontally(arr, 0);
		}
		if(tempArr.equals(lastArr)){
            return tempArr;
        }
        else{
            tempArr = addRandom(tempArr);
            lastArr = tempArr;
            return tempArr;
        }
	}
		
	public static boolean isThereRoom(ArrayList<ArrayList<Integer>> arr) {
		int dim = arr.size();
		for(int i = 0; i<dim;i++) {
			for(int j = 0; j<dim;j++) {
				if(arr.get(i).get(j)==0) {return true;}
			}
		}
		return false;
	}
	
	public static ArrayList<ArrayList<Integer>> addRandom(ArrayList<ArrayList<Integer>> arr){
		int dim = arr.size();
		ArrayList<Integer> iposts = new ArrayList<Integer>();
		ArrayList<Integer> jposts = new ArrayList<Integer>();
		for(int i = 0; i<dim;i++) {
			for(int j = 0; j<dim;j++) {
				if(arr.get(i).get(j)==0) {
					iposts.add(i);
					jposts.add(j);
				}
			}
		}
		
		int rando = (int) (Math.random() * iposts.size());
	
		ArrayList<Integer>tempArr = arr.get(iposts.get(rando));
		tempArr.set(jposts.get(rando), 2);
		arr.set(iposts.get(rando), tempArr);
		
		return arr;
	}

}

