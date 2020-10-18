package com.example.camera2;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Example_Adapter extends RecyclerView.Adapter<Example_Adapter.Example_View_Holder> {
    private Context mContext;
    public ArrayList<Ex_item_java> ex_list;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }
    public static class Example_View_Holder extends RecyclerView.ViewHolder {

        public ImageView iv;

        public Example_View_Holder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }

    public Example_Adapter(ArrayList<Ex_item_java> ex_list, Context context)
    {
        this.ex_list=ex_list;
        this.mContext = context;
    }
    @NonNull
    @Override
    public Example_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ex_layout,parent,false);
        Example_View_Holder evh=new Example_View_Holder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull Example_View_Holder holder, int position) {
        Ex_item_java currentItem=ex_list.get(position);
        //Picasso.with(mContext).load(currentItem.getimage()).into(holder.iv);
        File imgFile = new  File(currentItem.getimage());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.iv.setImageBitmap(myBitmap);

        }
    }

    @Override
    public int getItemCount() {
        return ex_list.size();
    }


}