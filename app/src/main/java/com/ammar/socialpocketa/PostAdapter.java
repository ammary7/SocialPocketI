package com.ammar.socialpocketa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.socialpocketa.models.Post;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private static final String TAG = "PostAdapter";

    //private ArrayList<String> mNames = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mTimes = new ArrayList<>();

    //private ArrayList<String> mTweets = new ArrayList<>();
    private List<String> mTweets = new ArrayList<>();


    private Context mContext;

    /*private List<Post> postList;



    /*public PostAdapter(Context mContext, ArrayList<String> mTweets) {
        this.mTweets = mTweets;
        this.mContext = mContext;
    }*/

    public PostAdapter(Context context, List<String> imageNames, ArrayList<String> images, ArrayList<String> times, List<String> tweets ) {
        mNames = imageNames;
        mImages = images;
        mContext = context;
        mTimes = times;
        mTweets = tweets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.name.setText(mNames.get(position));
        holder.tvTime.setText(mTimes.get(position));
        holder.tweet.setText(mTweets.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mNames.get(position));

                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, PostDetailsActivity.class);
                intent.putExtra("image", mImages.get(position));
                intent.putExtra("name", mNames.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;
        TextView tvTime;
        TextView tweet;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgView_proPic);
            name = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tweet = itemView.findViewById(R.id.tv_tweet);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}