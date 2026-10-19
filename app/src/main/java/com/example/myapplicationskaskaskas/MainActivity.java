package com.example.myapplicationskaskaskas;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText editName;
    private EditText editSurname;
    private EditText editEmail;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.name);
        editSurname = findViewById(R.id.surname);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.registerBtn);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        btnRegister.setOnClickListener(v -> sprawdzDane());
    }

    private void sprawdzDane() {


        String imie = editName.getText().toString().trim();
        String nazwisko = editSurname.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String haslo = editPassword.getText().toString().trim();


        if (imie.isEmpty() || nazwisko.isEmpty()
                || email.isEmpty() || haslo.isEmpty()) {

            Toast.makeText(this,
                    "Uzupełnij wszystkie pola",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            Toast.makeText(this,
                    "Niepoprawny adres email",
                    Toast.LENGTH_SHORT).show();
            return;
        }


        String bladHasla = sprawdzHaslo(haslo);

        if (!bladHasla.isEmpty()) {
            Toast.makeText(this,
                    "Hasło musi " + bladHasla,
                    Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this,
                "Dane są poprawne",
                Toast.LENGTH_SHORT).show();

    }

    private String sprawdzHaslo(String tekst) {

        StringBuilder bledy = new StringBuilder();

        if (tekst.length() < 8) {
            bledy.append("mieć co najmniej 8 znaków, ");
        }

        if (!tekst.matches(".*[A-Z].*")) {
            bledy.append("zawierać dużą literę, ");
        }

        if (!tekst.matches(".*[a-z].*")) {
            bledy.append("zawierać małą literę, ");
        }

        if (!tekst.matches(".*[^a-zA-Z0-9].*")) {
            bledy.append("zawierać znak specjalny, ");
        }


        if (bledy.length() > 0) {
            bledy.setLength(bledy.length() - 2);
        }

        return bledy.toString();
    }
}