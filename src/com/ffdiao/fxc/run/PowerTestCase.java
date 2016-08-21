package com.ffdiao.fxc.run;

import com.ffdiao.fxc.runner.PowerRunner;

public class PowerTestCase extends PowerRunner{

	@Override
	protected void setCaseMap(){
        caseMap.put("GameTest", "GameTest");
        caseMap.put("GameTest2", "GameTest2");
        caseMap.put("MusicTest", "MusicTest");
        caseMap.put("ReaderTest", "ReaderTest");
        caseMap.put("VideoOfficalTest", "VideoOfficalTest");
        caseMap.put("VideoTest", "VideoTest");
    }
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

    public void testGameTest() throws Exception{
        Runner(caseMap.get("GameTest"));
    }

    public void testGameTest2() throws Exception{
    	Runner(caseMap.get("GameTest2"));
    }

    public void testMusicTest() throws Exception{
    	Runner(caseMap.get("MusicTest"));
    }

    public void testReaderTest() throws Exception{
    	Runner(caseMap.get("ReaderTest"));
    }

    public void testVideoOfficalTest() throws Exception{
    	Runner(caseMap.get("VideoOfficalTest"));
    }

    public void testVideoTest() throws Exception{
    	Runner(caseMap.get("VideoTest"));
    }

}