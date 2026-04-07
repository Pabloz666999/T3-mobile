package com.example.aplikasiformidentitasmahasiswa

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplikasiformidentitasmahasiswa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Phase 3: Inisialisasi ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set OnClickListener pada tombol tampilkan
        binding.btnTampilkan.setOnClickListener {
            tampilkanData()
        }
    }

    private fun tampilkanData() {
        val nama = binding.etNama.text.toString().trim()
        
        // 1. Implementasi Validasi Nama
        if (nama.isEmpty()) {
            binding.etNama.error = "Nama tidak boleh kosong"
            return
        }

        // 2. Cek RadioButton yang terpilih (Gender)
        val selectedGenderId = binding.rgGender.checkedRadioButtonId
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
            return
        }

        val gender = when (selectedGenderId) {
            R.id.rb_laki -> getString(R.string.gender_laki)
            R.id.rb_perempuan -> getString(R.string.gender_perempuan)
            else -> ""
        }

        // 3. Cek status CheckBox (Hobi)
        val listHobi = mutableListOf<String>()
        if (binding.cbMembaca.isChecked) listHobi.add(getString(R.string.hobi_membaca))
        if (binding.cbCoding.isChecked) listHobi.add(getString(R.string.hobi_coding))
        if (binding.cbOlahraga.isChecked) listHobi.add(getString(R.string.hobi_olahraga))

        val hobi = if (listHobi.isEmpty()) "Tidak ada hobi" else listHobi.joinToString(", ")

        // 4. Tampilkan Hasil dengan format yang rapi
        val hasil = """
            Nama      : $nama
            Kelamin : $gender
            Hobi       : $hobi
        """.trimIndent()

        binding.tvHasil.text = hasil
    }
}
