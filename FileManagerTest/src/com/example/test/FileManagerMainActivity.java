package com.example.test;

import java.util.ArrayList;
import java.util.List;

import com.hq.source.ChatFragment;
import com.hq.source.FriendFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FileManagerMainActivity extends FragmentActivity  {
	private List<Fragment> mFragmentList = new ArrayList<Fragment>();  
    private FragmentAdapter mFragmentAdapter;  
    
    private LinearLayout mClassify ,mLocal;
    private ViewPager mPageVp;  
    
    /** 
     * Tab��ʾ����TextView 
     */  
    private TextView mTabChatTv, mTabFriendTv;  
    /** 
     * Tab���Ǹ������� 
     */  
    private ImageView mTabLineIv;  
    /** 
     * Fragment 
     */  
    private ChatFragment mChatFg;  
    private FriendFragment mFriendFg;  
    /** 
     * ViewPager�ĵ�ǰѡ��ҳ 
     */  
    private int currentIndex;  
    /** 
     * ��Ļ�Ŀ�� 
     */  
    private int screenWidth;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);  
        findById(); 
        init();  
        initTabLineWidth();  
        
  
    }  
  
    private void findById() {  
        mTabChatTv = (TextView) this.findViewById(R.id.id_chat_tv);  
        mTabFriendTv = (TextView) this.findViewById(R.id.id_friend_tv);  
        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);
        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);  
        mClassify = (LinearLayout)this.findViewById(R.id.id_tab_chat_ll);
        mLocal = (LinearLayout)findViewById(R.id.id_tab_friend_ll);
    }  
  
    private void init() {  
        mFriendFg = new FriendFragment();   
        mChatFg = new ChatFragment();  
        mFragmentList.add(mChatFg);  
        mFragmentList.add(mFriendFg);  
  
        mFragmentAdapter = new FragmentAdapter(  
                this.getSupportFragmentManager(), mFragmentList);  
        mPageVp.setAdapter(mFragmentAdapter);  
        mPageVp.setCurrentItem(0);  
  
        mPageVp.setOnPageChangeListener(new OnPageChangeListener() {  
  
            /** 
             * state�����е�״̬ ������״̬��0��1��2�� 1�����ڻ��� 2��������� 0��ʲô��û���� 
             */  
            public void onPageScrollStateChanged(int state) {  
  
            }  
  
            /** 
             * position :��ǰҳ�棬������������ҳ�� offset:��ǰҳ��ƫ�Ƶİٷֱ� 
             * offsetPixels:��ǰҳ��ƫ�Ƶ�����λ�� 
             */  
            public void onPageScrolled(int position, float offset,  
                    int offsetPixels) {  
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv  
                        .getLayoutParams();  
  
                Log.e("offset:", offset + "");  
                /** 
                 * ����currentIndex(��ǰ����ҳ��)��position(��һ��ҳ��)�Լ�offset�� 
                 * ����mTabLineIv����߾� ���������� 
                 * ��2��ҳ��, 
                 * �����ҷֱ�Ϊ0,1  
                 * 0->1; 1->0 
                 */  
  
                if (currentIndex == 0 && position == 0)// 0->1  
                {  
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex  
                            * (screenWidth / 2));  
  
                } else if (currentIndex == 1 && position == 0) // 1->0  
                {  
                    lp.leftMargin = (int) (-(1 - offset)  
                            * (screenWidth * 1.0 / 2) + currentIndex  
                            * (screenWidth / 2));  
  
                } 
                mTabLineIv.setLayoutParams(lp);  
            }  
  
            public void onPageSelected(int position) {   
                switch (position) {  
                case 0:  
                    mTabChatTv.setTextColor(Color.WHITE);  
                    break;  
                case 1:  
                    mTabFriendTv.setTextColor(Color.WHITE);  
                    break;  
                }  
                currentIndex = position;  
            }  
        });  
  
    }  
  
    /** 
     * ���û������Ŀ��Ϊ��Ļ��1/2(����Tab�ĸ�������) 
     */  
    private void initTabLineWidth() {  
        DisplayMetrics dpMetrics = new DisplayMetrics();  
        getWindow().getWindowManager().getDefaultDisplay()  
                .getMetrics(dpMetrics);  
        screenWidth = dpMetrics.widthPixels;  
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv  
                .getLayoutParams();  
        lp.width = screenWidth / 2;  
        mTabLineIv.setLayoutParams(lp);  
    }  
    
    
}