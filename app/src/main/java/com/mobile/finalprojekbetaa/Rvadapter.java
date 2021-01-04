package com.mobile.finalprojekbetaa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Rvadapter extends RecyclerView.Adapter<Rvadapter.ViewHolder>{
    
    private List<SampahEntity> dataList = new ArrayList<>();
    private DataListListener listener;
    public void setData(List<SampahEntity> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            SampahEntity data = dataList.get(i);
            int position = findPosition(data);
            if (position == -1) {
                this.dataList.add(data);
                notifyItemInserted(this.dataList.size() - 1);
            } else {
                this.dataList.remove(position);
                this.dataList.add(position, data);
                notifyItemChanged(position);
            }
        }
    }

    private int findPosition(SampahEntity data) {
        int position = -1;

        if (!this.dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                if (this.dataList.get(i).getId() == data.getId()) {
                    position = i;
                }
            }
        }

        return position;
    }
    public void removeData(SampahEntity data) {
        if (this.dataList.isEmpty()) {
            return;
        }

        for (int i = 0; i < dataList.size(); i++) {
            if (this.dataList.get(i).getId() == data.getId()) {
                this.dataList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void setRemoveListener(DataListListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public Rvadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Rvadapter.ViewHolder holder, int position) {
        holder.bind(dataList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RequestOptions requestOptions;
        private DataListListener listener;
        private TextView tvjudul, tvdesk, tvalamat;
        private SampahEntity data;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .circleCrop();
            tvjudul = itemView.findViewById(R.id.tv_judul);
            tvdesk = itemView.findViewById(R.id.tv_deskripsi);
            tvalamat = itemView.findViewById(R.id.tv_alamatt);
            imageView = itemView.findViewById(R.id.imagecam);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            
        }

        public void bind(SampahEntity sampahEntity, DataListListener listener) {
        this.data = sampahEntity;
        this.listener = listener;
        
        tvjudul.setText(data.getJudul());
        tvdesk.setText(data.getDeskripsi());
        tvalamat.setText(data.getAlamat());

        loadImage(new File(data.getGambar()));

        }

        private void loadImage(File file) {
            Glide.with(itemView.getContext())
                    .asBitmap()
                    .apply(requestOptions)
                    .load(file)
                    .into(imageView);
        }
    }
}
