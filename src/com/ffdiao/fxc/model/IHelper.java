package com.ffdiao.fxc.model;

import android.os.RemoteException;

public interface IHelper {
	
	/**
	 * get battery information from android phone
	 * @return
	 */
	public int readBatteryCapacity();
	
	/**
	 * out export report to file
	 * @param info
	 */
	public void reportExport(String info);
	
	/**
	 * return a String type information with battery which display or writed in report file
	 * @param time
	 * @param casename
	 * @param usetime
	 * @param startBattery
	 * @param endBattery
	 * @param useBattery
	 * @return
	 */
	public String reportInfomation(long time, String casename, long usetime, int startBattery, int endBattery, int useBattery);
	
	/**
	 * unlock screen with android phone
	 * @param horizon
	 * @throws RemoteException
	 */
	public void UnlockScreen(boolean horizon) throws RemoteException;
	
	
}
