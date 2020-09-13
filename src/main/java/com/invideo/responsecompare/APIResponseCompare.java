package com.invideo.responsecompare;

import java.io.File;
import com.invideo.responsecompare.model.Response;
import com.invideo.responsecompare.model.ResponseComparator;

public class APIResponseCompare {

	public static void main(String[] args) {
		IComparator<Response, Response> c= new ResponseComparator();
		
		File file1 = new File("Input1.txt");
		File file2 = new File("Input2.txt");
		
		c.getData( file1, file2);
		
		
		
	}

}
