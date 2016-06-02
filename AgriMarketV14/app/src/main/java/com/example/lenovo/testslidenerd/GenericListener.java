package com.example.lenovo.testslidenerd;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import chemicals.all_chemicals.Chemicals;
import chemicals.specific_chemicals.SpecificChemical;
import crops.crops_categories.fruits.all_fruits.Fruits;
import crops.crops_categories.fruits.specific_fruit.SpecificFruit;
import crops.crops_categories.seeds.all_seeds.Seeds;
import crops.crops_categories.seeds.specific_seed.SpecificSeed;
import crops.crops_categories.vegetables.all_vegetables.Vegetables;
import crops.crops_categories.vegetables.specific_vegetable.SpecificVegetable;
import crops.crops_types.Crops;
import fertilizers.all_fertilizers.Fertilizers;
import fertilizers.specific_fertilizer.SpecificFertilizer;
import luncher.landing_page.MainActivity;
import ta2awi.all_t2awi.Ta2awy;
import ta2awi.specific_t2awi.SpecificT2awi;

public class GenericListener implements AdapterView.OnItemClickListener {

    MainActivity mainActivity;
    Crops crops;
    Vegetables vegetables;
    SpecificVegetable specificVegetable;
    Fruits fruits;
    SpecificFruit specificFruit;
    Seeds seeds;
    SpecificSeed specificSeed;
    Fertilizers fertilizers;
    SpecificFertilizer specificFertilizer;
    Chemicals chemicals;
    SpecificChemical specificChemical;
    Ta2awy ta2awy;
    SpecificT2awi specificT2awi;
    Object object;
    int activityNumber;
    public GenericListener(int activityNumber,Object object) {
        this.activityNumber=activityNumber;
        this.object=object;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (activityNumber) {

            case -1: {
                mainActivity=(MainActivity)object;
                Intent intent=null;
                switch (position){
                    case 0:    {intent = new Intent(mainActivity, Crops.class); break;}
                    case 1:    {intent = new Intent(mainActivity, Fertilizers.class); break;}
                    case 2:    {intent = new Intent(mainActivity, Chemicals.class); break;}
                    case 3:    {intent = new Intent(mainActivity, Ta2awy.class); break;}
                }
                mainActivity.startActivity(intent);
                mainActivity.finish();
                break;
            }  case 0: {
                crops=(Crops)object;
                Intent intent=null;
                switch (position){
                    case 0:    {intent = new Intent(crops, Vegetables.class); break;}
                    case 1:    {intent = new Intent(crops, Fruits.class); break;}
                    case 2:    {intent = new Intent(crops, Seeds.class); break;}
                }
                crops.startActivity(intent);
                crops.finish();
                break;
            }
            case 1: {
                vegetables=(Vegetables)object;
                Intent intent = new Intent(vegetables, SpecificVegetable.class);
                vegetables.startActivity(intent);
                vegetables.finish();
                break;
            }
            case 2: {
                specificVegetable=(SpecificVegetable)object;
                Intent intent = new Intent(specificVegetable, ProductDetails.class);
                intent.putExtra("from","SpecificVegetable");
                specificVegetable.startActivity(intent);
                specificVegetable.finish();
                break;
            }
            case 3: {
                fruits=(Fruits)object;
                Intent intent = new Intent(fruits, SpecificFruit.class);
                fruits.startActivity(intent);
                fruits.finish();
                break;
            }
            case 4: {
                specificFruit=(SpecificFruit)object;
                Intent intent = new Intent(specificFruit, ProductDetails.class);
                intent.putExtra("from","SpecificFruit");
                specificFruit.startActivity(intent);
                specificFruit.finish();
                break;
            }
            case 5: {
                seeds=(Seeds)object;
                Intent intent = new Intent(seeds, SpecificSeed.class);
                seeds.startActivity(intent);
                seeds.finish();
                break;
            }
            case 6: {
                specificSeed=(SpecificSeed)object;
                Intent intent = new Intent(specificSeed, ProductDetails.class);
                intent.putExtra("from","SpecificSeed");
                specificSeed.startActivity(intent);
                specificSeed.finish();
                break;
            }
            case 7: {
                fertilizers=(Fertilizers)object;
                Intent intent = new Intent(fertilizers, SpecificFertilizer.class);
                fertilizers.startActivity(intent);
                fertilizers.finish();
                break;
            }
            case 8: {
                specificFertilizer=(SpecificFertilizer)object;
                Intent intent = new Intent(specificFertilizer, ProductDetails.class);
                intent.putExtra("from","SpecificFertilizer");
                specificFertilizer.startActivity(intent);
                specificFertilizer.finish();
                break;
            }
            case 9: {
                chemicals=(Chemicals)object;
                Intent intent = new Intent(chemicals, SpecificChemical.class);
                chemicals.startActivity(intent);
                chemicals.finish();
                break;
            }
            case 10: {
                specificChemical=(SpecificChemical)object;
                Intent intent = new Intent(specificChemical, ProductDetails.class);
                intent.putExtra("from","SpecificChemical");
                specificChemical.startActivity(intent);
                specificChemical.finish();
                break;
            }
            case 11: {
                ta2awy=(Ta2awy)object;
                Intent intent = new Intent(ta2awy, SpecificT2awi.class);
                ta2awy.startActivity(intent);
                ta2awy.finish();
                break;
            }
            case 12: {
                specificT2awi=(SpecificT2awi)object;
                Intent intent = new Intent(specificT2awi, ProductDetails.class);
                intent.putExtra("from","SpecificT2awi");
                specificT2awi.startActivity(intent);
                specificT2awi.finish();
                break;
            }
        }
    }
}
