package bestpriceshopping.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button newListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newListButton = (Button)findViewById(R.id.newList);
        newListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewList();
            }
        });
    }

    public void openNewList(){
        Intent intent = new Intent(this, NewList.class);
        startActivity(intent);
    }
}
