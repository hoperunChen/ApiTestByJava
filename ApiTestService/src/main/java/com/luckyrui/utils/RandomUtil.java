package com.luckyrui.utils;

/**
 * 随机字符/数工具类
 * 
 * @author chenrui
 * @date 2016-04-07 16:18:02
 *
 */
public class RandomUtil {
	private static class RandomUtilHandler {
		private static RandomUtil instance = new RandomUtil();
	}

	private RandomUtil() {
	}

	public static RandomUtil getInstance() {
		return RandomUtilHandler.instance;
	}

	/**
	 * 生成小写大写数字混合的固定长度的随机字符串
	 * @param length
	 * @return
	 */
	public String genMixString(int length) {
		return genString(length,null);
	}
	
	/**
	 * 生成随机字符串
	 * @param length
	 * @param type 0:只生成大写字母,1只生成小写字母,2:只生成数字,其他/null:混合类型
	 * @return
	 */
	public String genString(Integer length,Integer type){
		if(null == length)
			throw new RuntimeException("length can not be null");
		char[] ss = new char[length];
		int i = 0;
		Integer f = type;
		while (i < length) {
			if(null == type )
				f = (int) (Math.random() * 3);
			if (f == 0)
				ss[i] = (char) ('A' + Math.random() * 26);
			else if (f == 1)
				ss[i] = (char) ('a' + Math.random() * 26);
			else if (f == 2)
				ss[i] = (char) ('0' + Math.random() * 10);
			else{
				type = null;
				continue;
			}
				
			i++;
		}
		String is = new String(ss);
		return is;
	}
	
//	public static void main(String[] args) {
//		System.out.println(RandomUtil.getInstance().genString(4, null));
//	}
}
