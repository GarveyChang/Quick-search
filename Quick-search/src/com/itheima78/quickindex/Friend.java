package com.itheima78.quickindex;

public class Friend implements Comparable<Friend>{

	private String name;//姓名
	private String pinyin;//姓名对应拼音
	private String letter;//拼音的首字母
	
	public Friend(String name){
		this.name = name;
		pinyin = PinYinUtil.getPinyin(name);
		letter = pinyin.charAt(0)+"";
	}
	
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	@Override
	public int compareTo(Friend another) {
		return pinyin.compareTo(another.getPinyin());
	}


}
