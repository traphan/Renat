package com.example.ren95.crimeactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ren95 on 07.08.2016.
 */
public class CrimeListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private CrimeAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_crime_list,container,false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimes=crimeLab.getCrimes();
        mAdapter=new CrimeAdapter(crimes);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder{
        public TextView mTitleTextView;
        public CrimeHolder(View itemView){
            super(itemView);
            mTitleTextView=(TextView)itemView;
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public  CrimeAdapter(List<Crime> crimes){
            mCrimes=crimes;
        }
        @Override
        public CrimeHolder onCreateCrimeHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater
                    .inflate(android.R.layout.simple_list_item_1,parent,false);
            return new CrimeHolder(view);
        }
        @Override
        public void onBindViewHolder(CrimeHolder holder,int position){
            Crime crime=mCrimes.get(position);
            holder.mTitleTextView.setText(crime.getTitle());
        }
        @Override
        public int getItemCount(){
            return mCrimes.size();
        }
    }

}
