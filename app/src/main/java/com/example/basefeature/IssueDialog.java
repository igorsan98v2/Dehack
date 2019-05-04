package com.example.basefeature;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.net.URI;
import java.security.Policy;

public class IssueDialog extends DialogFragment {

    public String name = "easy";
    private String category;
    private String issue;
    private GoogleMap mMap;
    private IssueDialog that = this;
    private LatLng coord;
    private  FragmentManager fragmentManager;
    private static final int PERMISSIONS_REQUEST_ACCESS_FILES = 2;
    private ImageView imageView;
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data!=null){
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContext().getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            // Now we need to set the GUI ImageView data with data read from the picked file.
            imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));

            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
        }
    }
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            // Inflate the layout to use as dialog or embedded fragment
            final View view =  inflater.inflate(R.layout.add_event_dialog, container, false);

            Button button = view.findViewById(R.id.ok);
            ImageView issuePhoto = view.findViewById(R.id.issue_image);
            imageView = issuePhoto;
            issuePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        //Create an Intent with action as ACTION_PICK
                        Intent intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        // Sets the type as image/*. This ensures only components of type image are selected
                        intent.setType("image/*");
                        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                        String[] mimeTypes = {"image/jpeg", "image/png"};
                        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                        // Launching the Intent
                        startActivityForResult(intent,PERMISSIONS_REQUEST_ACCESS_FILES);


                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     EditText editText = view.findViewById(R.id.issue_title);
                     String name = editText.getText().toString();

                     Log.d("XYZ",name);


                     final Marker marker= mMap.addMarker(new MarkerOptions().position(that.coord).title(name));

                     mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                         @Override
                         public boolean onMarkerClick(Marker eventMarker) {
                             if(eventMarker.equals(marker)){
                                 Log.d("MARKER",marker.getTitle());

                                 IssueInfoActivity newFragment = new IssueInfoActivity();
                                 FragmentTransaction transaction = fragmentManager.beginTransaction();
                                 // For a little polish, specify a transition animation
                                 transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                 // To make it fullscreen, use the 'content' root view as the container
                                 // for the fragment, which is always the root view for the activity
                                 transaction.add(android.R.id.content, newFragment)
                                         .addToBackStack(null).commit();
                                 return true;

                             }
                             return false;
                         }
                     });



                     that.dismiss();
                }
            });
            return view;
        }

        /** The system calls this only when creating the layout in a dialog. */
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // The only reason you might override this method when using onCreateView() is
            // to modify any dialog characteristics. For example, the dialog includes a
            // title by default, but your custom layout might not need it. So here you can
            // remove the dialog title, but you must call the superclass to get the Dialog.
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }




    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }
    public void setCoord(LatLng coord){
        this.coord = coord;
    }
    private String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
