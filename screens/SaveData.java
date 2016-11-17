package com.game.only.screens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.brashmonkey.spriter.Player;
import com.game.only.player.Player1;

public class SaveData {
	
	public static void writeXML(int player1, int player2, int stage){
	File file = new File("save.txt");
	ReadData rd = new ReadData();
	rd.readTxt();
	String temp1;
	String temp2;
	String temp3;
	if(rd.getPlayer1()<player1){
		temp1 = Integer.toString(player1);  
	}
	else{
		temp1 = Integer.toString(rd.getPlayer1());
	}
	if(rd.getPlayer2()<player2){
		temp2 = Integer.toString(player2);  
	}
	else{
		temp2 = Integer.toString(rd.getPlayer2());
	}
	if(rd.getStage()<stage+1){
		temp3 = Integer.toString(stage+1);  
	}
	else{
		temp3 = Integer.toString(rd.getStage());
	}

    String data = temp1 +  " " + temp2 + " " + temp3;
    try{

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);  

		
		bw.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	
	public void appendTextFile(String text) {     
		File file = new File("leaderboard.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	  
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(file, true)); 
			buf.append(text);
			buf.newLine();
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		  
}

