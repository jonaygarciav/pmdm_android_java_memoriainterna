package com.example.memoriainterna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private String filename = "file_mem_int.txt";

    private Button bt_write;
    private Button bt_read;
    private TextView tv_file_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Elements of Activity
        bt_write = findViewById(R.id.bt_write);
        bt_read = findViewById(R.id.bt_read);
        tv_file_content = findViewById(R.id.tv_file_content);

        bt_write.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                try
                {
                    OutputStreamWriter fout=
                            new OutputStreamWriter(
                                    openFileOutput(filename, Context.MODE_PRIVATE));

                    fout.write("Escribiendo en un fichero en la memoria interna.");
                    fout.close();

                    String mensaje = "Fichero " + filename + " creado correctamente.";
                    Toast toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG);
                    toast1.show();

                    bt_write.setEnabled(false);
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                }

            }
        });

        bt_read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                try
                {
                    BufferedReader fin =
                            new BufferedReader(
                                    new InputStreamReader(
                                            openFileInput(filename)));

                    String texto = fin.readLine();
                    tv_file_content.setText(texto);
                    fin.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde memoria interna");
                }

            }
        });

    }
}
