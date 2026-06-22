package com.example.list_tugas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data Buah
        initData();

        ListView listView = findViewById(R.id.listView);
        FruitAdapter adapter = new FruitAdapter();
        listView.setAdapter(adapter);

        // Klik Item untuk Pindah ke Detail
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Fruit selectedFruit = fruitList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("fruit", selectedFruit);
            startActivity(intent);
        });
    }

    private void initData() {
        fruitList = new ArrayList<>();
        fruitList.add(new Fruit("Semangka", "🍉", "Afrika Selatan", "Manis segar",
                "Semangka adalah buah besar dengan kulit hijau dan daging merah yang mengandung 92% air. Sangat menyegarkan di musim panas.",
                Arrays.asList("Menghidrasi tubuh", "Menurunkan tekanan darah", "Kaya likopen anti-kanker")));
        
        fruitList.add(new Fruit("Mangga", "🥭", "Asia Tenggara", "Manis",
                "Mangga adalah buah tropis populer dengan daging buah yang lembut dan manis. Kaya akan vitamin C.",
                Arrays.asList("Meningkatkan imun", "Bagus untuk pencernaan", "Menjaga kesehatan mata")));
        
        fruitList.add(new Fruit("Anggur", "🍇", "Eropa", "Manis & Segar",
                "Anggur merupakan buah perdu merambat yang kaya akan antioksidan. Biasanya dikonsumsi langsung.",
                Arrays.asList("Menjaga kesehatan jantung", "Melawan penuaan dini", "Antioksidan tinggi")));
        
        fruitList.add(new Fruit("Stroberi", "🍓", "Eropa", "Asam Manis",
                "Stroberi adalah buah berwarna merah cerah yang memiliki aroma kuat dan rasa yang unik.",
                Arrays.asList("Kesehatan jantung", "Mencegah peradangan", "Kaya Vitamin C")));
        
        fruitList.add(new Fruit("Nanas", "🍍", "Amerika Selatan", "Asam Manis",
                "Nanas adalah buah tropis yang kaya akan vitamin, enzim, dan antioksidan.",
                Arrays.asList("Membantu pencernaan", "Mengurangi peradangan", "Memperkuat sistem imun")));
        
        fruitList.add(new Fruit("Durian", "🍈", "Asia Tenggara", "Manis Legit",
                "Durian dikenal sebagai 'Raja Buah' di Asia Tenggara karena ukurannya yang besar dan aromanya yang kuat.",
                Arrays.asList("Menambah energi", "Kaya zat besi", "Meningkatkan mood")));
    }

    // Adapter dengan ViewHolder agar Scroll Mulus
    private class FruitAdapter extends BaseAdapter {
        @Override
        public int getCount() { return fruitList.size(); }
        @Override
        public Object getItem(int position) { return fruitList.get(position); }
        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_fruit, parent, false);
                holder = new ViewHolder();
                holder.icon = convertView.findViewById(R.id.fruitIcon);
                holder.name = convertView.findViewById(R.id.fruitName);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Fruit fruit = fruitList.get(position);
            holder.icon.setText(fruit.getIcon());
            holder.name.setText(fruit.getName());

            return convertView;
        }

        class ViewHolder {
            TextView icon;
            TextView name;
        }
    }
}
