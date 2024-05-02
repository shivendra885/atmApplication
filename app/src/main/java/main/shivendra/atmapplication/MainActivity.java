package main.shivendra.atmapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView amount, one, two, five, twenty;
    Button btnWithdraw;
    EditText textAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        amount = findViewById(R.id.amount);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        five = findViewById(R.id.five);
        twenty = findViewById(R.id.twenty);
        btnWithdraw = findViewById(R.id.btnWithdraw);
        textAmount = findViewById(R.id.textAmount);
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println(textAmount + " " + amount);
                int currAmo = Integer.parseInt(amount.getText().toString());
                int withDrawAmo = Integer.parseInt(amount.getText().toString());
                amount.setText(String.valueOf(currAmo-withDrawAmo));
            }
        });
    }
}
