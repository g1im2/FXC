package com.ffdiao.fxc.runner;

import java.util.HashMap;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.ffdiao.fxc.model.IHelper;
import com.ffdiao.fxc.model.ITestCase2;

import android.os.Bundle;
import android.os.SystemClock;

public abstract class PowerRunner extends UiAutomatorTestCase {
	
	private IHelper helper;

    private long MINUTES = 1000 * 60;

    private long TIMER = 20;

    protected HashMap<String, String> caseMap = new HashMap<String, String>();
    
    protected abstract void setCaseMap();

    @Override
    protected void setUp() throws Exception {
        setCaseMap();
        Bundle mBundle = getParams();
        String casename = mBundle.getString("case");
        TIMER = Integer.valueOf(mBundle.getString("time"));
        if(casename != null){
            String[] runCaseList = casename.split("-");
            for(String key:caseMap.keySet()){
                for(String Case:runCaseList){
                    if(Case.equals(key)){
                        caseMap.put(key, "DoNotTest");
                    }
                }
            }
        }
        UiDevice.getInstance().sleep();
        SystemClock.sleep(5000);
        helper.UnlockScreen(false);
    }

    @Override
    protected void tearDown() throws Exception {
        UiDevice.getInstance().sleep();
    }

    protected void Runner(String className) throws Exception{
        Class<?> cXX = Class.forName("com.ffdiao.fxc.testcases." + className);
        ITestCase2 Case = (ITestCase2)cXX.newInstance();
        int startBatteryValue = helper.readBatteryCapacity();
        long end_time = System.currentTimeMillis() + TIMER * MINUTES;
        while(true){
            try{
                Case.CaseStart();
                while(true){
                    Case.CaseControl();
                    long now_time = System.currentTimeMillis();
                    if(end_time < now_time){
                        break;
                    }
                }
                Case.CaseClean();
            }catch (Exception e){
                //do nothing
            }
            long now_time2 = System.currentTimeMillis();
            if(end_time < now_time2){
                break;
            }
        }
        int endBatteryValue = helper.readBatteryCapacity();
        int usedBatteryValue = endBatteryValue - startBatteryValue;
        helper.reportExport(helper.reportInfomation(end_time, Thread.currentThread().getStackTrace()[1].getMethodName(), TIMER, startBatteryValue, endBatteryValue, usedBatteryValue));
    }
}
