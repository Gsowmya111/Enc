package edison.deepakraoj.enc;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib");
    }

    DBhrlper db=null;
    public void pullout() {

        //db.open();

        File f = new File("/data/data/" + this.getPackageName() + "/databases/" + "testt" + ".db"/*"SecurityServer.db"*/);
        FileInputStream fin = null;
        FileOutputStream fout = null;
        Log.d("DATABASE", "path "+f);
        try {
            fin = new FileInputStream(f);
            fout = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + "tdd.db");
            int i = 0;
            while ((i = fin.read()) != -1) {
                fout.write(i);
            }

            fout.flush();
            Toast.makeText(this.getApplicationContext(), "dump created!!", Toast.LENGTH_LONG).show();
            // db.close();
        } catch (Exception e) {
            e.printStackTrace();

            //Log.d("DATABASE", "path "+f);
            Toast.makeText(this.getApplicationContext(), "exception occurs in creating dump!!", Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.t);
        TextView tv1 = (TextView) findViewById(R.id.t1);
        TextView t1 = (TextView) findViewById(R.id.t11);
        String str1=encrypt1("shree");
        String str2=encrypt1("deepak");
        String str3=encrypt1("divya");
        String str4=encrypt1("prabhanjan");
        String str5=encrypt1("ashok");
        String str6=encrypt1("bharath");
        String str7=encrypt1("sudha");
        String str8=encrypt1("prabhakaran");
        String str9=encrypt1("srikanth");
        String str10=encrypt1("venkatesh");
db=new DBhrlper(MainActivity.this);
        db.open();

        boolean isthere=db.checkdata();
        if(!isthere) {
            boolean b = db.insertOrUpdate_Logintype(str1, str1,str1,str1);
            b = db.insertOrUpdate_Logintype(str2, str1,str1,str1);
            b = db.insertOrUpdate_Logintype( str3, str1,str1,str1);
            b = db.insertOrUpdate_Logintype(str4 , str1,str1,str1);
            b = db.insertOrUpdate_Logintype( str5, str1,str1,str1);
            b = db.insertOrUpdate_Logintype( str6, str1,str1,str1);
            b = db.insertOrUpdate_Logintype(str7 , str1,str1,str1);
            b = db.insertOrUpdate_Logintype(str8 , str1,str1,str1);
            b = db.insertOrUpdate_Logintype(str9 , str1,str1,str1);
            b = db.insertOrUpdate_Logintype(str10 , str1,str1,str1);

        }
        else
        {
            pullout();
            List<String> ala=db.fetch();
            StringBuilder sb=new StringBuilder();
            tv.setText("encrypt data= "+ala.get(0)+" "+ala.get(1)+" "+ala.get(2)+ala.get(3)+ala.get(4)+ala.get(5)+ala.get(6)+ala.get(7));
            for (int i=0;i<ala.size();i++)
            {
            String dec=ala.get(i) ;

                dec=decrypt1(dec);
                sb.append(dec+" ");

            }

            t1.setText("decr data= "+sb.toString());
            //aladnam=db.fetchdname();
            // Log.d("DATABASE","db "+ala.get(0));
            // Log.d("DATABASE","db "+ala.get(1));
        }


        String st1=decrypt1(str1);
        tv1.setText("decrypt= "+st1);




    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String encrypt1(String a);
    public native String decrypt1(String a);

}
