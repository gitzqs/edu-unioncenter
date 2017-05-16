package com.zqs.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zqs.service.TestService;
import com.zqs.utils.json.JacksonUtils;
@Service("testService")
public class DefaultTestServiceImpl implements TestService {

	@Override
	public String test() {
		System.out.println("11---------------成功");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", "123456");
		return JacksonUtils.object2json(map);
	}

}
