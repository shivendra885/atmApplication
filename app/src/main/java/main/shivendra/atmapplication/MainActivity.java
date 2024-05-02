package main.shivendra.atmapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                String amountStr = textAmount.getText().toString();
                if(amountStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter the withdrawal amount.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int currAmount = Integer.parseInt(amount.getText().toString());
                int withdrawAmount = Integer.parseInt(textAmount.getText().toString());

                if (withdrawAmount > currAmount) {
                    Toast.makeText(MainActivity.this, "Insufficient funds!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (withdrawAmount < 100) {
                    Toast.makeText(MainActivity.this, "Minimum Withdraw Amount is : 100!", Toast.LENGTH_SHORT).show();
                }
                int remainingAmount = currAmount - withdrawAmount;
                amount.setText(String.valueOf(remainingAmount));

                int twentiesToDeduct = withdrawAmount / 2000;
                withdrawAmount %= 2000;
                int fiftiesToDeduct = withdrawAmount / 500;
                withdrawAmount %= 500;
                int fivesToDeduct = withdrawAmount / 200;
                withdrawAmount %= 200;
                int onesToDeduct = withdrawAmount / 100;

                // Update the number of twenties, fifties, fives, and ones
                int currentTwenties = Integer.parseInt(twenty.getText().toString());
                int currentFifties = Integer.parseInt(five.getText().toString());
                int currentFives = Integer.parseInt(two.getText().toString());
                int currentOnes = Integer.parseInt(one.getText().toString());

                // Subtract the calculated numbers of twenties, fifties, fives, and ones
                twenty.setText(String.valueOf(currentTwenties - twentiesToDeduct));
                five.setText(String.valueOf(currentFifties - fiftiesToDeduct));
                two.setText(String.valueOf(currentFives - fivesToDeduct));
                one.setText(String.valueOf(currentOnes - onesToDeduct));

                textAmount.setText("");
            }
        });
    }
}
