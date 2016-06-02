package ta2awi.all_t2awi;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import luncher.landing_page.MainActivity;

import com.example.lenovo.testslidenerd.AddProduct;
import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;

public class Ta2awy extends AppCompatActivity {

    ListView listView;
    String[]  title;
    String[]  subTitle;
    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button
        listView=(ListView)findViewById(R.id.listView);
        initDate();
        listView.setAdapter(new GenericAdapter(getBaseContext(),title,subTitle,images));
        listView.setOnItemClickListener(new GenericListener(11,this));
    }

    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initDate(){
        title=getResources().getStringArray(R.array.t2awiTypes);
        subTitle=getResources().getStringArray(R.array.t2awiDescription);
        images= new int[]{R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat};

    }


}
