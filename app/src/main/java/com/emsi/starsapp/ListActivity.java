package com.emsi.starsapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emsi.starsapp.adapter.StarAdapter;
import com.emsi.starsapp.beans.Star;
import com.emsi.starsapp.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service =null;
    private static final String TAG = "StarAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        //insérer le code
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void init(){
        service.create(new Star("Karim Benzema", "https://www.lequipe.fr/_medias/img-photo-jpg/benzema-a-ete-tres-touche-par-l-accueil-des-supporters-lyonnais-p-lahalle-l-equipe/1500000001539608/548:220,1991:1182-828-552-75/7d539.jpg", 3.5f));
        service.create(new Star("Lionel Messi", "https://cdn.foot-sur7.fr/768x512/articles/2018/03/Lionel%20Messi%20iconsport_icon_spu_111117_33_34.jpg", 3));
        service.create(new Star("Mohamed Salah",
                "https://images.beinsports.com/9tX5r8Ve2HWCpgKBc1Z3YIhYDUY=/full-fit-in/1000x0/4109746-PANORAMIC_217726_0007.jpg", 5));
        service.create(new Star("Kevin De Bruyne", "https://i2-prod.manchestereveningnews.co.uk/sport/football/football-news/article21798106.ece/ALTERNATES/s810/0_GettyImages-1235751910.jpg", 1));
        service.create(new Star("Cristiano Ronaldo", "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt64890c907349b031/60db38a98491e60f790a36a9/0d5815259ae6a395605fe51fb0bad3a8fe928351.jpg?auto=webp&format=pjpg&quality=80&width=1440", 5));
        service.create(new Star("David Beckham", "https://i.pinimg.com/564x/54/94/8a/54948ac2419e92a92883a6de0c0a052e.jpg", 1));
        service.create(new Star("Hakim Ziyech", "https://www.footalgerien.com/wp-content/uploads/2022/02/hakim-ziyech-768x432.jpg", 1));
        service.create(new Star("Robert Lewandowski", "https://s.bundesliga.com/assets/img/1180000/1171775_imgw968.jpg", 1));
        service.create(new Star("Andres Iniesta", "https://sport360.com/wp-content/uploads/2018/03/andres-iniesta-1.jpg", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new
                                                  SearchView.OnQueryTextListener() {
                                                      @Override
                                                      public boolean onQueryTextSubmit(String query) {
                                                          return true;
                                                      }
                                                      @Override
                                                      public boolean onQueryTextChange(String newText) {
                                                          if (starAdapter != null){
                                                              starAdapter.getFilter().filter(newText);
                                                          }
                                                          return true;
                                                      }
                                                  });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }



}
