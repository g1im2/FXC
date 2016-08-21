package com.ffdiao.fxc.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PowerBuilder {
	
	private final String TARGET_JAVA_FILE = "./src/com/ffdiao/fxc/run/PowerTestCase2.java";
	
	private final String TARGET_JAVA_DIR = "./src/com/ffdiao/fxc/testcases";
	
	private static ArrayList<String> addFiles = new ArrayList<String>();

	public static void main(String[] args) {
		/*
		 * add case file in addFiles and case ClassFile will be create with addFiles
		 * else create all files which in package testcases
		 */
//		addFiles.add("GameTest");
//		addFiles.add("GameTest2");
		PowerBuilder builder = new PowerBuilder();
		builder.mkTestFile();
		System.out.println("writed done");
	}
	
	private ArrayList<String> getClassFile(){
		if(addFiles.isEmpty()){
			ArrayList<String> CaseFileList = new ArrayList<String>();
			File rootFile = new File(TARGET_JAVA_DIR);
			File[] fileList = rootFile.listFiles();
			for(File file:fileList){
				if(file.isFile()){
					CaseFileList.add(file.getName().split(".java")[0]);
				}
			}
			return CaseFileList;
		}
		return addFiles;
	}
	
	public void mkTestFile(){
		WriteJavaFile(HeadData(), false);
		ArrayList<String> fileList = getClassFile();
		WriteJavaFile(CaseMapData(fileList), true);
		for(String filename:fileList){
			WriteJavaFile(CaseData(filename), true);
		}
		WriteJavaFile("}", true);
	}
	
	private void WriteJavaFile(String data, Boolean add){
		try {
			FileWriter writer = new FileWriter(TARGET_JAVA_FILE, add);
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String CaseMapData(ArrayList<String> caseList){
		String caseData = "    @Override\n"
				+ "    protected void setCaseMap(){\n";
		for(String Case:caseList){
			caseData = caseData 
					+ "        caseMap.put(\"" + Case + "\", \"" + Case + "\");\n";
		}
		caseData = caseData + "    }\n\n";
		return caseData;
	}
	
	private String HeadData(){
		return "package com.ffdiao.fxt.run;\n\n"
			+ "import com.ffdiao.fxc.runner.PowerRunner;\n\n"
			+ "public class PowerTestCase2 extends PowerRunner{\n\n"
			+ "    @Override\n"
			+ "    protected void setUp() throws Exception {\n"
			+ "        super.setUp();\n"
			+ "    }\n\n"
			+ "    @Override\n"
			+ "    protected void tearDown() throws Exception {\n"
			+ "        super.tearDown();\n"
			+ "    }\n\n";
	}
	
	private String CaseData(String className){
		return "    public void test" + className + "() throws Exception{\n"
			+ "        Runner(caseMap.get(\"" + className + "\"));\n"
			+ "    }\n\n";
	}
	
}

