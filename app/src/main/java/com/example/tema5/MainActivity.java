package com.example.tema5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
int n,n2;
String message = "";
 public  void randomNumberGenerator(){
    Random rand = new Random();
    n=rand.nextInt(5)+1;
   // n2=rand.nextInt()

}

private EditText etNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNr= findViewById(R.id.etNr);
       // randomNumberGenerator();
    }
   public void onClickStart(View view)
   { randomNumberGenerator();

       ExecutorService service = Executors.newSingleThreadExecutor();
       service.execute(new Runnable() {
           @Override

           public void run() {
                for (int i=0; i<10;i++){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           Random r = new Random();
                           n2=r.nextInt(5)+1;
                            if (n2==n){
                               Toast.makeText(MainActivity.this, "The bg thread wins", Toast.LENGTH_SHORT).show();
                           }
                        }
                    });


                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

           }
       });

   }
    public void onClickGuess(View view){
       // EditText etNr = (EditText) findViewById(R.id.etNr);
        int guessInt= Integer.parseInt(etNr.getText().toString());

        if((n>guessInt)||(n<guessInt)){
            Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show();
        }
        else if(n==guessInt){
            Toast.makeText(this, "correct, play again", Toast.LENGTH_SHORT).show();
          //  randomNumberGenerator();
        }
       // Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}