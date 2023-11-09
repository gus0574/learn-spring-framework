package com.in28minutes.mockito.mockitodemo.business;

public class SomebusinessImpl {

	DataService dataService;
	
	public SomebusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}
	
	public int findTheGreatestFromAllData() {
		
		int[] data = dataService.retrieveAllData();
		int greatestValue = Integer.MIN_VALUE;
		
		for(int value:data) {
			if(value > greatestValue) {
				greatestValue = value;
			}
		}
		
		return greatestValue;
	}
}
	
	interface DataService {
		int[] retrieveAllData();
	}
