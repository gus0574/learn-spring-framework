package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SomeBusinessImplStubTest {

	@Test
	void findTheGreatestFromAllData() {
		DataService dataServiceStub = new DataServiceStub();
		SomebusinessImpl someBusinessImpl = new SomebusinessImpl(dataServiceStub);
		
		int result = someBusinessImpl.findTheGreatestFromAllData();
		assertEquals(25, result);
	}

}

class DataServiceStub implements DataService {
	@Override
	public int[] retrieveAllData() {
		// TODO Auto-generated method stub
		return new int[] {25, 15, 5} ;
	}
}