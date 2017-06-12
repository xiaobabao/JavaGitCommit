package com.atguigu.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Test01 {

	public static void main(String[] args) {
		String aString  = new String("abcde");
		aString.replace('c', 'x');
		System.out.println(aString);
	}

}

class Test02 {
	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("./src/jdbc.properties"));
			String property = properties.getProperty("3");
			System.out.println(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Test03 {
	public static void main(String[] args) {
		FileReader fileReader = null;
		FileWriter filterWriter = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileReader = new FileReader("./src/jdbc.properties");
			filterWriter = new FileWriter(new File("./src/abc.properties"));
			bufferedReader = new BufferedReader(fileReader);
			bufferedWriter = new BufferedWriter(filterWriter);
			String line = bufferedReader.readLine();
			while (line != null) {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
				line = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedWriter != null) {
				try {
					bufferedWriter.flush();
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}



