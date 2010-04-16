package tritop.android.androsens;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.hardware.*;

public class Androsens extends Activity {
	TextView oriHead,accHead,magHead,tv_orientationA,tv_orientationB,tv_orientationC,tv_accelA,tv_accelB,tv_accelC,tv_magneticA,tv_magneticB,tv_magneticC;
	ProgressBar pb_orientationA,pb_orientationB,pb_orientationC,pb_accelA,pb_accelB,pb_accelC,pb_magneticA,pb_magneticB,pb_magneticC;
	SensorManager m_sensormgr;
	List<Sensor> m_sensorlist;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        oriHead = (TextView) this.findViewById(R.id.TextView_oriHead);
        accHead = (TextView) this.findViewById(R.id.TextView_accHead);
        magHead = (TextView) this.findViewById(R.id.TextView_magHead);
        tv_orientationA = (TextView) this.findViewById(R.id.TextView_oriA); pb_orientationA = (ProgressBar) this.findViewById(R.id.ProgressBar_oriA);
        tv_orientationB = (TextView) this.findViewById(R.id.TextView_oriB); pb_orientationB = (ProgressBar) this.findViewById(R.id.ProgressBar_oriB);
        tv_orientationC = (TextView) this.findViewById(R.id.TextView_oriC); pb_orientationC = (ProgressBar) this.findViewById(R.id.ProgressBar_oriC);
        tv_accelA = (TextView) this.findViewById(R.id.TextView_accA);       pb_accelA = (ProgressBar) this.findViewById(R.id.ProgressBar_accA);
        tv_accelB = (TextView) this.findViewById(R.id.TextView_accB);       pb_accelB = (ProgressBar) this.findViewById(R.id.ProgressBar_accB);
        tv_accelC = (TextView) this.findViewById(R.id.TextView_accC);       pb_accelC = (ProgressBar) this.findViewById(R.id.ProgressBar_accC);
        tv_magneticA = (TextView) this.findViewById(R.id.TextView_magA);    pb_magneticA = (ProgressBar) this.findViewById(R.id.ProgressBar_magA);
        tv_magneticB = (TextView) this.findViewById(R.id.TextView_magB);    pb_magneticB = (ProgressBar) this.findViewById(R.id.ProgressBar_magB);
        tv_magneticC = (TextView) this.findViewById(R.id.TextView_magC);    pb_magneticC = (ProgressBar) this.findViewById(R.id.ProgressBar_magC);
        
        m_sensormgr  = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        m_sensorlist =  m_sensormgr.getSensorList(Sensor.TYPE_ALL);
        
    }
}