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
        recyclerView.setAdapter(new MAdapter());
        Log.i("TAG", "CommitOrderActivity: getBaseContext="+this.getBaseContext()+" this="+this);
    }


    class MAdapter extends RecyclerView.Adapter{

        private VHPoolVM vhPoolVM;
        private ArrayList<IAdapterModel> adapterModels=new ArrayList<>();
        /**
         * 缓存数据对象类型对应的ProxyViewAdapter实例，多个同类型对象的实例共用一个ProxyViewAdapter实例
         * TODO: 这里可以考虑用LruCache来优化这个缓存
         */
        private HashMap<String, ProxyViewAdapter> viewAdapterMap = new HashMap<>();
        public MAdapter() {

            this.adapterModels.add(new AddressModel());
            this.adapterModels.add(new OrderListModel());
            this.adapterModels.add(new OrderPayModel());
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            Log.i("TAG", "MAdapter: onCreateViewHolder="+viewGroup.getContext());
            return vhPoolVM.onCreateViewHolder(viewGroup,viewType);
        };

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            getItemViewAdapter(adapterModels.get(i)).onBindViewHolder((ProxyViewHolder) viewHolder, adapterModels.get(i));
        }


        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            recyclerView.getContext();
            Log.i("TAG", "onAttachedToRecyclerView: "+recyclerView.getContext());
            if (recyclerView.getContext() instanceof FragmentActivity) {
                vhPoolVM = ViewModelProviders.of((FragmentActivity) recyclerView.getContext()).get(VHPoolVM.class);
                recyclerView.setRecycledViewPool(vhPoolVM.getRecycledViewPool());
            }else {
                throw new IllegalArgumentException(recyclerView.getContext() + " 必须 是FragmentActivity 的子类");
            }
        }

        @Override
        public int getItemViewType(int position) {
            return vhPoolVM.getViewHolderType(getItemViewAdapter(adapterModels.get(position)).getVHType());
        }

        /**
         * 处理ViewAdapter对象实例的缓存
         * @param adapterModel
         * @return
         */
        private ProxyViewAdapter getItemViewAdapter(IAdapterModel adapterModel){
            String dataCanonicalName =  adapterModel.getClass().getCanonicalName();
            ProxyViewAdapter proxyViewAdapter = viewAdapterMap.get(dataCanonicalName);
            if (proxyViewAdapter == null) {
                proxyViewAdapter = adapterModel.getViewAdapter();
                viewAdapterMap.put(dataCanonicalName, proxyViewAdapter);
            }
            return proxyViewAdapter;
        }
        @Override
        public int getItemCount() {
            return adapterModels.size();
        }
    }
}
