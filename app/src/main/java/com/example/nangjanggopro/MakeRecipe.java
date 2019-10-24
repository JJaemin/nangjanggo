package com.example.nangjanggopro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nangjanggopro.models.Post;
import com.example.nangjanggopro.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeRecipe extends BaseActivity {

    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";
    private Uri filePath;
    private String filePathString;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]
    private  String filename;
    ImageView backImgView;
    ImageView save_recipe;
    EditText recipeTitle, recipeSogae, pLevel, pHowLong,pMaterial, pNumOfPerson, pText;
    Button ImageSave;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_recipe);

        backImgView = findViewById(R.id.backImgView);
        backImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        save_recipe = findViewById(R.id.save_recipe);
        recipeTitle = findViewById(R.id.recipeTitle);
        recipeSogae = findViewById(R.id.recipeSogae);
        ImageSave = findViewById(R.id.ImageSave);
        pNumOfPerson = findViewById(R.id.pNumOfPerson);
        pMaterial = findViewById(R.id.pMaterial);
        pHowLong = findViewById(R.id.pHowLong);
        pLevel= findViewById(R.id.pLevel);
        pText = findViewById(R.id.pText);

        ImageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);
            }
        });

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        save_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                submitPost();

            }
        });

        pNumOfPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumOfPerson();
            }
        });

        pLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLevel();

            }
        });

    }

    private void submitPost() {
        final String title = recipeTitle.getText().toString();
        final String body = recipeSogae.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            recipeTitle.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            recipeSogae.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = getUid();
        final String numofperson = pNumOfPerson.getText().toString();
        final String howlong = pHowLong.getText().toString();
        final String level = pLevel.getText().toString();
        final String material = pMaterial.getText().toString();
        final String text = pText.getText().toString();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);
                        // Write new post
                        writeNewPost(userId,user.username, title, body, filePathString, material, numofperson, howlong, level, text);

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }

    private void setEditingEnabled(boolean enabled) {
        recipeTitle.setEnabled(enabled);
        recipeSogae.setEnabled(enabled);

    }

    // [START write_fan_out]
    private void writeNewPost( String userId, String username, String title, String body,String filePathString,String material, String numofperson,String howlong,String level, String text) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body, filePathString,material,numofperson, howlong, level, text);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]

    //결과 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //request코드가 0이고 OK를 선택했고 data에 뭔가가 들어 있다면
        if(requestCode == 0 && resultCode == RESULT_OK){
            filePath = data.getData();
            filePathString = filePath.toString();
            Log.d(TAG, "uri:" + filePathString);

            }
        }

    //upload the file
    private void uploadFile() {
        //업로드할 파일이 있으면 수행
        if (filePath != null) {
            //업로드 진행 Dialog 보이기
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("업로드중...");
            progressDialog.show();

            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            filename = formatter.format(now) + ".png";

            //storage 주소와 폴더 파일명을 지정해 준다.
             storageRef = storage.getReferenceFromUrl("gs://nangjanggo-87b2b.appspot.com").child("images/" + filename);
            //올라가거라...
            storageRef.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    void showLevel() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("Hard");
        ListItems.add("Normal");
        ListItems.add("easy");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);
        final List SelectedItems = new ArrayList();
        int defaultItem = 0;
        SelectedItems.add(defaultItem);


        builder.setTitle("난이도를 선택해주세요");
        builder.setSingleChoiceItems(items, defaultItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SelectedItems.clear();
                SelectedItems.add(i);
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String msg="";
                if(!SelectedItems.isEmpty()){
                    int index = (int) SelectedItems.get(0);
                    msg = ListItems.get(index);

                }

                pLevel.setText(msg);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }

    void showNumOfPerson() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("1명");
        ListItems.add("2명");
        ListItems.add("4명");
        ListItems.add("8명");
        ListItems.add("8명 이상");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);
        final List SelectedItems = new ArrayList();
        int defaultItem = 0;
        SelectedItems.add(defaultItem);


        builder.setTitle("인원 선택해주세요");
        builder.setSingleChoiceItems(items, defaultItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SelectedItems.clear();
                SelectedItems.add(i);
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String msg="";
                if(!SelectedItems.isEmpty()){
                    int index = (int) SelectedItems.get(0);
                    msg = ListItems.get(index);

                }

                pNumOfPerson.setText(msg);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }
}