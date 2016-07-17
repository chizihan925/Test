package com.hq.source;

import com.example.test.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hq.source.MyGridAdapter;
import com.hq.source.MyGridView;

public class ChatFragment extends Fragment {
	private MyGridView gridview;
	private Context mContext;
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View chatView = inflater.inflate(R.layout.lattice_main, container,false); 
        return chatView;      
    }  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState){  
        super.onActivityCreated(savedInstanceState); 
        mContext = getActivity();
        initSwitch();
    }  
    private void initSwitch(){
    	gridview=(MyGridView) getView().findViewById(R.id.gridview);
		gridview.setAdapter(new MyGridAdapter(mContext));
    }
}
