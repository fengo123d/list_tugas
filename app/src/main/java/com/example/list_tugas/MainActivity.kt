package com.example.list_tugas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.list_tugas.ui.theme.List_tugasTheme

// 1. Model Data Buah
data class Fruit(
    val name: String,
    val icon: String, // Emoji sebagai pengganti gambar
    val origin: String,
    val taste: String,
    val description: String,
    val healthBenefits: List<String>,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            List_tugasTheme {
                FruitApp()
            }
        }
    }
}

@Composable
fun FruitApp() {
    // Data list buah (6 buah)
    val fruitList = remember {
        listOf(
            Fruit(
                "Semangka", "🍉", "Afrika Selatan", "Manis segar",
                "Semangka adalah buah besar dari keluarga Cucurbitaceae dengan kulit hijau dan daging merah yang mengandung 92% air. Sangat menyegarkan di musim panas.",
                listOf("Menghidrasi tubuh", "Menurunkan tekanan darah", "Mencegah kerusakan otot", "Kaya likopen anti-kanker")
            ),
            Fruit(
                "Mangga", "🥭", "Asia Tenggara", "Manis",
                "Mangga adalah buah tropis yang sangat populer dengan daging buah yang lembut dan manis. Kaya akan vitamin C dan serat.",
                listOf("Meningkatkan imun", "Bagus untuk pencernaan", "Menjaga kesehatan mata", "Menurunkan kolesterol")
            ),
            Fruit(
                "Anggur", "🍇", "Eropa", "Manis & Segar",
                "Anggur merupakan buah perdu merambat yang kaya akan antioksidan. Biasanya dikonsumsi langsung atau dibuat jus.",
                listOf("Mencegah kanker", "Menjaga kesehatan jantung", "Melawan penuaan dini", "Mengatur gula darah")
            ),
            Fruit(
                "Stroberi", "🍓", "Eropa", "Asam Manis",
                "Stroberi adalah buah berwarna merah cerah yang memiliki aroma kuat dan rasa yang unik.",
                listOf("Kaya Vitamin C", "Kesehatan jantung", "Mencegah peradangan", "Menjaga kesehatan kulit")
            ),
            Fruit(
                "Nanas", "🍍", "Amerika Selatan", "Asam Manis",
                "Nanas adalah buah tropis yang kaya akan vitamin, enzim, dan antioksidan. Bisa membantu memperkuat sistem imun.",
                listOf("Membantu pencernaan", "Mengurangi peradangan", "Kaya antioksidan", "Mempercepat penyembuhan luka")
            ),
            Fruit(
                "Durian", "🍈", "Asia Tenggara", "Manis Legit",
                "Durian dikenal sebagai 'Raja Buah' di Asia Tenggara, terkenal karena ukurannya yang besar dan baunya yang menyengat.",
                listOf("Menambah energi", "Menjaga tekanan darah", "Meningkatkan mood", "Kaya akan zat besi")
            )
        )
    }

    // State untuk navigasi
    var selectedFruit by remember { mutableStateOf<Fruit?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (selectedFruit == null) {
            FruitListScreen(fruits = fruitList) { selectedFruit = it }
        } else {
            FruitDetailScreen(fruit = selectedFruit!!) { selectedFruit = null }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitListScreen(fruits: List<Fruit>, onFruitClick: (Fruit) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Buah-buahan", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1B1B1B),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(fruits) { fruit ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onFruitClick(fruit) }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = fruit.icon, fontSize = 40.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = fruit.name, fontSize = 18.sp)
                }
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitDetailScreen(fruit: Fruit, onBack: () -> Unit) {
    BackHandler { onBack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail: ${fruit.name}", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1B1B1B))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
        ) {
            // Bagian Header (Gambar Ikon)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFF388E3C)),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier.size(150.dp),
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.15f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = fruit.icon, fontSize = 100.sp)
                    }
                }
            }

            // Banner Nama Buah
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2E7D32))
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = fruit.name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Kartu Informasi
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // Baris Asal & Rasa
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        InfoItem(label = "🌍 Asal", value = fruit.origin)
                        VerticalDivider(modifier = Modifier.height(40.dp))
                        InfoItem(label = "😋 Rasa", value = fruit.taste)
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp))

                    // Deskripsi
                    Text(
                        text = "📖 Deskripsi",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF388E3C)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = fruit.description,
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Manfaat Kesehatan
                    Text(
                        text = "✅ Manfaat Kesehatan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF388E3C)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    fruit.healthBenefits.forEach { benefit ->
                        Text(
                            text = "• $benefit",
                            modifier = Modifier.padding(start = 8.dp, bottom = 6.dp),
                            fontSize = 15.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
