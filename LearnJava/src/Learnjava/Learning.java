package Learnjava;

import java.util.Scanner;

public class Learning {
	public static    boolean isPrime( int n) {
		
		for(int i=2;i<n-1;i++) {
		if(n%i==0)	return false;
			
		}
		return true;
			
	}
		
	

	
	public static void main(String[] args) {
	int	N=20;
	for(int i=1;i<=N;i++) {
		if(isPrime(i)) {
			
		System.out.println( i +" ");
	}
	
		
		
			
		
			
		
			
	
		}

}
}