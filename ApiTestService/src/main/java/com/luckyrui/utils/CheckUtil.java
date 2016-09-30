package com.luckyrui.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
	
	public static boolean isEmpty(Object... param){
		if(null == param){
			return true;
		}
		
		for(Object obj :param){
			if(!validatorParamNotEmpty(obj)){
				return true;
			}
		}
		return false;
	}

	private static boolean validatorParamNotEmpty(Object obj) {
		if(null == obj){
			return false;
		}
		// validate String
		if(String.class.isInstance(obj)){
			if("undefined".equals(obj) || 0 == ((String)obj).length()){
				return false;
			}
		}
		// validate Collection
		if(Collection.class.isInstance(obj)){
			if(0 == ((Collection<?>)obj).size()){
				return false;
			}
		}
		// validate Map
		if(Map.class.isInstance(obj)){
			if(0 == ((Map<?, ?>)obj).size()){
				return false;
			}
		}
		// validate Arrays
		if(Arrays.class.isInstance(obj)){
			if(0 == Array.getLength(obj)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 验证手机号
	 * @param mobile
	 * @author chenrui
	 * @return 手机号格式正确:true,不正确:false
	 */
	public static boolean isMobile(String mobile) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(mobile);  
        b = m.matches();   
        return b;  
    }  
	

}
