package com.demo.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParamUtil {

	//private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	public HashMap<String,String> getParamMap(HttpServletRequest httpServletRequest){
		
		HashMap<String,String> recvParamMap = null;
		
		try {
			recvParamMap = new HashMap<String,String>();
			//파라메터 추출
			Map<String, String[]> paramMap = httpServletRequest.getParameterMap();
			
			for(String param : paramMap.keySet()) {
				String[] tempValue = paramMap.get(param);
				recvParamMap.put(param, tempValue[0]);
			}
			
			//logger.info("parameters:"+recvParamMap);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return recvParamMap;
	}
	
	public HashMap<String,Object> getParamOMap(HttpServletRequest httpServletRequest){
		
		HashMap<String,Object> recvParamMap = null;
		
		try {
			
			recvParamMap = new HashMap<String,Object>();
			
			String paramHist = "";
			
			Map<String,String[]> paramMap = httpServletRequest.getParameterMap();
			for(String param : paramMap.keySet()) {
				String[] tempValue = paramMap.get(param);
				recvParamMap.put(param, tempValue[0]);
				paramHist += "&" +param + "="+ tempValue[0];
			}
			recvParamMap.put("paramHist", paramHist);
			
			//logger.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			//logger.info("parameters",recvParamMap);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return recvParamMap;
		
	}
	
	public List<HashMap<String,Object>> transColNameForList(List<HashMap<String,Object>> list){
		
	
		//logger.info("transColNameForList:" + list+ "=>"+list);
		Pattern p = Pattern.compile("\\_[a-z]{1}");
		ArrayList<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
		
		for(int i=0; i<list.size(); i++) {
			
			HashMap<String,Object> rowMap = list.get(i);
			HashMap<String,Object> rowResultMap = new HashMap<String,Object>();
			
			for(String key : rowMap.keySet()) {
				
				String copyKey = key.toLowerCase();
				String transKey = "";
				
				Matcher m = p.matcher(copyKey);
				
				while(m.find()) {
					transKey = m.replaceFirst(m.group().replaceAll("\\_","").toUpperCase());
					m = p.matcher(transKey);
				}
				
				if("".equals(transKey)) {
					transKey = copyKey;
				}
				
				if(i == 0) {
					//logger.info("key:"+key +"=>                          "+transKey);
				}
				
				rowResultMap.put(transKey, rowMap.get(key));
			}
			
			resultList.add(rowResultMap);
		}
		return resultList;
		
	}

public Paging setPagingFromParam(HashMap<String,Object> paramMap, int totalCount) {
	
	String strNowPage     ="";
	String strPagePerRow  ="";
	String strPagePerBlock="";
	
	int nNowPage     	  =0;
	int nPagePerRow		  =0;
	int nPagePerBlock	  =0;
	
	if(paramMap.get("nowPage") == null || paramMap.get("nowPage") == "" ||((String )paramMap.get("nowPagg")).trim() == "") {
		
		strNowPage = "1";
//		logger.info("strNowPage1:"+strNowPage);
	}else {
		strNowPage =((String)paramMap.get("nowPage")).trim();
		//logger.info("strNowPage2:"+"["+((String)paramMap.get("nowPage")).trim() +"]");
	}
	
	if(isNumCheck(strNowPage) || isRegularText(strNowPage)) {
		nNowPage = Integer.parseInt(strNowPage);
	}else {
		nNowPage = 1;
	}
	
	if(paramMap.get("pagePerRow") == null|| paramMap.get("pagePerRow") == "" || ((String)paramMap.get("pagePerRow")).trim() == "") {
		
		strPagePerRow = "10";
	}else {
		strPagePerRow = ((String)paramMap.get("pagePerRow")).trim();
	}
	
	if(isNumCheck(strPagePerRow)) {
		nPagePerRow = Integer.parseInt(strPagePerRow);
	}else {
		nPagePerRow = 10;
	}
	
	if(paramMap.get("pagePerBlock") == null || paramMap.get("pagePerBlock") == "" || ((String)paramMap.get("pagePerBlock")).trim() == "") {
		strPagePerBlock = "5";
	}else {
		strPagePerBlock = ((String)paramMap.get("pagePerBlock")).trim();
	}
	
	if(isNumCheck(strPagePerBlock)) {
		nPagePerBlock = Integer.parseInt(strPagePerBlock);
	}else {
		nPagePerBlock = 5;
	}
	
	return new Paging(nNowPage,nPagePerRow, nPagePerBlock, totalCount);
}
	
private boolean isRegularText(String strNowPage) {

	
	
	
	
	return false;
}

public HashMap<String,Object> addPagingToParam(HashMap<String,Object> paramMap, Paging paging){
	
	paramMap.put("startRow", paging.getStartRow());
	paramMap.put("nowPage", paging.getNowPage());
	paramMap.put("pagePerRow", paging.getPagePerRow());
	
	return paramMap;
}

public boolean isNumCheck(String paramStr) {
	
	boolean isNum = false;
	isNum = paramStr.matches("^[0-9]*$");
	
	//logger.info("isNum:" + isNum + "/"+paramStr);
	
	return isNum;
}


}

