package hilay.edu.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;

public class MainActivity extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener,
        DialogInterface.OnClickListener {

    Button btnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTime = (Button) findViewById(R.id.btnTime);
    }

    public void pickTheTime(View view) {
        TimePickerDialog dialog = new
                TimePickerDialog(this, this, 00, 00, true);
        dialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(this, "Hour is set for " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
    }

    public void showStandardDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("שים לב")
                .setMessage("האם אתה בטוח שברצונך לצאת?")
                .setPositiveButton("כן", this)
                .setNegativeButton("לא", this);
        AlertDialog dialog = builder.show();
//        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case AlertDialog.BUTTON_POSITIVE:
                System.exit(0);
                //finish() only exits current activity
                break;
            case AlertDialog.BUTTON_NEGATIVE:

                break;
        }
    }

    public void showListDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final String[] colors = {"Red", "Green", "Blue"};

        builder.setTitle("Pick a color")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedColor = colors[which];
                        Toast.makeText(MainActivity.this, selectedColor, Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }
    ArrayList choices = new ArrayList();

    public void multiChoiceDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] toppings = getResources().getStringArray(R.array.toppings);
        builder.setMultiChoiceItems(toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked)
                    choices.add(toppings[which]);
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "" + choices.toString(),
                        Toast.LENGTH_SHORT).show();
                choices.clear();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
