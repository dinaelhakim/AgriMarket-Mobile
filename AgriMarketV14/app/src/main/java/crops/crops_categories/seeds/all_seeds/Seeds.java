package crops.crops_categories.seeds.all_seeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;
import crops.crops_types.Crops;

public class Seeds extends AppCompatActivity {

    ListView listView;
    String[]  title;
    int[] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button
        listView=(ListView)findViewById(R.id.listView);
        initData();
        listView.setAdapter(new GenericAdapter(getBaseContext(),title,images));
        listView.setOnItemClickListener(new GenericListener(5,this));
    }
    private void initData(){
        images=new int[]{R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat,R.drawable.b2oliat};
        title=getResources().getStringArray(R.array.seedsNames);
    }
    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  android.R.id.home:
                Intent intent=new Intent(this, Crops.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, Crops.class);
        startActivity(intent);
        finish();
    }
}
