package com.lehow.assembledrecyclerview.add_order;



import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lehow.assembledrecyclerview.R;
import com.lehow.assembledrecyclerview.component.AssembledAdapter;
import com.lehow.assembledrecyclerview.component.IAdapterModel;
import com.lehow.assembledrecyclerview.component.ProxyViewAdapter;
import com.lehow.assembledrecyclerview.component.ProxyViewHolder;
import com.lehow.assembledrecyclerview.component.VHPoolVM;

import java.util.ArrayList;
import java.util.HashMap;

public class CommitOrderActivity extends FragmentActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_order);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MAdapterA());
    }

    class MAdapterA extends AssembledAdapter {

        private ArrayList<IAdapterModel> adapterModels=new ArrayList<>();
        public MAdapterA() {

            this.adapterModels.add(new AddressModel());
            this.adapterModels.add(new AddressModel());
            this.adapterModels.add(new OrderListModel());
            this.adapterModels.add(new OrderPayModel());
            this.adapterModels.add(new OrderPayModel());
        }

        @Override
        public IAdapterModel getAdapterModel(int position) {
            return adapterModels.get(position);
        }

        @Override
        public int getItemCount() {
            return adapterModels.size();
        }
    }

}
