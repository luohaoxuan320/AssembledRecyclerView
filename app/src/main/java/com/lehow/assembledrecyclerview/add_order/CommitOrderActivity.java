package com.lehow.assembledrecyclerview.add_order;


import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lehow.assembledrecyclerview.R;
import com.lehow.assembledrecyclerview.component.AssembledAdapter;
import com.lehow.assembledrecyclerview.component.IAdapterModel;

import java.util.ArrayList;

public class CommitOrderActivity extends FragmentActivity {

    private RecyclerView recyclerView;
    private OrderViewModel orderViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_order);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MAdapterA());
        orderViewModel= ViewModelProviders.of(this).get(OrderViewModel.class);
        orderViewModel.setName("张三");
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
