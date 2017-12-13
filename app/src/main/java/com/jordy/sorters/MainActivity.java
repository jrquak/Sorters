package com.jordy.sorters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText aantalStudentenField;
    private EditText aantalHerhalingen;
    private TextView bubbleTime;
    private TextView bucketTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button maakStudenten = (Button) findViewById(R.id.maakStudenten);
        maakStudenten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //opgaveEenPuntEen();
                effiëntieSorters();
            }
        });

        aantalStudentenField = (EditText) findViewById(R.id.aantalStudenten);
        aantalHerhalingen = (EditText) findViewById(R.id.aantalHeralingen);
        bubbleTime = (TextView) findViewById(R.id.bubbleTime);
        bucketTime = (TextView) findViewById(R.id.bucketTime);
        //maakStudenten = (Button) findViewById(R.id);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testRandomGetallen() {
        final int aantalStudenten = Integer.parseInt(aantalStudentenField.getText().toString());
        final int laagsteCijferInteger = 1;
        final int hoogsteCijferInteger = 10;
        final int correctie = 10;
        final int gecorrigeerdeOndergrens = correctie * laagsteCijferInteger;
        final int gecorrigeerdeBovengrens = correctie * hoogsteCijferInteger;
        final int aantalMogelijkeCijfers = gecorrigeerdeBovengrens - gecorrigeerdeOndergrens + 1;
        final int[] telling = new int[aantalMogelijkeCijfers];
        maakWillekeurigeTelling(aantalStudenten, gecorrigeerdeOndergrens,
                gecorrigeerdeBovengrens, telling, correctie);
        printTellingen(aantalMogelijkeCijfers, gecorrigeerdeOndergrens,
                correctie, telling);
        grootsteVerschil(telling);
    }

    public void maakWillekeurigeTelling(final int aantalStudenten,
                                                final int gecorrigeerdeOndergrens, final int gecorrigeerdeBovengrens,
                                                final int[] telling, final int correctie) {
        final StudentFactory studentMaker = new StudentFactory();
        for (int i = 0; i < aantalStudenten; i++) {
            final int willekeurigCijfer = RandomExpended.randomNumberInsideBoundariesInteger(
                    gecorrigeerdeOndergrens, gecorrigeerdeBovengrens);
            telling[willekeurigCijfer - gecorrigeerdeOndergrens] += 1;
            System.out.println(studentMaker.makeStudent((double) willekeurigCijfer /
                    correctie).getGrade());
        }
        System.out.println("Klaar met studenten maken");
    }

    public void printTellingen(final int aantalMogelijkeCijfers,
                                       final int gecorrigeerdeOndergrens, final int correctie,
                                       final int[] telling) {
        System.out.println("Tellingen printen...");

            for (int i = 0; i < aantalMogelijkeCijfers; i++) {
                System.out.print(((double) i + gecorrigeerdeOndergrens) / correctie);
                System.out.print(";");
                System.out.println(telling[i]);
            }
        System.out.println("Dat waren de tellingen.");
    }

    public void grootsteVerschil(final int[] telling) {
        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;
        for (int n : telling) {
            if (n > maximum) {
                maximum = n;
            }
            if (n < minimum) {
                minimum = n;
            }
        }
        System.out.println("Klaar met grootste verschil uitrekeken");
        System.out.println("Verschil is: " + (maximum - minimum));
    }

    private Student[] randomStudentArrayGenerator(final int amount) {
        final Student[] student = new Student[amount];
        final StudentFactory studentMaker = new StudentFactory();
        for (int i = 0; i < amount; i++) {
            student[i] = studentMaker.makeStudent((double) RandomExpended.randomNumberInsideBoundariesInteger(
                    10, 100) / 10);
        }
        return student;
    }

    public void effiëntieSorters() {
        final int repeatTests = Integer.parseInt(aantalHerhalingen.getText().toString());
        final int[] inputSizes = new int[1];
        inputSizes[0] = Integer.parseInt(aantalStudentenField.getText().toString());
        //final int inputSize = Integer.parseInt(aantalStudentenField.getText().toString());
        final long[] bucketTimeResult = new long[repeatTests];
        final long[] bubbleTimeResult = new long[repeatTests];
        long averageBucketTime;
        long averageBubbleTime;
        long start;
        long end;
        Student[] student;
        //test bubbleSort & bucketSort
        StudentHolder bucketSorter;
        final Bubblesorter bubbleSorter = new Bubblesorter();
        System.out.println("Repetion;Students;Bucket;Bubble");
        for (int i = 0; i < inputSizes.length; i++) {
            for (int j = 0; j < repeatTests; j++) {
                //make some students and assign to classes
                student = randomStudentArrayGenerator(inputSizes[i]);
                String[] klassenTabel = KlasGenerator.maakKlassen(inputSizes[i]);
                for (int k = 0; k < inputSizes[i]; k++) {
                    student[k].setGroup(klassenTabel[k]);
                }
                //bucketSort first because bubble is in-array.
                bucketSorter = new StudentHolder();
                start = System.nanoTime();
                bucketSorter.addAll(student);
                bucketSorter.getStudents();
                end = System.nanoTime();
                bucketTimeResult[j] = end - start;
                //bucketTime.setText(Long.toString(bucketTimeResult[i]/1000000));

                //now bubbleSort measurement
                start = System.nanoTime();
                bubbleSorter.bubbleSort(student);
                end = System.nanoTime();
                bubbleTimeResult[j] = end - start;
                //bubbleTime.setText(Long.toString(bubbleTimeResult[j]/1000000));
            }
            averageBucketTime = gemiddeldeTijd(bucketTimeResult);
            averageBubbleTime = gemiddeldeTijd(bubbleTimeResult);

            bucketTime.setText(Long.toString(averageBucketTime/1000000));
            bubbleTime.setText(Long.toString(averageBubbleTime/1000000));

            for (int j = 0; j < repeatTests; j++) {
                System.out.println(j + ";" + inputSizes[i] + ";" + bucketTimeResult[j]/1000000 + ";" + bubbleTimeResult[j]/1000000);
            }
            //print ? safe to futher array?
        }

    }

    public long gemiddeldeTijd(long[] tijd) {
        long sum = 0;
        long average;
        for(int i=0; i<tijd.length; i++) {
            sum =+ tijd[i];
        }

        average = sum /tijd.length;
        return average;
    }


}
