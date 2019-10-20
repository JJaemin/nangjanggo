package com.example.nangjanggopro.ui.share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.nangjanggopro.PostListFragment;
import com.example.nangjanggopro.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class ShareFragment extends PostListFragment {

    public ShareFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        Query myTopPostsQuery = databaseReference.child("user-posts")
                .orderByChild("starCount");
        // [END my_top_posts_query]

        return myTopPostsQuery;
    }
}