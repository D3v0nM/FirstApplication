package com.devon.firstapplication;

import android.content.Context;
import android.widget.Toast;

public class Tools {
    private Context applicationContext;

    public static void exceptionToast(Context context, String error) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();

      /*  LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.android);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Hello! This is a custom toast!");*/
//
//        Toast toast = new Toast(getApplicationContext());
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//       toast.setView;
//        toast.show();
    }

//    public Context getApplicationContext() {
//        return applicationContext;
    }


