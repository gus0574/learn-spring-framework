package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest {

	@Mock
	DataService dataServiceMock;
	
	@InjectMocks
	SomebusinessImpl someBusinessImpl;
	
	@Test
	void findTheGreatestFromAllData_basic() {
		
//		DataService dataServiceMock = mock(DataService.class);
//		SomebusinessImpl someBusinessImpl = new SomebusinessImpl(dataServiceMock);
//		int result = someBusinessImpl.findTheGreatestFromAllData();
		
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25, 15, 5});
		assertEquals(25, someBusinessImpl.findTheGreatestFromAllData());
	}

}

