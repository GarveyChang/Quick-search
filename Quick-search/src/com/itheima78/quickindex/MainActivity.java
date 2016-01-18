package com.itheima78.quickindex;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima78.quickindex.IndexBar.OnSelectedLetterListener;

public class MainActivity extends Activity {

	private IndexBar indexBar;//导航栏
	private ListView name_lv;//姓名列表
	private ArrayList<Friend> friends = new ArrayList<Friend>();
	private TextView show_letter_tv;//显示当前letter的文字
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		indexBar = (IndexBar) findViewById(R.id.indexbar);
		show_letter_tv = (TextView) findViewById(R.id.show_letter_tv);
		indexBar.setOnSelectedLetterListener(new OnSelectedLetterListener() {
			@Override
			public void onSelecteLetter(String letter) {
				Log.e("tag","==============letter="+letter);
				for(int i=0;i<friends.size();i++){
					if(letter.equals(friends.get(i).getLetter())){
						name_lv.setSelection(i);
						break;
					}
				}
				
				show_letter_tv.setVisibility(View.VISIBLE);
				show_letter_tv.setText(letter);
				mHandler.removeCallbacksAndMessages(null);//清空消息队列
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						show_letter_tv.setVisibility(View.GONE);
					}
				}, 1000);
			}
		});
		name_lv = (ListView) findViewById(R.id.name_lv);
		
		//1.初始化数据
		fillList();
		
		//2.对friends集合进行排序
		Collections.sort(friends);
		
		name_lv.setAdapter(new MyAdapter());
	}

	private class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return friends.size();
		}
		@Override
		public Object getItem(int position) {
			return friends.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh = null;
			if(convertView == null){
				convertView = View.inflate(MainActivity.this, R.layout.adapter_friend, null);
				vh = new ViewHolder();
				vh.first_word_tv = (TextView) convertView.findViewById(R.id.first_word);
				vh.name_tv = (TextView) convertView.findViewById(R.id.name);
				convertView.setTag(vh);
			}else{
				vh = (ViewHolder)convertView.getTag();
			}
			
			
			if(position>0){
				if(friends.get(position).getLetter() .equals(friends.get(position-1).getLetter())){//让相邻item的first_word_tv隐藏
					vh.first_word_tv.setVisibility(View.GONE);
				}else{
					vh.first_word_tv.setVisibility(View.VISIBLE);
				}
			}else{
				vh.first_word_tv.setVisibility(View.VISIBLE);
			}
			
			vh.name_tv.setText(friends.get(position).getName());
			vh.first_word_tv.setText(friends.get(position).getLetter());
			
			return convertView;
		}
		
		class ViewHolder{
			TextView first_word_tv;
			TextView name_tv;
		}
		
	}
	
	private void fillList() {
		// 虚拟数据
		friends.add(new Friend("李伟"));
		friends.add(new Friend("张三"));
		friends.add(new Friend("阿三"));
		friends.add(new Friend("阿四"));
		friends.add(new Friend("段誉"));
		friends.add(new Friend("段正淳"));
		friends.add(new Friend("张三丰"));
		friends.add(new Friend("陈坤"));
		friends.add(new Friend("林俊杰1"));
		friends.add(new Friend("陈坤2"));
		friends.add(new Friend("王二a"));
		friends.add(new Friend("林俊杰a"));
		friends.add(new Friend("张四"));
		friends.add(new Friend("林俊杰"));
		friends.add(new Friend("王二"));
		friends.add(new Friend("王二b"));
		friends.add(new Friend("赵四"));
		friends.add(new Friend("杨坤"));
		friends.add(new Friend("赵子龙"));
		friends.add(new Friend("杨坤1"));
		friends.add(new Friend("李伟1"));
		friends.add(new Friend("宋江"));
		friends.add(new Friend("宋江1"));
		friends.add(new Friend("李伟3"));
	}

}
