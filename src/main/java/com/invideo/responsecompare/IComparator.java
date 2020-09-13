package com.invideo.responsecompare;

import java.io.File;

public interface IComparator<X, Y> {
	public boolean compare(X x, Y y);

	public void getData(File file1, File file2);
}
