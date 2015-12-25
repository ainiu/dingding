package com.ghsh.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ghsh.R;
import com.ghsh.adapter.GoodsDetailsMainPagerAdapter;

/**
 * 商品详情 主
 * */
public class GoodsDetailsMainActivity extends FragmentActivity implements OnClickListener{
	private ImageView backView;
	private TextView titleView;
	private TextView tabhost1,tabhost2;
	private ViewPager viewPager;
	private View line_view;
	private int widths;
	private String goodsID;
	private LayoutParams params;
	private int currentIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goodsdetails_main);
		Intent intent=getIntent();
		currentIndex=intent.getIntExtra("tabIndex", 0);
		goodsID=intent.getStringExtra("goodsID");
		
		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		widths = outMetrics.widthPixels;
		
		this.initView();
		this.initListener();
	}
	private void initView() {
		titleView = (TextView) this.findViewById(R.id.header_title_view);
		titleView.setText(R.string.goods_details_des);
		backView = (ImageView) this.findViewById(R.id.header_back_view);
		backView.setVisibility(View.VISIBLE);
		
		
		tabhost1 = (TextView) findViewById(R.id.tabhost1);
		tabhost2 = (TextView) findViewById(R.id.tabhost2);
		
		line_view = findViewById(R.id.goods_details_main_line);
		line_view.setBackgroundResource(R.color.app_global_color);
		
		List<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new GoodsDetailsDesActivity(goodsID));
		fragments.add(new GoodsDetailsEvaluateActivity(goodsID));
		viewPager = (ViewPager) this.findViewById(R.id.shop_viewPager);
		viewPager.setAdapter(new GoodsDetailsMainPagerAdapter(getSupportFragmentManager(), fragments));
		viewPager.setOffscreenPageLimit(2);
		updataLine(currentIndex);
		viewPager.setCurrentItem(currentIndex);
		
	}

	private void updataLine(int index){
		LayoutParams params = (LayoutParams) line_view.getLayoutParams();
		params.leftMargin = widths/2*index;
		params.width = widths/2;
		line_view.setLayoutParams(params);
	}
	
	private class MyOnClickListener implements OnClickListener{
		private int index;
		public MyOnClickListener(int index){
			this.index = index;
		}
		@Override
		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}
	}
	private void initListener() {
		backView.setOnClickListener(this);
		tabhost1.setOnClickListener(new MyOnClickListener(0));
		tabhost2.setOnClickListener(new MyOnClickListener(1));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int index) {
				updataLine(index);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
	}
	
	@Override
	public void onClick(View v) {
		if (v == backView) {
			this.finish();
		}
	}
}
