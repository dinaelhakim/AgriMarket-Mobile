package crops.crops_categories.vegetables.all_vegetables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;
import crops.crops_types.Crops;

public class Vegetables extends AppCompatActivity {

    ListView listView;
    String[]  title;
    int[] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button
        initDate();
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(new GenericAdapter(getBaseContext(),title,images));
        listView.setOnItemClickListener(new GenericListener(1,this));
    }

    private void initDate(){
        images=new int[]{R.drawable.tomato,R.drawable.cucumber,R.drawable.carrots,R.drawable.zuccini,R.drawable.tomato,R.drawable.cucumber};
        title=getResources().getStringArray(R.array.vegetablesNames);
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
