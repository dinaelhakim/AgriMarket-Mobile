package crops.crops_types;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;
import luncher.landing_page.MainActivity;


public class Crops extends AppCompatActivity {

    ListView listView;
    FloatingActionButton floatingActionButton;
    int[] images;
    String[]  title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crops_types);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button
        listView=(ListView)findViewById(R.id.listView);
        initData();
        listView.setAdapter(new GenericAdapter(getBaseContext(),title,images));
        listView.setOnItemClickListener(new GenericListener(0,this));
        floatingActionButton=(FloatingActionButton)findViewById(R.id.addButton);
        floatingActionButton.setVisibility(View.INVISIBLE);

    }

    private void initData(){
        title=getResources().getStringArray(R.array.cropsMainList);
        images=new int[]{R.drawable.vegetables,R.drawable.fruits,R.drawable.seeds};
    }


    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  android.R.id.home:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
