package com.luckyrui.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProfileUtil {
	private Log log = LogFactory.getLog("ProfileUtil.class");

	private static class ProfileUtilHandler {
		private static ProfileUtil instance = new ProfileUtil();
	}

	private ProfileUtil() {
	}

	public static ProfileUtil getInstance() {
		return ProfileUtilHandler.instance;
	}

	/**
	 * 读取配置文件配置
	 * 
	 * @param filename
	 * @param key
	 * @return
	 * @author chenrui
	 * @date 2016年8月29日 下午1:53:06
	 * @version 2016_Anniversary
	 */
	public String read(String filename, String key) {

		// 非静态方法时使用：
		InputStream path = this.getClass().getResourceAsStream("/" + filename);

		Properties pros = new Properties();
		try {
			pros.load(path);
		} catch (IOException ex) {
			log.error("配置文件 " + filename + " 不存在！");
			System.out.println("资源文件不存在");
		}

		String value = pros.getProperty(key);

		return value;
	}

	/**
	 * 写入配置文件
	 * 
	 * @param filename
	 * @param key
	 * @return
	 * @author chenrui
	 * @date 2016年8月29日 下午1:53:06
	 * @version 2016_Anniversary
	 * @throws IOException
	 */
	public void wirte(String filename, String key, String value) throws IOException {
		try {
			InputStream in = this.getClass().getResourceAsStream("/" + filename);
			Properties pros = new Properties();
			pros.load(in);
			in.close();
			pros.setProperty(key, value);
			FileOutputStream out = new FileOutputStream(this.getClass().getResource("/" + filename).getPath(), true);
			pros.store(out, "sdfsdf");
			out.close();
		} catch (IOException ex) {
			log.error("配置文件 " + filename + " 不存在！");
			System.out.println("资源文件不存在");
		}
	}
}
