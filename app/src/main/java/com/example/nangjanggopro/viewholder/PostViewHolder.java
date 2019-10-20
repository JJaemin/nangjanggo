package com.example.nangjanggopro.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.load.model.GlideUrl;
import com.example.nangjanggopro.R;
import com.example.nangjanggopro.models.Post;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PostViewHolder extends RecyclerView.ViewHolder {
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;
    public ImageView UrlView;


    public PostViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.postTitle);
        authorView = itemView.findViewById(R.id.postAuthor);
        starView = itemView.findViewById(R.id.star);
        numStarsView = itemView.findViewById(R.id.postNumStars);
        bodyView = itemView.findViewById(R.id.postBody);
        UrlView = itemView.findViewById(R.id.postUrl);
    }

    public void bindToPost(Post post, View.OnClickListener starClickListener) {
            titleView.setText(post.title);
            authorView.setText(post.author);
            numStarsView.setText(String.valueOf(post.starCount));
            bodyView.setText(post.body);

        Glide.with(itemView.getContext()).load(post.filepath).into(UrlView);

        starView.setOnClickListener(starClickListener);
    }
}
