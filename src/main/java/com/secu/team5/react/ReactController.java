package com.secu.team5.react;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/react")
public class ReactController {

	@GetMapping("list")
	public List<Map<String, String>> getList(){
		List<Map<String, String>> strs = new ArrayList<>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		map.put("name", "고길동의 전설의검");
		strs.add(map);
		
		map = new HashMap<String, String>();
		map.put("id", "2");
		map.put("name", "고길동은 천사인가");
		strs.add(map);
		
		map = new HashMap<String, String>();
		map.put("id", "3");
		map.put("name", "고길동은 소드마스터인가");
		strs.add(map);

		
		return strs;
	}
}
