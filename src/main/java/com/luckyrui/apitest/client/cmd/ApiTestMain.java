package com.luckyrui.apitest.client.cmd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.alibaba.fastjson.JSONArray;
import com.luckyrui.apitest.fn.ApiRequestFn;
import com.luckyrui.exception.HttpClientException;
import com.luckyrui.utils.CheckUtil;
import com.luckyrui.utils.FileUtil;
import com.luckyrui.utils.ProfileUtil;

public class ApiTestMain {

	public static void main(String[] args) throws IOException, HttpClientException {
		String apiConfigPath = ProfileUtil.getInstance().read("config.properties", "api.config.file.path");
		Scanner in = new Scanner(System.in);
		try {
			JSONArray ja = new FileUtil().getJsonArrFile(apiConfigPath);
			System.out.println("已加载配置文件:" + apiConfigPath);
			System.out.println("开始解析解析.....");
			ApiRequestFn rf = new ApiRequestFn(ja);
			System.out.println(rf.showApiList());
			System.out.println("输入要调用的接口:id");
			String key = in.next();
			rf.request(key);

		} catch (FileNotFoundException e) {
			System.err.println("找不到文件:" + apiConfigPath);
			while (true) {
				System.out.println("请输入配置文件绝对路径或输入q退出:");
				String path = in.next();
				if (CheckUtil.isEmpty(path)) {
					continue;
				}
				if (path.equals("q")) {
					break;
				} else {
					ProfileUtil.getInstance().wirte("config.properties", "api.config.file.path", path);
					System.out.println("配置文件路径已记录,请重新启动程序");
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("发生异常");
			e.printStackTrace();
		} finally {
			in.close();
		}

	}
}
