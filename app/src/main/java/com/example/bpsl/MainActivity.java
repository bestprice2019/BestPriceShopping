package com.example.bpsl;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.example.bpsl.R;




public class MainActivity extends AppCompatActivity {

    private Button MainButton;

//    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        MainButton = (Button) findViewById(R.id.newlistbutton);
        MainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewListDescription.class);
                startActivity(intent);

            }
        });

        MainButton = (Button) findViewById(R.id.savedbutton);
        MainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SavedListSecond.class);
                startActivity(intent);
            }
        });


        MainButton = (Button) findViewById(R.id.findstoresbutton);
        MainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });




    }

//    public void getItem(){
//        myDb = new Database(this);
//        InputStream in = getResources().openRawResource(R.raw.ten);
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(in, Charset.forName("UTF-8"))
//        );
//
//        String line ="";
//        try{while((line = reader.readLine())!=null){
//            String[] tokens = line.split(",");
//            myDb.insertDataItemTable(tokens[0],tokens[1],tokens[2]);
//        }
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
