package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerParser {
	public List <String[]> parser(String name) throws Exception {
		List <String[]> list = new ArrayList<>();
		System.out.println("PlayerParser / parser / String name : " + name);
		
		String currentLine=null;
		String filePath = this.getClass().getResource("").getPath();
		String[] player= null;
		FileReader fileReader = new FileReader(filePath + "name.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while(bufferedReader.ready()) {
			currentLine = bufferedReader.readLine();
			if (currentLine.toLowerCase().contains(name.toLowerCase())) {
				
				System.out.println("PlayerParser / parser / list.add : " + currentLine.substring(0, currentLine.indexOf(",")));
				player=currentLine.split(",");	
				list.add(player);
				for(int i=0;i<player.length;i++ ) System.out.println("Player[" + i + "]" + player[i]);
			}
		}
		if (list.size()==1 ) return list;
		if (list.size()>1) {
			System.out.println("검색어로 동일한 인물이 1명이상 있음 : " + list.size());
			return  list;
		}
		else  {
			throw new Exception("찾는 사람이 없습니다.");
		}
		
	}
}
