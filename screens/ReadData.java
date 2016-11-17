package com.game.only.screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadData {
	String[] temp = new String[3];
	private int stage;
	private int player1;
	private int player2;
	int i = 0;
	int num_line = 0;
	private int numline = 0;
	
	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getPlayer1() {
		return player1;
	}

	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	public int getPlayer2() {
		return player2;
	}

	public void setPlayer2(int player2) {
		this.player2 = player2;
	}

	public void readTxt(){
		try {
			File fileDir = new File("save.txt");

			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));

			String str;

			while ((str = in.readLine()) != null) {
				temp = str.split("\\s+");
			}

	                in.close();
		    }
		    catch (UnsupportedEncodingException e)
		    {
				System.out.println(e.getMessage());
		    }
		    catch (IOException e)
		    {
				System.out.println(e.getMessage());
		    }
		    catch (Exception e)
		    {
				System.out.println(e.getMessage());
		    }
		player1 = Integer.parseInt(temp[0]);
		player2 = Integer.parseInt(temp[1]);
		stage = Integer.parseInt(temp[2]);
}
	public void readTxt_(ArrayList<String> rank){
		try {
			File fileDir = new File("leaderboard.txt");

			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));

			String str;

			while ((str = in.readLine()) != null) {
				rank.add(str);
			}
	                in.close();
		    }
		    catch (UnsupportedEncodingException e)
		    {
				System.out.println(e.getMessage());
		    }
		    catch (IOException e)
		    {
				System.out.println(e.getMessage());
		    }
		    catch (Exception e)
		    {
				System.out.println(e.getMessage());
		    }
}

	public int getNumline() {
		return numline;
	}

	public void setNumline(int numline) {
		this.numline = numline;
	}
}
