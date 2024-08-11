package com.tia102g4.util;

import java.util.Random;
//產生房間的邀請碼
public class CodeMaker {

	private static final int CODE_LENGTH = 7;
	private static final int[] ASCII_ARRAY = new int [62];
	
	static {
		for(int i = 0 ; i < ASCII_ARRAY.length ; i++ ) {
			if( i < 10 )
				ASCII_ARRAY[i] = 48 + i ;
			else if( i < 36 )
				ASCII_ARRAY[i] = 55 + i ;
			else ASCII_ARRAY[i] = 61 + i ;
		}
	}
	
	public static String invitationCode() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(CODE_LENGTH);
		
		for( int i =0; i < CODE_LENGTH; i++) {
			int index = random.nextInt(ASCII_ARRAY.length);
			sb.append((char)ASCII_ARRAY[index]);
		}
		
		return sb.toString();
	}
	
}
