package chemicals;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;
import chemicals.specific_chemicals.SpecificChemical;

public class ChemicalsTabPagesAdapter extends PagerAdapter {

    private static final int NUM_TABS = 3;

    private ListView page1;
    private ListView page2;
    private ListView page3;
    private SpecificChemical specificChemical;
    private Context context;
    String[] title;
    String[] subTitle;
    int[] images;
    private final int[] titles = {R.string.pricePage, R.string.locationPage, R.string.datePage};

    public ChemicalsTabPagesAdapter(SpecificChemical specificChemical, Context context, String[] title, String[] subTitle, int[] images) {
        this.specificChemical = specificChemical;
        this.context = context;
        this.title = title;
        this.subTitle = subTitle;
        this.images = images;
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return specificChemical.getString(titles[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View page;
        switch (position) {
            case 0:
                if (page1 == null) {
                    page1 = (ListView) LayoutInflater.from(specificChemical).inflate(R.layout.generic_layout, collection, false);
                    initListView(page1);
                }
                page = page1;
                break;
            case 1:
                if (page2 == null) {
                    page2 = (ListView) LayoutInflater.from(specificChemical).inflate(R.layout.generic_layout, collection, false);
                    initListView(page2);
                }
                page = page2;
                break;
            default:
                if (page3 == null) {
                    page3 = (ListView) LayoutInflater.from(specificChemical).inflate(R.layout.generic_layout, collection, false);
                    initListView(page3);
                }
                page = page3;
                break;
        }

        collection.addView(page, 0);

        return page;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    private void initListView(ListView page) {
        page.setAdapter(new GenericAdapter(context,title,subTitle,images));
        page.setOnItemClickListener(new GenericListener(10,specificChemical));
    }
}
