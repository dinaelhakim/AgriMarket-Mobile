package crops.crops_categories.fruits.all_fruits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;
import crops.crops_types.Crops;

public class Fruits extends AppCompatActivity {

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
        listView.setOnItemClickListener(new GenericListener(3,this));

    }

    private void initDate(){
        title=getResources().getStringArray(R.array.fruitsNames);
        images=new int[]{R.drawable.apple,R.drawable.strawberry,R.drawable.pineapple,R.drawable.orange,R.drawable.mango,R.drawable.banana,R.drawable.fruits,R.drawable.pineapple};
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
