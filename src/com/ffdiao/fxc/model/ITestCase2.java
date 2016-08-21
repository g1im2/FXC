package com.ffdiao.fxc.model;

public interface ITestCase2 {
	
	/**
	 * start test app and enter control page
	 */
	public void CaseStart() throws Exception;
	
	/**
	 * test case control
	 */
	public void CaseControl() throws Exception;
	
	/**
	 * after test, case will be clean
	 */
	public void CaseClean() throws Exception;
}
