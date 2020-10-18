package com.example.camera2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;


import java.io.File;
import java.util.ArrayList;

public class ImageSelectActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ArrayList<Ex_item_java> ex_items;
    RecyclerView.LayoutManager mLayoutManager;
    Example_Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createExampleList();
        setContentView(R.layout.activity_image_select);
        buildRecyclerView();

    }

    public void buildRecyclerView() {
        mRecyclerView=findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new Example_Adapter(ex_items,this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Example_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent goToImageSend = new Intent(ImageSelectActivity.this, ChatImage.class);
                Uri mImageUri = Uri.fromFile(new File(ex_items.get(position).getimage()));
                goToImageSend.putExtra("mImageUri", mImageUri.toString());
                Log.i("Sent","Sent");
                startActivity(goToImageSend);
            }
        });
    }

    public void createExampleList() {
        ex_items=new ArrayList<>();
        ArrayList<String> source_images=getAllShownImagesPath(this);
        Log.i("URI: ",source_images.get(0));
        Log.i("URI: ",source_images.get(1));
        Log.i("URI: ",source_images.get(2));
        Log.i("URI: ",source_images.get(3));
        Log.i("URI: ",source_images.get(4));
        ex_items.add(new Ex_item_java(source_images.get(0)));
        ex_items.add(new Ex_item_java(source_images.get(1)));
        ex_items.add(new Ex_item_java(source_images.get(2)));
        ex_items.add(new Ex_item_java(source_images.get(3)));
        ex_items.add(new Ex_item_java(source_images.get(4)));
    }



    private ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        int i=0;
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            Log.i("PATH: ",absolutePathOfImage);
            listOfAllImages.add(absolutePathOfImage);
            if(i>=5)break;
            i++;
        }
        Log.i("List Images: ",listOfAllImages.get(0));
        return listOfAllImages;
    }


}