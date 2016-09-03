package com.luckyrui.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 文件工具类
 * @author chenrui
 * @date 2016年9月2日下午2:24:33
 */
public class FileUtil {
	
	/**
	 * 读取文件内容
	 * @param fileName
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午3:16:32
	 * @version 2016_Anniversary
	 */
	public String readFileInProject(String fileName){
		String laststr = "";
		BufferedReader reader = null;
		try {
			InputStream is = this.getClass().getResourceAsStream("/" + fileName);
			InputStreamReader isr = new InputStreamReader(is);
			reader = new BufferedReader(isr);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
	
	/**
	 * 通过绝对路径读取文件
	 * @param path
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午3:22:58
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException 
	 */
	public String readFile(String path) throws FileNotFoundException{
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	/**
	 * 加载工程中的jsonObject
	 * 
	 * @param fileName
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午2:24:37
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException 
	 */
	public JSONObject getJsonFileInProject(String fileName) throws FileNotFoundException {
		return JSONObject.parseObject(readFile(fileName));
	}
	
	/**
	 * 加载工程中的jsonArray
	 * @param fileName
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午3:24:19
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException 
	 */
	public JSONArray getJsonArrFileInProject(String fileName) throws FileNotFoundException {
		return (JSONArray) JSONArray.parse(readFile(fileName));
	}

	/**
	 * 根据绝对路径加载jsonObject
	 * @param Path
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午2:56:37
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException 
	 */
	public JSONObject getJsonFile(String path) throws FileNotFoundException {
		return JSONObject.parseObject(readFile(path));
	}
	
	/**
	 * 根据绝对路径加载jsonObject
	 * @param Path
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午2:56:37
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException 
	 */
	public JSONArray getJsonArrFile(String path) throws FileNotFoundException {
		return (JSONArray) JSONArray.parse(readFile(path));
	}

}
