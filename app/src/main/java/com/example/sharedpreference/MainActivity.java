package com.example.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sharedpreference.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.moveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.text.setText(binding.editTextBtn.getText().toString());
            }
        });

        againLoadText();

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void saveText() {
        String myText = binding.text.getText().toString();
        //by using share preference we actually save small data
        //this code save the data into shared preference
        SharedPreferences preferences = getSharedPreferences("sharedPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("text", myText);

        //save switch in shared preference
        editor.putBoolean("switch",binding.switchBtn.isChecked());
        editor.apply();
        Toast.makeText(this, "Data is saved", Toast.LENGTH_SHORT).show();
    }

    private void againLoadText(){
        //this method load the data during app opening which is shared in sharedpreference
        String txt;
        Boolean switchOnOff;
        SharedPreferences preferences = getSharedPreferences("sharedPrefs",MODE_PRIVATE);
        txt = preferences.getString("text","Sanjoy ");
        switchOnOff = preferences.getBoolean("switch",false);

        binding.text.setText(txt);
        binding.switchBtn.setChecked(switchOnOff);

    }
}