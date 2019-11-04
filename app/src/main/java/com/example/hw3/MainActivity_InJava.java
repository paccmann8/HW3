package com.example.hw3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;

public class MainActivity_InJava extends AppCompatActivity {

    private Button startCameraButton;
    private EditText nameEdittext;
    private Button displayUser;
    private ImageView myCameraImageView;
    private Button emiCalcBtn;
    private Button inputprincipal;
    private EditText principal;
    private Button inputinterest;
    private EditText interest;
    private Button inputlayouttenure;
    private EditText years;
    private Button calculate;
    private EditText emi;
    private Button inputtotalinterest;
    private EditText interesttotal;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777 && resultCode == RESULT_OK) {
            try {
                Bitmap fromCamera = (Bitmap) data.getExtras().get("data");
                myCameraImageView.setImageBitmap(fromCamera);

            } catch (Exception e) {
                //An error occurred..
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startCameraButton = findViewById(R.id.camera_button);
        myCameraImageView = findViewById(R.id.my_imageview);
        nameEdittext = findViewById(R.id.name_edittext);
        displayUser = findViewById(R.id.display_person_button);

        startCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 777);
            }
        });

        displayUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEdittext.getText().toString();
                HumanJava newHuman = new HumanJava(name, "Black/Brown");

                Intent humanIntent = new Intent(MainActivity_InJava.this, HumanActivity.class);
                humanIntent.putExtra("my_human_key", newHuman);
                startActivity(humanIntent);
            }
        });

        final EditText P = (EditText)findViewById(R.id.principal);
        final EditText I = (EditText)findViewById(R.id.interest);
        final EditText Y = (EditText)findViewById(R.id.years);
        final EditText TI = (EditText)findViewById(R.id.interest_total);




        final EditText result = (EditText)findViewById(R.id.emi) ;


        emiCalcBtn = (Button) findViewById(R.id.calculate);

        emiCalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st1 = P.getText().toString();
                String st2 = I.getText().toString();
                String st3 = Y.getText().toString();

                if (TextUtils.isEmpty(st1)) {
                    P.setError("Enter Prncipal Amount");
                    P.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st2)) {
                    I.setError("Enter Interest Rate");
                    I.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st3)) {
                    Y.setError("Enter Years");
                    Y.requestFocus();
                    return;
                }

                float p = Float.parseFloat(st1);
                float i = Float.parseFloat(st2);
                float y = Float.parseFloat(st3);

                float Principal = calPric(p);

                float Rate = calInt(i);

                float Months = calMonth(y);

                float Dvdnt = calDvdnt( Rate, Months);

                float FD = calFinalDvdnt (Principal, Rate, Dvdnt);

                float D = calDivider(Dvdnt);

                float emi = calEmi(FD, D);

                float TA = calTa (emi, Months);

                float ti = calTotalInt(TA, Principal);



                result.setText(String.valueOf(emi));




                TI.setText(String.valueOf(ti));

            }
        });
    }

    public  float calPric(float p) {

        return (float) (p);

    }

    public  float calInt(float i) {

        return (float) (i/12/100);

    }

    public  float calMonth(float y) {

        return (float) (y * 12);

    }

    public  float calDvdnt(float Rate, float Months) {

        return (float) (Math.pow(1+Rate, Months));

    }

    public  float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {

        return (float) (Principal * Rate * Dvdnt);

    }

    public  float calDivider(float Dvdnt) {

        return (float) (Dvdnt-1);

    }

    public  float calEmi(float FD, Float D) {

        return (float) (FD/D);

    }

    public  float calTa(float emi, Float Months) {

        return (float) (emi*Months);

    }

    public  float calTotalInt(float TA, float Principal) {

        return (float) (TA - Principal);

    }

}