package main.shivendra.atmapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView amount, one, two, five, twenty;
    Button btnWithdraw;
    EditText textAmount;

    TableLayout tableLayout;
    List<Transaction> transHistory = new ArrayList<>();
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
        tableLayout = findViewById(R.id.transTable);

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
                    return;
                }
                if(withdrawAmount % 100 != 0) {
                    Toast.makeText(MainActivity.this,"You can Withdraw only 100 ,200, 500, 2000", Toast.LENGTH_LONG).show();
                    return;
                }
                int remainingAmount = currAmount - withdrawAmount;
                amount.setText(String.valueOf(remainingAmount));
                Transaction trans = new Transaction();
                trans.debitAmount = withdrawAmount;
                trans.availAmount = remainingAmount;

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

                transHistory.add(trans);
                for (int i=0;i<transHistory.size();i++) {
                    System.out.println(transHistory.get(i).debitAmount+" "+transHistory.get(i).availAmount);
                    TableRow newRow = new TableRow(MainActivity.this);

                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.weight = 1;

                    TextView amountDebit = new TextView(MainActivity.this);
                    amountDebit.setLayoutParams(layoutParams);
                    amountDebit.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    TextView amountCredit = new TextView(MainActivity.this);
                    amountCredit.setLayoutParams(layoutParams);
                    amountCredit.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    TextView reference = new TextView(MainActivity.this);
                    reference.setLayoutParams(layoutParams);
                    reference.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

                    amountDebit.setTextColor(Color.WHITE);
                    amountCredit.setTextColor(Color.WHITE);
                    reference.setTextColor(Color.WHITE);

                    amountDebit.setText(String.valueOf(transHistory.get(i).debitAmount));
                    amountCredit.setText(String.valueOf(transHistory.get(i).debitAmount));
                    reference.setText(String.valueOf(System.currentTimeMillis()));

                    newRow.addView(reference);
                    newRow.addView(amountDebit);
                    newRow.addView(amountCredit);
                    tableLayout.addView(newRow);
                }
                transHistory.removeAll(transHistory);
            }
        });
    }
}
