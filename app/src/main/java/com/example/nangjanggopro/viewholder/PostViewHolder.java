package com.example.nangjanggopro.viewholder;

import android.net.Uri;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
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
    public TextView postNumOfPerson;
    public TextView postHowLong;
    public TextView postMaterial;
    public TextView postLevel;
    public TextView postText;




    public PostViewHolder(View itemView) {
        super(itemView);


        titleView = itemView.findViewById(R.id.postTitle);
        authorView = itemView.findViewById(R.id.postAuthor);
        starView = itemView.findViewById(R.id.star);
        numStarsView = itemView.findViewById(R.id.postNumStars);
        bodyView = itemView.findViewById(R.id.postBody);
        UrlView = itemView.findViewById(R.id.postUrl);
        postNumOfPerson = itemView.findViewById(R.id.postNumOfPerson);
        postHowLong = itemView.findViewById(R.id.postHowLong);
        postMaterial = itemView.findViewById(R.id.postMaterial);
        postLevel = itemView.findViewById(R.id.postLevel);
        postText = itemView.findViewById(R.id.postText);

    }

    public void bindToPost(Post post, View.OnClickListener starClickListener) {
            titleView.setText(post.title);
            authorView.setText(post.author);
            numStarsView.setText(String.valueOf(post.starCount));
            bodyView.setText(post.body);
            postNumOfPerson.setText(post.numofperson);
            postHowLong.setText(post.howlong);
            postMaterial.setText(post.material);
            postLevel.setText(post.level);
            postText.setText(post.text);


        Glide.with(itemView.getContext()).load(post.filepathString).into(UrlView);

        starView.setOnClickListener(starClickListener);
    }
}
