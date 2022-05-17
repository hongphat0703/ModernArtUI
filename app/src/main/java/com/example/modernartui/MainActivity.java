package com.example.modernartui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    static private final String URL = "http://www.MoMA.org";
    private View[] mRectangles =  new View[5];
    private RandomColor[] mColors = new RandomColor[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        mRectangles[0] = findViewById(R.id.tv1);
        mRectangles[1] = findViewById(R.id.tv2);
        mRectangles[2] = findViewById(R.id.tv3);
        mRectangles[3] = findViewById(R.id.tv4);
        mRectangles[4] = findViewById(R.id.tv5);

        restartColors();
        setRectanglesColors(seekBar.getProgress());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setRectanglesColors(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void setRectanglesColors(int step){
        for(int i = 0; i < 5; i++){
            mRectangles[i].setBackgroundColor(mColors[i].actualColor(step));
        }
    }

    private void restartColors(){
        for (int i = 0; i < 5; i++){
            mColors[i] =  new RandomColor(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id == R.id.action_more_info){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            TextView dialog_title = new TextView(this);
            dialog_title.setText(R.string.dialog_title);
            dialog_title.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_title.setTextSize(20);
            dialog_title.setPadding(100,30,100,30);
            builder.setCustomTitle(dialog_title);

            builder.setMessage(R.string.dialog_message);

            builder.setPositiveButton(R.string.not_now, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.setNegativeButton(R.string.visit_MOMA, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(intent);
                }
            });

            AlertDialog dialog = builder.show();

            TextView dialog_msg = (TextView) dialog.findViewById(android.R.id.message);
            dialog_msg.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_msg.setTextSize(30);
            dialog_msg.setPadding(10,60,10,60);
        }
        return super.onOptionsItemSelected(menuItem);
    }
}