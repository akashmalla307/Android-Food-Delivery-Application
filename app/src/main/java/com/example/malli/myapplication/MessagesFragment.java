package com.example.malli.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by malli on 3/12/2016.
 */
public class MessagesFragment extends Fragment {

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        startActivity(emailIntent);
   */
        Intent intent = new Intent(Intent.ACTION_SEND);

        String[] strTo = { getString("abhilash576@gmail.com") };

        intent.putExtra(Intent.EXTRA_EMAIL, strTo);
     //   intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));
     //   intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.mail_body));

      //  Uri attachments = Uri.parse(image_path);
      //  intent.putExtra(Intent.EXTRA_STREAM, attachments);

        intent.setType("message/rfc822");

        intent.setPackage("com.google.android.gm");

        startActivity(intent);
    }

    private String getString(String s) {
        return "akashmalla07@gmail.com";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
