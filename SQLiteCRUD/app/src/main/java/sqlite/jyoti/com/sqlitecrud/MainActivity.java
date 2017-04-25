package sqlite.jyoti.com.sqlitecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getName();

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intView();
        mDatabaseHelper=new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEnrty = editText.getText().toString();
                if (editText.length()!=0){
                    addUserData(newEnrty);
                    editText.setText("");
                    showToast("Added Sucessfully");
                }
                else{
                    showToast("You must put something in the text field!");
                }
            }
        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }


    public void intView(){
        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
    }


    public void addUserData(String newEntry){

        boolean insertData = mDatabaseHelper.addData(newEntry);
        if (insertData){
            showToast("Data inserted sucessfully");
        }
        else
        {
            showToast("Something went wrong");
        }

    }



    public void showToast(String message){

        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

    }
}
